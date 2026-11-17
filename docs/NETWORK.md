# QUANTA MOD - ENTANGLEMENT NETWORK

> **Document based on QUANTA_MASTERLIST v10.6**
> **Last updated:** 2026-11-16
> **Scope:** Entanglement Network (Signatures, Qubits, Servers)

## 🎯 OVERVIEW

The **Entanglement Network** is Quanta Mod's automation system. Unlike AE2 (channels, cables, controller), the Quanta network is based on **quantum signatures** (Signatures) and **quantum servers** (which provide **qubits**).

**Core concept:** the network is not a mesh. It is a **set of entangled pairs**. Things with the same Signature are "the same thing," regardless of distance.

---

## 🧩 1. FUNDAMENTAL CONCEPTS

| Concept               | Definition                                                      |
| ---------------------- | -------------------------------------------------------------- |
| **Signature**          | Item that defines an entanglement group (UUID)              |
| **Entanglement Group** | Set of blocks with the same Signature                       |
| **Qubit**              | Network processing unit (not an item — a network stat) |
| **Server**             | Block that provides qubits to the network                           |
| **Decoherence**        | Server failure (crash) due to overload                       |
| **Decoherence**        | Server failure (crash) due to overload                       |

---

## 📡 2. QUBITS (PROCESSING)

The network consumes **qubits** for each operation. Qubits are provided by **Quantum Servers**.
### 2.1 Servers

| Block                     | Qubits | Slots for Servers | Tier |
| ------------------------- | ------ | ------------------ | ---- |
| **Quantum Server**        | 16     | —                  | T1   |
| **Quantum Server Rack**   | 64     | 4 Servers          | T2   |
| **Quantum Mainframe**     | 256    | 4 Racks            | T3   |
| **Quantum Supercomputer** | 1,024  | 4 Mainframes       | T4   |

**Hierarchy:** Server < Rack < Mainframe < Supercomputer. Each one aggregates the previous tier.

### 2.2 Qubit consumption per task

| Task                             | Qubits | Time    |
| ---------------------------------- | ------ | -------- |
| Pattern crafting (Nexus Assembler) | 1      | 1 tick   |
| Item import                     | 1      | 1 tick   |
| Item export                     | 1      | 1 tick   |
| Terminal sync                   | 2      | 2 ticks  |
| Monitor update                  | 1      | 1 tick   |
| Cross-dimensional transfer    | 4      | 5 ticks  |
| Endgame crafting (Singularity)        | 8      | 20 ticks |

**Rule:** without enough qubits, the operation fails (the item is not crafted; a message appears in the Terminal).

---

## 🔑 3. SIGNATURES

### 3.1 How to obtain

Craft a **Quantum Signature** (item, small crystal):

[ ] [Adamas Resonans] [ ]
[Pulvis Nexus] [Aurum Nobilis] [Pulvis Nexus]
[ ] [Adamas Resonans] [ ]

- **Output:** 1 Signature with a random UUID.
- It can be **duplicated** by crafting it with a **Blank Signature** (empty crafting recipe).


### 3.2 How to use

1. Place an **Entangler** adjacent to a machine/cable.
2. Right-click the Entangler with a Signature → it pairs.
3. Place another Entangler elsewhere.
4. Right-click it with the **same Signature** → it pairs.
5. Done: both are in the same group.

**There is no distance limit** (starting at T3 — see tiers).

### 3.3 Sealing (security)

- A Signature can be **sealed** (right-click with a Quantum Wrench + shift).
- Once sealed: **it cannot be duplicated**.
- Good for public servers (prevents signature theft).
- It can be **unsealed** with a specific key (rare item).

---

## 🖥️ 4. NETWORK BLOCKS

| Block                       | Function                              | Qubits | Minimum Tier |
| --------------------------- | ----------------------------------- | ------ | ----------- |
| **Quantum Signature**       | Item (not a block)                  | —      | T1          |
| **Entangler**               | Connects an adjacent block to a Signature | 1      | T1          |
| **Quantum Server**          | Provides 16 qubits                   | —      | T1          |
| **Quantum Server Rack**     | Aggregates 4 Servers (64 qubits)        | —      | T2          |
| **Quantum Mainframe**       | Aggregates 4 Racks (256 qubits)         | —      | T3          |
| **Quantum Supercomputer**   | Aggregates 4 Mainframes (1,024 qubits)  | —      | T4          |
| **Quantum Terminal**        | Network interface                   | 1      | T2          |
| **Quantum Drive**           | Digital storage               | —      | T2          |
| **Quantum Storage Crystal** | Storage item (T1–T4)       | —      | —           |
| **Quantum Interface**       | I/O with adjacent machines         | 1      | T2          |
| **Quantum Import Bus**      | Pulls items into the network              | 1      | T2          |
| **Quantum Export Bus**      | Sends items from the network                 | 1      | T2          |
| **Pattern Encoder**         | Encodes recipes into Patterns       | 1      | T3          |
| **Nexus Assembler**         | Executes Pattern crafts         | 1      | T3          |
| **Quantum Storage Monitor** | Shows inventory                      | 1      | T2          |
| **Quantum Energy Monitor**  | Shows Q                            | 1      | T2          |
| **Quantum Redstone Relay**  | Q ↔ Redstone                        | 1      | T1          |
| **Quantum Mode Switcher**   | Toggles Order/Chaos via redstone    | 1      | T1          |

---

## 🔄 5. PROTOCOLS (CUSTOM)

