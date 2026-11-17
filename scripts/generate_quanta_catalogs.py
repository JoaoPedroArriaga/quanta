import argparse
import json
import os
import re
import sys
import tempfile
import time
from pathlib import Path


ROOT = Path(__file__).resolve().parents[1]
DATA_PATH = ROOT / "data" / "quanta_catalog.json"


def markdown_cell(value):
    return str(value).replace("|", r"\|").replace("\r", " ").replace("\n", " ")


def markdown_table(headers, rows):
    lines = [
        "| " + " | ".join(headers) + " |",
        "| " + " | ".join("---" for _ in headers) + " |",
    ]
    lines.extend(
        "| " + " | ".join(markdown_cell(value) for value in row) + " |"
        for row in rows
    )
    return "\n".join(lines)


def render_machines(catalog):
    tier_labels = {
        "T1": "TIER 1 — DECOHERENT",
        "T2": "TIER 2 — ENTANGLED",
        "T3": "TIER 3 — SUPERPOSED",
        "T4": "TIER 4 — SINGULAR",
        "Multi-tier": "MULTI-TIER",
    }
    blocks = []
    overview = [machine for machine in catalog["machines"] if machine["tier"] != "T0"]
    for tier, label in tier_labels.items():
        machines = [machine for machine in overview if machine["tier"] == tier]
        blocks.extend(
            [
                f"## 🏭 {label} ({len(machines)} machines)",
                "",
                markdown_table(
                    ["Machine", "Function", "Energy/t", "Buffer", "Time"]
                    if tier != "Multi-tier"
                    else ["Machine", "Function", "Q/t"],
                    [
                        [
                            machine["name"],
                            machine["function"],
                            machine["q_per_tick"],
                            machine["buffer"],
                            machine.get("time", "—"),
                        ]
                        if tier != "Multi-tier"
                        else [machine["name"], machine["function"], machine["q_per_tick"]]
                        for machine in machines
                    ],
                ),
                "",
                f"**Total:** {len(machines)} machines.",
                "",
                "---",
                "",
            ]
        )
    counts = [
        (tier, label, sum(machine["tier"] == tier for machine in overview))
        for tier, label in [
            ("T1", "Tier 1 (Decoherent)"),
            ("T2", "Tier 2 (Entangled)"),
            ("T3", "Tier 3 (Superposed)"),
            ("T4", "Tier 4 (Singular)"),
            ("Multi-tier", "Multi-tier"),
        ]
    ]
    rows = [
        [label, ", ".join(machine["name"] for machine in overview if machine["tier"] == tier), count]
        for tier, label, count in counts
    ]
    rows.append(["**Total**", "", len(overview)])
    blocks.extend(["## 📊 GENERAL SUMMARY", "", markdown_table(["Tier", "Machines", "Total"], rows)])
    return "\n".join(blocks)


def render_machine_parts(catalog):
    machines = [machine for machine in catalog["machines"] if machine.get("part")]
    return "\n".join(
        [
            markdown_table(
                ["Tier", "Machine", "Part", "Material"],
                [[machine["tier"], machine["name"], machine["part"], machine["material"]]
                 for machine in machines],
            ),
            "",
            f"**Total:** {len(machines)} Machine Parts.",
        ]
    )


def render_tools(catalog):
    blocks = []
    for category in ["Crafting Tools", "Configuration Tools", "Tool Components"]:
        entries = [tool for tool in catalog["tools"] if tool["category"] == category]
        blocks.extend(
            [
                f"### {category}",
                "",
                markdown_table(
                    ["Tool", "Function", "Durability", "Tier"]
                    if category != "Tool Components"
                    else ["Component", "Function", "Tier"],
                    [
                        [tool["name"], tool["function"], tool["durability"], tool["tier"]]
                        if category != "Tool Components"
                        else [tool["name"], tool["function"], tool["tier"]]
                        for tool in entries
                    ],
                ),
                "",
            ]
        )
    return "\n".join(blocks).rstrip()


def render_addons(catalog):
    return markdown_table(
        ["Addon", "Effect", "Tier", "Craft"],
        [[item["name"], item["effect"], item["tier"], item["craft"]] for item in catalog["addons"]],
    )


