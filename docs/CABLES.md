---
canvas:
  - "[[STRUCTURE.canvas]]"
parent: "[[SYSTEM_INTERACTIONS]]"
toc-order: 1
STRUCTURE:
  - "[[SYSTEM_INTERACTIONS]]"
---
QUANTA MOD — CABLES.md (v10.6)
markdown

# QUANTA MOD - CABLES

> **Document based on QUANTA_MASTERLIST v10.6**
> **Last updated:** 2026-11-16
> **Scope:** Transport cables (Quanta, Item, Fluid, Gas) in Quanta Mod

## 🎯 OVERVIEW

Quanta Mod uses **a single cable block** that supports **4 simultaneous transfer types**. Each type occupies its own "channel" within the block — there is no collision or conflict. The player can have energy + items + fluids + gas in the same space.

The visual system uses particles orbiting the central node to indicate which types are present.

**Important:** cables transport information. Filters, priority, and type are configured on **anchors** (Seam/Flow Anchor), not on the cables.

---

## 🧩 1. TRANSFER TYPES

| Type     | Name             | Base color     | Icon |
| -------- | ---------------- | -------------- | ----- |
| `QUANTA` | Quanta Cable     | `#e6a4ff`      | Photon |
| `ITEM`   | Item Cable       | `#c67cfc`      | Cube   |
| `FLUID`  | Pipe             | Fluid color    | Drop   |
| `GAS`    | Pressurized Pipe | Gas color      | Cloud  |

**Note:** the names are **simple** (without "Quantum" at the beginning), except for Quanta Cable.

---

## ⚙️ 2. COEXISTENCE (THE KEY DIFFERENTIATOR)

A single block can have 1 to 4 types installed. Installation is done by **placing one cable of each type in the same block** (shift-click).

| Types in block | Appearance                               |
| -------------- | ----------------------------------------- |
| 1              | One ring of particles orbiting the core     |
| 2              | Two rings on different planes (XZ + XY)     |
| 3              | Three rings forming an "atom"               |
| 4              | Four rings, occupying all planes            |

### Why this is unique

- **AE2:** different cables = different blocks
- **Mekanism:** different cables = different blocks
- **Quanta:** different cables = **same block**

This is possible because `CableBlockEntity` implements **4 independent capabilities** (`IQuantaStorage`, `IItemHandler`, `IFluidHandler`, `IGasHandler`), and `CableTypeManager` controls which are active.

---

## 📊 3. THROUGHPUT BY TIER

| Tier                | Quanta Cable | Item Cable    | Pipe         | Pressurized Pipe |
| ------------------- | ------------ | ------------- | ------------ | ---------------- |
| **T1 (Decoherent)** | 5,000 Q/t    | 16 items/t    | 2,000 mB/t   | 2,000 mB/t       |
| **T2 (Entangled)**  | 25,000 Q/t   | 64 items/t    | 8,000 mB/t   | 8,000 mB/t       |
| **T3 (Superposed)** | 125,000 Q/t  | 256 items/t   | 32,000 mB/t  | 32,000 mB/t      |
| **T4 (Singular)**   | 625,000 Q/t  | 1,024 items/t | 128,000 mB/t | 128,000 mB/t     |

**Upgrade:** apply an **Upgrade Kit** to the cable (Wrench + Kit) → tier upgrade.

**Rule:** the cable has the **same name** at every tier (the Upgrade Kit changes the tier, not the name).

---

## 🔧 4. FACE CONFIGURATION

Each cable face has **1 configuration** (not 4). The configuration determines what the face does:

| Mode               | Function             |
| ------------------ | -------------------- |
| `DISABLED`         | Face closed          |
| `INSERT`           | Receives from neighbor |
| `EXTRACT`          | Sends to neighbor    |
| `INSERT + EXTRACT` | Both                 |

**Filters, priority, and type** are configured on the **anchor** (Seam/Flow/Nexus Anchor), not on the cable.

**Configuration:** right-click a face with the Wrench to cycle through modes.

---

## 🎨 5. PARTICLE SYSTEM

The current system (`CableBundleModel` + `ParticleManager`) remains as is, but is enhanced:

### 5.1 Orbits by type

Each type present in the block occupies its own **orbital radius**:

| Type   | Radius | Plane           |
| ------ | ---- | --------------- |
| Quanta | 0.15 | XZ (horizontal) |
| Item   | 0.22 | XY (vertical)   |
| Fluid  | 0.29 | YZ (vertical)   |
| Gas    | 0.36 | XZ (horizontal) |

**Result:** when 4 types coexist, you see 4 concentric rings on alternating planes. It is instantly readable.

### 5.2 Buffer state (saturation)

