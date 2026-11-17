import json
import unittest
from pathlib import Path

from scripts.generate_quanta_catalogs import (
    DATA_PATH,
    build_item_catalog,
    load_recipe_documents,
    markdown_table,
    normalize_tier_recipe_markers,
    render_machines,
    render_tier_recipe_index,
    replace_generated_region,
    validate_catalog,
    validate_recipe_documents,
)


class GenerateQuantaCatalogsTests(unittest.TestCase):
    def test_inventory_is_valid_and_has_expected_catalogs(self):
        catalog = json.loads(DATA_PATH.read_text(encoding="utf-8"))
        validate_catalog(catalog)
        self.assertEqual(len(catalog["machines"]), 44)
        self.assertEqual(len(catalog["tools"]), 16)
        self.assertNotIn("tier_recipe_sections", catalog)
        self.assertNotIn("function", catalog["machines"][0])

    def test_machine_summary_and_item_catalog_derive_from_markdown_recipes(self):
        catalog = json.loads(DATA_PATH.read_text(encoding="utf-8"))
        _, recipe_lines = load_recipe_documents(catalog)
        details = validate_recipe_documents(catalog, recipe_lines)
        for machine in catalog["machines"]:
            if machine["name"] in details:
                machine.update(details[machine["name"]])
        overview = render_machines(catalog)
        self.assertEqual(details["Energy Quantifier"]["buffer"], "1.000.000 FE")
        self.assertEqual(details["Energy Quantifier"]["function"], "FE → Q (100 FE = 1 Q)")
        self.assertIn("| Quanta Infuser | Infuses Q into a base item", overview)
        rows = build_item_catalog(catalog, recipe_lines)
        names = {row[0] for row in rows}
        self.assertIn("Blank Glyph", names)
        self.assertIn("Coal", names)

    def test_item_catalog_includes_machine_parts_and_recipe_outputs(self):
        catalog = json.loads(DATA_PATH.read_text(encoding="utf-8"))
        _, recipe_lines = load_recipe_documents(catalog)
        rows = build_item_catalog(catalog, recipe_lines)
        names = {row[0] for row in rows}
        self.assertIn("Combustor", names)
        self.assertIn("Burner Nozzle", names)
        self.assertIn("Blank Glyph", names)
        self.assertIn("Coal", names)

    def test_validation_rejects_missing_machine_recipe_heading(self):
        catalog = json.loads(DATA_PATH.read_text(encoding="utf-8"))
        _, recipe_lines = load_recipe_documents(catalog)
        recipe_lines["T0"] = [line for line in recipe_lines["T0"] if line != "### 1.1 Combustor"]
        with self.assertRaisesRegex(ValueError, "recipe headings do not match"):
            validate_recipe_documents(catalog, recipe_lines)

    def test_legacy_recipe_markers_are_narrowed_without_losing_recipe_text(self):
        source = (
            "<!-- AUTO-GENERATED: recipe-index-t0 START -->\n"
            "## Machines\n\n| 1 | Machine |\n\n"
            "### 1.1 Machine\n\n**Output:** 1 Machine\n\n"
            "## Progression\n\nPreserve this prose.\n"
            "<!-- AUTO-GENERATED: recipe-index-t0 END -->\n"
        )
        normalized = normalize_tier_recipe_markers(source, "recipe-index-t0", {"Machine"})
        self.assertIn("<!-- AUTO-GENERATED: recipe-index-t0 END -->\n### 1.1 Machine", normalized)
        self.assertIn("## Progression\n\nPreserve this prose.", normalized)
        self.assertEqual(
            normalize_tier_recipe_markers(normalized, "recipe-index-t0", {"Machine"}),
            normalized,
        )

    def test_recipe_index_is_generated_without_overwriting_recipe_markdown(self):
        catalog = json.loads(DATA_PATH.read_text(encoding="utf-8"))
        document = "## 🏭 1. MACHINES (8)\n\n### 1.1 Combustor\n\n**Output:** 1 Combustor"
        index = render_tier_recipe_index(catalog, "T0", document)
        self.assertIn("## 🏭 1. MACHINES (8)", index)
        self.assertIn("| 1 | Combustor |", index)
        self.assertNotIn("**Output:**", index)

    def test_markdown_table_escapes_cells(self):
        self.assertEqual(
            markdown_table(["Name"], [["Order | Chaos"]]),
            "| Name |\n| --- |\n| Order \\| Chaos |",
        )

    def test_generated_region_requires_exact_markers(self):
        content = "<!-- AUTO-GENERATED: sample START -->\nold\n<!-- AUTO-GENERATED: sample END -->"
        updated = replace_generated_region(content, "sample", "new")
        self.assertIn("\nnew\n", updated)
        with self.assertRaises(ValueError):
            replace_generated_region(content + content, "sample", "new")


if __name__ == "__main__":
    unittest.main()
