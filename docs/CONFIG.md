# QUANTA MOD - CONFIG

> **Document based on QUANTA_MASTERLIST v10.6**
> **Last updated:** 2026-11-16
> **Scope:** Configuration options for the Quanta Mod

## 🎯 OVERVIEW

The Quanta Mod has **configurable options** for energy, machines, difficulty, and worldgen. These options can be changed in the config file (`config/quanta-common.toml`).

**Philosophy:** the mod is **configurable** so players can adjust the experience (easier, harder, or balanced).

---

## ⚡ 1. ENERGY CONFIG

### 1.1 Conversion Rate

| Option       | Default | Range    | Description                 |
| ------------ | ------- | -------- | --------------------------- |
| `quantaToFe` | 100     | 1-10.000 | How many FE = 1 Q           |
| `feToQuanta` | 100     | 1-10.000 | How many FE = 1 Q (reverse) |

### 1.2 Buffer Sizes

| Option                    | Default | Range    | Description                    |
| ------------------------- | ------- | -------- | ------------------------------ |
| `machineBufferMultiplier` | 1.0     | 0.1-10.0 | Multiplier for machine buffers |
| `cableBufferMultiplier`   | 1.0     | 0.1-10.0 | Multiplier for cable buffers   |

---

## 🏭 2. MACHINE CONFIG

### 2.1 Speed

| Option                   | Default | Range    | Description                  |
| ------------------------ | ------- | -------- | ---------------------------- |
| `machineSpeedMultiplier` | 1.0     | 0.1-10.0 | Multiplier for machine speed |
| `upgradeKitSpeedBonus`   | 1.5     | 1.0-3.0  | Speed bonus per Upgrade Kit  |

### 2.2 Energy Consumption

| Option                    | Default | Range    | Description                               |
| ------------------------- | ------- | -------- | ----------------------------------------- |
| `machineEnergyMultiplier` | 1.0     | 0.1-10.0 | Multiplier for machine energy consumption |
| `addonEnergyMultiplier`   | 1.0     | 0.1-10.0 | Multiplier for addon energy consumption   |

### 2.3 Failure Chance

| Option                       | Default | Range   | Description                           |
| ---------------------------- | ------- | ------- | ------------------------------------- |
| `chaosFailureChance`         | 0.15    | 0.0-1.0 | Failure chance in Chaos mode          |
| `stabilizerFailureReduction` | 0.05    | 0.0-0.5 | Failure reduction per Stabilizer Coil |

---

## ⚠️ 3. DIFFICULTY CONFIG

### 3.1 Decoherence

| Option                 | Default | Range      | Description                           |
| ---------------------- | ------- | ---------- | ------------------------------------- |
| `decoherenceEnabled`   | true    | true/false | Enable/disable decoherence            |
| `decoherenceThreshold` | 0.9     | 0.5-1.0    | Qubit usage threshold for decoherence |
| `decoherenceDuration`  | 30      | 5-120      | Decoherence duration (seconds)        |
| `decoherenceQubitLoss` | 0.5     | 0.0-1.0    | Qubit loss during decoherence         |

### 3.2 Implants

| Option                    | Default | Range     | Description                                   |
| ------------------------- | ------- | --------- | --------------------------------------------- |
| `implantInstallationTime` | 100     | 20-600    | Installation time (ticks)                     |
| `implantFailureChance`    | 0.5     | 0.0-1.0   | Chance of losing components if wall is broken |
| `implantMaxCount`         | -1      | -1 to 100 | Max implants per player (-1 = unlimited)      |

---

## 🌍 4. WORLDGEN CONFIG (when implemented)

### 4.1 Ore Generation

| Option           | Default | Range      | Description         |
| ---------------- | ------- | ---------- | ------------------- |
| `generateTin`    | true    | true/false | Generate Tin Ore    |
| `generateZinc`   | true    | true/false | Generate Zinc Ore   |
| `generateNickel` | true    | true/false | Generate Nickel Ore |
| `generateSilver` | true    | true/false | Generate Silver Ore |

### 4.2 Ore Rarity

| Option              | Default | Range | Description          |
| ------------------- | ------- | ----- | -------------------- |
| `tinVeinsPerChunk`  | 8       | 0-20  | Tin veins per chunk  |
| `tinVeinSize`       | 8       | 1-20  | Tin vein size        |
| `zincVeinsPerChunk` | 6       | 0-20  | Zinc veins per chunk |
| `zincVeinSize`      | 6       | 1-20  | Zinc vein size       |

---

## 🔌 5. COMPATIBILITY CONFIG

### 5.1 Mod Integration

| Option                      | Default | Range      | Description                 |
| --------------------------- | ------- | ---------- | --------------------------- |
| `enableAe2Integration`      | true    | true/false | Enable AE2 integration      |
| `enableMekanismIntegration` | true    | true/false | Enable Mekanism integration |
| `enableCreateIntegration`   | true    | true/false | Enable Create integration   |
| `enableThermalIntegration`  | true    | true/false | Enable Thermal integration  |

### 5.2 Tags

| Option             | Default | Range      | Description                    |
| ------------------ | ------- | ---------- | ------------------------------ |
| `useOreDictionary` | true    | true/false | Use ore dictionary for recipes |
| `useFluidTags`     | true    | true/false | Use fluid tags for recipes     |

---

## 📊 6. SUMMARY OF CONFIG OPTIONS

| Category          | Options | Default                                  |
| ----------------- | ------- | ---------------------------------------- |
| **Energy**        | 4       | 1 Q = 100 FE                             |
| **Machines**      | 7       | 1.0× speed, 1.0× energy                  |
| **Difficulty**    | 7       | Decoherence enabled, 50% implant failure |
| **Worldgen**      | 8       | All ores enabled                         |
| **Compatibility** | 6       | All integrations enabled                 |

**Total:** ~32 config options.

---

## 🔗 7. INTEGRATION

- **QUANTA_MASTERLIST.md:** overview of the mod.
- **SYSTEM_INTERACTIONS.md:** how systems interact.
- **COMPATIBILITY.md:** mod compatibility details.

**This document covers 100% of the config options in the Quanta Mod.**