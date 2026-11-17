---
canvas:
  - "[[STRUCTURE.canvas]]"
parent: "[[MACHINES]]"
toc-order: 1
---
# QUANTA MOD - TIER 0 (INDUSTRIAL) — FINAL SCOPE

> **Document based on QUANTA_MASTERLIST v10.6**
> **Last updated:** 2026-11-16
> **Scope:** Complete Tier 0 — 8 machines + 2 cables + 21 components + 1 alloy + 4 tools

## 🎯 OVERVIEW

Tier 0 (Industrial) is the Quanta Mod's **entry point**:

- Uses **FE** (Forge Energy), not Q.
- Has **8 machines** (5 base + 3 extras).
- Has **2 cables** (Copper, Gold).
- Has **21 components** (Sheets, Wires, Gears, Rods, Casings, Frames, Cores).
- Has **1 alloy** (Steel).
- Has **4 tools** (Iron Hammer, Iron File, Steel Hammer, Steel File).
- Has a **complete GUI** (status + configuration + filters).
- Has a **buffer** (50,000-200,000 FE per machine).

**Philosophy:** Tier 0 is a "complete technical mod" that competes with Mekanism/Thermal, but is **less efficient** than Quanta (Tier 1+).

---

<!-- AUTO-GENERATED: recipe-index-t0 START -->
## 🏭 1. MACHINES (8)

| # | Machine recipe |
| --- | --- |
| 1 | Combustor |
| 2 | Industrial Crusher |
| 3 | Industrial Furnace |
| 4 | Industrial Press |
| 5 | Industrial Wire Drawer |
| 6 | Industrial Saw |
| 7 | Industrial Harvester |
| 8 | Industrial Mixer |
<!-- AUTO-GENERATED: recipe-index-t0 END -->
### 1.1 Combustor

**Function:** Burns vanilla fuel → FE.
**Buffer:** 200.000 FE.
**Generates:** 100 FE/t.
**GUI:** Status + Configuration (auto-output) + Filters (fuel).

| Input                       | Output | Rate     | Duration |
| ---------------------------- | ------ | -------- | ------- |
| Coal                         | FE     | 100 FE/t | 8s      |
| Charcoal                     | FE     | 100 FE/t | 8s      |
| Blaze Rod                    | FE     | 100 FE/t | 12s     |
| Lava (bucket)                | FE     | 100 FE/t | 20s     |
| Any vanilla fuel              | FE     | 100 FE/t | varies  |

**Craft (9 slots):**

[Steel Casing] [Burner Nozzle] [Steel Casing]
[Piston] [Energy Core (Basic)] [Piston]
[Iron Frame] [Copper Ingot] [Iron Frame]

**Output:** 1 Combustor

### 1.2 Industrial Crusher

**Function:** Crushes ores → Dust (with Fortune I).
**Buffer:** 100.000 FE.
**Consumes:** 20 FE/t.
**Time:** 40 ticks (2s per craft).
**GUI:** Status + Configuration (auto-output) + Filters (input/output).

**Ores with Raw:**

| Input               | Output (with Fortune) | Output (without Fortune) | Chance     |
| ------------------- | -------------- | ------------------ | ---------- |
| Iron Ore            | 2 Raw Iron     | 1 Raw Iron         | 50%        |
| Raw Iron            | 2 Iron Dust    | —                  | 100%       |
| Block of Raw Iron   | 18 Iron Dust   | —                  | 100% (9×2) |
| Gold Ore            | 2 Raw Gold     | 1 Raw Gold         | 50%        |
| Raw Gold            | 2 Gold Dust    | —                  | 100%       |
| Block of Raw Gold   | 18 Gold Dust   | —                  | 100% (9×2) |
| Copper Ore          | 2 Raw Copper   | 1 Raw Copper       | 50%        |
| Raw Copper          | 2 Copper Dust  | —                  | 100%       |
| Block of Raw Copper | 18 Copper Dust | —                  | 100% (9×2) |

**Ores that produce Dust (Coal):**

| Input         | Output (with Fortune) | Output (without Fortune) | Chance     |
| ------------- | -------------- | ------------------ | ---------- |
| Coal Ore      | 2 Coal         | 1 Coal             | 50%        |
| Coal          | 2 Carbon Dust  | —                  | 100%       |
| Block of Coal | 18 Carbon Dust | —                  | 100% (9×2) |

