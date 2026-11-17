---
canvas:
  - "[[STRUCTURE.canvas]]"
parent: "[[MACHINES]]"
toc-order: 4
---
# QUANTA MOD - TIER 3 (SUPERPOSED) — FINAL SCOPE

> **Document based on QUANTA_MASTERLIST v10.6**
> **Last updated:** 2026-11-16
> **Scope:** Complete Tier 3 — 10 machines + 4 cables + 2 components + 2 cores + 2 structural components + 3 materials

## 🎯 OVERVIEW

Tier 3 (Superposed) is the **superposition layer**:

- Uses **Q** (Quanta), like the previous tiers.
- Introduces **auto-crafting, duplication, massive generation, and Quanta Weaver**.
- Has **10 machines** (Matter Rewriter, Nexus Assembler, Pattern Encoder, Void Generator, Quanta Engine, Gas Burner, Particle Collider, Tunneling Teleporter, Observator Scanner, Quanta Weaver).
- Has **4 cables** (T3, upgraded).
- Has **2 electronic components** (Quantum Capacitor Superpositus, Quantum Transistor Superpositus).
- Has **2 cores** (Superposed Core, Void Core).
- Has **2 structural components** (Superpositum Casing, Superpositum Frame; Dechoerentium Casing, Dechoerentium Frame).
- Has **3 materials** (Superpositum, Dechoerentium, Essence of Void).

**Philosophy:** Tier 3 is **superposition**. You can now automate crafting, duplicate materials, generate massive amounts of energy, and create ores with the Quanta Weaver.

---

<!-- AUTO-GENERATED: recipe-index-t3 START -->
## 🏭 1. MACHINES (10)

| # | Machine recipe |
| --- | --- |
| 1 | Matter Rewriter |
| 2 | Nexus Assembler |
| 3 | Pattern Encoder |
| 4 | Void Generator |
| 5 | Quanta Engine |
| 6 | Gas Burner |
| 7 | Particle Collider |
| 8 | Tunneling Teleporter |
| 9 | Observator Scanner |
| 10 | Quanta Weaver |
<!-- AUTO-GENERATED: recipe-index-t3 END -->
### 1.1 Matter Rewriter

**Function:** Duplicates Dust (2×-8× per tier).
**Buffer:** 100.000 Q.
**Consumes:** 10 Q/t.
**Time:** 40 ticks (2s).
**GUI:** Status + Configuration (auto-output) + Filters (input/output).

**Craft (T1) (9 slots):**

[Superpositum Casing] [Rewriter Chamber] [Superpositum Casing]
[Control Core] [Quanta Infuser] [Control Core]
[Superpositum Frame] [Energy Core] [Superpositum Frame]

**Output:** 1 Matter Rewriter (Decoherent)

**Upgrade:** Upgrade Kit (Superposed) → T3 (duplicates 8×).

**Recipes:**

| Input       | Output (T1)   | Output (T2)   | Output (T3)   |
| ----------- | ------------- | ------------- | ------------- |
| Iron Dust   | 2 Iron Dust   | 4 Iron Dust   | 8 Iron Dust   |
| Gold Dust   | 2 Gold Dust   | 4 Gold Dust   | 8 Gold Dust   |
| Copper Dust | 2 Copper Dust | 4 Copper Dust | 8 Copper Dust |

### 1.2 Nexus Assembler

**Function:** Auto-crafting.
**Buffer:** 100.000 Q.
**Consumes:** 15 Q/t.
**Time:** 20 ticks (1s per craft).
**GUI:** Status + Configuration (auto-output) + Filters (input/output) + Patterns (9 slots).

**Craft (T1) (9 slots):**

[Superpositum Casing] [Assembly Arm] [Superpositum Casing]
[Control Core] [Quanta Infuser] [Control Core]
[Superpositum Frame] [Energy Core] [Superpositum Frame]

**Output:** 1 Nexus Assembler (Decoherent)

**Upgrade:** Upgrade Kit (Superposed) → T3 (more patterns, faster).

**Recipes:**

| Input            | Output        | Time     |
| ---------------- | ------------- | -------- |
| Pattern          | Crafted item  | 20 ticks |

### 1.3 Pattern Encoder

**Function:** Encodes Patterns (recipes).
**Buffer:** 100.000 Q.
**Consumes:** 10 Q/t.
**Time:** 40 ticks (2s).
**GUI:** Status + Configuration (auto-output) + Filters (input/output) + Patterns (9 slots).

**Craft (T1) (9 slots):**

[Superpositum Casing] [Pattern Lens] [Superpositum Casing]
[Control Core] [Quanta Infuser] [Control Core]
[Superpositum Frame] [Energy Core] [Superpositum Frame]

**Output:** 1 Pattern Encoder (Decoherent)

**Upgrade:** Upgrade Kit (Superposed) → T3 (more patterns).

**Recipes:**

| Input          | Output               | Time     |
| -------------- | -------------------- | -------- |
| Blank Pattern  | Encoded Pattern      | 40 ticks |