def render_upgrade_kit_types(catalog):
    kits = catalog["upgrade_kits"]
    return markdown_table(
        ["Kit", "Tier", "How to obtain"],
        [[kit["name"], kit["tier"], kit["obtained"]] for kit in kits],
    )


def render_upgrade_kit_summary(catalog):
    return markdown_table(
        ["Kit", "Tier", "Craft"],
        [[kit["name"], kit["tier"], kit["craft"]] for kit in catalog["upgrade_kits"]],
    )


def render_alloy_table(catalog):
    return markdown_table(
        ["Alloy", "Composition", "Tier", "Conduct.", "Res.", "Reson.", "Stab."],
        [
            [alloy["name"], alloy["composition"], alloy["tier"], alloy["conductivity"],
             alloy["resistance"], alloy["resonance"], alloy["stability"]]
            for alloy in catalog["alloys"]
        ],
    )


def render_alloy_recipes(catalog):
    return markdown_table(
        ["Alloy", "Where", "Input 1", "Input 2"],
        [[alloy["name"], alloy["where"], alloy["input1"], alloy["input2"]] for alloy in catalog["alloys"]],
    )


def render_tier_recipe_index(catalog, tier, document):
    heading = next(
        (
            line for line in document.splitlines()
            if re.match(r"^## .*1\. MACHINES\b", line)
        ),
        None,
    )
    if heading is None:
        raise ValueError(f"{tier} recipe document is missing its machine section heading")
    machines = [machine for machine in catalog["machines"] if machine["tier"] == tier]
    title = heading.split(" (", 1)[0]
    return "\n".join(
        [
            f"{title} ({len(machines)})",
            "",
            markdown_table(
                ["#", "Machine recipe"],
                [[index, machine["recipe_name"]] for index, machine in enumerate(machines, start=1)],
            ),
        ]
    )


def normalize_tier_recipe_markers(document, region, machine_names):
    start = f"<!-- AUTO-GENERATED: {region} START -->"
    end = f"<!-- AUTO-GENERATED: {region} END -->"
    if document.count(start) != 1 or document.count(end) != 1:
        raise ValueError(f"expected exactly one START and END marker for {region}")
    start_end = document.index(start) + len(start)
    end_start = document.index(end)
    if end_start < start_end:
        raise ValueError(f"invalid marker order for {region}")
    content = document[start_end:end_start]
    recipe_heading = re.compile(r"^###\s+1\.\d+\s+(.+?)\s*$", re.M)
    first_recipe = next(
        (
            match for match in recipe_heading.finditer(content)
            if match.group(1).strip() in machine_names
        ),
        None,
    )
    if first_recipe is None:
        return document

    newline = "\r\n" if "\r\n" in document else "\n"
    index = content[:first_recipe.start()].strip("\r\n")
    recipe_content = content[first_recipe.start():].strip("\r\n")
    tail = document[document.index(end) + len(end):]
    return (
        document[:start_end]
        + newline
        + index
        + newline
        + end
        + newline
        + recipe_content
        + tail
    )


def tier_catalog_lines(document, tier):
    lines = document.splitlines()
    start = next(
        (
            index for index, line in enumerate(lines)
            if re.match(r"^## .*1\. MACHINES\b", line)
        ),
        None,
    )
    if start is None:
        raise ValueError(f"{tier} recipe document is missing its machine section heading")
    end = next(
        (
            index for index in range(start + 1, len(lines))
            if lines[index].startswith("## ") and "PROGRESSION" in lines[index].upper()
        ),
        len(lines),
    )
    return lines[start:end]


def load_recipe_documents(catalog):
    documents = {}
    recipe_lines = {}
    for tier in ["T0", "T1", "T2", "T3", "T4"]:
        region = f"recipe-index-{tier.lower()}"
        path = ROOT / "docs" / f"RECIPES_TIER{tier[1:]}.md"
        document = read_text_exact(path)
        machine_names = {
            machine["recipe_name"]
            for machine in catalog["machines"]
            if machine["tier"] == tier
        }
        document = normalize_tier_recipe_markers(document, region, machine_names)
        documents[tier] = document
        recipe_lines[tier] = tier_catalog_lines(document, tier)
    return documents, recipe_lines