Each Signature carries a **Protocol** — a configuration that affects the entire network:

| Protocol              | Effect                                                          |
| ---------------------- | --------------------------------------------------------------- |
| **Order Protocol**     | +10% qubit cost, 0% failure, +stability                       |
| **Chaos Protocol**     | -10% qubit cost, +15% failure, +50% speed                        |
| **Balanced Protocol**  | Neutral                                                          |

**How to choose:** when crafting the Signature, or reconfigure it using a Quantum Wrench on the Signature (before sealing).

---

## ⚠️ 6. DECOHERENCE (SERVER FAILURE)

Servers can **decohere** (crash) if:

- Overload: > 90% of qubits in use for 60 consecutive seconds.
- Chaos Protocol with insufficient qubits (failed crafting attempt).
- Entity attack (specific mobs, configurable).

### 6.1 Effects of decoherence

- The server enters a **collapse state** for 30s.
- Loses **50% of its qubits** during this time.
- Red particles, static sound (client-side).
- Terminal message: `"Server X decohered — qubits: Y"`.

### 6.2 Recovery

- Right-click the server with a **Quantum Wrench** → manual reset.
- Costs 100 Q + 1 qubit.
- Cooldown: 10s between resets.

---

## 🖥️ 7. QUANTUM TERMINAL (GUI)

Tabs:

1. **Inventory** (aggregated inventory, AE2-style)
2. **Crafting** (craft queue)
3. **Connections** (connected signatures, servers, qubits)
4. **Patterns** (encoded Patterns)
5. **Logs** (decoherence events, failures)
6. **Protocol** (configure the active Protocol)

---

## 📦 8. QUANTUM DRIVE + STORAGE CRYSTALS

### 8.1 Quantum Drive

Block that holds **Quantum Storage Crystals**. Each crystal acts as a capacity module.

| Crystal                          | Capacity     | Tier |
| -------------------------------- | -------------- | ---- |
| **Quantum Storage Crystal (T1)** | 10,000 items   | T1   |
| **Quantum Storage Crystal (T2)** | 1.000.000      | T2   |
| **Quantum Storage Crystal (T3)** | 100.000.000    | T3   |
| **Quantum Storage Crystal (T4)** | 10,000.000.000 | T4   |

**Drive slots:** 10 (10 Crystals per Drive).

### 8.2 Quantum Storage Crystal (craft)

[Pulvis Densus] [Pulvis Densus] [Pulvis Densus]
[Pulvis Densus] [Adamas Resonans] [Pulvis Densus]
[Pulvis Densus] [Pulvis Densus] [Pulvis Densus]

T1 = 8 Pulvis Densus + 1 Adamas Resonans. T2+ = upgrade kit.

---

## 🔄 9. AUTO-CRAFTING (COMPLETE FLOW)

[Pattern Encoder] ──(Pattern)──► [Nexus Assembler]
│
▼
[Terminal] ──(request)──► [Nexus Assembler] ──► items returned to the network
▲
[Quantum Drive] ◄──(inventory)── [Quantum Interface] ◄─────────┘

1. The player encodes a recipe in the **Pattern Encoder** (consumes 1 Blank Silk).
2. The Pattern is inserted into the **Nexus Assembler**.
3. The **Terminal** displays available recipes.
4. The player requests 64×.
5. The system checks the **Quantum Drive** inventory; if anything is missing, it requests it from the **Nexus Assembler**.
6. The **Nexus Assembler** pulls items via the **Quantum Interface**, crafts, and returns them.
7. Consumption: 1 qubit per craft.

---

## 📊 10. NETWORK TIERS

| Tier                | Wireless range | Entanglers/group | Cross-dimensional? |
| ------------------- | ---------------- | ---------------- | ------------------ |
| **T1 (Decoherent)** | 16 blocks        | 16               | No                 |
| **T2 (Entangled)**  | 32 blocks        | 64               | No                 |
| **T3 (Superposed)** | 64 blocks        | 256              | Yes                |
| **T4 (Singular)**   | 128 blocks       | 1,024            | Yes                |

**Wireless range:** maximum distance between an Entangler and a Signature-linked device. It can be extended with **Quantum Link** (+16 blocks each, max. 3).

---

## 🔗 11. INTEGRATION

- **CABLES.md:** the network uses cables for local transport; wireless via Entanglers.
- **ASSEMBLIES.md:** the Assembly Core connects to the network via an Entangler; it uses qubits.
- **STATIONS.md:** Circuit Etcher and Fabrication Rig can be on the network (they use qubits).
- **METALLURGY.md:** Aeternum is the base material for cables and Entanglers.

---

## 🧪 12. USE CASE: LARGE BASE

[Assembly A] ──Entangler──┐
[Assembly B] ──Entangler──┼──► [Quantum Mainframe] ──► [Quantum Terminal]
[Assembly C] ──Entangler──┘ │
▼
[Quantum Drive + Storage Crystals]
│
[Quantum Interface] ──► [Machine X]

- 3 assemblies (crafting different items).
- 1 Quantum Mainframe (256 qubits).
- 1 Quantum Terminal (interface).
- 1 Quantum Drive with 10 Storage Crystals.
- 1 Quantum Interface for an external machine.

**Qubits used:** 3 (assemblies) + 1 (terminal) + 1 (interface) = 5/256.
Plenty remains. Add more assemblers to scale.

**This document covers 100% of Quanta Mod's entanglement network.**
