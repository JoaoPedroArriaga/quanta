# QUANTA MOD - SYSTEM INTERACTIONS

> **Document based on QUANTA_MASTERLIST v10.6**
> **Last updated:** 2026-11-16
> **Scope:** Interactions between Quanta Mod systems

## 🎯 OVERVIEW

This document specifies **how Quanta Mod systems interact with each other**:

- Energy flow (FE → Q → qubits).
- Item, fluid, and gas flow.
- Qubit flow through the network.
- Assembly flow (standalone and networked).
- Auto-crafting flow.
- Implant flow.
- Error states and recovery.

**Golden rule:** every system has a "3-click path" to get it working and takes "30 hours" to master.

---

## ⚡ 1. ENERGY FLOW

### 1.1 FE → Q Chain

[Combustor] ──(FE)──► [Energy Quantifier] ──(Q)──► [Quanta Cable] ──► [Quanta Machines]
(generates) (converts, slim) (transfers) (consumes)

**Rules:**

- Combustor generates FE (100 FE/t from coal).
- Energy Quantifier converts 100 FE = 1 Q (configurable rate).
- Quanta Cable transports Q (throughput by tier).
- Quanta machines consume Q (they do not accept FE directly).

**Tier 0 (Industrial) uses FE directly.** Only T1+ machines use Q.

### 1.2 Q → FE Chain (reverse)

[Quanta Cable] ──► [Quanta Collapser] ──(FE)──► [Tier 0 Machines]
(Q) (converts, slim) (generates)

**Use:** sell Q as FE to other mods (Mekanism, Thermal).

### 1.3 Energy Buffer

| Block            | Capacity         | Type    |
| ---------------- | ---------------- | ------- |
| Quanta Cell (T1) | 10.000 Q         | Bateria |
| Quanta Cell (T2) | 1.000.000 Q      | Bateria |
| Quanta Cell (T3) | 100.000.000 Q    | Bateria |
| Quanta Cell (T4) | 10.000.000.000 Q | Bateria |

**Rule:** each machine has its own buffer (10% of consumption per tick). An external battery is optional (for peaks).

### 1.4 What Happens Without Energy

| Situation      | Behavior                            |
| -------------- | ---------------------------------- |
| Machine without Q | Shuts down (gray particles)       |
| Cable without Q   | Does not transport (gray particles) |
| Assembly without Q | Beam breaks (particles fall)    |
| Network without Q | Terminal does not open (message) |

---

## 🔄 2. ITEM / FLUID / GAS FLOW

### 2.1 Item Chain

[Machine] ──(item)──► [Item Cable] ──► [Seam Anchor] ──► [Vanilla Chest]
│ (interface) (matter)
└──► [Machine] (internal network)

**Rule:** Item Cable transports items abstractly (via cable network). To connect to vanilla inventories (chest, furnace), use a **Seam Anchor** (control interface).

### 2.2 Fluid/Gas Chain

[Tank] ──(fluid)──► [Pipe] ──► [Flow Anchor] ──► [Vanilla Tank]
│ (interface) (matter)
└──► [Machine] (internal network)

**Rule:** Pipe transports fluids. Pressurized Pipe transports gases. To connect to vanilla tanks, use a **Flow Anchor**.

### 2.3 Coexistence (4 types in the same block)

- Quanta Cable + Item Cable + Pipe + Pressurized Pipe = 4 in the same block.
- Each has its own throughput.
- There are no collisions (the systems are independent).
- **Filters and configurations are on the anchors, not the cables.**

### 2.4 What Happens if the System Gets Clogged

| Situation                | Behavior                             |
| ------------------------ | ----------------------------------- |
| Cable full (buffer 100%) | Red particles, shake, then stop     |
| Machine full             | Particles reverse, craft stops      |
| Anchor full              | No more accepted (own buffer)       |

---

## 🎛️ 3. QUBITS (PROCESSING)

### 3.1 What Qubits Are

**Qubits are the network's processing unit.** They are not items. They are network stats.

- Each network operation (craft, import, export) **occupies** N qubits for M ticks.
- After M ticks, the qubits are **released** (available again).
- The total qubit pool is the sum of all connected Servers.

**Example:**

- 1 Mainframe = 256 qubits.
- 1 pattern craft = 1 qubit occupied for 10 ticks.
- 10 simultaneous crafts = 10 qubits occupied, 246 free.
- 256 simultaneous crafts = 256 qubits occupied, 0 free.
- 300 simultaneous crafts = 256 occupied, 44 queued (waiting for a qubit).

### 3.2 Server Hierarchy