def extract_machine_details(catalog, recipe_lines_by_tier):
    details = {}
    field_patterns = {
        "function": re.compile(r"^\*\*Function:\*\*\s*(.+)$"),
        "buffer": re.compile(r"^\*\*Buffer:\*\*\s*(.+)$"),
        "time": re.compile(r"^\*\*Time:\*\*\s*(.+)$"),
        "q_per_tick": re.compile(r"^\*\*(?:Consumes|Generates):\*\*\s*(.+)$"),
    }
    for tier, lines in recipe_lines_by_tier.items():
        for machine in catalog["machines"]:
            if machine["tier"] != tier:
                continue
            heading = re.compile(rf"^###\s+1\.\d+\s+{re.escape(machine['recipe_name'])}$")
            starts = [index for index, line in enumerate(lines) if heading.match(line)]
            if len(starts) != 1:
                raise ValueError(f"{tier} must contain exactly one recipe heading for {machine['recipe_name']}")
            start = starts[0]
            end = next(
                (index for index in range(start + 1, len(lines)) if lines[index].startswith("### ")),
                len(lines),
            )
            recipe = lines[start + 1:end]
            values = {}
            for field, pattern in field_patterns.items():
                match = next(
                    (match for line in recipe if (match := pattern.match(line))),
                    None,
                )
                if match:
                    values[field] = match.group(1).strip().rstrip(".")
            missing = {"function", "buffer", "q_per_tick"} - values.keys()
            if missing:
                raise ValueError(
                    f"{tier} {machine['recipe_name']} is missing recipe fields: {', '.join(sorted(missing))}"
                )
            if not any(line.startswith("**Output:**") for line in recipe):
                raise ValueError(f"{tier} {machine['recipe_name']} is missing its recipe output")
            details[machine["name"]] = values
    return details


def validate_recipe_documents(catalog, recipe_lines_by_tier):
    for tier, lines in recipe_lines_by_tier.items():
        expected = {
            machine["recipe_name"]
            for machine in catalog["machines"]
            if machine["tier"] == tier
        }
        headings = [
            match.group(1).strip()
            for line in lines
            if (match := re.match(r"^###\s+1\.\d+\s+(.+)$", line))
        ]
        if len(headings) != len(set(headings)) or set(headings) != expected:
            raise ValueError(f"{tier} machine recipe headings do not match machine records")
    return extract_machine_details(catalog, recipe_lines_by_tier)


