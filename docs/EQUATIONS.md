---
canvas:
  - "[[STRUCTURE.canvas]]"
parent: "[[QUANTA_MASTERLIST]]"
toc-order: 7
---
# QUANTA MOD - EQUATIONS

> **Document based on QUANTA_MASTERLIST v10.6**
> **Last updated:** 2026-11-16
> **Scope:** Magic system (Equations, Black Board, Quantum Codex)

## 🎯 OVERVIEW

The **Equations** system is Quanta Mod's **magic**. The player creates **formulas** on the **Black Board**, stores them in the **Quantum Codex**, and **casts** them into the world.

**Philosophy:** magic is the **conscious manipulation of Quanta**. Unlike machines (which process matter), Equations **temporarily rewrite reality**.

---

## 📖 1. QUANTUM CODEX (ITEM)

**Function:** Grimoire that stores Equations.
**How it works:**

- Portable item (not a block).
- Opens a GUI on right-click (like the Ars Nouveau book).
- Stores up to **6 equipped Equations** (NBT).
- **10,000 Q** buffer (recharges with Adamas Resonans).
- Passively gains **knowledge** (1/tick while observing machines).
- Knowledge unlocks symbols on the Black Board.

**Craft (3 slots):**

[Lignum Stabilis Planks] [Book] [Adamas Resonans]

**Output:** 1 Quantum Codex

**States:**

- Empty: no Equations.
- Basic: 1-3 Equations.
- Advanced: 4-6 Equations.
- Master: 6 Equations + all unlocked.

---

## 🖤 2. BLACK BOARD (BLOCK)

**Function:** Equation creation station.
**How it works:**

- Unique block (1×1 appearance, 3×3×1).
- Opens a GUI on right-click.
- Symbols (Form, Element, Effect) on the side.
- The player drags symbols to the center to combine them.
- Equations are saved on the Black Board (right side of the GUI).
- The player drags them to the Codex (bottom of the GUI) to equip them.

**Craft (9 slots):**

[Lignum Stabilis Planks] [Adamas Resonans] [Lignum Stabilis Planks]
[Quantum Circuit] [Quantum Processor] [Quantum Circuit]
[Lignum Stabilis Planks] [Processing Core] [Lignum Stabilis Planks]

**Output:** 1 Black Board

---

## 📜 3. BLANK GLYPH (ITEM)

**Function:** Base for creating Equations.
**How it works:**

- Item crafted by hand.
- Placed on the Black Board to create Equations.
- Consumed when creating an Equation.

**Craft (4 slots):**

[Lignum Stabilis Planks] [Paper]
[Ink Sac] [Adamas Resonans]

**Output:** 1 Blank Glyph

---

## 🧩 4. EQUATION STRUCTURE

### 4.1 Form + Element + Effect

Every Equation consists of **3 components**:

| Component   | Options                                                                    | Function                  |
| ------------ | ------------------------------------------------------------------------- | ----------------------- |
| **Form**    | Self, Projectile, Touch, Area                                             | How the magic is applied |
| **Element** | Order, Chaos, Singularity                                                 | Nature of the magic       |
| **Effect**   | Damage, Heal, Teleport, Acceleration, Infusion, Revelation, Stabilization | What the magic does       |

**Total combinations:** 4 × 3 × 7 = **84**.

---

## 🎯 5. FORMS (4)

| Form           | Description               | Example                            |
| -------------- | ----------------------- | ---------------------------------- |
| **Self**       | Affects the player | Healing, buff                         |
| **Projectile** | Fires a projectile | Fireball, lightning bolt                 |
| **Touch**      | Touch the target | Heals an ally, teleports an enemy |
| **Area**       | Area around the player | Area damage, area healing         |

---

## ⚡ 6. ELEMENTS (3)

| Element         | Cost | Failure | Power |
| --------------- | ----- | ----- | ----- |
| **Order**       | +25%  | 0%    | Base  |
| **Chaos**       | -25%  | +15%  | +25%  |
| **Singularity** | +100% | 0%    | +100% |

---

## 💥 7. EFFECTS (7)

| Effect            | Description                         |
| ----------------- | --------------------------------- |
| **Damage**        | Damage (direct, area, projectile)     |
| **Heal**          | Healing (direct, area)               |
| **Teleport**      | Teleport (self, target, area)      |
| **Acceleration**  | Speed (self, target)           |
| **Infusion**      | Infuses an item (with Quanta)         |
| **Revelation**    | Reveals an area (ores, entities) |
| **Stabilization** | Removes negative effects          |

---

## 📊 8. EFFECT TABLE (SUMMARY)

### 8.1 Self (affects the player)

| Effect        | Order                             | Chaos                          | Singularity                             |
| ------------- | --------------------------------- | ------------------------------ | --------------------------------------- |
| Damage        | +75% damage, +20% resistance (30s) | +100% damage, +25% damage taken | +200% damage, immortal, burns targets (15s) |
| Heal          | Heal 12 + regeneration (10s)             | Heal 4 + absorption 8            | Full heal, Regeneration V, absorption 20 (20s)  |
| Teleport      | Teleport + slow motion          | Random teleport + nausea   | Teleport to any coordinates      |
| Acceleration  | +75% speed, step assist           | +125% speed, blurred vision     | +300% speed, passes through blocks           |
| Infusion      | +100% Quanta regeneration, -25% cost    | +200% regeneration, 25% failure chance         | Creative flight, indestructible, pulls in items |
| Revelation    | Vision 32 blocks + entities       | Vision 64 blocks + tinnitus     | Vision 100 blocks, machines, liquids    |
| Stabilization | Removes effects + immunity (30s)  | Removes effects + transfers             | Removes everything + total immunity (120s)    |

