# QUANTA MOD - TIER 1 (DECOHERENT) — FINAL SCOPE

> **Document based on QUANTA_MASTERLIST v10.6**
> **Last updated:** 2026-11-16
> **Scope:** Complete Tier 1 — 10 machines + 4 cables + 7 components + 4 cores + 1 item + 2 anchors + 11 materials + 1 derivative

## 🎯 OVERVIEW

Tier 1 (Decoherent) is the **first Quanta layer**:

- Uses **Q** (Quanta), not FE.
- Has **10 machines** (Quanta Infuser, Thermal Infuser, Quanta Densifier, Quanta Collapser, Energy Quantifier, Quanta Separator, Quanta Crusher, Quanta Press, Quanta Wire Drawer, Quanta Mixer).
- Has **4 cables** (Quanta, Item, Pipe, Pressurized).
- Has **7 components** (Coil, Circuit, Diode, Capacitor, Resistor, Processor, Transistor).
- Has **4 cores** (Processing, Energy, Fluid, Control).
- Has **1 item** (Quantum Wrench).
- Has **2 anchors** (Seam, Flow).
- Has **11 infused materials** + 1 derivative (Quantum Planks).
- **Ferrum Conducens, Cuprum Transmissor, Aurum Nobilis** have Gear, Sheet, and Rod forms.
- All machines can receive an Upgrade Kit (increases output and speed).

**Philosophy:** Tier 1 is the **first contact with Quanta**. All materials used in Quanta machines/items are **infused** (not vanilla).

---

## 🏭 1. MACHINES (10)

### 1.1 Quanta Infuser

**Function:** Infuses Q into a base item → Quanta material.
**Buffer:** 10.000 Q.
**Consumes:** 20 Q/t.
**Time:** 60 ticks (base) / 45 ticks (Dust) / 30 ticks (Pallet).
**GUI:** Status + Configuration (auto-output) + Filters (input/output) + **Catalyst Bar**.

**Craft (9 slots):**

[Ferrum Casing] [Fluid Core] [Ferrum Casing]
[Control Core] [Brew Stand] [Control Core]
[Ferrum Frame] [Processing Core] [Ferrum Frame]

**Output:** 1 Quanta Infuser

**Recipes:**

| Input 1      | Catalyst    | Output               | Time     |
| ------------ | ----------- | -------------------- | -------- |
| Iron Ingot   | —           | 1 Ferrum Conducens   | 60 ticks |
| Iron Ingot   | Dust        | 1 Ferrum Conducens   | 45 ticks |
| Iron Ingot   | Pallet      | 1 Ferrum Conducens   | 30 ticks |
| Diamond      | —           | 1 Adamas Resonans    | 60 ticks |
| Diamond      | Dust        | 1 Adamas Resonans    | 45 ticks |
| Diamond      | Pallet      | 1 Adamas Resonans    | 30 ticks |
| Ender Dust   | —           | 2 Pulvis Nexus       | 60 ticks |
| Ender Dust   | Dust        | 2 Pulvis Nexus       | 45 ticks |
| Ender Dust   | Pallet      | 2 Pulvis Nexus       | 30 ticks |
| Copper Ingot | —           | 1 Cuprum Transmissor | 60 ticks |
| Copper Ingot | Dust        | 1 Cuprum Transmissor | 45 ticks |
| Copper Ingot | Pallet      | 1 Cuprum Transmissor | 30 ticks |
| Wood (log)   | —           | 1 Lignum Stabilis    | 60 ticks |
| Wood (log)   | Dust        | 1 Lignum Stabilis    | 45 ticks |
| Wood (log)   | Pallet      | 1 Lignum Stabilis    | 30 ticks |
| Glass        | —           | 1 Vitrum Perspicuum  | 60 ticks |
| Glass        | Dust        | 1 Vitrum Perspicuum  | 45 ticks |
| Glass        | Pallet      | 1 Vitrum Perspicuum  | 30 ticks |
| Coal         | —           | 1 Carbonis Fervens   | 60 ticks |
| Coal         | Dust        | 1 Carbonis Fervens   | 45 ticks |
| Coal         | Pallet      | 1 Carbonis Fervens   | 30 ticks |
| Gold Ingot   | —           | 1 Aurum Nobilis      | 60 ticks |
| Gold Ingot   | Dust        | 1 Aurum Nobilis      | 45 ticks |
| Gold Ingot   | Pallet      | 1 Aurum Nobilis      | 30 ticks |
| Redstone     | —           | 1 Rubrum Pulsans     | 60 ticks |
| Redstone     | Dust        | 1 Rubrum Pulsans     | 45 ticks |
| Redstone     | Pallet      | 1 Rubrum Pulsans     | 30 ticks |
| Quartz       | —           | 1 Quarzum Vibrans    | 60 ticks |
| Quartz       | Dust        | 1 Quarzum Vibrans    | 45 ticks |
| Quartz       | Pallet      | 1 Quarzum Vibrans    | 30 ticks |