**Materials without Raw or Dust:**

| Input                 | Output (with Fortune) | Output (without Fortune) | Chance              |
| --------------------- | ----------------- | ------------------ | ------------------- |
| Redstone Ore          | 6 Redstone Dust   | 4 Redstone Dust    | 50%                 |
| Block of Redstone     | 9 Redstone Dust   | —                  | 100% (no bonus)     |
| Diamond Ore           | 2 Diamond         | 1 Diamond          | 50%                 |
| Block of Diamond      | 9 Diamond         | —                  | 100% (no bonus)     |
| Emerald Ore           | 2 Emerald         | 1 Emerald          | 50%                 |
| Block of Emerald      | 9 Emerald         | —                  | 100% (no bonus)     |
| Lapis Ore             | 6 Lapis Lazuli    | 4 Lapis Lazuli     | 50%                 |
| Block of Lapis Lazuli | 9 Lapis Lazuli    | —                  | 100% (no bonus)     |
| Nether Quartz Ore     | 2 Quartz          | 1 Quartz           | 50%                 |
| Block of Quartz       | 4 Quartz          | —                  | 100% (crafting only) |
| Nether Gold Ore       | 4 Gold Nugget     | 2 Gold Nugget      | 50%                 |
| Ancient Debris        | 1 Netherite Scrap | 1 Netherite Scrap  | 100%                |
| Amethyst Cluster      | 3 Amethyst Shard  | 2 Amethyst Shard   | 50%                 |

**Other materials:**

| Input       | Output (with Fortune) | Output (without Fortune) | Chance              |
| ----------- | ---------------- | ------------------ | ------------------- |
| Ender Pearl | 2 Ender Dust     | —                  | 100%                |
| Blaze Rod   | 2 Blaze Powder   | —                  | 100%                |
| Bone        | 6 Bone Meal      | —                  | 100%                |
| Bone Block  | 9 Bone Meal      | —                  | 100% (crafting only) |
| Glowstone   | 4 Glowstone Dust | 2 Glowstone Dust   | 50%                 |

**Craft (9 slots):**

[Steel Casing] [Crusher Head] [Steel Casing]
[Piston] [Processing Core (Basic)] [Piston]
[Steel Frame] [Stonecutter] [Steel Frame]

**Output:** 1 Industrial Crusher

### 1.3 Industrial Furnace

**Function:** Smelting (Ore/Raw/Dust → Ingot) and cooking (food).
**Buffer:** 100.000 FE.
**Consumes:** 30 FE/t.
**Time:** 60 ticks (smelting) / 40 ticks (cooking).
**GUI:** Status + Configuration (auto-output) + Filters (input/output).

**Smelting (60 ticks):**

| Input       | Output         |
| ----------- | -------------- |
| Iron Ore    | 1 Iron Ingot   |
| Gold Ore    | 1 Gold Ingot   |
| Copper Ore  | 1 Copper Ingot |
| Raw Iron    | 1 Iron Ingot   |
| Raw Gold    | 1 Gold Ingot   |
| Raw Copper  | 1 Copper Ingot |
| Iron Dust   | 1 Iron Ingot   |
| Gold Dust   | 1 Gold Ingot   |
| Copper Dust | 1 Copper Ingot |
| Steel Dust  | 1 Steel Ingot  |

**Cooking (40 ticks):**

| Input                 | Output            |
| --------------------- | ----------------- |
| Any raw meat          | 1 cooked meat     |
| Any raw fish          | 1 cooked fish     |
| Potato                | 1 baked potato    |
| Any raw food          | 1 cooked food     |

**Craft (9 slots):**

[Steel Casing] [Heat Chamber] [Steel Casing]
[Blast Furnace] [Energy Core (Basic)] [Smoker]
[Iron Frame] [Copper Ingot] [Iron Frame]

**Output:** 1 Industrial Furnace

### 1.4 Industrial Press

**Function:** Ingot → Sheet.
**Buffer:** 50.000 FE.
**Consumes:** 15 FE/t.
**Time:** 20 ticks (1s per craft).
**GUI:** Status + Configuration (auto-output) + Filters (input/output).

