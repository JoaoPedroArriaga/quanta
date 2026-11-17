# QUANTA MOD - TIER 2 (ENTANGLED) — FINAL SCOPE

> **Document based on QUANTA_MASTERLIST v10.6**
> **Last updated:** 2026-11-16
> **Scope:** Complete Tier 2 — 7 machines + 4 cables + 5 components + 4 cores + 3 structural components + 5 materials + 2 items + 1 addon

## 🎯 OVERVIEW

Tier 2 (Entangled) is the **entanglement layer**:

- Uses **Q** (Quanta), like Tier 1.
- Introduces **liquids, gases, and Essences**.
- Has **7 machines** (Fluid Infuser, Phase Evaporator, Phase Condenser, Fluid Mixer, Gas Centrifuge, Essence Collector, Quanta Purifier).
- Has **4 cables** (T2, higher throughput).
- Has **5 electronic components** (Transistor, Inductor, Relay, Oscillator, Modulator).
- Has **4 functional cores** (Network, Computation, Entangled) — actually 3 new ones, 7 total including those from T1.
- Has **3 structural components** (Entangled Casing, Frame, Reinforced Casing).
- Has **5 materials** (Aeternum, Entangled Essence, Observer Lens, Chaos Fragment, Order Fragment).
- Has **2 items** (Quantum Codex, Blank Glyph).
- Has **1 addon** (Fluid Addon).

**Philosophy:** Tier 2 is **entanglement**. You can now manipulate liquids, gases, and Essences. The machines are more complex, and the materials are more powerful. All machines start at Tier 1 (Decoherent) and can be upgraded with an Upgrade Kit. The Fluid Addon adds a liquid catalyst slot (consumable, pump-fed).

---

## 🏭 1. MACHINES (7)

### 1.1 Fluid Infuser

**Function:** Infuses an item with fluid → new fluid.
**Buffer:** 50.000 Q.
**Consumes:** 20 Q/t.
**Time:** 60 ticks (base).
**GUI:** Status + Configuration (auto-output) + Filters (input/output) + Fluid tank.

**Craft (T1) (9 slots):**

[Entangled Casing] [Fluid Chamber] [Entangled Casing]
[Control Core] [Quanta Infuser] [Control Core]
[Entangled Frame] [Processing Core] [Entangled Frame]

**Output:** 1 Fluid Infuser (Decoherent)

**Upgrade:** Upgrade Kit (Entangled) → T2 (more recipes, faster).

**Recipes:**

| Input 1            | Input 2 (Fluid)        | Output                    | Time     |
| ------------------ | ---------------------- | ------------------------- | -------- |
| Iron Ingot         | Liquid Quanta (100 mB) | 1 Liquid Steel (500 mB)   | 60 ticks |
| Diamond            | Liquid Quanta (100 mB) | 1 Liquid Crystal (500 mB) | 60 ticks |
| (other materials)  | Liquid Quanta          | (other liquids)           | 60 ticks |

### 1.2 Phase Evaporator

**Function:** Liquid → Gas.
**Buffer:** 50.000 Q.
**Consumes:** 15 Q/t.
**Time:** 40 ticks (2s).
**GUI:** Status + Configuration (auto-output) + Filters (input/output) + Tank.

**Craft (T1) (9 slots):**

[Entangled Casing] [Evaporation Coil] [Entangled Casing]
[Control Core] [Fluid Core] [Control Core]
[Entangled Frame] [Energy Core] [Entangled Frame]

**Output:** 1 Phase Evaporator (Decoherent)

**Upgrade:** Upgrade Kit (Entangled) → T2 (faster).

**Recipes:**

| Input (Liquid) | Output (Gas) | Time     |
| --------------- | ------------ | -------- |
| Liquid Quanta   | Quanta Gas   | 40 ticks |
| Liquid Chaos    | Chaos Gas    | 40 ticks |
| Liquid Order    | Order Gas    | 40 ticks |

### 1.3 Phase Condenser

**Function:** Gas → Liquid.
**Buffer:** 50.000 Q.
**Consumes:** 15 Q/t.
**Time:** 40 ticks (2s).
**GUI:** Status + Configuration (auto-output) + Filters (input/output) + Tank.