| Block                 | Qubits | Slots        | Tier |
| --------------------- | ------ | ------------ | ---- |
| Quantum Server        | 16     | —            | T1   |
| Quantum Server Rack   | 64     | 4 Servers    | T2   |
| Quantum Mainframe     | 256    | 4 Racks      | T3   |
| Quantum Supercomputer | 1.024  | 4 Mainframes | T4   |

**Rule:** Server < Rack < Mainframe < Supercomputer. Each aggregates 4 of the previous tier (as items).

### 3.3 Consumption per Operation

| Operation                          | Qubits | Ticks | Throughput      |
| ---------------------------------- | ------ | ----- | --------------- |
| Pattern craft (Nexus Assembler)    | 1      | 10    | 0.1 craft/tick  |
| Item import                        | 1      | 5     | 0.2 item/tick   |
| Item export                        | 1      | 5     | 0.2 item/tick   |
| Terminal sync                      | 2      | 2     | —               |
| Monitor update                     | 1      | 1     | —               |
| Cross-dimensional transfer         | 4      | 20    | 0.2 item/tick   |
| Craft endgame (Singularity)        | 8      | 100   | 0.08 craft/tick |

### 3.4 Acceleration (3 methods, AE2-style)

**Method 1 — More Servers (horizontal parallelism):**

- Add a Server = +16 qubits.
- 1 Server = 16 simultaneous crafts.
- 10 Servers = 160 simultaneous crafts.
- **Cost:** Adamas Resonans + Quantum Processor + Casing.

**Method 2 — Quantum Accelerator (vertical upgrade):**

- New item: Quantum Accelerator (Server addon).
- 1 Accelerator = -2 ticks per craft (10 → 8).
- 4 Accelerators = -8 ticks (10 → 2).
- **Cost:** Superpositum + Adamas Resonans + Chaos Fragment.
- **Trade-off:** Accelerator consumes +20% Q.

**Method 3 — Pattern Array (co-processing):**

- Splitter in the assembly divides a craft among N Receptors.
- 1 Emitter + Splitter (2 outputs) = 2 simultaneous crafts.
- 1 Emitter + Splitter (4 outputs) = 4 simultaneous crafts.
- **Cost:** Superpositum + Pattern Crystal + Quantum Processor.
- **Trade-off:** Requires N Receptors (space).

**Summary:**

- Early game: 1 Server (16 qubits) = 16 simultaneous crafts.
- Mid game: 1 Mainframe (256 qubits) + 2 Accelerators = 256 crafts every 5 ticks.
- Endgame: 1 Supercomputer (1,024 qubits) + 4 Accelerators + Pattern Array = thousands of crafts/tick.

### 3.5 What Happens Without Qubits

| Situation          | Behavior                                                             |
| ------------------ | ------------------------------------------------------------------- |
| Operation without a qubit | Fails, Terminal message: `"Not enough qubits — 256/256 in use"` |
| Queue > 50% for 60s | Terminal warning (yellow)                                              |
| Queue > 90% for 60s | **Decoherence** (see §3.6)                                             |

### 3.6 Decoherence (server failure)

**Causes:**

- > 90% of qubits in use for 60 consecutive seconds.
- Chaos Protocol with insufficient qubits (craft attempt fails).
- Entity attack (specific mobs, configurable).

**Effects:**

- Server enters a **collapsed state** for 30s.
- Loses 50% of its qubits during this time.
- Red particles, static sound.
- Terminal message: `"Server X decohered — qubits: Y"`.

**Recovery:**

- Right-click the server with a Quantum Wrench → reset.
- Cost: 100 Q + 1 qubit.
- Cooldown: 10s between resets.

---

## ⚙️ 4. ASSEMBLIES (STANDALONE VS NETWORKED)

### 4.0 Physical Structure

[Core] [Emitter] ──(beam)──► [Modifier] ──► [Receptor]
│
└── (cable/entangler)

- **Core** sits next to the Emitter (adjacent).
- **Core** has 3 faces: Emitter (internal link), Cable (Quanta), Entangler (network).
- **Emitter** only emits a beam if a Core is adjacent.
- **Core** only works if an Emitter is adjacent.
- Breaking either one breaks the assembly.

### 4.1 Standalone Assembly

**Works without a network.** Uses Q directly from the Emitter.

[Quanta Cable] ──(Q)──► [Emitter] ──(beam)──► [Modifier] ──(beam)──► [Receptor]

**Rules:**

- Emitter consumes Q (10-1,000 Q/t, depending on tier).
- Does not use qubits.
- Craft: 1 craft/tick (fixed).
- No access to patterns, queues, etc.
- **Good for early/mid game.**

### 4.2 Networked Assembly

**Connected to the network via an Entangler.** Uses qubits.

