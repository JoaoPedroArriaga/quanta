---
canvas:
  - "[[STRUCTURE.canvas]]"
parent: "[[QUANTA_MASTERLIST]]"
toc-order: 8
STRUCTURE:
  - "[[SYSTEM_INTERACTIONS]]"
---
# QUANTA MOD - COMPATIBILITY

> **Document based on QUANTA_MASTERLIST v10.3**
> **Last updated:** 2026-11-14
> **Status:** Planned (to be implemented)

## 🎯 OVERVIEW

This document specifies **how Quanta Mod interacts with other mods**:

- Supported mods (list + priority)
- How to integrate (capabilities, APIs)
- Anchors by mod
- What will NOT be included
- Compatibility roadmap

**Philosophy:** **optional** compatibility (the mod works on its own) + **integrated** compatibility (when another mod is present).

**Related systems:** [[SYSTEM_INTERACTIONS]] · [[MACHINES]] · [[CABLES]] · [[NETWORK]]

## 📋 1. SUPPORTED MODS (priority)

### Priority 1 — Essential (without them, the mod is unplayable)

| Mod                      | Function         | How to integrate  |
| ------------------------ | ---------------- | ----------------- |
| **JEI/REI**              | Recipe viewer    | Recipe plugin     |
| **The One Probe / Jade** | Machine info     | Data provider     |

**Why:**

- **JEI/REI:** without a recipe viewer, players cannot discover the recipes.
- **TOP/Jade:** without information, players do not know what the machine is doing.

### Priority 2 — Important (complete experience)

| Mod                       | Function          | How to integrate                                   |
| ------------------------- | ----------------- | ------------------------------------------------- |
| **Curios API**            | Dedicated slots   | **NOT used** (implants are passive, not slots)     |
| **KubeJS / CraftTweaker** | Datapacks         | Recipe API                                         |
| **AE2**                   | Automation network | Anchors (Seam/Flow) + Interface                   |
| **Mekanism**              | Energy + gases    | Energy Quantifier + Flow Anchor                   |

**Why:**

- **KubeJS:** allows recipes to be customized via datapacks.
- **AE2:** AE2 players can use Quanta as a "parallel network."
- **Mekanism:** Q ↔ FE conversion is built in (Energy Quantifier).

### Priority 3 — Desirable (deep integration)

| Mod             | Function           | How to integrate                 |
| --------------- | ------------------ | ------------------------------- |
| **Create**      | Mechanical automation | Anchors (Seam) + belts       |
| **Thermal**     | Energy + fluids    | Energy Quantifier + Flow Anchor |
| **XNet**        | Automation cables  | Seam Anchor (item)              |
| **Botania**     | Magic              | None (different systems)        |
| **Ars Nouveau** | Magic              | Codex (similar)                 |

### Priority 4 — Optional (nice to have)

| Mod                       | Function  | How to integrate           |
| ------------------------- | --------- | -------------------------- |
| **Draconic Evolution**    | Endgame   | Energy Quantifier (Q ↔ FE) |
| **Immersive Engineering** | Energy    | Energy Quantifier (FE)     |
| **Powah**                 | Energy    | Energy Quantifier (FE)     |
| **Refined Storage**       | Automation | Seam Anchor (item)        |

## 🔌 2. ANCHORS BY MOD

### 2.1 AE2

**Problem:** AE2 uses cables and channels. Quanta uses information.

**Solution:** **Seam Anchor** (item) + **Flow Anchor** (fluid).
[Quanta Cable] ──► [Seam Anchor] ──► [AE2 ME Interface] ──► [AE2 Network]

- Seam Anchor translates abstract item ↔ physical item (for the ME Interface)
- Flow Anchor translates abstract fluid ↔ physical fluid
- **There is no network integration** (they are parallel systems)
- **Related:** [[CABLES]] · [[NETWORK]] · [[MACHINES]]

### 2.2 Mekanism

**Problem:** Mekanism uses FE and gases.

**Solution:** **Energy Quantifier** (FE) + **Flow Anchor** (gases).
[Mekanism Generator] ──(FE)──► [Energy Quantifier] ──(Q)──► [Quanta Cable]
[Mekanism Gas Tank] ──(gas)──► [Flow Anchor] ──(gas)──► [Quantum Pipe]

- Energy Quantifier converts FE → Q
- Flow Anchor translates abstract gas ↔ physical gas
- **Q can be converted back** via Quanta Collapser
- **Related:** [[MACHINES]] · [[CABLES]]

### 2.3 Create

**Problem:** Create uses belts and shafts.

**Solution:** **Seam Anchor** (item) + **Flow Anchor** (fluid).
[Create Belt] ──► [Seam Anchor] ──► [Quantum Item Cable]
[Create Fluid Tank] ──► [Flow Anchor] ──► [Quantum Pipe]

- Seam Anchor translates physical item ↔ abstract item
- **There is no rotational integration** (Create is mechanical, Quanta is quantum)
- **Related:** [[CABLES]] · [[MACHINES]]

### 2.4 Thermal

**Problem:** Thermal uses RF (Redstone Flux).

**Solution:** **Energy Quantifier** (RF) + **Flow Anchor** (fluids).

- Energy Quantifier accepts RF (100 RF = 1 Q)
- Flow Anchor translates abstract fluid ↔ physical fluid
- **Related:** [[MACHINES]] · [[CABLES]]

### 2.5 XNet

**Problem:** XNet uses automation cables.

**Solution:** **Seam Anchor** (item).
[XNet Cable] ──► [Seam Anchor] ──► [Quantum Item Cable]

