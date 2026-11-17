---
canvas:
  - "[[STRUCTURE.canvas]]"
parent: "[[QUANTA_MASTERLIST]]"
toc-order: 10
---
# QUANTA MOD - TOOLS

> **Document based on QUANTA_MASTERLIST v10.6**
> **Last updated:** 2026-11-16
> **Scope:** Tools, tool components, and configuration tools in the Quanta Mod

## 🎯 OVERVIEW

The Quanta Mod has **3 tool categories**:

1. **Crafting Tools** (Hammer, File) — used to process materials manually.
2. **Configuration Tools** (Wrench, Tester, Scanner) — used to configure machines.
3. **Combat Tools** (Sword, Katana, etc.) — weapons (detailed in ARMORS.md).

**Philosophy:** crafting tools are the "first step" (before machines). Configuration tools are essential for managing your base.

---

## 🛠️ 1. CRAFTING TOOLS

### 1.1 Iron Hammer

**Function:** Ingot → Sheet (manual, 1:1).
**Durability:** 100 uses.
**Tier:** T0.
**Craft (5 slots):**

[Iron Ingot] [Iron Rod] [ ]
[ ] [Iron Rod] [Iron Ingot]
[Iron Rod] [ ] [ ]

**Output:** 1 Iron Hammer

### 1.2 Iron File

**Function:** Ingot → Dust (manual, 1:1).
**Durability:** 100 uses.
**Tier:** T0.
**Craft (3 slots):**

[ ] [ ] [ ]
[ ] [Iron Rod] [ ]
[Iron Ingot] [ ] [ ]

**Output:** 1 Iron File

### 1.3 Steel Hammer

**Function:** Ingot → Sheet (manual, 1:1).
**Durability:** 500 uses.
**Tier:** T0.
**Craft (5 slots):**

[Steel Ingot] [Steel Rod] [ ]
[ ] [Steel Rod] [Steel Ingot]
[Steel Rod] [ ] [ ]

**Output:** 1 Steel Hammer

### 1.4 Steel File

**Function:** Ingot → Dust (manual, 1:1).
**Durability:** 500 uses.
**Tier:** T0.
**Craft (3 slots):**

[ ] [ ] [ ]
[ ] [Steel Rod] [ ]
[Steel Ingot] [ ] [ ]

**Output:** 1 Steel File

---

## ⚙️ 2. CONFIGURATION TOOLS

### 2.1 Quantum Wrench

**Function:** Machine configuration (faces, modes, filters, entanglement).
**Durability:** Indestructible.
**Tier:** T1+.
**Craft (5 slots):**

[Ferrum] [ ] [Ferrum]
[ ] [Ferrum Sheet] [ ]
[ ] [Ferrum] [ ]

**Output:** 1 Quantum Wrench

### 2.2 Quantum Screwdriver

**Function:** Component configuration (screws, fine adjustments).
**Durability:** Indestructible.
**Tier:** T1+.
**Craft (5 slots):**

[ ] [Ferrum Rod] [ ]
[ ] [Ferrum Wire] [ ]
[ ] [Ferrum Rod] [ ]

**Output:** 1 Quantum Screwdriver

### 2.3 Quantum Multitool

**Function:** Combines Wrench + Screwdriver + Tester.
**Durability:** Indestructible.
**Tier:** T2+.
**Craft (9 slots):**

[Quantum Wrench] [Quantum Processor] [Quantum Wrench]
[Ferrum] [Adamas Resonans] [Ferrum]
[Ferrum] [Ferrum] [Ferrum]

**Output:** 1 Quantum Multitool

### 2.4 Quantum Tester

**Function:** Tests machines (status, energy, progress).
**Durability:** Indestructible.
**Tier:** T1+.
**Craft (9 slots):**

[Quantum Circuit] [Redstone] [Quantum Circuit]
[Vitrum Perspicuum] [Redstone] [Vitrum Perspicuum]
[Ferrum] [Redstone] [Ferrum]

**Output:** 1 Quantum Tester

### 2.5 Quantum Scanner

**Function:** Scans machines and items (technical details).
**Durability:** Indestructible.
**Tier:** T2+.
**Craft (9 slots):**

[Quantum Tester] [Quantum Processor] [Quantum Tester]
[Observer Lens] [Adamas Resonans] [Observer Lens]
[Ferrum] [Ferrum] [Ferrum]

**Output:** 1 Quantum Scanner

---

## 🗡️ 3. TOOL COMPONENTS

### 3.1 Tool Core (Decoherent)

**Function:** Tool core (T1).
**Craft (4 slots):**

[Quantum Processor] [Ferrum]
[Quantum Circuit] [Ferrum]

**Output:** 1 Tool Core (Decoherent)

### 3.2 Tool Core (Entangled)

**Function:** Tool core (T2).
**Craft (4 slots):**

[Tool Core (Decoherent)] [Tool Core (Decoherent)]
[Tool Core (Decoherent)] [Entangled Essence]

**Output:** 1 Tool Core (Entangled)

### 3.3 Tool Core (Superposed)

**Function:** Tool core (T3).
**Craft (4 slots):**

