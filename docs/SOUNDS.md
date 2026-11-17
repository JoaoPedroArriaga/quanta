---
canvas:
  - "[[STRUCTURE.canvas]]"
parent: "[[VISUALS]]"
toc-order: 2
---
# QUANTA MOD - SOUNDS

> **Document based on QUANTA_MASTERLIST v10.6**
> **Last updated:** 2026-11-16
> **Scope:** Sound effects for the Quanta Mod

## 🎯 OVERVIEW

The Quanta Mod uses **sound effects** for machines, effects, and implants. Some sounds are **vanilla** (reused), others are **custom** (created for the mod).

**Philosophy:** sounds should be **satisfying** (feedback) and **thematic** (techno-magical).

---

## 🏭 1. MACHINE SOUNDS

### 1.1 Loop Sounds

| Machine                | Sound                             | Description    |
| ---------------------- | --------------------------------- | -------------- |
| Combustor              | `block.furnace.fire_crackle`      | Fire crackling |
| Industrial Crusher     | `block.piston.retract` (loop)     | Piston moving  |
| Industrial Furnace     | `block.furnace.fire_crackle`      | Fire crackling |
| Industrial Press       | `block.anvil.land` (loop)         | Anvil hitting  |
| Industrial Wire Drawer | `block.piston.retract` (loop)     | Piston moving  |
| Industrial Saw         | `block.stonecutter.saw` (loop)    | Saw spinning   |
| Industrial Harvester   | `block.grass.break` (loop)        | Grass breaking |
| Industrial Mixer       | `block.brewing_stand.brew` (loop) | Bubbling       |

### 1.2 Craft Sounds

| Machine                | Sound                      | Description     |
| ---------------------- | -------------------------- | --------------- |
| Combustor              | `block.fire.ambient`       | Fire whoosh     |
| Industrial Crusher     | `block.stone.break`        | Stone breaking  |
| Industrial Furnace     | `block.fire.extinguish`    | Fire extinguish |
| Industrial Press       | `block.anvil.use`          | Anvil use       |
| Industrial Wire Drawer | `block.piston.extend`      | Piston extend   |
| Industrial Saw         | `block.stonecutter.cut`    | Saw cutting     |
| Industrial Harvester   | `block.crop.break`         | Crop breaking   |
| Industrial Mixer       | `block.brewing_stand.brew` | Brewing         |

### 1.3 Failure Sounds

| Machine                | Sound                      | Description     |
| ---------------------- | -------------------------- | --------------- |
| Combustor              | `block.fire.extinguish`    | Fire extinguish |
| Industrial Crusher     | `block.stone.break`        | Stone breaking  |
| Industrial Furnace     | `block.fire.extinguish`    | Fire extinguish |
| Industrial Press       | `block.anvil.land`         | Anvil land      |
| Industrial Wire Drawer | `block.piston.retract`     | Piston retract  |
| Industrial Saw         | `block.stonecutter.cut`    | Saw cutting     |
| Industrial Harvester   | `block.crop.break`         | Crop breaking   |
| Industrial Mixer       | `block.brewing_stand.brew` | Brewing         |

---

## ⚙️ 2. QUANTA MACHINE SOUNDS

### 2.1 Loop Sounds

| Machine            | Sound                             | Description    |
| ------------------ | --------------------------------- | -------------- |
| Quanta Infuser     | `block.beacon.ambient`            | Beacon ambient |
| Thermal Infuser    | `block.furnace.fire_crackle`      | Fire crackling |
| Quanta Densifier   | `block.piston.retract` (loop)     | Piston moving  |
| Quanta Collapser   | `block.portal.ambient`            | Portal ambient |
| Energy Quantifier  | `block.beacon.ambient`            | Beacon ambient |
| Quanta Separator   | `block.piston.retract` (loop)     | Piston moving  |
| Quanta Crusher     | `block.stone.break` (loop)        | Stone breaking |
| Quanta Press       | `block.anvil.land` (loop)         | Anvil hitting  |
| Quanta Wire Drawer | `block.piston.retract` (loop)     | Piston moving  |
| Quanta Mixer       | `block.brewing_stand.brew` (loop) | Bubbling       |

### 2.2 Craft Sounds

| Machine            | Sound                      | Description     |
| ------------------ | -------------------------- | --------------- |
| Quanta Infuser     | `block.beacon.activate`    | Beacon activate |
| Thermal Infuser    | `block.fire.extinguish`    | Fire extinguish |
| Quanta Densifier   | `block.piston.extend`      | Piston extend   |
| Quanta Collapser   | `block.portal.trigger`     | Portal trigger  |
| Energy Quantifier  | `block.beacon.activate`    | Beacon activate |
| Quanta Separator   | `block.piston.extend`      | Piston extend   |
| Quanta Crusher     | `block.stone.break`        | Stone breaking  |
| Quanta Press       | `block.anvil.use`          | Anvil use       |
| Quanta Wire Drawer | `block.piston.extend`      | Piston extend   |
| Quanta Mixer       | `block.brewing_stand.brew` | Brewing         |

### 2.3 Failure Sounds