| Input        | Output         | Time     |
| ------------ | -------------- | -------- |
| Iron Ingot   | 1 Iron Sheet   | 20 ticks |
| Gold Ingot   | 1 Gold Sheet   | 20 ticks |
| Copper Ingot | 1 Copper Sheet | 20 ticks |
| Steel Ingot  | 1 Steel Sheet  | 20 ticks |

**Craft (9 slots):**

[Steel Casing] [Press Plate] [Steel Casing]
[Copper Ingot] [Processing Core (Basic)] [Copper Ingot]
[Steel Frame] [Anvil] [Steel Frame]

**Output:** 1 Industrial Press

### 1.5 Industrial Wire Drawer

**Function:** Sheet → Wire (2×).
**Buffer:** 50.000 FE.
**Consumes:** 15 FE/t.
**Time:** 20 ticks (1s per craft).
**GUI:** Status + Configuration (auto-output) + Filters (input/output).

| Input        | Output        | Time     |
| ------------ | ------------- | -------- |
| Iron Sheet   | 2 Iron Wire   | 20 ticks |
| Gold Sheet   | 2 Gold Wire   | 20 ticks |
| Copper Sheet | 2 Copper Wire | 20 ticks |

**Craft (9 slots):**

[Iron Casing] [Drawer Die] [Iron Casing]
[Tripwire Hook] [Processing Core (Basic)] [Tripwire Hook]
[Iron Frame] [Copper Ingot] [Iron Frame]

**Output:** 1 Industrial Wire Drawer

### 1.6 Industrial Saw

**Function:** Mass wood processing (logs → planks 8×, planks → sticks 4×).
**Buffer:** 50.000 FE.
**Consumes:** 15 FE/t.
**Time:** 20 ticks (1s per craft).
**GUI:** Status + Configuration (auto-output) + Filters (input/output).

**Craft (9 slots):**

[Iron Casing] [Saw Blade] [Iron Casing]
[Gold Gear] [Processing Core (Basic)] [Gold Gear]
[Iron Frame] [Copper Ingot] [Iron Frame]

**Output:** 1 Industrial Saw

### 1.7 Industrial Harvester

**Function:** Harvests + replants (3×3 radius).
**Buffer:** 50.000 FE.
**Consumes:** 10 FE/t.
**Time:** 60 ticks (3s per cycle).
**GUI:** Status + Configuration (auto-output) + Filters (input/output).

**Craft (9 slots):**

[Iron Casing] [Harvest Blade] [Iron Casing]
[Iron Gear] [Control Core (Basic)] [Iron Gear]
[Iron Frame] [Copper Ingot] [Iron Frame]

**Output:** 1 Industrial Harvester

**Operation:**

- Place seeds in the input slot (once).
- The Harvester automatically harvests and replants.
- Harvested seeds return to the input (self-sustaining).
- The harvest goes to the output.
- 3×3 radius (9 farmland blocks).
- Stops harvesting when the output fills up.

### 1.8 Industrial Mixer

**Function:** Mixes dusts → alloys (Steel).
**Buffer:** 100.000 FE.
**Consumes:** 20 FE/t.
**Time:** 60 ticks (3s per craft).
**GUI:** Status + Configuration (auto-output) + Filters (input/output).

| Input 1           | Input 2                    | Output       | Time     |
| ----------------- | -------------------------- | ------------ | -------- |
| Iron Dust         | Carbon Dust                | 2 Steel Dust | 60 ticks |
| (Compatibility)   | Any dust from another mod  | 2 Steel Dust | 60 ticks |

**Craft (9 slots):**

[Iron Casing] [Whisker] [Iron Casing]
[Copper Gear] [Fluid Core (Basic)] [Copper Gear]
[Iron Frame] [Copper Ingot] [Iron Frame]

**Output:** 1 Industrial Mixer

**Recipes:**

- Iron Dust + Carbon Dust → 2 Steel Dust (Mixer, 1:2).
- (Crafting Table: Iron Dust + Carbon Dust → 1 Steel Dust, manual, 1:1).
- **Compatibility:** accepts dusts from other mods (via tags).

---

## 🔌 2. CABLES (2)