[Quanta Cable] ──(Q)──► [Emitter] ──(beam)──► [Modifier] ──(beam)──► [Receptor]
│
▼
[Assembly Core] ──► [Entangler] ──► [Network]

**Rules:**

- Emitter consumes Q **and** qubits.
- Craft: 1 qubit occupied for 10 ticks (5 ticks with an Accelerator).
- Access to patterns, queues, and the auto-crafting queue.
- Can be controlled from the Terminal.
- **Good for mid/end game.**

### 4.3 Order/Chaos Mode

**Default:** The Assembly inherits the Core's mode.

- Core in Order → all blocks in Order.
- Core in Chaos → all blocks in Chaos.
- **Advanced mode (T4+):** each block has its own mode (for complex contraptions).

**How to change:**

- **Right-click the Core with a Wrench** (quick).
- **Core GUI** (precise, with preview).
- **Redstone on the Mode Switcher** (automation).

### 4.4 What Happens if the Core Breaks

- Assembly enters **orphaned mode** (does not work).
- Any in-progress craft is **lost** (does not drop).
- Particles fall (visual effect).
- To fix it: place a new Core.

---

## 🏭 5. AUTO-CRAFTING (COMPLETE FLOW)

### 5.1 Step-by-step Flow

- Player encodes a recipe in the [Pattern Encoder] (consumes 1 Blank Silk)
→ Generates a [Pattern Crystal]

- Pattern Crystal is inserted into the [Pattern Array] (assembly) or [Nexus Assembler] (block)
→ Craft pattern becomes available on the network

- Player opens the [Quantum Terminal] (Crafting tab)
→ Sees available recipes

- Player requests 64×
→ Terminal sends the request to the Assembler

- Assembler checks the inventory in the [Quantum Drive]
→ If available: crafts and returns the items
→ If missing: requests ingredients through the [Quantum Interface] and waits

- Assembler crafts
→ Consumption: 1 qubit per craft
→ Time: 10 ticks (5 with an Accelerator)

- Items return to the [Quantum Drive]
→ Player retrieves them from the Terminal

### 5.2 What Happens if an Ingredient Is Missing

| Situation                    | Behavior                              |
| ---------------------------- | ------------------------------------- |
| 1 item missing               | Queue pauses, yellow warning in Terminal |
| All items missing            | Queue pauses, red warning             |
| >5min without an ingredient  | Queue cancels (configurable)          |

### 5.3 What Happens if a Qubit Is Missing

| Situation          | Behavior                                                       |
| ------------------ | ------------------------------------------------------------- |
| No qubit           | Craft fails, message: `"Not enough qubits — 256/256 in use"` |
| Queue > 50% for 60s | Yellow warning                                                 |
| Queue > 90% for 60s | Decoherence (see §3.6)                                        |

---

## 🦾 6. IMPLANTS (COMPLETE FLOW)

### 6.1 Installation

- Player enters the [Cyber Chamber] (3×3×3 multiblock)
→ The chamber has 2 fluid ports (Chaos + Order)

- Places implant components in the [Controller]
→ Each implant has specific components (see IMPLANTS.md)

- Controller consumes Q + gases
→ 500-10,000 Q (per implant)
→ 100 mB Chaos Gas + 100 mB Order Gas

- Animation (5-10s)
→ Purple/cyan particles circulate around the body

- Player exits with the implant installed
→ Implant is permanent (passive)

### 6.2 What Happens if a Wall Is Broken

| Situation                        | Behavior                               |
| -------------------------------- | -------------------------------------- |
| Wall broken during installation | 50% chance of losing components       |
| Controller broken                | Installation cancels (components lost) |
| Incomplete chamber               | Controller does not accept implant     |

### 6.3 Action Wheel

**How it works:**

- Default key: `V` (configurable).
- Opens a radial wheel with all 16 installed implants.
- Each implant has an ON/OFF toggle.
- **Quantum Cortex:** always ON (gray, not clickable) — it is the hub.
- **Other 15:** clickable (ON/OFF).
- **Wheel shows all implants** (including those that are always ON), but only togglable ones are clickable.

**Interface:**

[Cortex] (gray)
│
┌───────┼───────┐
│ │ │
[Sight] [Reach] [Speed]
│ │ │
... ... ...
│ │ │
[Tunnel] [Flight] [Buffer]

### 6.4 Persistence

- Implants are saved in the player's NBT (not the item's).
- If the player dies, implants are retained (they are part of the body).
- If the player changes dimensions, implants are retained.

---

## 🔌 7. ANCHORS AND CONVERTERS

### 7.1 Why They Exist

Quanta cables transport **information**, not physical matter. To connect to vanilla inventories/tanks, an **anchor** (control interface) is required.

