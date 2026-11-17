---
canvas:
  - "[[STRUCTURE.canvas]]"
parent: "[[MACHINES]]"
toc-order: 10
---
# QUANTA MOD - STATIONS

> **Document based on QUANTA_MASTERLIST v10.6**
> **Last updated:** 2026-11-16
> **Scope:** Crafting stations (Circuit Etcher, Fabrication Rig, Black Board)

## 🎯 OVERVIEW

The Quanta Mod has **3 crafting stations** (in addition to assemblies):

1. **Quantum Circuit Etcher** — circuits.
2. **Quantum Fabrication Rig** — tools, armor, implants.
3. **Black Board** — Equations (magic).

They all share the same visual language: GUI, slots, preview, tiers.

---

## ⚡ 1. QUANTUM CIRCUIT ETCHER

### 1.1 Function

Transforms components (Quantum Diode, Quantum Capacitor, Quantum Resistor, Quantum Transistor, Quantum Coil, Ferrum Wire) into **Circuits** with stats.

### 1.2 GUI Structure

┌──────────────────────────────────────────────┐
│ [Blueprint Slot] [Etchant Slot]              │
│                                              │
│ [1][2][3] ← Component grid (3×3)              │
│ [4][5][6]                                    │
│ [7][8][9]                                    │
│                                              │
│ [Board Slot]                                 │
│                                              │
│ [Progress Bar] [Output]                      │
└──────────────────────────────────────────────┘

### 1.3 Inputs

- **Blueprint Slot:** Silk (encoded recipe) — optional.
- **Etchant Slot:** liquid/gas that defines the circuit's "nature."
- **Board Slot:** Quantum Circuit Board (base).
- **Grid:** 9 component slots.

### 1.4 Etchants

| Etchant           | Result         | Bonus                 |
| ----------------- | -------------- | --------------------- |
| Liquid Order      | Order Circuit | +1 range, 0% failure     |
| Liquid Chaos      | Chaos Circuit | +15% speed, 15% failure |
| Essence of Order  | Pure Order     | Amplifies bonus       |
| Essence of Chaos  | Pure Chaos     | Amplifies bonus       |
| Liquid Quanta     | Neutral         | No bonus             |
| Stabilized Liquid | Singular       | Enables tier 3+       |

### 1.5 Circuitos

| Circuit   | Components                                                               | Stats                            | Use               |
| ---------- | ------------------------------------------------------------------------ | -------------------------------- | ----------------- |
| Basic      | 2 Quantum Diode + 2 Quantum Resistor                                     | +1 slot, 0 bonus                 | T1 machines       |
| Processing | 2 Quantum Capacitor + 2 Quantum Transistor                               | +2 slots, +10% speed             | T2 machines       |
| Quantum    | 1 Quantum Coil + 2 Quantum Diode + 1 Quantum Capacitor                   | +3 slots, +20% speed, +10% yield | T3 machines       |
| Order      | 2 Quantum Diode + 1 Quantum Resistor + 1 Quantum Coil + Essence of Order | +0 failure                       | Safe Addons    |
| Chaos      | 2 Chaos Fragment + 1 Quantum Coil + Essence of Chaos                     | +50% speed, +25% failure         | Aggressive Addons |
| Singular   | 1 Quantum Circuit + 1 Order + 1 Chaos + 1 Superpositum                   | +5 slots, maximum stats          | Endgame           |
| Resonance  | 2 Quantum Coil + 1 Quantum Capacitor + 1 Observer Lens                   | +2 network range                 | Entanglers        |

### 1.6 Sub-circuits (nesting)

- 4 Basic → 1 Advanced.
- 4 Advanced → 1 Processing.
- 4 Processing → 1 Quantum.
- 4 Quantum → 1 Singular.

### 1.7 Silk (blueprint)

- **Blank Silk:** Pulvis Densus + Paper.
- **Circuit Silk:** Blank Silk + recorded recipe.
- Consumed when crafting.
- Stored in a **Silk Vault** (block).