**Craft (T1) (9 slots):**

[Entangled Casing] [Condensation Plate] [Entangled Casing]
[Control Core] [Fluid Core] [Control Core]
[Entangled Frame] [Processing Core] [Entangled Frame]

**Output:** 1 Phase Condenser (Decoherent)

**Upgrade:** Upgrade Kit (Entangled) → T2 (faster).

**Recipes:**

| Input (Gas) | Output (Liquid) | Time     |
| ----------- | ---------------- | -------- |
| Quanta Gas  | Liquid Quanta    | 40 ticks |
| Chaos Gas   | Liquid Chaos     | 40 ticks |
| Order Gas   | Liquid Order     | 40 ticks |

### 1.4 Fluid Mixer

**Function:** Mixes liquids.
**Buffer:** 50.000 Q.
**Consumes:** 30 Q/t.
**Time:** 60 ticks (3s).
**GUI:** Status + Configuration (auto-output) + Filters (input/output) + 2 tanks.

**Craft (T1) (9 slots):**

[Entangled Casing] [Fluid Whisker] [Entangled Casing]
[Control Core] [Piston] [Control Core]
[Entangled Frame] [Energy Core] [Entangled Frame]

**Output:** 1 Fluid Mixer (Decoherent)

**Upgrade:** Upgrade Kit (Entangled) → T2 (more recipes).

**Recipes:**

| Input 1                | Input 2              | Output                | Time     |
| ---------------------- | -------------------- | --------------------- | -------- |
| Liquid Quanta (500 mB) | Essence of Chaos (1) | Liquid Chaos (500 mB) | 60 ticks |
| Liquid Quanta (500 mB) | Essence of Order (1) | Liquid Order (500 mB) | 60 ticks |

### 1.5 Gas Centrifuge

**Function:** Separates entangled gases.
**Buffer:** 50.000 Q.
**Consumes:** 40 Q/t.
**Time:** 80 ticks (4s).
**GUI:** Status + Configuration (auto-output) + Filters (input/output) + Tank.

**Craft (T1) (9 slots):**

[Entangled Casing] [Centrifuge Drum] [Entangled Casing]
[Control Core] [Fluid Core] [Control Core]
[Entangled Frame] [Energy Core] [Entangled Frame]

**Output:** 1 Gas Centrifuge (Decoherent)

**Upgrade:** Upgrade Kit (Entangled) → T2 (faster).

**Recipes:**

| Input (Gas)              | Output 1           | Output 2           | Time     |
| ------------------------ | ------------------ | ------------------ | -------- |
| Entangled Gas (1.000 mB) | Order Gas (500 mB) | Chaos Gas (500 mB) | 80 ticks |

### 1.6 Essence Collector

**Function:** Passively generates Essences (Order/Chaos).
**Buffer:** 50.000 Q.
**Consumes:** 0 (generates 5 Q/t).
**Time:** 100 ticks (5s per Essence).
**GUI:** Status + Configuration (auto-output) + Filters (input/output) + Mode (Order/Chaos).

**Craft (T1) (9 slots):**

[Entangled Casing] [Essence Lens] [Entangled Casing]
[Control Core] [Adamas Resonans] [Control Core]
[Entangled Frame] [Energy Core] [Entangled Frame]

**Output:** 1 Essence Collector (Decoherent)

**Upgrade:** Upgrade Kit (Entangled) → T2 (faster).

**Recipes:**

| Mode  | Input (catalyst)               | Output           | Time      |
| ----- | ----------------------------- | ---------------- | --------- |
| Order | Adamas Resonans (not consumed) | Essence of Order | 100 ticks |
| Chaos | Adamas Resonans (not consumed) | Essence of Chaos | 100 ticks |

### 1.7 Quanta Purifier

**Function:** Washes ores (removes impurities).
**Buffer:** 50.000 Q.
**Consumes:** 20 Q/t.
**Time:** 40 ticks (2s).
**GUI:** Status + Configuration (auto-output) + Filters (input/output) + Tank.

**Craft (T1) (9 slots):**

[Entangled Casing] [Filter Mesh] [Entangled Casing]
[Control Core] [Quanta Infuser] [Control Core]
[Entangled Frame] [Processing Core] [Entangled Frame]