### 2.1 Copper Cable

**Function:** Transports FE (basic).
**Throughput:** 1.000 FE/t.
**Craft (5 slots):**

[ ] [Copper Wire] [ ]
[Copper Wire] [Copper Ingot] [Copper Wire]
[ ] [Copper Wire] [ ]

**Output:** 4 Copper Cable

### 2.2 Gold Cable

**Function:** Transports FE (improved).
**Throughput:** 5.000 FE/t.
**Craft (5 slots):**

[ ] [Gold Wire] [ ]
[Gold Wire] [Gold Ingot] [Gold Wire]
[ ] [Gold Wire] [ ]

**Output:** 4 Gold Cable

---

## 📦 3. COMPONENTS (21)

### 3.1 Sheets (4)

| Sheet        | Craft                | Output |
| ------------ | -------------------- | ------ |
| Iron Sheet   | Press (Iron Ingot)   | 1      |
| Gold Sheet   | Press (Gold Ingot)   | 1      |
| Copper Sheet | Press (Copper Ingot) | 1      |
| Steel Sheet  | Press (Steel Ingot)  | 1      |

### 3.2 Wires (3)

| Wire        | Craft                      | Output |
| ----------- | -------------------------- | ------ |
| Iron Wire   | Wire Drawer (Iron Sheet)   | 2      |
| Gold Wire   | Wire Drawer (Gold Sheet)   | 2      |
| Copper Wire | Wire Drawer (Copper Sheet) | 2      |

### 3.3 Gears (3)

| Gear        | Craft                     | Output |
| ----------- | ------------------------- | ------ |
| Iron Gear   | Crafting (4 Iron Sheet)   | 1      |
| Copper Gear | Crafting (4 Copper Sheet) | 1      |
| Steel Gear  | Crafting (4 Steel Sheet)  | 1      |

### 3.4 Rods (3)

| Rod        | Craft                     | Output |
| ---------- | ------------------------- | ------ |
| Iron Rod   | Crafting (2 Iron Ingot)   | 1      |
| Copper Rod | Crafting (2 Copper Ingot) | 1      |
| Steel Rod  | Crafting (2 Steel Ingot)  | 1      |

### 3.5 Casings (2)

| Casing       | Craft                    | Output |
| ------------ | ------------------------ | ------ |
| Iron Casing  | Crafting (8 Iron Sheet)  | 1      |
| Steel Casing | Crafting (8 Steel Sheet) | 1      |

### 3.6 Frames (2)

| Frame       | Craft                                 | Output |
| ----------- | ------------------------------------- | ------ |
| Iron Frame  | Crafting (4 Iron Rod + 1 Iron Gear)   | 1      |
| Steel Frame | Crafting (4 Steel Rod + 1 Steel Gear) | 1      |

### 3.7 Cores (4)

| Core                    | Craft                                   | Output |
| ----------------------- | --------------------------------------- | ------ |
| Processing Core (Basic) | Crafting (2 Iron Gear + 2 Iron Sheet)   | 1      |
| Energy Core (Basic)     | Crafting (2 Copper Wire + 2 Iron Sheet) | 1      |
| Fluid Core (Basic)      | Crafting (2 Copper Rod + 2 Glass)       | 1      |
| Control Core (Basic)    | Crafting (2 Gold Wire + 2 Gold Rod)     | 1      |

---

## 🔩 4. ALLOY (1)

### Steel Dust

**Crafting (Mixer):**

- Iron Dust + Carbon Dust → 2 Steel Dust (automatic, 1:2).
- (Crafting Table: Iron Dust + Carbon Dust → 1 Steel Dust, manual, 1:1).

**Crafting (Blast Furnace/Industrial Furnace):**

- Steel Dust → 1 Steel Ingot.

**Uses:**

- Steel Sheet (Press).
- Steel Casing (Crafting).
- Steel Rod.
- Steel Frame.
- Tools (Hammer, File).

---

## 🛠️ 5. TOOLS (4)

### 5.1 Iron Hammer

**Function:** Ingot → Sheet (manual, 1:1).
**Durability:** 100 uses.
**Craft (5 slots):**

[Iron Ingot] [Stick] [ ]
[ ] [Stick] [Iron Ingot]
[Stick] [ ] [ ]