[Tool Core (Entangled)] [Tool Core (Entangled)]
[Tool Core (Entangled)] [Superpositum]

**Output:** 1 Tool Core (Superposed)

### 3.4 Tool Core (Singular)

**Function:** Tool core (T4).
**Craft (4 slots):**

[Tool Core (Superposed)] [Tool Core (Superposed)]
[Tool Core (Superposed)] [Singular Alloy]

**Output:** 1 Tool Core (Singular)

### 3.5 Tool Head (Pick, Axe, Shovel, Drill, Saw)

**Function:** Tool head (defines the type).
**Craft (3 slots, vertical):**

[Ferrum Sheet]
[Ferrum Sheet]
[Ferrum Sheet]

**Output:** 1 Tool Head (per type)

**Variants:**

- **Pick Head:** 3 Ferrum Sheets + 1 Ferrum Rod (crafted separately)
- **Axe Head:** 3 Ferrum Sheets + 1 Ferrum Rod
- **Shovel Head:** 1 Ferrum Sheet + 1 Ferrum Rod
- **Drill Head:** 3 Ferrum Sheets + 1 Adamas Resonans
- **Saw Head:** 3 Ferrum Sheets + 1 Ferrum Gear

### 3.6 Tool Blade (Sword, Katana, Claymore)

**Function:** Weapon blade (defines the type).
**Craft (3 slots, vertical):**

[Ferrum Sheet]
[Ferrum Sheet]
[Ferrum Rod]

**Output:** 1 Tool Blade (per type)

**Variants:**

- **Sword Blade:** 2 Ferrum Sheets + 1 Ferrum Rod
- **Katana Blade:** 3 Ferrum Sheets + 1 Ferrum Rod (thinner)
- **Claymore Blade:** 3 Ferrum Sheets + 1 Ferrum Rod (wider)

### 3.7 Tool Handle

**Function:** Tool handle (determines durability).
**Craft (3 slots, horizontal):**

[Ferrum] [Quantum Circuit] [Ferrum]

**Output:** 1 Tool Handle

---

## 📊 4. TOOL SUMMARY

<!-- AUTO-GENERATED: tool-summary START -->
### Crafting Tools

| Tool | Function | Durability | Tier |
| --- | --- | --- | --- |
| Iron Hammer | Ingot → Sheet | 100 | T0 |
| Iron File | Ingot → Dust | 100 | T0 |
| Steel Hammer | Ingot → Sheet | 500 | T0 |
| Steel File | Ingot → Dust | 500 | T0 |

### Configuration Tools

| Tool | Function | Durability | Tier |
| --- | --- | --- | --- |
| Quantum Wrench | Machine configuration | Indestructible | T1+ |
| Quantum Screwdriver | Component configuration | Indestructible | T1+ |
| Quantum Multitool | Combines Wrench + Screwdriver + Tester | Indestructible | T2+ |
| Quantum Tester | Tests machines | Indestructible | T1+ |
| Quantum Scanner | Scans machines | Indestructible | T2+ |

### Tool Components

| Component | Function | Tier |
| --- | --- | --- |
| Tool Core (Decoherent) | Tool core | T1 |
| Tool Core (Entangled) | Tool core | T2 |
| Tool Core (Superposed) | Tool core | T3 |
| Tool Core (Singular) | Tool core | T4 |
| Tool Head | Tool head | T1+ |
| Tool Blade | Weapon blade | T1+ |
| Tool Handle | Tool handle | T1+ |
<!-- AUTO-GENERATED: tool-summary END -->

---

## 🔧 5. CRAFTING COMPLETE TOOLS

### 5.1 Decoherent Pickaxe

**Craft (3 slots, vertical):**

[Pick Head]
[Tool Core (Decoherent)]
[Tool Handle]

**Output:** 1 Decoherent Pickaxe

### 5.2 Entangled Drill

**Craft (4 slots, vertical):**

[Drill Head]
[Tool Core (Entangled)]
[Motor Implant]
[Tool Handle]

**Output:** 1 Entangled Drill

### 5.3 Superposed Katana

**Craft (3 slots, vertical):**

[Katana Blade]
[Tool Core (Superposed)]
[Tool Handle]

**Output:** 1 Superposed Katana

### 5.4 Singular Cannon

**Craft (4 slots, vertical):**

[Barrel]
[Tool Core (Singular)]
[Energy Buffer]
[Tool Handle]

**Output:** 1 Singular Cannon

---

## ⚠️ 6. BALANCING NOTES

- **Crafting Tools:** the "first step" (before machines).
- **Configuration Tools:** essential for managing your base.
- **Tool Components:** each tool uses **1 Core + 1 Head/Blade + 1 Handle**.
- **Tiers:** each tool tier has better stats (durability, speed, damage).

---

## 🔗 7. INTEGRATION

- **ARMORS.md:** armor (Helmet, Chestplate, Leggings, Boots).
- **RECIPES_TIER*:** tool recipes (by tier).
- **MACHINE_PARTS.md:** machine components (not tool components).

**This document covers 100% of the Quanta Mod's tools.**