**Catalyst Bar:**

- **Dust:** +25% per unit (4 Dust = 100%).
- **Pallet:** +100% per unit (1 Pallet = 100%).
- **Consumption:** 1% per tick (100% = 100 ticks of acceleration).
- **When depleted:** the catalyst breaks, and speed returns to normal.

### 1.2 Thermal Infuser

**Function:** Efficient smelting (2× faster than Industrial Furnace).
**Buffer:** 10.000 Q.
**Consumes:** 10 Q/t.
**Time:** 30 ticks (smelting) / 20 ticks (cooking).
**GUI:** Status + Configuration (auto-output) + Filters (input/output).

**Craft (9 slots):**

[Ferrum Casing] [Heat Coil] [Ferrum Casing]
[Control Core] [Blast Furnace] [Control Core]
[Ferrum Frame] [Energy Core] [Ferrum Frame]

**Output:** 1 Thermal Infuser

**Recipes:**

| Input                 | Output            | Time     |
| --------------------- | ----------------- | -------- |
| Iron Dust             | 1 Iron Ingot      | 30 ticks |
| Gold Dust             | 1 Gold Ingot      | 30 ticks |
| Copper Dust           | 1 Copper Ingot    | 30 ticks |
| Steel Dust            | 1 Steel Ingot     | 30 ticks |
| Any raw food          | 1 cooked food     | 20 ticks |

**Upgrades:**

| Tier                 | Time (smelting)  | Output   |
| -------------------- | ---------------- | -------- |
| Decoherent (base)    | 30 ticks         | 1 ingot  |
| Entangled (upgrade)  | 20 ticks         | 2 ingots |
| Superposed (upgrade) | 10 ticks         | 3 ingots |
| Singular (upgrade)   | 5 ticks          | 4 ingots |

### 1.3 Quanta Densifier

**Function:** Dust → Pallet (1:1, like an Enrichment Chamber).
**Buffer:** 10.000 Q.
**Consumes:** 25 Q/t.
**Time:** 20 ticks (1s).
**GUI:** Status + Configuration (auto-output) + Filters (input/output).

**Craft (9 slots):**

[Ferrum Casing] [Compression Chamber] [Ferrum Casing]
[Control Core] [Adamas Resonans] [Control Core]
[Ferrum Frame] [Energy Core] [Ferrum Frame]

**Output:** 1 Quanta Densifier

**Recipes:**

| Input          | Output          | Time     |
| -------------- | --------------- | -------- |
| 1 Pulvis Nexus | 1 Pulvis Densus | 20 ticks |

### 1.4 Quanta Collapser

**Function:** Q → FE (1 Q = 100 FE).
**Buffer:** 10.000 Q.
**Consumes:** 0.
**GUI:** Status + Config (auto-output).

**Craft (9 slots):**

[Ferrum Casing] [Collapse Chamber] [Ferrum Casing]
[Control Core] [Rubrum Pulsans] [Control Core]
[Ferrum Frame] [Energy Core] [Ferrum Frame]

**Output:** 1 Quanta Collapser

### 1.5 Energy Quantifier

**Function:** FE → Q (100 FE = 1 Q).
**Buffer:** 1.000.000 FE.
**Consumes:** 0.
**GUI:** Status + Config (auto-output).

**Craft (9 slots):**