### 1.4 Void Generator

**Function:** Passive generation by location.
**Buffer:** 100.000 Q.
**Consumes:** 0 (generates 500-1,000 Q/t).
**Time:** —.
**GUI:** Status + Configuration (auto-output).

**Craft (T1) (9 slots):**

[Superpositum Casing] [Void Chamber] [Superpositum Casing]
[Control Core] [Essence of Void] [Control Core]
[Superpositum Frame] [Energy Core] [Superpositum Frame]

**Output:** 1 Void Generator (Decoherent)

**Upgrade:** Upgrade Kit (Superposed) → T3 (more generation).

**Generation by location:**

| Location             | Generation (Q/t) |
| -------------------- | ------------- |
| Overworld surface    | 500           |
| Bedrock (Overworld)  | 650           |
| Nether               | 600           |
| End (islands)          | 800           |
| End (void)           | 1.000         |

### 1.5 Quanta Engine

**Function:** Burns Liquid Quanta/Chaos.
**Buffer:** 100.000 Q.
**Consumes:** 0 (generates 50-250 Q/t).
**Time:** —.
**GUI:** Status + Configuration (auto-output) + Tank.

**Craft (T1) (9 slots):**

[Superpositum Casing] [Combustion Chamber] [Superpositum Casing]
[Control Core] [Quanta Infuser] [Control Core]
[Superpositum Frame] [Fluid Core] [Superpositum Frame]

**Output:** 1 Quanta Engine (Decoherent)

**Upgrade:** Upgrade Kit (Superposed) → T3 (more generation).

**Recipes:**

| Input (Liquid)  | Generation (Q/t) |
| --------------- | ------------- |
| Liquid Quanta   | 50            |
| Liquid Chaos    | 250           |

### 1.6 Gas Burner

**Function:** Burns Quanta Gas/Chaos Gas.
**Buffer:** 100.000 Q.
**Consumes:** 0 (generates 50-250 Q/t).
**Time:** —.
**GUI:** Status + Configuration (auto-output) + Tank.

**Craft (T1) (9 slots):**

[Superpositum Casing] [Gas Nozzle] [Superpositum Casing]
[Control Core] [Quanta Infuser] [Control Core]
[Superpositum Frame] [Fluid Core] [Superpositum Frame]

**Output:** 1 Gas Burner (Decoherent)

**Upgrade:** Upgrade Kit (Superposed) → T3 (more generation).

**Recipes:**

| Input (Gas) | Generation (Q/t) |
| ----------- | ------------- |
| Quanta Gas  | 50            |
| Chaos Gas   | 250           |

### 1.7 Particle Collider

**Function:** Collides materials (multiblock).
**Buffer:** 100.000 Q.
**Consumes:** Variable.
**Time:** Variable.
**GUI:** Status + Configuration (auto-output) + Filters (input/output).

**Craft (T1) (9 slots):**

[Superpositum Casing] [Collider Ring] [Superpositum Casing]
[Control Core] [Quanta Infuser] [Control Core]
[Superpositum Frame] [Energy Core] [Superpositum Frame]

**Output:** 1 Particle Collider (Decoherent)

**Upgrade:** Upgrade Kit (Superposed) → T3 (more collisions).

**Recipes:** (to be defined).

### 1.8 Tunneling Teleporter

**Function:** Teleports the player (Q).
**Buffer:** 100.000 Q.
**Consumes:** 50 Q/t.
**Time:** —.
**GUI:** Status + Configuration (auto-output) + Coordinates.

**Craft (T1) (9 slots):**

[Superpositum Casing] [Teleport Chamber] [Superpositum Casing]
[Control Core] [Echo Shard] [Control Core]
[Superpositum Frame] [Energy Core] [Superpositum Frame]

**Output:** 1 Tunneling Teleporter (Decoherent)

**Upgrade:** Upgrade Kit (Superposed) → T3 (greater range).

**Operation:**

- Enter coordinates in the GUI.
- Consumes 50 Q/t to teleport the player.

### 1.9 Observator Scanner

**Function:** Scans an area (ores, entities).
**Buffer:** 100.000 Q.
**Consumes:** 20 Q/t.
**Time:** —.
**GUI:** Status + Configuration (auto-output) + Radius (3×3 to 7×7).

**Craft (T1) (9 slots):**

[Superpositum Casing] [Scanner Lens] [Superpositum Casing]
[Control Core] [Observer Lens] [Control Core]
[Superpositum Frame] [Energy Core] [Superpositum Frame]

**Output:** 1 Observator Scanner (Decoherent)

**Upgrade:** Upgrade Kit (Superposed) → T3 (greater radius).

**Operation:**

- Scans an area (3×3 to 7×7).
- Displays ores and entities in the GUI.

### 1.10 Quanta Weaver

**Function:** Creates ores (with Collapse Lenses).
**Buffer:** 100.000 Q.
**Consumes:** 100 Q/t.
**Time:** 100 ticks (5s per ore).
**GUI:** Status + Configuration (auto-output) + Filters (input/output) + Collapse Lens.