| Machine            | Sound                      | Description       |
| ------------------ | -------------------------- | ----------------- |
| Quanta Infuser     | `block.beacon.deactivate`  | Beacon deactivate |
| Thermal Infuser    | `block.fire.extinguish`    | Fire extinguish   |
| Quanta Densifier   | `block.piston.retract`     | Piston retract    |
| Quanta Collapser   | `block.portal.travel`      | Portal travel     |
| Energy Quantifier  | `block.beacon.deactivate`  | Beacon deactivate |
| Quanta Separator   | `block.piston.retract`     | Piston retract    |
| Quanta Crusher     | `block.stone.break`        | Stone breaking    |
| Quanta Press       | `block.anvil.land`         | Anvil land        |
| Quanta Wire Drawer | `block.piston.retract`     | Piston retract    |
| Quanta Mixer       | `block.brewing_stand.brew` | Brewing           |

---

## 🎵 3. EFFECT SOUNDS

### 3.1 Craft Complete

| Effect                          | Sound                   | Description       |
| ------------------------------- | ----------------------- | ----------------- |
| Craft complete (any machine)    | `block.anvil.use`       | Anvil use (light) |
| Craft complete (Quanta machine) | `block.beacon.activate` | Beacon activate   |

### 3.2 Craft Failed (Chaos)

| Effect                        | Sound                     | Description       |
| ----------------------------- | ------------------------- | ----------------- |
| Craft failed                  | `block.fire.extinguish`   | Fire extinguish   |
| Craft failed (Quanta machine) | `block.beacon.deactivate` | Beacon deactivate |

### 3.3 Decoherence

| Effect               | Sound                     | Description           |
| -------------------- | ------------------------- | --------------------- |
| Decoherence start    | `block.beacon.deactivate` | Beacon deactivate     |
| Decoherence (static) | `ambient.cave`            | Cave ambient (static) |
| Decoherence end      | `block.beacon.activate`   | Beacon activate       |

### 3.4 Implant Installation

| Effect                | Sound                       | Description         |
| --------------------- | --------------------------- | ------------------- |
| Installation start    | `block.beacon.activate`     | Beacon activate     |
| Installation (loop)   | `block.beacon.ambient`      | Beacon ambient      |
| Installation complete | `block.beacon.power_select` | Beacon power select |

---

## 🔮 4. IMPLANT SOUNDS

### 4.1 Activation

| Implant              | Sound                        | Description         |
| -------------------- | ---------------------------- | ------------------- |
| Quantum Sight        | `block.beacon.activate`      | Beacon activate     |
| Quantum Reach        | `block.piston.extend`        | Piston extend       |
| Quantum Speed        | `entity.player.attack.sweep` | Sweep attack        |
| Quantum Strength     | `entity.player.attack.crit`  | Critical attack     |
| Quantum Resistance   | `block.anvil.land`           | Anvil land          |
| Quantum Lungs        | `entity.player.breathe`      | Breathe             |
| Quantum Night Vision | `block.beacon.activate`      | Beacon activate     |
| Quantum Tunneling    | `block.portal.travel`        | Portal travel       |
| Quantum Flight       | `entity.ender_dragon.flap`   | Dragon flap         |
| Quantum Buffer       | `block.beacon.activate`      | Beacon activate     |
| Quantum Metabolism   | `entity.player.burp`         | Burp                |
| Quantum Reflex       | `entity.player.attack.sweep` | Sweep attack        |
| Quantum Storage      | `block.ender_chest.open`     | Ender chest open    |
| Quantum Interface    | `block.beacon.activate`      | Beacon activate     |
| Quantum Anchor       | `block.anvil.land`           | Anvil land          |
| Quantum Cortex       | `block.beacon.power_select`  | Beacon power select |

### 4.2 Deactivation

| Implant     | Sound                     | Description       |
| ----------- | ------------------------- | ----------------- |
| Any implant | `block.beacon.deactivate` | Beacon deactivate |

---

## 🎶 5. AMBIENT SOUNDS

### 5.1 Machine Ambient

| Machine                | Sound                        | Description                 |
| ---------------------- | ---------------------------- | --------------------------- |
| Any Quanta machine     | `block.beacon.ambient`       | Beacon ambient (low volume) |
| Any Industrial machine | `block.furnace.fire_crackle` | Fire crackling (low volume) |

### 5.2 Network Ambient

| Block            | Sound                  | Description    |
| ---------------- | ---------------------- | -------------- |
| Entangler        | `block.portal.ambient` | Portal ambient |
| Quantum Server   | `block.beacon.ambient` | Beacon ambient |
| Quantum Terminal | `block.beacon.ambient` | Beacon ambient |

---

## 📊 6. SUMMARY OF SOUNDS

| Category       | Quantity | Source  |
| -------------- | -------- | ------- |
| Machine sounds | 36       | Vanilla |
| Effect sounds  | 10       | Vanilla |
| Implant sounds | 18       | Vanilla |
| Ambient sounds | 4        | Vanilla |
| **Total**      | **68**   | Vanilla |

**Note:** all sounds are **vanilla** (reused). Custom sounds can be added later.

---

## 🔗 7. INTEGRATION

- **VISUALS.md:** visual effects (particles, animations).
- **MACHINES.md:** machine details.
- **IMPLANTS.md:** implant details.

**This document covers 100% of the sounds in the Quanta Mod.**