[Ferrum Casing] [Quantum Converter] [Ferrum Casing]
[Control Core] [Redstone] [Control Core]
[Ferrum Frame] [Energy Core] [Ferrum Frame]

**Output:** 1 Energy Quantifier

### 1.6 Quanta Separator

**Function:** Separates items by type (filter block).
**Buffer:** 10.000 Q.
**Consumes:** 10 Q/t.
**Time:** 20 ticks (1s per item).
**GUI:** Status + Configuration (auto-output) + Filters (4 slots, 4 outputs).

**Craft (9 slots):**

[Ferrum Casing] [Sieve] [Ferrum Casing]
[Control Core] [Hopper] [Control Core]
[Ferrum Frame] [Energy Core] [Ferrum Frame]

**Output:** 1 Quanta Separator

**Operation:**

- **Input:** item mixture.
- **Filters:** 4 slots (each holds a sample item).
- **Output:** 4 outputs (each receives the item matching its filter).
- **Items without a filter:** go to a "reject output".

### 1.7 Quanta Crusher

**Function:** Crushes ores → Dust (Fortune I).
**Buffer:** 10.000 Q.
**Consumes:** 20 Q/t.
**Time:** 40 ticks (2s per craft).
**GUI:** Status + Configuration (auto-output) + Filters (input/output).

**Craft (9 slots):**

[Ferrum Casing] [Crusher Head] [Ferrum Casing]
[Control Core] [Piston] [Control Core]
[Ferrum Frame] [Stonecutter] [Ferrum Frame]

**Output:** 1 Quanta Crusher

**Recipes:** (same as the Industrial Crusher, but with Fortune I).

### 1.8 Quanta Press

**Function:** Ingot → Sheet.
**Buffer:** 10.000 Q.
**Consumes:** 15 Q/t.
**Time:** 20 ticks (1s).
**GUI:** Status + Configuration (auto-output) + Filters (input/output).

**Craft (9 slots):**

[Ferrum Casing] [Press Plate] [Ferrum Casing]
[Control Core] [Piston] [Control Core]
[Ferrum Frame] [Anvil] [Ferrum Frame]

**Output:** 1 Quanta Press

**Recipes:**

| Input                    | Output         | Time     |
| ------------------------ | -------------- | -------- |
| Iron Ingot               | 1 Iron Sheet   | 20 ticks |
| Gold Ingot               | 1 Gold Sheet   | 20 ticks |
| Copper Ingot             | 1 Copper Sheet | 20 ticks |
| Steel Ingot              | 1 Steel Sheet  | 20 ticks |
| Ferrum Conducens Ingot   | 1 Ferrum Sheet | 20 ticks |
| Cuprum Transmissor Ingot | 1 Cuprum Sheet | 20 ticks |
| Aurum Nobilis Ingot      | 1 Aurum Sheet  | 20 ticks |

### 1.9 Quanta Wire Drawer

**Function:** Sheet → Wire (2×).
**Buffer:** 10.000 Q.
**Consumes:** 15 Q/t.
**Time:** 20 ticks (1s).
**GUI:** Status + Configuration (auto-output) + Filters (input/output).

**Craft (9 slots):**

[Ferrum Casing] [Drawer Die] [Ferrum Casing]
[Control Core] [Tripwire Hook] [Control Core]
[Ferrum Frame] [Stonecutter] [Ferrum Frame]

**Output:** 1 Quanta Wire Drawer

**Recipes:**

| Input        | Output        | Time     |
| ------------ | ------------- | -------- |
| Iron Sheet   | 2 Iron Wire   | 20 ticks |
| Gold Sheet   | 2 Gold Wire   | 20 ticks |
| Copper Sheet | 2 Copper Wire | 20 ticks |
| Ferrum Sheet | 2 Ferrum Wire | 20 ticks |
| Cuprum Sheet | 2 Cuprum Wire | 20 ticks |
| Aurum Sheet  | 2 Aurum Wire  | 20 ticks |

### 1.10 Quanta Mixer

**Function:** Mixes dusts → alloys.
**Buffer:** 10.000 Q.
**Consumes:** 20 Q/t.
**Time:** 60 ticks (3s).
**GUI:** Status + Configuration (auto-output) + Filters (input/output).

**Craft (9 slots):**