### 1.8 Modes

- **Order:** 1 craft, 0% failure.
- **Chaos:** batch of 4, +15% failure, -25% time/craft.

### 1.9 Energy

- 20 Q/t (Order), 40 Q/t (Chaos).

### 1.10 Recipe

[Ferrum Casing] [Observer Lens] [Ferrum Casing]
[Quantum Circuit] [Etcher Core] [Quantum Circuit]
[Ferrum Casing] [Processing Core] [Ferrum Casing]

---

## 🛠️ 2. QUANTUM FABRICATION RIG

### 2.1 Function

Assembles **tools, armor, and implants** from parts. Tinker's/Silent Gear style.

### 2.2 GUI Structure

┌─────────────────────────────────────────────────────┐
│ Tabs: [Tool] [Armor] [Implant] [Special]            │
├─────────────────────────────────────────────────────┤
│ ┌────────┐ ┌────────────────────────┐               │
│ │ Preview│ │ [Skeleton Slot]        │               │
│ │        │ │ [Head Slot]            │               │
│ │ Stats  │ │ [Guard Slot]           │               │
│ │        │ │ [Handle Slot]          │               │
│ │        │ │ [Wrap Slot]            │               │
│ │        │ │ [Pommel Slot]          │               │
│ └────────┘ └────────────────────────┘               │
│                                                     │
│ [Modifier Slots: 0 / 4] [Trait Slots: 0 / 2]        │
│                                                     │
│ [Assemble] [Disassemble]                            │
└─────────────────────────────────────────────────────┘

### 2.3 Parts by Category

**Tools:**

| Tool       | Parts                                   |
| ---------- | --------------------------------------- |
| Pickaxe    | Skeleton, Head, Handle, Wrap            |
| Axe        | Skeleton, Head, Handle                  |
| Shovel     | Skeleton, Head, Handle                  |
| Sword      | Skeleton, Blade, Guard, Handle, Pommel  |
| Katana     | Skeleton, Blade, Guard, Handle, Wrap    |
| Drill      | Skeleton, Head, Motor, Handle, Battery  |
| Saw        | Skeleton, Blade, Guard, Handle          |
| Cannon     | Skeleton, Barrel, Core, Handle, Battery |
| Grappler   | Skeleton, Hook, Wire, Handle            |
| Shield     | Skeleton, Plate, Plate, Handle          |

**Armor:**

| Part       | Slots                           |
| ---------- | ------------------------------- |
| Helmet     | Skeleton, Visor, Plate, Padding |
| Chestplate | Skeleton, Plate, Plate, Padding |
| Leggings   | Skeleton, Plate, Plate, Padding |
| Boots      | Skeleton, Plate, Plate, Padding |

**Implants:**

| Part           | Slots                           |
| -------------- | ------------------------------- |
| Neural Implant | Skeleton, Interface, Core, Chip |

### 2.4 Part Materials

Each part accepts different materials. The material determines its stats:

- Ferrum Conducens, Adamas Resonans, Aeternum, Superpositum, Dechoerentium, Singular Alloy.

See **METALLURGY.md** for the complete table.

### 2.5 Traits

Each material+part combination can generate **traits** (passive effects):

| Trait    | Source                   | Effect                   |
| -------- | ------------------------ | ------------------------ |
| Resonant | Adamas Resonans Head     | +10% yield               |
| Chaotic  | Chaotic Steel Blade      | +25% damage, 10% self-damage |
| Stable   | Order Steel Handle       | -20% breakage            |
| Phase    | Entangled Steel Blade    | Passes through 1 block   |
| Singular | Singular Alloy part      | +1 trait slot            |

### 2.6 Modifiers (slots per tier)

- T1 (Decoherent): 2 slots.
- T2 (Entangled): 3 slots.
- T3 (Superposed): 4 slots.
- T4 (Singular): 6 slots.