def build_item_catalog(catalog, recipe_lines_by_tier):
    items = {}

    def add_item(name, category, tier, source):
        name = name.strip()
        if not name:
            return
        key = name.casefold()
        item = items.setdefault(
            key,
            {"name": name, "categories": set(), "tiers": set(), "sources": set()},
        )
        item["categories"].add(category)
        if tier:
            item["tiers"].add(tier)
        item["sources"].add(source)

    for machine in catalog["machines"]:
        add_item(machine["name"], "Machine", machine["tier"], "[[MACHINES]]")
        if machine["part"]:
            add_item(machine["part"], "Machine part", machine["tier"], "[[MACHINE_PARTS]]")
    for tool in catalog["tools"]:
        category = "Tool component" if tool["category"] == "Tool Components" else "Tool"
        add_item(tool["name"], category, tool["tier"], "[[TOOLS]]")
    for alloy in catalog["alloys"]:
        add_item(alloy["name"], "Alloy", alloy["tier"], "[[METALLURGY]]")
    for addon in catalog["addons"]:
        add_item(addon["name"], "Addon", addon["tier"], "[[ADDONS]]")
    for kit in catalog["upgrade_kits"]:
        add_item(kit["name"], "Upgrade kit", kit["tier"], "[[UPGRADE_KITS]]")

    output_pattern = re.compile(r"^\*\*Output:\*\*\s*(?:(?:[\d,]+)\s+)?(.+?)\s*$")
    for tier, lines in recipe_lines_by_tier.items():
        category = "Item"
        table_columns = None
        source = f"[[RECIPES_TIER{tier[1:]}]]"
        for line in lines:
            if line.startswith("## "):
                title = line.upper()
                if "MACHINE" in title:
                    category = "Machine"
                elif "CABLE" in title:
                    category = "Cable"
                elif "COMPONENT" in title:
                    category = "Component"
                elif "CORE" in title:
                    category = "Core"
                elif "ALLOY" in title or "METALL" in title:
                    category = "Alloy"
                elif "TOOL" in title:
                    category = "Tool"
                elif "ANCHOR" in title:
                    category = "Anchor"
                elif "ADDON" in title:
                    category = "Addon"
                elif "UPGRADE" in title:
                    category = "Upgrade kit"
                else:
                    category = "Item"
            if line.startswith("|"):
                cells = [cell.strip() for cell in line.strip().strip("|").split("|")]
                if table_columns is None:
                    table_columns = [
                        (index, header.casefold())
                        for index, header in enumerate(cells)
                        if any(
                            keyword in header.casefold()
                            for keyword in (
                                "input", "ingredient", "catalyst", "fuel", "fluid",
                                "gas", "material", "resource", "output", "result",
                            )
                        )
                    ]
                    continue
                if all(re.fullmatch(r":?-{3,}:?", cell) for cell in cells):
                    continue
                for index, header in table_columns:
                    if index >= len(cells):
                        continue
                    value = re.sub(r"^[\d,]+(?:\.\d+)?\s+", "", cells[index])
                    value = value.strip().strip("`")
                    if value and value not in {"—", "-", "N/A"}:
                        cell_category = "Recipe output" if "output" in header or "result" in header else "Ingredient"
                        add_item(value, cell_category, tier, source)
            else:
                table_columns = None
                for value in re.findall(r"\[([^\[\]]+)\]", line):
                    add_item(value, "Ingredient", tier, source)
            match = output_pattern.match(line)
            if match:
                name = re.sub(r"^[\d,]+\s+", "", match.group(1)).strip()
                add_item(name, category, tier, source)

    return sorted(
        [
            [
                item["name"],
                ", ".join(sorted(item["categories"])),
                ", ".join(sorted(item["tiers"])),
                ", ".join(sorted(item["sources"])),
            ]
            for item in items.values()
        ],
        key=lambda row: (row[1].casefold(), row[0].casefold()),
    )


def render_item_catalog(catalog, recipe_lines_by_tier):
    rows = build_item_catalog(catalog, recipe_lines_by_tier)
    return "\n".join(
        [
            f"**Total:** {len(rows)} indexed entries.",
            "",
            markdown_table(["Item / material", "Category", "Tier", "Sources"], rows),
        ]
    )


def validate_catalog(catalog):
    valid_tiers = {"T0", "T1", "T2", "T3", "T4", "T1+", "T2+", "T3+", "T4+", "Multi-tier"}
    required = {
        "machines": ["tier", "name", "part", "material", "recipe_name"],
        "tools": ["category", "name", "function", "tier"],
        "alloys": ["name", "composition", "tier", "conductivity", "resistance", "resonance", "stability", "where", "input1", "input2"],
        "addons": ["name", "effect", "tier", "craft"],
        "upgrade_kits": ["name", "tier", "obtained", "craft"],
    }
    for collection, fields in required.items():
        entries = catalog.get(collection)
        if not isinstance(entries, list):
            raise ValueError(f"{collection} must be a list")
        for index, entry in enumerate(entries):
            if not isinstance(entry, dict):
                raise ValueError(f"{collection}[{index}] must be an object")
            missing = [field for field in fields if field not in entry]
            if missing:
                raise ValueError(f"{collection}[{index}] is missing fields: {', '.join(missing)}")
            if not isinstance(entry["name"], str) or not entry["name"].strip():
                raise ValueError(f"{collection}[{index}] name must be a non-empty string")
            if entry.get("tier") not in valid_tiers:
                raise ValueError(f"{collection}[{index}] has invalid tier {entry.get('tier')!r}")
        names = [entry["name"].casefold() for entry in entries]
        if len(names) != len(set(names)):
            raise ValueError(f"{collection} contains duplicate names")

    for index, machine in enumerate(catalog["machines"]):
        if machine["tier"] != "Multi-tier":
            if not machine["recipe_name"]:
                raise ValueError(f"machines[{index}] requires recipe_name")