- Seam Anchor translates physical item ↔ abstract item
- **There is no channel integration** (XNet uses channels; Quanta uses a qubit pool)
- **Related:** [[CABLES]] · [[NETWORK]]

### 2.6 Ars Nouveau

**Problem:** Ars Nouveau uses magic (mana).

**Solution:** **None** (different systems).

- Ars Nouveau: mana, glifos, rituais
- Quanta: Q, Equations, assemblies
- **They can coexist** but do not integrate
- **Related:** [[EQUATIONS]] · [[ASSEMBLIES]]

### 2.7 Curios API

**Problem:** Curios API adds accessory slots.

**Solution:** **NOT used**.

- Implants are **passive** (they do not occupy a slot)
- Action wheel (`V`) to toggle
- There is no dedicated slot (unlike Oritech)
- **Related:** [[IMPLANTS]]

## 📊 3. EXPOSED CAPABILITIES

### 3.1 What Quanta exposes

| Capability       | What it does   | Can other mods read it? |
| ---------------- | -------------- | ---------------------- |
| `IQuantaStorage` | Q energy       | ✅ Yes (via NeoForge)  |
| `IItemHandler`   | Items          | ✅ Yes (standard)      |
| `IFluidHandler`  | Fluids         | ✅ Yes (standard)      |
| `IGasHandler`    | Gases (custom) | ⚠️ Only if mod knows it |
| `INetworkNode`   | Network        | ❌ No (internal)       |

### 3.2 What Quanta reads from other mods

| Capability                         | What it does   | How it reads it      |
| ---------------------------------- | -------------- | -------------------- |
| `Capabilities.EnergyStorage.BLOCK` | FE             | ✅ Energy Quantifier |
| `Capabilities.ItemHandler.BLOCK`   | Items          | ✅ Seam Anchor       |
| `Capabilities.FluidHandler.BLOCK`  | Fluids         | ✅ Flow Anchor       |
| `IGasHandler`                      | Gases (custom) | ⚠️ Only if mod exposes it |

## 🚫 4. WHAT WILL NOT BE INCLUDED

| Mod                | Why                                     |
| ------------------ | --------------------------------------- |
| **Curios API**     | Implants are passive (not slots)        |
| **Botania**        | Different systems (mana vs Q)           |
| **Thaumcraft**     | Discontinued mod (1.12)                 |
| **Blood Magic**    | Different systems (blood vs Q)          |
| **Astral Sorcery** | Different systems (constellations vs Q) |

## 🗺️ 5. COMPATIBILITY ROADMAP

### Phase 1 (MVP)

- JEI/REI (essential)
- The One Probe/Jade (essential)

### Phase 2

- KubeJS / CraftTweaker (datapacks)

### Phase 3

- AE2 (Seam + Flow Anchor)
- Mekanism (Energy Quantifier + Flow Anchor)

### Phase 4

- Create (Seam Anchor)
- Thermal (Energy Quantifier + Flow Anchor)
- XNet (Seam Anchor)

### Phase 5

- Refined Storage (Seam Anchor)
- Immersive Engineering (Energy Quantifier)
- Powah (Energy Quantifier)
- Draconic Evolution (Energy Quantifier)

## 🔧 6. HOW TO INTEGRATE (for developers)

### 6.1 Exposing Q to other mods

```java
// Block that accepts Q
public class MyMachine extends BlockEntity {
    public IQuantaStorage getQuantaStorage(Direction side) {
        return new IQuantaStorage() {
            @Override public int getQuantaStored() { return this.stored; }
            @Override public int getQuantaCapacity() { return this.capacity; }
            @Override public int addQuanta(int amount) { ... }
            @Override public int extractQuanta(int amount, boolean allowTunnel, boolean simulate) { ... }
            // ...
        };
    }
}
```

### 6.2 Reading FE from other mods

```java
// Block that consumes FE (e.g., Energy Quantifier)
IEnergyStorage feStorage = level.getCapability(
    Capabilities.EnergyStorage.BLOCK,
    neighborPos,
    side.getOpposite()
);
if (feStorage != null) {
    int feReceived = feStorage.receiveEnergy(amount, false);
    // Convert FE → Q
}
```

### 6.3 Adapting items/fluids

```java
// Seam Anchor: extracts from a vanilla inventory
IItemHandler vanilla = level.getCapability(
    Capabilities.ItemHandler.BLOCK,
    neighborPos,
    side.getOpposite()
);
if (vanilla != null) {
    ItemStack extracted = vanilla.extractItem(slot, amount, false);
    // Send to Quanta Item Cable
}
```

## 7. COMPATIBILITY CHECKLIST

Before release (Phase 1)
□ JEI/REI works
□ The One Probe/Jade works
□ Recipes appear in JEI

Phase 2
□ KubeJS reads recipes
□ CraftTweaker modifies recipes

Phase 3
□ AE2: Seam Anchor connects
□ AE2: Flow Anchor connects
□ Mekanism: Energy Quantifier works
□ Mekanism: Flow Anchor works

Phase 4
□ Create: Seam Anchor connects to belts
□ Thermal: Energy Quantifier works
□ XNet: Seam Anchor connects

Phase 5
□ Refined Storage: Seam Anchor connects
□ Immersive Engineering: Energy Quantifier works
□ Powah: Energy Quantifier works
□ Draconic Evolution: Energy Quantifier works

## 8. FINAL NOTES

- Compatibility is optional: the mod works 100% on its own.

- Integration is gradual: Phase 1 includes only JEI/TOP; Phase 5 includes everything.

- There are no mandatory dependencies: no mod is required (except JEI/TOP for usability).

- Developer documentation: this document serves as a contract for anyone who wants to integrate.

**This document covers 100% of the planned compatibility.**