Full list in **MACHINES.md** (Equipment section).

### 2.7 Leveling (XP)

| Level | XP      | Bonus                         |
| ----- | ------- | ----------------------------- |
| 1     | 0       | —                             |
| 2     | 1.000   | +1 modifier                   |
| 3     | 5.000   | +1 trait slot                 |
| 4     | 20.000  | +1 native enchantment         |
| 5     | 100.000 | +1 modifier, +1 trait slot    |

### 2.8 Disassemble

- Costs 50 Q.
- Returns the parts (modules are preserved).
- Traits are lost (unless the tool is level 5).

### 2.9 Modes

- **Order:** assembles with no failure.
- **Chaos:** assembles with a 15% failure chance, but generates an extra random trait.

### 2.10 Energy

- 50 Q/craft (assemble), 50 Q/craft (disassemble).

### 2.11 Recipe

[Ferrum Casing] [Fabrication Core] [Ferrum Casing]
[Quantum Circuit] [Assembly Core] [Quantum Circuit]
[Ferrum Casing] [Processing Core] [Ferrum Casing]

---

## 📖 3. BLACK BOARD (MANTIDO + REFINADO)

### 3.1 Function

Station for creating **Equations** (magic). It is not an assembly or a tool. It is the third station.

### 3.2 Structure

Single block (1×1 visual, 3×3×1). Serves as a **physical table** for creating Equations.

### 3.3 GUI

┌──────────────────────────────────────────────┐
│ [Blank Equation Slot] [Result Preview]       │
│                                              │
│ Symbols (Form / Element / Effect):           │
│ [Self] [Projectile] [Touch] [Area]           │
│ [Order] [Chaos] [Singularity]                │
│ [Damage] [Heal] [Teleport] [Acceleration]    │
│ [Infusion] [Revelation] [Stabilization]      │
│                                              │
│ [Saved Equations] [Quantum Codex Slot]       │
└──────────────────────────────────────────────┘

### 3.4 Workflow

1. Place **Blank Glyph** (item) in the slot.
2. Drag symbols to the center (combine Form + Element + Effect).
3. Preview the Q cost + stat effect.
4. Confirm → the Equation is saved to the Black Board.
5. Drag it from the Black Board to the Quantum Codex (equip).

### 3.5 Cost

- Equation creation: 50 Q (basic) to 400 Q (Singularity).
- Equipping in the Codex: free.
- Casting an Equation: variable (see EQUATIONS.md).

### 3.6 Rules

- Black Board **does not program assemblies** — Equations only.
- Black Board **does not replace** the Circuit Etcher or Fabrication Rig.
- It is the "magic" station, separate from the "science" stations.

### 3.7 Recipe

[Ferrum Casing] [Observer Lens] [Ferrum Casing]
[Quantum Circuit] [Blackboard Core] [Quantum Circuit]
[Ferrum Casing] [Processing Core] [Ferrum Casing]

---

## 🔗 4. INTEGRATION

- **Network:** stations can be on the network (they use qubits for operations).
- **Cables:** stations have cable ports (Quanta + Item).
- **Assemblies:** Nexus Assembler (block) is the preassembled Auto-Crafting option; Circuit Etcher is the preassembled Etching option.
- **Metallurgy:** alloys determine the quality of circuits and parts.

---

## 🎨 5. STATION TIERS

| Station         | Tier T1            | T2              | T3               | T4             |
| --------------- | ------------------ | --------------- | ---------------- | -------------- |
| Circuit Etcher  | Basic + Processing | + Quantum       | + Singular       | (re-etch only) |
| Fabrication Rig | Decoherent parts   | Entangled parts | Superposed parts | Singular parts |
| Black Board     | Basic Equations    | + Chaos/Order   | + Singularity    | (all)          |

**Station upgrade:** Apply an Upgrade Kit to the station.

**This document covers 100% of the Quanta Mod's crafting stations.**