**Output:** 1 Quanta Purifier (Decoherent)

**Upgrade:** Upgrade Kit (Entangled) → T2 (more fluids).

**Recipes:**

| Input (Ore)     | Fluid                  | Output           | Bonus                  |
| --------------- | ---------------------- | ---------------- | ---------------------- |
| Iron Ore        | Water (100 mB)         | Iron Ore (clean) | +25% yield in Crusher  |
| Iron Ore        | Liquid Quanta (100 mB) | Iron Ore (clean) | +50% yield in Crusher  |
| Iron Ore        | Liquid Order (100 mB)  | Iron Ore (clean) | +25% yield + 0% failure |
| Iron Ore        | Liquid Chaos (100 mB)  | Iron Ore (clean) | +50% yield + 15% failure |

---

## 🔌 2. CABLES (4)

### 2.1 Quanta Cable T2

**Throughput:** 25.000 Q/t.
**How to obtain:** Apply Upgrade Kit (Entangled) to Quanta Cable T1.

### 2.2 Item Cable T2

**Throughput:** 64 items/t.
**How to obtain:** Apply Upgrade Kit (Entangled) to Item Cable T1.

### 2.3 Pipe T2

**Throughput:** 8.000 mB/t.
**How to obtain:** Apply Upgrade Kit (Entangled) to Pipe T1.

### 2.4 Pressurized Pipe T2

**Throughput:** 8.000 mB/t.
**How to obtain:** Apply Upgrade Kit (Entangled) to Pressurized Pipe T1.

---

## 📦 3. ELECTRONIC COMPONENTS (5)

| Component          | Craft                                                 | Output |
| ------------------ | ----------------------------------------------------- | ------ |
| Quantum Transistor | Quantum Diode + Order Fragment + Quantum Circuit      | 1      |
| Quantum Inductor   | Quantum Coil + Adamas Resonans + Aeternum             | 1      |
| Quantum Relay      | Quantum Transistor + Quantum Circuit + Rubrum Pulsans | 1      |
| Quantum Oscillator | Adamas Resonans + Quarzum Vibrans + Aurum Nobilis     | 1      |
| Quantum Modulator  | Quantum Diode + Quantum Transistor + Rubrum Pulsans   | 1      |

---

## ⚡ 4. FUNCTIONAL CORES (7 total, 4 T1 + 3 T2)

| Core                 | Tier | Craft                                                       | Function               |
| -------------------- | ---- | ----------------------------------------------------------- | ---------------------- |
| Processing Core      | T1   | 2 Quantum Coil + 2 Quantum Circuit                          | Processing          |
| Energy Core          | T1   | 2 Quantum Coil + 2 Quantum Capacitor                        | Energy                |
| Fluid Core           | T1   | 2 Pipe + 2 Vitrum Perspicuum                                | Fluid                 |
| Control Core         | T1   | 2 Quantum Circuit + 2 Quantum Processor                     | Control               |
| **Network Core**     | T2   | 2 Quantum Circuit + 2 Adamas Resonans + 1 Entangled Essence | Network                   |
| **Computation Core** | T2   | 2 Quantum Processor + 2 Quantum Transistor                  | Advanced processing |
| **Entangled Core**   | T2   | 2 Adamas Resonans + 2 Ender Pearl + 2 Essence of Chaos      | Direct entanglement  |

**Note:** cores are **crafting items** (used in machines). They do not connect anything.

---

## 🏗️ 5. STRUCTURAL COMPONENTS (3)

| Component         | Craft                            | Output |
| ----------------- | -------------------------------- | ------ |
| Entangled Casing  | 8 Aeternum Sheet                 | 1      |
| Entangled Frame   | 4 Aeternum Rod + 1 Aeternum Gear | 1      |
| Reinforced Casing | 8 Superpositum Sheet             | 1      |

---

## 🔩 6. MATERIALS (5)