### 8.2 Projectile (fires a projectile)

| Effect        | Order                         | Chaos                       | Singularity                        |
| ------------- | ----------------------------- | --------------------------- | ---------------------------------- |
| Damage        | Piercing (damage 20, 3 targets) | Explosive (damage 30, radius 3) | Black hole (damage 50, pulls in items) |
| Heal          | Heal 15 + shield 10           | Heal 5 + area regeneration 2       | Heal 50 + healing field (20/s, 5s) |
| Teleport      | Target → your position            | Target → the void (or damage 50)    | Dimensional rift (target sent to the void)   |
| Acceleration  | Fast, paralyzes (3s)         | Erratic, inverts controls  | 90% slow motion (15s)             |
| Infusion      | 50% chance to infuse an item             | Infuses + drops Chaos       | 100% infuses with Singularity       |
| Revelation    | Reveals area + marks target      | Reveals + nausea             | Reveals everything within 20 blocks (60s)     |
| Stabilization | Removes effects + resistance (30s)    | Removes effects + transfers          | Removes all effects within 5 blocks           |

### 8.3 Touch (touches the target)

| Effect        | Order                      | Chaos                     | Singularity                          |
| ------------- | -------------------------- | ------------------------- | ------------------------------------ |
| Damage        | 30 damage + knockback        | 15-60 random           | 150 damage, ignores armor            |
| Heal          | Heal 30 + regeneration (20s)      | 10-50 random           | Heal 100 + removes all negative effects    |
| Teleport      | Target → your position         | Target → Nether/End         | Instantly kills mobs (void)               |
| Acceleration  | +100% speed, step assist   | +200% speed + fatigue damage | Global 80% slow motion (15s)        |
| Infusion      | 75% chance to infuse an item          | Infuses + damage 10         | Infuses + duplicates item               |
| Revelation    | Marks target + reveals inventory  | Marks target + particles        | Reveals everything (HP, items, location) |
| Stabilization | Removes effects + resistance (60s) | Removes effects + transfers        | Removes all effects within 10 blocks            |

### 8.4 Area (area around the player)

| Effect        | Order                           | Chaos                        | Singularity                             |
| ------------- | ------------------------------- | ---------------------------- | --------------------------------------- |
| Damage        | Damage 20, radius 6 (hostiles only)     | Damage 25, radius 8 (everyone)      | Black hole, damage 100, radius 10          |
| Heal          | Heal 15 + regeneration, radius 6         | Heal 5 + damage 5 to hostiles       | Heal 50 + field 20/s, radius 6            |
| Teleport      | Everyone within radius 4 → safe locations   | 30% go to the void, radius 5      | Everyone within radius 8 → the void                     |
| Acceleration  | +75% speed, radius 6              | +125% speed + nausea, radius 8 | Reverse gravity, float for 15s          |
| Infusion      | 50% chance to infuse items on the ground, radius 6 | 100%, but 20% chance to disappear, radius 8   | All items infused + duplicated     |
| Revelation    | Ores + entities, radius 12   | Everything + nausea                | Omni scan radius 30 (60s)                 |
| Stabilization | Removes effects + immunity, radius 8      | Removes effects + transfers mobs      | Removes black holes + effects radius 32 |

**Quanta cost:** varies by effect (50–400 Q). Modifiers: Order +25%, Chaos -25%, Singularity +100%.

---

## ⚙️ 9. HOW TO CREATE EQUATIONS

### 9.1 Step by step

1. Place **Blank Glyph** in the Black Board slot.
2. Drag **Form** (Self, Projectile, Touch, Area) to the center.
3. Drag **Element** (Order, Chaos, Singularity) to the center.
4. Drag **Effect** (Damage, Heal, etc.) to the center.
5. Preview the cost in Q + stat effect.
6. Confirm → the Equation is saved on the Black Board.
7. Drag it from the Black Board to the Quantum Codex (to equip it).

### 9.2 Cost

- Equation creation: 50 Q (basic) to 400 Q (Singularity).
- Equipping in the Codex: free.
- Casting an Equation: variable (see the effect table).

---

## 📊 10. KNOWLEDGE (XP)

### 10.1 How to earn

- **Observing machines:** 1 knowledge/tick.
- **Casting Equations:** 5 knowledge/cast.

### 10.2 How to use

- Knowledge unlocks **symbols** on the Black Board.
- Unlocked symbols enable **new combinations**.

**Total symbols:** 4 (Forms) + 3 (Elements) + 7 (Effects) = **14 symbols**.

---

## 🔗 11. INTEGRATION

- **BLACK BOARD:** creation station.
- **QUANTUM CODEX:** grimoire (stores Equations).
- **BLANK GLYPH:** Equation base.
- **MACHINES:** the Codex gains knowledge by observing machines.
- **CORTEX (IMPLANT):** integrates the Codex into the body (T4).

**This document covers 100% of Quanta Mod's Equations system.**