**Output:** 1 Iron Hammer

### 5.2 Iron File

**Function:** Ingot → Dust (manual, 1:1).
**Durability:** 100 uses.
**Craft (3 slots):**

[ ] [ ] [ ]
[ ] [Stick] [ ]
[Iron Ingot] [ ] [ ]

**Output:** 1 Iron File

### 5.3 Steel Hammer

**Function:** Ingot → Sheet (manual, 1:1).
**Durability:** 500 uses.
**Craft (5 slots):**

[Steel Ingot] [Iron Rod] [ ]
[ ] [Iron Rod] [Steel Ingot]
[Iron Rod] [ ] [ ]

**Output:** 1 Steel Hammer

### 5.4 Steel File

**Function:** Ingot → Dust (manual, 1:1).
**Durability:** 500 uses.
**Craft (3 slots):**

[ ] [ ] [ ]
[ ] [Iron Rod] [ ]
[Steel Ingot] [ ] [ ]

**Output:** 1 Steel File

---
## 📊 6. TIER 0 → TIER 1 PROGRESSION

### 6.1 Step 1 — Basic Survival

Mine Iron Ore + Coal Ore
↓
Vanilla Furnace → Iron Ingot + Coal
↓
Craft an Iron File (Ingot → Dust) + Iron Hammer (Ingot → Sheet)

**Goal:** obtain Dust and Sheet without machines.

### 6.2 Step 2 — First Steel (manual)

Iron Dust + Carbon Dust (Coal) → Crafting Table → Steel Dust (1:1)
↓
Steel Dust → Blast Furnace vanilla → Steel Ingot
↓
Steel Ingot + Steel Rod → Steel Hammer (500 uses) + Steel File (500 uses)

**Goal:** obtain Steel and better tools.

### 6.3 Step 3 — First Machines

Steel Casing (8 Steel Sheet) + Iron Casing (8 Iron Sheet) + Iron Frame + Cores
↓
Combustor (FE) + Crusher (Dust) + Furnace (Ingot)

**Goal:** obtain FE and automated processing.

### 6.4 Step 4 — Tier 0 Automation

Press (Sheet) + Wire Drawer (Wire) + Mixer (Steel)
↓
Copper Cable + Gold Cable
↓
Complete automation (ore → dust → ingot → sheet → wire → component)

**Goal:** complete Tier 0 automation.

### 6.5 Step 5 — Bridge to Tier 1

Ender Pearl → Crusher → Ender Dust
↓
Ender Dust + Redstone → Crafting Table → 2 Pulvis Nexus (Tier 1!)
↓
Pulvis Nexus + Iron → Quanta Infuser → Ferrum Conducens
Pulvis Nexus + Diamond → Quanta Infuser → Adamas Resonans
↓
Energy Quantifier (FE → Q)
↓
Quanta machines (Quanta Infuser, Thermal Infuser, Quanta Densifier)

**Goal:** obtain Pulvis Nexus, Ferrum Conducens, Adamas Resonans, Q, and the first Quanta machines.

**Note:** the first **Pulvis Nexus** is Tier 1. The bridge is Ender Pearl (Overworld) → Ender Dust (Crusher) → Pulvis Nexus (Crafting).

---

## ⚠️ 7. BALANCING NOTES

- **Combustor:** 100 FE/t (technical mod standard).
- **Cables:** Copper (1,000 FE/t) and Gold (5,000 FE/t).
- **Tier 0:** "less efficient" than Quanta, but competitive.
- **Steel:** the only Tier 0 alloy (used in Casings and tools).
- **Buffer:** 50k-200k FE (machines do not stop due to lack of FE).
- **Tools:** Iron Hammer (Sheet) and Iron File (Dust) as a first step.

---

## 🔗 8. INTEGRATION

- **Tier 1 (Decoherent):** begins after Tier 0 (Energy Quantifier converts FE → Q).
- **Cables:** Copper/Gold Cable transport FE to Tier 0 machines.
- **Components:** used in Tier 0 machines and (some) Quanta machines.
- **Progression:** Tier 0 → Tier 1 → Tier 2 → Tier 3 → Tier 4.

**This document covers 100% of Tier 0.**