| Material          | Name                 | Craft                                                             | Output |
| ----------------- | -------------------- | ----------------------------------------------------------------- | ------ |
| Aeternum          | (unchanged)           | Quanta Infuser (Netherite + Q)                                    | 1      |
| Entangled Essence | (formerly Entangled Core) | 2 Adamas Resonans + 2 Ender Pearl + 2 Essence of Chaos (Crafting) | 1 |
| Observer Lens     | (unchanged)           | Adamas Resonans + Essence of Order + Glass (Crafting)             | 1      |
| Chaos Fragment    | (unchanged)           | 4 Essence of Chaos + 1 Adamas Resonans (Crafting)                 | 1      |
| Order Fragment    | (unchanged)           | 4 Essence of Order + 1 Adamas Resonans (Crafting)                 | 1      |

---

## 📖 7. ITEMS (2)

### 7.1 Quantum Codex

**Craft (3 slots):**

[Lignum Stabilis Planks] [Book] [Adamas Resonans]

**Output:** 1 Quantum Codex

### 7.2 Blank Glyph

**Craft (4 slots):**

[Pulvis Nexus] [Ink Sac] [Paper] [Adamas Resonans]

**Output:** 1 Blank Glyph

---

## 🔧 8. ADDON (1)

### 8.1 Fluid Addon

**Function:** Adds a **liquid catalyst slot** to a machine.
**How it works:**

- Place the Fluid Addon in the machine (addon slot).
- The machine gains a **fluid slot** (catalyst).
- The fluid is **pump-fed** (not inserted manually).
- The fluid is **consumed** (the bar decreases).
- If you **pump continuously**, the bar **does not decrease**.
- If you stop pumping, the bar drops until empty, and speed returns to normal.

**Craft (5 slots):**

[ ] [Vitrum Perspicuum] [ ]
[Quantum Circuit] [Fluid Core] [Quantum Circuit]
[ ] [Vitrum Perspicuum] [ ]

**Output:** 1 Fluid Addon

**Effects by fluid:**

| Fluid              | Effect                                |
| ------------------ | ------------------------------------- |
| Liquid Quanta      | +50% speed                            |
| Liquid Order       | +25% speed + 0% failure            |
| Liquid Chaos       | +75% speed + 15% failure           |
| Stabilized Liquid  | +50% speed + 0% failure            |
| Entangled Liquid   | +75% speed + output teleportation |
| Liquid Singularity | +100% speed + 10% destruction     |

---

## 📊 9. TIER 2 PROGRESSION

Complete Tier 1 (Q, Ferrum Conducens, cables, etc.)
↓
Entangled Essence (Adamas Resonans + Ender Pearl + Essence of Chaos)
↓
Aeternum (Netherite + Q)
↓
Fluid Infuser (liquids)
↓
Phase Evaporator / Condenser (gases)
↓
Essence Collector (Essences)
↓
Chaos/Order Fragments
↓
Advanced electronic components (Transistor, Inductor, Relay, etc.)
↓
Tier 2 machines (Fluid Infuser, Phase Evaporator, etc.)

---

## ⚠️ 10. BALANCING NOTES

- **Fluid Infuser:** 20 Q/t, infuses with liquids.
- **Phase Evaporator:** 15 Q/t, liquid → gas.
- **Phase Condenser:** 15 Q/t, gas → liquid.
- **Fluid Mixer:** 30 Q/t, mixes liquids.
- **Gas Centrifuge:** 40 Q/t, separates entangled gases.
- **Essence Collector:** 0 Q/t (generates 5 Q/t), generates Essences.
- **Quanta Purifier:** 20 Q/t, washes ores.
- **T2 cables:** Quanta (25,000 Q/t), Item (64/t), Pipe (8,000 mB/t), Pressurized (8,000 mB/t).
- **Fluid Addon:** liquid catalyst (consumable, pump-fed).

---

## 🔗 11. INTEGRATION

- **Tier 1 (Decoherent):** provides Q, Ferrum Conducens, cables, and basic components.
- **Tier 2 (Entangled):** introduces liquids/gases, Essences, Aeternum, Entangled Essence, Codex, Glyph, and Fluid Addon.
- **Tier 3 (Superposed):** introduces auto-crafting, duplication, massive generation, and Quanta Weaver.
- **Tier 4 (Singular):** introduces Singularity, implants, and endgame.

**This document covers 100% of Tier 2.**