[Ferrum Casing] [Whisker] [Ferrum Casing]
[Control Core] [Piston] [Control Core]
[Ferrum Frame] [Fluid Core] [Ferrum Frame]

**Output:** 1 Quanta Mixer

**Recipes:**

| Input 1      | Input 2          | Output                  | Time     |
| ------------ | ---------------- | ----------------------- | -------- |
| Iron Dust    | Carbon Dust      | 2 Steel Dust            | 60 ticks |
| Pulvis Nexus | Carbonis Fervens | 2 Ferrum Conducens Dust | 60 ticks |

---

## 🔌 2. CABLES (4)

### 2.1 Quanta Cable

**Function:** Transports Q.
**Throughput:** 5.000 Q/t.
**Craft (3 slots):**

[Ferrum Sheet] [Cuprum Transmissor] [Ferrum Sheet]

**Output:** 4 Quanta Cable

### 2.2 Item Cable

**Function:** Transports items.
**Throughput:** 16 items/t.
**Craft (3 slots):**

[Ferrum Sheet] [Quantum Circuit] [Ferrum Sheet]

**Output:** 4 Item Cable

### 2.3 Pipe

**Function:** Transports fluids.
**Throughput:** 2.000 mB/t.
**Craft (3 slots):**

[Ferrum Sheet] [Vitrum Perspicuum] [Ferrum Sheet]

**Output:** 4 Pipe

### 2.4 Pressurized Pipe

**Function:** Transports gases.
**Throughput:** 2.000 mB/t.
**Craft (3 slots):**

[Ferrum Sheet] [Adamas Resonans] [Ferrum Sheet]

**Output:** 4 Pressurized Pipe

---

## 📦 3. COMPONENTS (7)

| Component          | Craft                                                        | Output |
| ------------------ | ------------------------------------------------------------ | ------ |
| Quantum Coil       | Ferrum Wire + Cuprum Transmissor (crafting)                  | 1      |
| Quantum Circuit    | Ferrum Conducens + Aurum Nobilis + Rubrum Pulsans (Crafting) | 1      |
| Quantum Diode      | Quantum Wire + Pulvis Nexus + Rubrum Pulsans (crafting)      | 1      |
| Quantum Capacitor  | Quantum Coil + Adamas Resonans + Quarzum Vibrans (crafting)  | 1      |
| Quantum Resistor   | Quantum Wire + Pulvis Nexus + Carbonis Fervens (crafting)    | 1      |
| Quantum Processor  | 2 Quantum Circuit + 2 Adamas Resonans (crafting)             | 1      |
| Quantum Transistor | Quantum Diode + Order Fragment + Quantum Circuit (crafting)  | 1      |

**Note:** Quantum Transistor requires an Order Fragment (Tier 2). It may belong in Tier 2.

---

## ⚡ 4. CORES (4)

| Core            | Craft                                   | Output |
| --------------- | --------------------------------------- | ------ |
| Processing Core | 2 Quantum Coil + 2 Quantum Circuit      | 1      |
| Energy Core     | 2 Quantum Coil + 2 Quantum Capacitor    | 1      |
| Fluid Core      | 2 Pipe + 2 Vitrum Perspicuum            | 1      |
| Control Core    | 2 Quantum Circuit + 2 Quantum Processor | 1      |

---

## 📖 5. ITEMS (1)

### 5.1 Quantum Wrench

**Function:** Machine configuration.
**Craft (5 slots):**

[Ferrum Conducens] [ ] [Ferrum Conducens]
[ ] [Ferrum Sheet] [ ]
[ ] [Ferrum Conducens] [ ]

**Output:** 1 Quantum Wrench

---

## 🔌 6. ANCHORS (2)

### 6.1 Seam Anchor

**Craft (5 slots):**

[ ] [Quantum Circuit] [ ]
[Ferrum Sheet] [Item Cable] [Ferrum Sheet]
[ ] [Ferrum Sheet] [ ]

**Output:** 1 Seam Anchor

### 6.2 Flow Anchor

**Craft (5 slots):**

[ ] [Quantum Circuit] [ ]
[Ferrum Sheet] [Pipe/Pressurized Pipe] [Ferrum Sheet]
[ ] [Ferrum Sheet] [ ]