**Rule:** cables **never** connect directly to vanilla blocks. An anchor is always required.

### 7.2 Seam Anchor (item)

Slim block. Interface between an Item Cable and a vanilla inventory.

- **Placement:** right-click the chest with the Seam Anchor.
- **Removal:** shift-right-click with a Wrench.
- **GUI:** filters + mode (whitelist/blacklist) + priority.
- **Levels:** 1 (1 filter), 2 (8), 3 (16), 4 (unlimited).
- **Tier:** T1+.

### 7.3 Flow Anchor (fluid/gas)

Slim block. Interface between a Pipe/Pressurized Pipe and a vanilla tank.

- **Placement:** right-click the tank with the Flow Anchor.
- **Removal:** shift-right-click with a Wrench.
- **GUI:** filters + mode + priority.
- **Levels:** same as Seam.
- **Tier:** T1+.

### 7.4 Nexus Anchor (universal, T4)

Slim block. Universal interface (detects type) with GUI configuration.

- **GUI:** type checkboxes (item/fluid/gas) + filters.
- **Limitation:** if multi-cable, select which types to adapt.
- **Tier:** T4.

### 7.5 Energy Quantifier / Quanta Collapser

Slim blocks. **Convert** energy (they are not anchors).

- **Energy Quantifier:** FE → Q.
- **Quanta Collapser:** Q → FE.
- **No GUI** (conversion only).
- **Tier:** T1+.

### 7.6 Filters (Where They Are)

**Filters are on the anchor, not the cable.**

- Cable: transports (throughput by tier).
- Anchor: controls (filters, priority, type).

**Advantage:** the cable stays simple. The player configures the anchor (where the chest/tank is visible).

### 7.7 Anchor Levels

| Level | Filters                         | Cost       | How to obtain            |
| ----- | ------------------------------- | ---------- | ------------------------ |
| 1     | 1 slot (specific item/fluid)    | Cheap      | Initial craft            |
| 2     | 8 slots (tag, regex)            | Moderate   | Upgrade Kit (Entangled)  |
| 3     | 16 slots + priority             | Expensive  | Upgrade Kit (Superposed) |
| 4     | Unlimited + universal Nexus    | Very expensive | Upgrade Kit (Singular) |

Upgrade with an Upgrade Kit (as with machines).

---

## ⚠️ 8. ERROR STATES AND RECOVERY

### 8.1 Error Table

| Error                   | Cause                              | Effect                | Recovery          |
| ----------------------- | ---------------------------------- | --------------------- | ----------------- |
| No Q                    | Machine without energy             | Shuts down            | Supply Q          |
| No qubit                | Network overloaded                 | Craft fails           | Add a Server      |
| Decoherence             | >90% qubits for 60s                | Server collapses for 30s | Wrench reset  |
| Clogging                | Buffer full                        | Red particles         | Empty it          |
| Broken line of sight    | Block in the beam's path           | Beam breaks           | Remove the block  |
| Orphaned Core           | Core broken                        | Assembly does not work | Place a new Core |
| Implant failure         | Wall broken during installation    | 50% chance of losing components | Try again |

### 8.2 Visual Feedback

| State           | Particles                     |
| --------------- | ----------------------------- |
| Normal          | Type color, normal flow       |
| Saturated (>75%)| Dense, bright                  |
| Clogged (100%)  | Red, shaking                  |
| No Q            | Gray, slow                    |
| Decohered       | Erratic red particles, static |
| Broken beam     | Fall from the beam            |

---

## 📊 9. IMPLEMENTATION ORDER (BY SYSTEM)

### Phase 1 — Foundation

- FE energy (Combustor → Quantifier → Q).
- 3 Quanta machines (Quanta Infuser, Thermal Infuser, Quanta Densifier).
- 4 cables (T1).
- Codex (NBT item) + Black Board + 10-20 Equations.

### Phase 2 — Processing

- Complete Tier 1 (10 machines).
- Standalone assemblies (Emitter + Receptor + Core).
- Fabrication Rig (3 tools).
- **No qubits** (assemblies use Q directly).

### Phase 3 — Network

- Network (Entangler + Signature + Server).
- Qubits (consumption, acceleration).
- Circuit Etcher.
- Cyber Chamber (multiblock) + first implants.
- Action wheel.

### Phase 4 — Expansion

- Tier 3 (Superposed).
- Auto-crafting (Nexus Assembler + Pattern Array).
- Complete Circuit Etcher.
- Nexus Anchor (universal).

### Phase 5 — Endgame

- Tier 4 (Singular).
- Multiblocks (Graviton Stabilizer, Superposed Reactor).
- All implants.

**This document covers 100% of Quanta Mod interactions.**