**Craft (T1) (9 slots):**

[Superpositum Casing] [Weaving Loom] [Superpositum Casing]
[Control Core] [Quanta Infuser] [Control Core]
[Superpositum Frame] [Energy Core] [Superpositum Frame]

**Output:** 1 Quanta Weaver (Decoherent)

**Upgrade:** Upgrade Kit (Superposed) → T3 (more ores).

**Recipes:**

| Collapse Lens     | Ores                   | Time      |
| ----------------- | ---------------------- | --------- |
| Terrestrial Lens  | Iron, Copper, Coal     | 100 ticks |
| Subterranean Lens | Gold, Redstone, Lapis  | 100 ticks |
| Celestial Lens    | Diamond, Emerald       | 150 ticks |
| Void Lens         | Quartz, Ancient Debris | 200 ticks |

---

## 🔌 2. CABLES (4) — UPGRADED

| Cable               | Throughput T3   |
| ------------------- | --------------- |
| Quanta Cable T3     | **125.000 Q/t** |
| Item Cable T3       | **256 items/t** |
| Pipe T3             | **32.000 mB/t** |
| Pressurized Pipe T3 | **32.000 mB/t** |

**How to obtain:** Apply Upgrade Kit (Superposed) to T2 cables.

---

## 📦 3. ELECTRONIC COMPONENTS (2)

| Component                           | Craft                                              | Output |
| ----------------------------------- | -------------------------------------------------- | ------ |
| **Quantum Capacitor Superpositus**  | Quantum Capacitor + Superpositum + Adamas Resonans | 1      |
| **Quantum Transistor Superpositus** | Quantum Transistor + Superpositum + Order Fragment | 1      |

---

## ⚡ 4. CORES (2)

| Core                | Craft                                                      | Output |
| ------------------- | ---------------------------------------------------------- | ------ |
| **Superposed Core** | 2 Computation Core + 2 Quantum Transistor + 1 Superpositum | 1      |
| **Void Core**       | 2 Computation Core + 2 Adamas Resonans + 1 Essence of Void | 1      |

---

## 🏗️ 5. STRUCTURAL COMPONENTS (2)

| Component                | Craft                                      | Output |
| ------------------------ | ------------------------------------------ | ------ |
| **Superpositum Casing**  | 8 Superpositum Sheet                       | 1      |
| **Superpositum Frame**   | 4 Superpositum Rod + 1 Superpositum Gear   | 1      |
| **Dechoerentium Casing** | 8 Dechoerentium Sheet                      | 1      |
| **Dechoerentium Frame**  | 4 Dechoerentium Rod + 1 Dechoerentium Gear | 1      |

---

## 🔩 6. MATERIALS (3)

| Material            | Craft                                                | Output |
| ------------------- | ---------------------------------------------------- | ------ |
| **Superpositum**    | Aeternum + Chaotic Steel (Black Hole Forge)          | 1      |
| **Dechoerentium**   | Aeternum + Order Steel (Black Hole Forge)            | 1      |
| **Essence of Void** | Dragon's Breath + Essence of Chaos + Adamas Resonans | 1      |

---
## 📊 7. TIER 3 PROGRESSION

Complete Tier 2 (liquids, gases, Essences)
↓
Superpositum / Dechoerentium (Black Hole Forge)
↓
Essence of Void (Dragon's Breath + Essence of Chaos + Adamas Resonans)
↓
Tier 3 machines (Matter Rewriter, Nexus Assembler, Pattern Encoder, etc.)
↓
Auto-crafting, duplication, massive generation, Quanta Weaver

---

## ⚠️ 8. BALANCING NOTES

- **Matter Rewriter:** 10 Q/t, duplicates Dust (2×-8× per tier).
- **Nexus Assembler:** 15 Q/t, auto-crafting.
- **Pattern Encoder:** 10 Q/t, encodes Patterns.
- **Void Generator:** 0 Q/t (generates 500-1,000 Q/t).
- **Quanta Engine:** 0 Q/t (generates 50-250 Q/t).
- **Gas Burner:** 0 Q/t (generates 50-250 Q/t).
- **Particle Collider:** Variable.
- **Tunneling Teleporter:** 50 Q/t.
- **Observator Scanner:** 20 Q/t.
- **Quanta Weaver:** 100 Q/t.
- **T3 cables:** Quanta (125,000 Q/t), Item (256/t), Pipe (32,000 mB/t), Pressurized (32,000 mB/t).

---

## 🔗 9. INTEGRATION

- **Tier 2 (Entangled):** provides liquids/gases, Essences, Aeternum, Entangled Essence.
- **Tier 3 (Superposed):** introduces auto-crafting, duplication, massive generation, and Quanta Weaver.
- **Tier 4 (Singular):** introduces Singularity, implants, and endgame.

**This document covers 100% of Tier 3.**