| Buffer          | Behavior                            |
| --------------- | ----------------------------------- |
| < 25%           | Sparse, slow, opaque particles      |
| 25–75%          | Normal flow                         |
| > 75%           | Dense, bright, pulsating            |
| 100% (blocked)  | **Stop, turn red, and shake**       |

### 5.3 Network Order/Chaos mode

| Mode      | Particles                                                    |
| --------- | ------------------------------------------------------------ |
| Order     | Perfect trajectory, no deviation                             |
| Chaos     | Sinusoidal noise in the radius, 10% chance of a "twin" (superposition) |
| Transition | Brief flash                                                 |

### 5.4 Network state

| State                         | Particles                                       |
| ----------------------------- | ----------------------------------------------- |
| Isolated (no network)          | Slow, erratic orbit (searching for a connection) |
| Connected                      | Normal orbit                                    |
| Overloaded (>90% qubits)        | Sparks fall                                     |
| Decoherent (server crashed)     | Erratic red particles, static sound            |

### 5.5 Direction indicators

`IndicatorParticleRenderer` draws on the faces:

- Insert = aqua green, inward arrow
- Extract = aqua pink, outward arrow
- Disabled = gray X

### 5.6 Sparks (new)

`SparkParticleRenderer` — particles that fall from the beam during overload (>90%).
Color: `#ff5533`.

---

## 🔌 6. CAPABILITIES

The cable exposes 4 capabilities (via `QuantaCapabilityHandler`):

```java
QuantaCapabilities.QUANTA_ENERGY  // IQuantaStorage
Capabilities.ItemHandler.BLOCK   // IItemHandler
Capabilities.FluidHandler.BLOCK  // IFluidHandler
QuantaCapabilities.QUANTA_GAS    // IGasHandler
```

**Important:** the cable is transparent — the capability exists, but the data comes from `CableNetwork` (via `NetQuanta`, `NetItems`, etc.). This allows any neighbor to interact with the cable as if it were a tank/chest/solid energy source.

## 🕸️ 7. NETWORK

The cable **is not the network**. The cable is the physical transport. The network (entanglement) is defined by **Signatures** (see NETWORK.md).

- Cable alone = local transport (works without a network)
- Cable + Entangler = transport + wireless bridge
- Cable + Assembly Core = local assembly
- Cable + Entangler + Assembly Core = networked assembly

Without cables, the network works 100% wirelessly (via entanglers). Cables are a "cost optimization" — cheaper than entanglers for short-range connections.

## 📋 8. RECIPES

### 8.1 Quanta Cable (Decoherent)

**Craft (3 slots):**

[Ferrum Conducens Sheet] [Cuprum Transmissor] [Ferrum Conducens Sheet]

**Output:** 4 Quanta Cable

### 8.2 Item Cable (Decoherent)

**Craft (3 slots):**

[Ferrum Conducens Sheet] [Quantum Circuit] [Ferrum Conducens Sheet]

**Output:** 4 Item Cable

### 8.3 Pipe (Decoherent)

**Craft (3 slots):**

[Ferrum Conducens Sheet] [Vitrum Perspicuum] [Ferrum Conducens Sheet]

**Output:** 4 Pipe

## 8.4 Pressurized Pipe (Decoherent)

**Craft (3 slots):**

[Ferrum Conducens Sheet] [Adamas Resonans] [Ferrum Conducens Sheet]

**Output:** 4 Pressurized Pipe

**Upgrade to tiers 2/3/4:** apply an **Upgrade Kit** (Entangled/Superposed/Singular).

## 🎯 9. ANCHORS (NOT CABLE CONFIGURATION)

To connect cables to inventories/tanks, use anchors:

| Anchor       | Connects                 | Tier |
| ------------ | ------------------------ | ---- |
| Seam Anchor  | Item Cable → chest       | T1+  |
| Flow Anchor  | Pipe → tank              | T1+  |
| Nexus Anchor | Universal (detects type) | T4   |

Filters and priority are configured on the anchor, not on the cable. See SYSTEM_INTERACTIONS.md §7.

## ⚠️ 10. LIMITATIONS

- The cable does not store — it transfers. The buffer is minimal (per tick).
- The cable has no filters — filters are configured on the anchor.
- The cable does not route intelligently — it follows the network.
- The cable is not wireless — it needs a physical connection or an Entangler.

## 🔗 11. INTEGRATION

- **NETWORK.md:** cables connect entanglers and assemblies.
- **ASSEMBLIES.md:** the Assembly Core uses cables to connect to the network.
- **STATIONS.md:** stations have cable ports.
- **METALLURGY.md:** Ferrum Conducens Wire is the basis for all cables.
- **ANCHORS: filters** and priority are configured on anchors, not cables.

**This document covers 100% of the cables in Quanta Mod.**