def replace_generated_region(text, region, content):
    start = f"<!-- AUTO-GENERATED: {region} START -->"
    end = f"<!-- AUTO-GENERATED: {region} END -->"
    if text.count(start) != 1 or text.count(end) != 1:
        raise ValueError(f"expected exactly one START and END marker for {region}")
    pattern = re.compile(re.escape(start) + r".*?" + re.escape(end), re.S)
    newline = "\r\n" if "\r\n" in text else "\n"
    replacement = start + newline + content.replace("\n", newline) + newline + end
    return pattern.sub(lambda _: replacement, text, count=1)


def read_text_exact(path):
    with path.open("r", encoding="utf-8", newline="") as source:
        return source.read()


def generate():
    catalog = json.loads(DATA_PATH.read_text(encoding="utf-8"))
    validate_catalog(catalog)
    recipe_documents, recipe_lines = load_recipe_documents(catalog)
    machine_details = validate_recipe_documents(catalog, recipe_lines)
    for machine in catalog["machines"]:
        if machine["tier"] in recipe_lines:
            machine.update(machine_details[machine["name"]])
    generated = {
        "docs/MACHINES.md": {
            "machine-overview": render_machines(catalog),
        },
        "docs/MACHINE_PARTS.md": {
            "machine-parts-summary": render_machine_parts(catalog),
        },
        "docs/TOOLS.md": {
            "tool-summary": render_tools(catalog),
        },
        "docs/ADDONS.md": {
            "addon-summary": render_addons(catalog),
        },
        "docs/UPGRADE_KITS.md": {
            "upgrade-kit-types": render_upgrade_kit_types(catalog),
            "upgrade-kit-summary": render_upgrade_kit_summary(catalog),
        },
        "docs/METALLURGY.md": {
            "alloy-catalog": render_alloy_table(catalog),
            "alloy-recipes": render_alloy_recipes(catalog),
        },
        "docs/ITEM_CATALOG.md": {
            "item-catalog": render_item_catalog(catalog, recipe_lines),
        },
    }
    for tier in ["T0", "T1", "T2", "T3", "T4"]:
        path = f"docs/RECIPES_TIER{tier[1:]}.md"
        generated[path] = {
            f"recipe-index-{tier.lower()}": render_tier_recipe_index(
                catalog, tier, recipe_documents[tier]
            ),
        }
    updates = []
    recipe_path_tiers = {
        f"docs/RECIPES_TIER{tier[1:]}.md": tier for tier in recipe_documents
    }
    for path, regions in generated.items():
        destination = ROOT / path
        if not destination.exists():
            raise FileNotFoundError(f"generated document does not exist: {destination}")
        tier = recipe_path_tiers.get(path)
        original = read_text_exact(destination)
        text = recipe_documents[tier] if tier else original
        for region, content in regions.items():
            text = replace_generated_region(text, region, content)
        if text != original:
            updates.append((destination, text))

    for destination, text in updates:
        temporary_path = None
        try:
            with tempfile.NamedTemporaryFile(
                mode="w", encoding="utf-8", newline="", dir=destination.parent, delete=False
            ) as output:
                temporary_path = Path(output.name)
                output.write(text)
            os.replace(temporary_path, destination)
        finally:
            if temporary_path and temporary_path.exists():
                temporary_path.unlink()


def watch():
    generate()
    watched_paths = [
        DATA_PATH,
        *(ROOT / "docs" / f"RECIPES_TIER{tier}.md" for tier in range(5)),
    ]

    def signature():
        return tuple(path.stat().st_mtime_ns for path in watched_paths)

    last_modified = signature()
    print("Watching the JSON inventory and tier recipe documents for changes.", flush=True)
    while True:
        time.sleep(1)
        modified = signature()
        if modified == last_modified:
            continue
        try:
            generate()
            print("Generated catalog sections.", flush=True)
        except (OSError, ValueError, json.JSONDecodeError) as error:
            print(f"Catalog generation failed: {error}", file=sys.stderr, flush=True)
        last_modified = signature()


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Generate synchronized Quanta catalog sections.")
    parser.add_argument("--watch", action="store_true", help="regenerate catalog sections when the inventory changes")
    arguments = parser.parse_args()
    try:
        if arguments.watch:
            watch()
        else:
            generate()
    except (OSError, ValueError, json.JSONDecodeError) as error:
        print(f"Catalog generation failed: {error}", file=sys.stderr)
        raise SystemExit(1)