**Output:** 1 Flow Anchor

---

## 🔩 7. MATERIALS (11 + 1 derivative)

| Material           | Craft                                                               | Output |
| ------------------ | ------------------------------------------------------------------- | ------ |
| Pulvis Nexus       | Crafting (Ender Dust + Redstone) → 2 / Infuser (Ender Dust + Q) → 2 | 2      |
| Ferrum Conducens   | Infuser (Iron + Q) / Crafting (Iron + Pulvis Nexus)                 | 1      |
| Adamas Resonans    | Infuser (Diamond + Q) / Crafting (Diamond + Pulvis Nexus)           | 1      |
| Pulvis Densus      | Densifier (1 Pulvis Nexus → 1 Pulvis Densus)                        | 1      |
| Cuprum Transmissor | Infuser (Copper + Q) / Crafting (Copper + Pulvis Nexus)             | 1      |
| Lignum Stabilis    | Infuser (Wood + Q) / Crafting (Wood + Pulvis Nexus)                 | 1      |
| Quantum Planks     | Crafting (1 Lignum → 4 Planks) / Saw (1 Lignum → 8 Planks)          | 4/8    |
| Vitrum Perspicuum  | Infuser (Glass + Q) / Crafting (Glass + Pulvis Nexus)               | 1      |
| Carbonis Fervens   | Infuser (Coal + Q) / Crafting (Coal + Pulvis Nexus)                 | 1      |
| Aurum Nobilis      | Infuser (Gold + Q) / Crafting (Gold + Pulvis Nexus)                 | 1      |
| Rubrum Pulsans     | Infuser (Redstone + Q) / Crafting (Redstone + Pulvis Nexus)         | 1      |
| Quarzum Vibrans    | Infuser (Quartz + Q) / Crafting (Quartz + Pulvis Nexus)             | 1      |

**Note:** Aeternum (Netherite + Q) is Tier 2+.

---

## 📊 8. TIER 1 PROGRESSION

Ender Pearl → Crusher (Tier 0) → Ender Dust
↓
Ender Dust + Redstone → Crafting → 2 Pulvis Nexus
↓
Energy Quantifier (FE → Q)
↓
Quanta Infuser (Iron + Q → Ferrum Conducens)
↓
Quanta Press (Ferrum Conducens → Ferrum Sheet)
↓
Quanta Wire Drawer (Sheet → Wire)
↓
Tier 1 machines (Quanta Infuser, Thermal Infuser, Quanta Densifier, Quanta Collapser, Energy Quantifier, Quanta Separator, Quanta Crusher, Quanta Press, Quanta Wire Drawer, Quanta Mixer)

---

## ⚠️ 9. BALANCING NOTES

- **Quanta Infuser:** 20 Q/t (base), accelerates with a catalyst.
- **Thermal Infuser:** 10 Q/t, 2× faster than Industrial; upgrades improve time/output.
- **Quanta Densifier:** 25 Q/t, 1:1 (Dust → Pallet).
- **Quanta Collapser:** Q → FE (1:100).
- **Quanta Separator:** 10 Q/t, filter block (4 filters, 4 outputs).
- **Quanta Crusher:** 20 Q/t, Fortune I.
- **Quanta Press:** 15 Q/t, Ingot → Sheet.
- **Quanta Wire Drawer:** 15 Q/t, Sheet → Wire (2×).
- **Quanta Mixer:** 20 Q/t, mixes dusts.
- **Cables:** Quanta (5,000 Q/t), Item (16/t), Pipe (2,000 mB/t), Pressurized (2,000 mB/t).
- **Energy:** 1 Q = 100 FE.

---

## 🔗 10. INTEGRATION

- **Tier 0 (Industrial):** provides FE and basic components.
- **Tier 1 (Decoherent):** introduces Q, Pulvis Nexus, Ferrum Conducens, Cuprum Transmissor, Lignum Stabilis, and the 10 Quanta machines.
- **Tier 2 (Entangled):** introduces liquids/gases, Essences, Quanta Purifier, Codex, Glyph, and Aeternum.
- **Tier 3+:** expands on the system.

**This document covers 100% of Tier 1.**
