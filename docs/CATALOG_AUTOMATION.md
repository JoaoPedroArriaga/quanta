# Quanta catalog automation

The central source for catalog data and generated sections is
[`data/quanta_catalog.json`](../data/quanta_catalog.json).

Edit that file to add or change machine overview data, machine-part mappings,
tool summaries, alloy tables, addon summaries, or upgrade-kit summaries.
Write full recipes, crafting instructions, and tier-specific item sections
directly in the corresponding `RECIPES_TIER*.md` file as normal Markdown. The
JSON stores only each machine's tier, name, recipe heading, part, and material;
function, energy, buffer, and time summaries are read from its recipe section.
The item catalog is derived from registered machines, parts, tools, materials,
and recipe ingredients and outputs.

Only the tier machine index is generated inside recipe documents. Its
`AUTO-GENERATED` markers surround the index table alone; the remaining recipe
and catalog content is yours to edit.

Run a one-time update from the repository root:

```powershell
python scripts/generate_quanta_catalogs.py
```

To keep the generated sections updated as the inventory is edited, leave this
watcher running:

```powershell
python scripts/generate_quanta_catalogs.py --watch
```

Recommended workflow: edit `quanta_catalog.json` and the tier recipe Markdown
files in VS Code, and use Obsidian to browse and read the vault. Open the
repository folder as the VS Code workspace; after you trust the workspace, its
task starts the watcher automatically. The watcher processes saved changes, so
save the file (or enable Auto Save) to refresh the generated sections. Obsidian
will show the updates because both apps use the same vault files.

If the VS Code task is not running, start the watcher in the integrated
terminal from the repository root with the command above.

The watcher uses only Python's standard library. It monitors the JSON inventory
and all five tier recipe documents, then updates marked summaries, the machine
index, and the derived item catalog. Generation validates machine headings,
recipe fields, and outputs before writing; unchanged files are left untouched.

To add a machine, add a compact record under `machines` and write its numbered
recipe section in the matching tier document. The generator checks that machine
records, recipe headings, and recipe outputs agree. Existing recipe documents
are migrated automatically from the old wide generated regions the next time
the generator runs.
