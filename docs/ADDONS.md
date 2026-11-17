---
canvas:
  - "[[STRUCTURE.canvas]]"
parent: "[[MACHINES]]"
toc-order: 7
STRUCTURE:
  - "[[MACHINES]]"
---
# QUANTA MOD - ADDONS

> **Document based on QUANTA_MASTERLIST v10.6**
> **Last updated:** 2026-11-16
> **Scope:** All machine addons (upgrades) in Quanta Mod

## 🎯 OVERVIEW

Addons are **temporary upgrades** that can be installed in machines to improve their performance. Unlike **Upgrade Kits** (which permanently raise a machine's tier), addons are **removable** and **complementary** (their effects stack with those of the Upgrade Kit).

**Philosophy:** the player chooses between an **Upgrade Kit** (permanent, expensive) and **Addons** (temporary, inexpensive). The two complement each other.

---

## 📦 1. ADDON SLOTS

### 1.1 Number of slots per tier

| Tier            | Addon Slots |
| --------------- | ----------- |
| Decoherent (T1) | 2           |
| Entangled (T2)  | 3           |
| Superposed (T3) | 4           |
| Singular (T4)   | 6           |

**Rule:** a maximum of **4 addons of the same type** per slot.

### 1.2 Slot position

Addon slots are on the **side of the GUI** (they do not compete with input/output slots).

---

## ⚡ 2. AVAILABLE ADDONS

### 2.1 Speed Coil

**Effect:** +20% speed, +25% energy consumption.
**Tier:** T1+.
**Craft:**

[ ] [Quantum Coil] [ ]
[Redstone] [Ferrum Wire] [Redstone]
[ ] [Quantum Coil] [ ]

**Output:** 1 Speed Coil

### 2.2 Energy Coil

**Effect:** -20% energy consumption.
**Tier:** T1+.
**Craft:**

[ ] [Quantum Capacitor] [ ]
[Redstone] [Ferrum Wire] [Redstone]
[ ] [Quantum Capacitor] [ ]

**Output:** 1 Energy Coil

### 2.3 Stabilizer Coil

**Effect:** -5% failure chance.
**Tier:** T1+.
**Craft:**

[ ] [Quantum Coil] [ ]
[Adamas Resonans] [Ferrum Wire] [Adamas Resonans]
[ ] [Quantum Coil] [ ]

**Output:** 1 Stabilizer Coil

### 2.4 Quantum Lock

**Effect:** -10% failure chance.
**Tier:** T2+.
**Craft:**

[ ] [Quantum Circuit] [ ]
[Observer Lens] [Ferrum Wire] [Observer Lens]
[ ] [Quantum Circuit] [ ]

**Output:** 1 Quantum Lock

### 2.5 Observer Link

**Effect:** -15% failure chance.
**Tier:** T2+.
**Craft:**

[ ] [Observer Lens] [ ]
[Adamas Resonans] [Ferrum Wire] [Adamas Resonans]
[ ] [Observer Lens] [ ]

**Output:** 1 Observer Link

### 2.6 Chaos Overdrive

**Effect:** +50% speed, +25% failure chance.
**Tier:** T2+.
**Craft:**

[ ] [Chaos Fragment] [ ]
[Quantum Coil] [Ferrum Wire] [Quantum Coil]
[ ] [Chaos Fragment] [ ]

**Output:** 1 Chaos Overdrive

### 2.7 Chaos Injector

**Effect:** +50% generation, +25% risk (reactor).
**Tier:** T2+.
**Craft:**

[ ] [Chaos Fragment] [ ]
[Adamas Resonans] [Ferrum Wire] [Adamas Resonans]
[ ] [Chaos Fragment] [ ]

**Output:** 1 Chaos Injector

### 2.8 Order Stabilizer

**Effect:** -30% risk, -10% generation (reactor).
**Tier:** T2+.
**Craft:**

[ ] [Order Fragment] [ ]
[Adamas Resonans] [Ferrum Wire] [Adamas Resonans]
[ ] [Order Fragment] [ ]

**Output:** 1 Order Stabilizer

### 2.9 Yield Addon

**Effect:** +15% secondary output.
**Tier:** T2+.
**Craft:**

[ ] [Quantum Processor] [ ]
[Adamas Resonans] [Ferrum Wire] [Adamas Resonans]
[ ] [Quantum Processor] [ ]

**Output:** 1 Yield Addon

### 2.10 Fluid Addon

**Effect:** Adds a liquid catalyst slot to the machine.
**Tier:** T2+.
**Craft:**

[ ] [Vitrum Perspicuum] [ ]
[Quantum Circuit] [Fluid Core] [Quantum Circuit]
[ ] [Vitrum Perspicuum] [ ]

**Output:** 1 Fluid Addon

**How it works:**

- The machine gains a **fluid slot** (catalyst).

- The fluid is **pumped in** (not added manually).
- The fluid is **consumed** (the bar decreases).
- If you **pump continuously**, the bar **does not decrease**.

**Effects by fluid:**

| Fluid              | Effect                            |
| ------------------ | --------------------------------- |
| Liquid Quanta      | +50% speed                        |
| Liquid Order       | +25% speed + 0% failure chance    |
| Liquid Chaos       | +75% speed + 15% failure chance   |
| Stabilized Liquid  | +50% speed + 0% failure chance    |
| Entangled Liquid   | +75% speed + output teleportation |
| Liquid Singularity | +100% speed + 10% destruction     |

---

## 📊 3. ADDON SUMMARY

<!-- AUTO-GENERATED: addon-summary START -->
| Addon | Effect | Tier | Craft |
| --- | --- | --- | --- |
| Speed Coil | +20% speed | T1 | Quantum Coil + Redstone |
| Energy Coil | -20% energy | T1 | Quantum Capacitor + Redstone |
| Stabilizer Coil | -5% failure chance | T1 | Quantum Coil + Adamas Resonans |
| Quantum Lock | -10% failure chance | T2 | Quantum Circuit + Observer Lens |
| Observer Link | -15% failure chance | T2 | Observer Lens + Adamas Resonans |
| Chaos Overdrive | +50% speed, +25% failure | T2 | Chaos Fragment + Quantum Coil |
| Chaos Injector | +50% generation, +25% risk | T2 | Chaos Fragment + Adamas Resonans |
| Order Stabilizer | -30% risk, -10% generation | T2 | Order Fragment + Adamas Resonans |
| Yield Addon | +15% output | T2 | Quantum Processor + Adamas Resonans |
| Fluid Addon | Liquid catalyst | T2 | Quantum Glass + Fluid Core |
<!-- AUTO-GENERATED: addon-summary END -->

---

## 🔧 4. HOW TO USE ADDONS

### 4.1 Installation

- Open the machine GUI.
- Place the addon in an **addon slot** (on the side).
- The effect is **immediate**.

### 4.2 Removal

- Open the machine GUI.
- Remove the addon from its slot.
- The effect is **removed**.

### 4.3 Stacking

- Addons of the **same type** stack (up to 4 per slot).
- Addons of **different types** complement each other.

**Example:**

- 4 Speed Coils = +80% speed (4 × 20%).
- 1 Speed Coil + 1 Energy Coil = +20% speed, -20% energy consumption.

---

## ⚠️ 5. BALANCE NOTES

- **Speed Coil:** +20% speed, +25% energy consumption (trade-off).
- **Energy Coil:** -20% energy consumption (no trade-off).
- **Stabilizer Coil:** -5% failure chance (no trade-off).
- **Chaos Overdrive:** +50% speed, +25% failure chance (trade-off).
- **Order Stabilizer:** -30% risk, -10% generation (trade-off).
- **Fluid Addon:** liquid catalyst (flexible).

**Rule:** addons with a **trade-off** (Speed Coil, Chaos Overdrive) are more powerful than addons **without a trade-off** (Energy Coil, Stabilizer Coil).

---

## 🔗 6. INTEGRATION

- **Upgrade Kit:** raises the tier (permanent).
- **Addons:** improve performance (temporary).
- **Machines:** all T1+ machines have addon slots.
- **Fluid Addon:** adds fluid support to machines that do not have it.

**This document covers 100% of the addons in Quanta Mod.**
