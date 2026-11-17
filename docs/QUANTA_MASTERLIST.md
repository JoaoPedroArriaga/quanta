---
canvas:
  - "[[STRUCTURE.canvas]]"
child:
  - "[[LORE]]"
  - "[[NOMENCLATURE]]"
  - "[[MACHINES]]"
  - "[[SYSTEM_INTERACTIONS]]"
  - "[[IMPLANTS]]"
  - "[[ARMOR]]"
  - "[[EQUATIONS]]"
  - "[[COMPATIBILITY]]"
  - "[[VISUALS]]"
  - "[[TOOLS]]"
  - "[[METALLURGY]]"
  - "[[ITEM_CATALOG]]"
STRUCTURE:
  - "[[NOMENCLATURE]]"
---
# QUANTA MOD - OFFICIAL MASTERLIST (v10.1)

> **Consolidated Quanta mod document for Minecraft NeoForge 1.21.1**  
> **Last updated:** 2026-11-13

### Reading paths

- **Start with the world and terms:** [[LORE]] → [[NOMENCLATURE]].
- **Understand the systems:** [[MACHINES]] and [[SYSTEM_INTERACTIONS]], then [[CABLES]], [[NETWORK]], and [[CONFIG]].
- **Follow machine progression:** [[RECIPES_TIER0]] → [[RECIPES_TIER1]] → [[RECIPES_TIER2]] → [[RECIPES_TIER3]] → [[RECIPES_TIER4]].
- **Look up materials and objects:** [[ITEM_CATALOG]], [[METALLURGY]], and [[TOOLS]].
- **Explore integrations and presentation:** [[COMPATIBILITY]] and [[VISUALS]].

---

## 🎯 CORE CONCEPT

A techno-magical mod where quantum mechanics and arcane magic are the same thing viewed from different angles. **Quanta** is a particle that alters information — it can rewrite reality in pink `#e6a4ff`.

**Inspirations:** Mekanism, Oritech, Create, EnderIO, Ars Nouveau, AE2, Modular Powersuits, Draconic Evolution, Thaumcraft.

**Core mechanic:** *Chaos / Order* — switching machine modes (risk vs. reward).

| Mode      | Characteristic                    | Color     | Failure Chance | Bonus                                           |
| :-------- | :-------------------------------- | :-------- | :------------- | :---------------------------------------------- |
| **Order** | Stable, predictable (Decoherence) | `#3ff9f9` | 0%             | Maximum stability                               |
| **Chaos** | Unstable, superposition           | `#c67cfc` | +15% to +25%   | +50% speed/generation, doubles generator output |

---

## 📊 TIER PROGRESSION

| Tier         | Name       | Palette                                               | Characteristic               |
| :----------- | :--------- | :---------------------------------------------------- | :--------------------------- |
| **0**        | Industrial | `#434343`, `#1f1e1e`, `#e75605`, `#a53e05`, `#351402` | FE-based, pre-Quanta         |
| **1**        | Decoherent | `#3c7d7d`, `#09000c`, `#ac01e4`, `#720098`, `#410156` | Stable, predictable          |
| **2**        | Entangled  | `#023436`, `#09000c`, `#c67cfc`, `#590199`, `#200136` | Entangled, connected         |
| **3**        | Superposed | `#011e1f`, `#09000c`, `#dd87fd`, `#3d0168`, `#150123` | Multiple simultaneous states |
| **Ultimate** | Singular   | `#b1fafa`, `#7df8f8`, `#ffffff`, `#e6a4ff`, `#c67cfc` | Absolute equilibrium point   |

* **Parallel progression:** The player can skip tiers if they have enough resources.
* All Quanta machinery uses the tier system and can be upgraded with **Upgrade Kits**.

---

## ⚡ 1. ENERGY SYSTEM

* **Unit:** Quanta (Q)
* **Base conversion:** $1\text{ Quanta} = 100\text{ FE}$ (configurable)
* Quanta machines **DO NOT** accept FE directly.
* Industrial Tier (Tier 0) machines use FE directly.

### FE ↔ Quanta Converters

| Machine               | Input  | Output | Rate                                   |
| :-------------------- | :----- | :----- | :------------------------------------- |
| **Energy Quantifier** | FE     | Quanta | $100\text{ FE} \rightarrow 1\text{ Q}$ |
| **Quanta Collapser**  | Quanta | FE     | $1\text{ Q} \rightarrow 100\text{ FE}$ |

### Quanta Generation

| Generator              | Source                   | Rate                              |
| :--------------------- | :----------------------- | :-------------------------------- |
| **Combustor**          | Coal, Lava, Blaze        | $100\text{ FE/t}$                 |
| **Essence Collector**  | Collects ambient Quanta  | $5\text{ Q/t}$                    |
| **Quanta Engine**      | Liquid Quanta/Chaos      | $50\text{-}250\text{ Q/t}$        |
| **Gas Burner**         | Quanta Gas/Chaos Gas     | $50\text{-}250\text{ Q/t}$        |
| **Void Generator**     | Vacuum fluctuations      | $500\text{-}1.000\text{ Q/t}$     |
| **Superposed Reactor** | Dimensional entanglement | $2.000\text{-}10.000+\text{ Q/t}$ |

#### Void Generator — by Location

| Location            | Generation (Q/t) |
| :------------------ | :--------------- |
| Overworld surface   | 500              |
| Bedrock (Overworld) | 650              |
| Nether              | 600              |
| End (ilhas)         | 800              |
| End (void)          | 1.000            |

### Quanta Storage

| Battery                    | Storage          | Use                     |
| :------------------------- | :--------------- | :---------------------- |
| **Quanta Cell (T1)**       | 10.000 Q         | Quanta battery block    |
| **Quanta Cell (T2)**       | 1.000.000 Q      | Quanta battery block    |
| **Quanta Cell (T3)**       | 100.000.000 Q    | Quanta battery block    |
| **Quanta Cell (T4)**       | 10.000.000.000 Q | Quanta battery block    |
| **Entangled Battery (T1)** | 10.000 Q         | Personal Quanta battery |
| **Entangled Battery (T2)** | 1.000.000 Q      | Personal Quanta battery |
| **Entangled Battery (T3)** | 100.000.000 Q    | Personal Quanta battery |
| **Entangled Battery (T4)** | 10.000.000.000 Q | Personal Quanta battery |

> **Note:** The network storage item was renamed to **Quantum Storage Crystal**. The battery block remains **Quanta Cell**.

---

## 💧 2. QUANTUM LIQUIDS AND GASES

### Liquids — Colors and Properties

| Liquid                 | Obtained From                                                                                                        | Special Property                            |
| :--------------------- | :------------------------------------------------------------------------------------------------------------------- | :------------------------------------------ |
| **Liquid Quanta**      | Quantum Dust + Water                                                                                                 | Base, neutral                               |
| **Liquid Chaos**       | Essence of Chaos + Lava (50 mB per 1 Essence of Chaos) or Quantum Liquid Infuser (Liquid Quanta + Essence of Chaos)  | Bucket breaks after 30s, doubles generation |
| **Liquid Order**       | Essence of Order + Water (50 mB per 1 Essence of Order) or Quantum Liquid Infuser (Liquid Quanta + Essence of Order) | Efficient cooling                           |
| **Stabilized Liquid**  | Liquid Order + Order Fragment                                                                                        | Premium cooling                             |
| **Entangled Liquid**   | Liquid Chaos + Entangled Core                                                                                        | Bucket teleports, cross-dimensional         |
| **Liquid Singularity** | Stabilized + Entangled + Nether Star                                                                                 | Creates temporary singularity               |

> **Note:** Dangerous liquids such as *Liquid Chaos*, *Entangled Liquid*, and *Liquid Singularity* require special tanks for storage and transport.

### Gases (evaporated from liquids)

* Quanta Gas, Chaos Gas, Order Gas, Entangled Gas, Stabilized Gas, Singularity Gas.

### Tanks

#### Standard Tanks

| Tank                              | Capacity     | Type   |
| :-------------------------------- | :----------- | :----- |
| **Quantum Tank (Decoherent)**     | 16.000 mB    | Liquid |
| **Quantum Tank (Entangled)**      | 64.000 mB    | Liquid |
| **Quantum Tank (Superposed)**     | 256.000 mB   | Liquid |
| **Quantum Tank (Singular)**       | 1.024.000 mB | Liquid |
| **Pressurized Tank (Decoherent)** | 16.000 mB    | Gas    |
| **Pressurized Tank (Entangled)**  | 64.000 mB    | Gas    |
| **Pressurized Tank (Superposed)** | 256.000 mB   | Gas    |
| **Pressurized Tank (Singular)**   | 1.024.000 mB | Gas    |

#### Special Tanks

| Tank                                          | Capacity     | Type              |
| :-------------------------------------------- | :----------- | :---------------- |
| **Containment Tank (Decoherent)**             | 16.000 mB    | Dangerous liquids |
| **Containment Tank (Entangled)**              | 64.000 mB    | Dangerous liquids |
| **Containment Tank (Superposed)**             | 256.000 mB   | Dangerous liquids |
| **Containment Tank (Singular)**               | 1.024.000 mB | Dangerous liquids |
| **Pressurized Containment Tank (Decoherent)** | 16.000 mB    | Dangerous gases   |
| **Pressurized Containment Tank (Entangled)**  | 64.000 mB    | Dangerous gases   |
| **Pressurized Containment Tank (Superposed)** | 256.000 mB   | Dangerous gases   |
| **Pressurized Containment Tank (Singular)**   | 1.024.000 mB | Dangerous gases   |

---

## 🔌 3. CABLES (CONDUITS)

> *Full reference: `CABLES.md`*

The Quanta Mod uses a single cable block that supports 4 simultaneous transfer types. Each type occupies its own "channel" within the block — no collisions, no conflicts.

### Cable Types

| Type       | Name                     | Base color  | Throughput (Decoherent → Singular)       |
| :--------- | :----------------------- | :---------- | :--------------------------------------- |
| **QUANTA** | Quanta Cable             | `#e6a4ff`   | $1.000 \rightarrow 1.000.000\text{ Q/t}$ |
| **ITEM**   | Quantum Item Cable       | `#c67cfc`   | $4 \rightarrow 256\text{ items/t}$       |
| **FLUID**  | Quantum Pipe             | Fluid color | $500 \rightarrow 32.000\text{ mB/t}$     |
| **GAS**    | Quantum Pressurized Pipe | Gas color   | $500 \rightarrow 32.000\text{ mB/t}$     |

### Coexistence

* 1 to 4 types in the same block.
* **Installation:** shift-click with a different cable type.
* **Visual:** concentric orbital rings (1 per type).

### Per-face Configuration

Each face has 4 independent configurations (one per type):

| Mode          | Function                                |
| :------------ | :-------------------------------------- |
| **DISABLED**  | Face closed                             |
| **INSERT**    | Receives from neighbor                  |
| **EXTRACT**   | Sends to neighbor                       |
| **WHITELIST** | Passes only if it matches filter        |
| **BLACKLIST** | Passes only if it does NOT match filter |

### Filters

* **Item:** specific item, tag, regex
* **Fluid:** specific fluid, tag
* **Gas:** specific gas, tag
* **Quanta:** minimum/maximum value

### Upgrade

Apply Upgrade Kit to the cable (`Wrench + Kit`) $\rightarrow$ tier upgrade.

### Throughput by Tier

| Tier           | Quanta (Q/t) | Item (items/t) | Fluid (mB/t) | Gas (mB/t) |
| :------------- | :----------- | :------------- | :----------- | :--------- |
| **Decoherent** | 1.000        | 4              | 500          | 500        |
| **Entangled**  | 10.000       | 16             | 2.000        | 2.000      |
| **Superposed** | 100.000      | 64             | 8.000        | 8.000      |
| **Singular**   | 1.000.000    | 256            | 32.000       | 32.000     |

---

## 🔧 4. NETWORK MACHINES (Entanglement Network)

> *Full reference: `NETWORK.md`*

### Fundamental Concepts

| Concept                | Definition                                               |
| :--------------------- | :------------------------------------------------------- |
| **Signature**          | Item that defines an entanglement group (UUID)           |
| **Entanglement Group** | Set of blocks with the same Signature                    |
| **Qubit**              | Network processing unit (not an item — a network stat)   |
| **Server**             | Block that provides qubits to the network                |
| **Protocol**           | Signature configuration (Order/Chaos/Balanced/Entangled) |
| **Decoherence**        | Server failure (crash) due to overload                   |

### Servers (Qubits)

| Block / Item              | Qubits | Slots        | Tier       |
| :------------------------ | :----- | :----------- | :--------- |
| **Quantum Server**        | 16     | —            | Decoherent |
| **Quantum Server Rack**   | 64     | 4 Servers    | Entangled  |
| **Quantum Mainframe**     | 256    | 4 Racks      | Superposed |
| **Quantum Supercomputer** | 1.024  | 4 Mainframes | Singular   |

> **Note:** **Quantum Server Rack**, **Quantum Mainframe**, and **Quantum Supercomputer** are network components in item form, crafted empty and filled with 4 items from the previous tier. They do not function as independent infrastructure blocks in the world.

### Qubit Consumption per Task

| Task                            | Qubits | Time     |
| :------------------------------ | :----- | :------- |
| Pattern craft (Auto-Fabricator) | 1      | 1 tick   |
| Item import                     | 1      | 1 tick   |
| Item export                     | 1      | 1 tick   |
| Terminal sync                   | 2      | 2 ticks  |
| Monitor update                  | 1      | 1 tick   |
| Cross-dimensional transfer      | 4      | 5 ticks  |
| Endgame craft (Singularity)     | 8      | 20 ticks |

### Network Components

| Block                       | Function                                  | Qubits | Minimum tier |
| :-------------------------- | :---------------------------------------- | :----- | :----------- |
| **Entangler**               | Connects an adjacent block to a Signature | 1      | Decoherent   |
| **Quantum Server**          | Provides 16 qubits                        | —      | Decoherent   |
| **Quantum Server Rack**     | Aggregates 4 Servers                      | —      | Entangled    |
| **Quantum Mainframe**       | Aggregates 4 Racks                        | —      | Superposed   |
| **Quantum Supercomputer**   | Aggregates 4 Mainframes                   | —      | Singular     |
| **Quantum Terminal**        | Network interface                         | 1      | Entangled    |
| **Quantum Drive**           | Digital storage (10 Cells)                | —      | Entangled    |
| **Quantum Storage Crystal** | Storage item (T1-T4)                      | —      | —            |
| **Quantum Interface**       | I/O with adjacent machines                | 1      | Entangled    |
| **Quantum Import Bus**      | Pulls items into the network              | 1      | Entangled    |
| **Quantum Export Bus**      | Sends items from the network              | 1      | Entangled    |
| **Quantum Pattern Bench**   | Encodes recipes in Pattern Crystals       | 1      | Superposed   |
| **Quantum Auto-Fabricator** | Executes pattern crafts                   | 1      | Superposed   |
| **Quantum Storage Monitor** | Displays inventory                        | 1      | Entangled    |
| **Quantum Energy Monitor**  | Displays Q                                | 1      | Entangled    |
| **Quantum Redstone Relay**  | Q ↔ Redstone                              | 1      | Decoherent   |
| **Quantum Mode Switcher**   | Switches Order/Chaos via redstone         | 1      | Decoherent   |
| **Quantum Link**            | Wireless range extender (+16 blocks)      | —      | Entangled    |

### Protocols

| Protocol               | Effect                                                              |
| :--------------------- | :------------------------------------------------------------------ |
| **Order Protocol**     | +10% qubit cost, 0% failure, +stability                             |
| **Chaos Protocol**     | -10% qubit cost, +15% failure, +50% speed                           |
| **Balanced Protocol**  | Neutral                                                             |
| **Entangled Protocol** | Shares qubits with other networks using the same Protocol (cluster) |

### Decoherence (server failure)

* **Causes:**
  * Overload: $> 90\%$ of qubits in use for 60s
  * Chaos Protocol with insufficient qubits
  * Entity attack (configurable)
* **Effects:**
  * Server collapses for 30s
  * Loses 50% of its qubits
  * Red particles, static sound
  * Terminal message: `"Server X decohered — qubits: Y"`

* **Recovery:**
  * Right-click the server with a Quantum Wrench $\rightarrow$ reset
  * Cost: $100\text{ Q} + 1\text{ qubit}$
  * Cooldown: 10s

### Network Tiers

| Tier           | Wireless range | Entanglers/group | Cross-dimensional? |
| :------------- | :------------- | :--------------- | :----------------- |
| **Decoherent** | 16 blocks      | 16               | No                 |
| **Entangled**  | 32 blocks      | 64               | No                 |
| **Superposed** | 64 blocks      | 256              | Yes                |
| **Singular**   | 128 blocks     | 1.024            | Yes                |

### Quantum Drive + Storage Crystals

| Cell                             | Capacity       | Tier       |
| :------------------------------- | :------------- | :--------- |
| **Quantum Storage Crystal (T1)** | 10.000 items   | Decoherent |
| **Quantum Storage Crystal (T2)** | 1.000.000      | Entangled  |
| **Quantum Storage Crystal (T3)** | 100.000.000    | Superposed |
| **Quantum Storage Crystal (T4)** | 10.000.000.000 | Singular   |

* **Slots in the Drive:** 10 (10 Cells per Drive).  
* **Compatibility:** the network storage item can also be used as a backup buffer in inventories and local networks.

---

## ⚙️ 5. ASSEMBLIES (Quanta Beams)

> *Full reference: `ASSEMBLIES.md`*

A **Quantum Assembly** is an experiment built in the world: a *Quantum Emitter* projects a straight Quanta beam, *Modifiers* alter the beam along its path, and a *Quantum Receptor* applies the modified beam to a material. An *Assembly Core* anchors the setup and connects it to the network.

### Beam Rules

| Rule                                        | Value                       |
| :------------------------------------------ | :-------------------------- |
| **Maximum range**                           | 10 blocks (straight line)   |
| **Passes through blocks?**                  | No — requires line of sight |
| **Can it turn?**                            | Only via Redirector         |
| **Multiple Emitters on the same Receptor?** | Yes                         |
| **Beam color**                              | `#e6a4ff` (base)            |

### Assembly Blocks

| Block                     | Function                                       | Tiers      |
| :------------------------ | :--------------------------------------------- | :--------- |
| **Quantum Emitter**       | Emits a beam (sets tier, throughput, range)    | 1/2/3/4    |
| **Quantum Receptor**      | Receives the beam, holds items/liquids/gases   | 1/2/3/4    |
| **Quantum Assembly Core** | Anchors, manages energy, records configuration | 1/2/3/4    |
| **Redirector**            | Reflects the beam (90° turn)                   | Decoherent |
| **Beam Splitter**         | Splits into N outputs                          | Superposed |
| **Beam Filter**           | Only passes if it matches                      | Entangled  |

> **Note:** The Assembly Core is required. Without it, the blocks do not form an assembly. It must be within $\le 10$ blocks of the Emitter.

### Emitters by Tier

| Tier           | Emitters/Assembly | Modifiers | Redirectors | Q/t emitted |
| :------------- | :---------------- | :-------- | :---------- | :---------- |
| **Decoherent** | 1                 | 1         | 0           | 10          |
| **Entangled**  | 2                 | 2         | 1           | 50          |
| **Superposed** | 4                 | 3         | 2           | 200         |
| **Singular**   | 8                 | 5         | 4           | 1.000       |

### Receptors by Tier

| Tier           | Slots | Speed         |
| :------------- | :---- | :------------ |
| **Decoherent** | 1     | 1 craft / 40t |
| **Entangled**  | 3     | 1 craft / 20t |
| **Superposed** | 6     | 1 craft / 10t |
| **Singular**   | 12    | 1 craft / 5t  |

### Assembly Cores by Tier

| Tier           | Assemblies managed | Connection radius |
| :------------- | :----------------- | :---------------- |
| **Decoherent** | 1                  | 10 blocks         |
| **Entangled**  | 2                  | 15 blocks         |
| **Superposed** | 4                  | 20 blocks         |
| **Singular**   | 8                  | 30 blocks         |

### Modifiers (alter the beam)

| Modifier                 | Effect                                          | Minimum tier |
| :----------------------- | :---------------------------------------------- | :----------- |
| **Heat Coil**            | Adds heat $\rightarrow$ smelting                | Decoherent   |
| **Grinder Ring**         | Adds friction $\rightarrow$ crushing            | Decoherent   |
| **Press Plate**          | Adds pressure $\rightarrow$ pressing            | Decoherent   |
| **Nozzle**               | Injects liquid $\rightarrow$ liquid infusion    | Entangled    |
| **Gas Nozzle**           | Injects gas $\rightarrow$ gas infusion          | Entangled    |
| **Infusion Ring**        | Adds resonance $\rightarrow$ infusing           | Entangled    |
| **Focus Lens**           | Focuses $\rightarrow$ +25% yield, -10% speed    | Entangled    |
| **Amplifier**            | Amplifies $\rightarrow$ +50% speed, +20% Q cost | Entangled    |
| **Chaos Resonator**      | Adds chaos $\rightarrow$ Chaos recipes          | Entangled    |
| **Order Resonator**      | Adds order $\rightarrow$ Order recipes          | Entangled    |
| **Etcher**               | Adds precision $\rightarrow$ circuit etching    | Entangled    |
| **Pattern Array**        | Adds logic $\rightarrow$ auto-crafting          | Superposed   |
| **Purifier**             | Removes impurities $\rightarrow$ +15% yield     | Entangled    |
| **Superposed Resonator** | Superposition $\rightarrow$ tier 3 recipes      | Superposed   |
| **Singularity Vessel**   | Convergence point $\rightarrow$ tier 4          | Singular     |

### Canonical Assemblies

| Assembly              | Layout                                                                                 | Preassembled Equivalent     |
| :-------------------- | :------------------------------------------------------------------------------------- | :-------------------------- |
| **Infuser**           | Emitter $\rightarrow$ Receptor                                                         | Quantum Infuser             |
| **Smelter**           | Emitter $\rightarrow$ Heat Coil $\rightarrow$ Receptor                                 | Quantum Furnace             |
| **Crusher**           | Emitter $\rightarrow$ Grinder Ring $\rightarrow$ Receptor                              | Quantum Crusher             |
| **Press**             | Emitter $\rightarrow$ Press Plate $\rightarrow$ Receptor                               | Quantum Press               |
| **Liquid Infuser**    | Emitter $\rightarrow$ Nozzle $\rightarrow$ Receptor                                    | Quantum Liquid Infuser      |
| **Circuit Etcher**    | Emitter $\rightarrow$ Etcher $\rightarrow$ Receptor                                    | Quantum Circuit Etcher      |
| **Chaos Infuser**     | Emitter $\rightarrow$ Chaos Resonator $\rightarrow$ Receptor                           | *(no preassembled version)* |
| **Order Infuser**     | Emitter $\rightarrow$ Order Resonator $\rightarrow$ Receptor                           | *(no preassembled version)* |
| **Precision Infuser** | Emitter $\rightarrow$ Focus $\rightarrow$ Purifier $\rightarrow$ Receptor              | *(no preassembled version)* |
| **Superposed Forge**  | 2 Emitters $\rightarrow$ Focus $\rightarrow$ Superposed Resonator $\rightarrow$ Vessel | *(no preassembled version)* |
| **Parallel Infuser**  | Emitter $\rightarrow$ Splitter $\rightarrow$ N Receptors                               | *(no preassembled version)* |
| **Auto-Crafting**     | Emitter $\rightarrow$ Pattern Array $\rightarrow$ Receptor                             | Quantum Auto-Fabricator     |

### Preassembled vs. DIY

| Aspect             | Preassembled                    | DIY (Assembly)                    |
| :----------------- | :------------------------------ | :-------------------------------- |
| **Cost**           | $\sim 1.5\times$ more expensive | Cheaper per block                 |
| **Space**          | 1 block                         | 2+ blocks                         |
| **Flexibility**    | Fixed (tier upgrades only)      | Modular                           |
| **Speed**          | Standard                        | Adjustable (Focus, Amplifier)     |
| **Scale**          | Vertical (tier)                 | Horizontal (splitters)            |
| **Learning curve** | Zero                            | Requires understanding the system |
| **When to use**    | Early/mid game                  | Mid/end game                      |

> **Disassembling a preassembled unit:** At the Assembly Core, returns the equivalent DIY parts (10% material loss).  
> **v10.1 correction:** The maximum range remains 10 blocks; material production bonuses must not exceed this limit without specific configuration.

---

## 🏭 6. MACHINES

> *Full reference: `MACHINES.md`*

### Tier 0 — Industrial (FE)

| Machine                    | Function                             | Energy                          |
| :------------------------- | :----------------------------------- | :------------------------------ |
| **Industrial Crusher**     | Crushes ores, Ender Pearl, Blaze Rod | $20\text{ FE/t}$                |
| **Industrial Press**       | Metals $\rightarrow$ Plates          | $15\text{ FE/t}$                |
| **Industrial Wire Drawer** | Plates $\rightarrow$ Wires           | $15\text{ FE/t}$                |
| **Industrial Furnace**     | Industrial furnace                   | $30\text{ FE/t}$                |
| **Combustor**              | Generates FE (coal, lava, blaze)     | 0 (generates $100\text{ FE/t}$) |

### Quanta Machines (Tier 1-4)

| Machine                     | Function                                                           | Consumption                                     | Tier       |
| :-------------------------- | :----------------------------------------------------------------- | :---------------------------------------------- | :--------- |
| **Matter Rewriter**         | Duplicates dusts ($2\times\text{-}8\times$ per tier)               | $10\text{ Q/t}$                                 | Decoherent |
| **Quantum Infuser**         | Infuses materials                                                  | $20\text{ Q/t}$                                 | Decoherent |
| **Quantum Furnace**         | Efficient furnace                                                  | $10\text{ Q/t}$                                 | Decoherent |
| **Quantum Crusher**         | Tier 1 refining                                                    | $15\text{ Q/t}$                                 | Decoherent |
| **Quantum Press**           | Efficient pressing                                                 | $30\text{ Q/t}$                                 | Decoherent |
| **Quantum Wire Drawer**     | Efficient wire drawing                                             | $25\text{ Q/t}$                                 | Decoherent |
| **Quantum Saw**             | Mass wood processing                                               | $15\text{ Q/t}$                                 | Decoherent |
| **Quantum Harvester**       | Harvests + replants ($3\times 3 \rightarrow 7\times 7$)            | $10\text{ Q/t}$                                 | Decoherent |
| **Essence Collector**       | Generates Essences (passive)                                       | 0 (generates $5\text{ Q/t}$)                    | Decoherent |
| **Quanta Enhancer**         | Dusts $\rightarrow$ Pallets (8× better)                            | $25\text{ Q/t}$                                 | Decoherent |
| **Quantum Liquid Infuser**  | Infuses liquids and materials                                      | $20\text{ Q/t}$                                 | Entangled  |
| **Quantum Evaporator**      | Liquid $\rightarrow$ Gas                                           | $15\text{ Q/t}$                                 | Entangled  |
| **Quantum Condenser**       | Gas $\rightarrow$ Liquid                                           | $15\text{ Q/t}$                                 | Entangled  |
| **Quantum Liquid Mixer**    | Mixes liquids                                                      | $30\text{ Q/t}$                                 | Entangled  |
| **Quantum Gas Centrifuge**  | Separates gases                                                    | $40\text{ Q/t}$                                 | Entangled  |
| **Quanta Engine**           | Burns Liquid Quanta/Chaos                                          | 0 (generates $50\text{-}250\text{ Q/t}$)        | Entangled  |
| **Gas Burner**              | Burns Quanta Gas/Chaos Gas                                         | 0 (generates $50\text{-}250\text{ Q/t}$)        | Entangled  |
| **Quantum Auto-Fabricator** | Auto-crafting                                                      | $15\text{ Q/t}$                                 | Superposed |
| **Quantum Pattern Bench**   | Encodes Patterns                                                   | $10\text{ Q/t}$                                 | Superposed |
| **Void Generator**          | Passive generation by location                                     | 0 (generates $500\text{-}1.000\text{ Q/t}$)     | Superposed |
| **Quantum Forge**           | Advanced alloy processing ($3\times 3\times 3$)                    | $100\text{ Q/t}$                                | Superposed |
| **Graviton Stabilizer**     | Creates Stabilized Singularity ($7\times 7\times 7$)               | $1.000\text{ Q/t}$                              | Superposed |
| **Black Hole Forge**        | Endgame processing ($5\times 5\times 5$)                           | $200\text{ Q/t}$                                | Singular   |
| **Superposed Reactor**      | Massive generation ($5\times 5\times 5$ to $21\times 21\times 21$) | 0 (generates $2.000\text{-}10.000+\text{ Q/t}$) | Singular   |

### Experimental Machines

| Machine                     | Tier       | Size                        | Function                         | Consumption      |
| :-------------------------- | :--------- | :-------------------------- | :------------------------------- | :--------------- |
| **Quantum Tester**          | Decoherent | 1 block                     | Test infusion/collision          | $10\text{ Q/t}$  |
| **Particle Collider**       | Entangled  | Multiblock (circle or line) | Collide materials (2+ injectors) | Variable         |
| **Wave Function Collapser** | Superposed | $3\times 3\times 3$         | Collapse superposition           | $100\text{ Q/t}$ |
| **Quantum Analyzer**        | Entangled  | 1 block                     | Analyze item                     | $5\text{ Q/t}$   |

#### Particle Collider Details

* **Geometries:** Circular (Synchrotron) or Linear (LINAC)
* **Minimum:** radius 3 (circular) or 5 blocks (linear)
* Multiple injectors (ternary fusion, quaternary fusion, etc.)
* Collision Chambers in different locations alter results
* Particles are cyan (`#00F5FF`) regardless of material
* Does not generate energy — only *knowledge* and items

### Quantum Forge ($3\times 3\times 3$)

* **Fuel:** Liquid Chaos ($\sim 10\text{ mB}$ per craft) + Quanta ($100\text{ Q/t}$)
* **Inputs:** 2 item slots | **Output:** 1 slot

| Input 1       | Input 2        | Output              |
| :------------ | :------------- | :------------------ |
| Quantum Steel | Ender Dust     | **Entangled Steel** |
| Quantum Steel | Chaos Fragment | **Chaotic Steel**   |
| Quantum Steel | Order Fragment | **Order Steel**     |

### Graviton Stabilizer ($7\times 7\times 7$)

* **Tier:** Superposed (3)
* **Catalyst:** Singularity + Order Fragment + Stabilized Liquid
* **Energy:** Quanta ($1.000\text{ Q/t}$)
* **Output:** Stabilized Singularity (1 item per craft)

### Black Hole Forge ($5\times 5\times 5$)

* **Tier:** Singular (4)
* **Permanent catalyst:** Singularity (not consumed)
* **Energy:** Quanta ($200\text{ Q/t}$)
* **Inputs:** 2 item slots | **Output:** 1 slot

| Input 1          | Input 2         | Output               |
| :--------------- | :-------------- | :------------------- |
| Quantum Alloy    | Chaotic Steel   | **Superposed Alloy** |
| Quantum Alloy    | Order Steel     | **Dechorent Alloy**  |
| Superposed Alloy | Dechorent Alloy | **Singular Alloy**   |

> **v10.1 correction:** **Graviton Stabilizer** is now tier **Superposed (3)** and produces **Stabilized Singularity**. **Black Hole Forge** is now tier **Singular (4)** and uses **Singularity** as a catalyst, avoiding an impossible progression loop.

---

## 🔩 7. ALLOYS AND STATS

> *Full reference: `METALLURGY.md`*

All alloys have 4 stats: **Conductivity, Resistance, Resonance, Stability** (scale 1-6).

| Stat             | Affects                                    |
| :--------------- | :----------------------------------------- |
| **Conductivity** | Cable throughput, qubits per server        |
| **Resistance**   | Part durability, machine HP                |
| **Resonance**    | Equation power, tool traits                |
| **Stability**    | Machine failure, resistance to decoherence |

### Alloy Table

| Alloy                | Composition                                | Tier       | Cond. | Res. | Reso. | Est. |
| :------------------- | :----------------------------------------- | :--------- | :---- | :--- | :---- | :--- |
| **Quantum Steel**    | Quantum Dust/Pallet + Iron                 | Decoherent | 3     | 3    | 3     | 3    |
| **Quantum Alloy**    | Quantum Dust/Pallet + Netherite            | Superposed | 4     | 4    | 4     | 4    |
| **Entangled Steel**  | Quantum Steel + Ender Dust (Forge)         | Superposed | 5     | 2    | 4     | 3    |
| **Chaotic Steel**    | Quantum Steel + Chaos Fragment (Forge)     | Superposed | 4     | 3    | 5     | 1    |
| **Order Steel**      | Quantum Steel + Order Fragment (Forge)     | Superposed | 3     | 5    | 2     | 5    |
| **Superposed Alloy** | Quantum Alloy + Chaotic Steel (Black Hole) | Superposed | 5     | 4    | 5     | 4    |
| **Dechorent Alloy**  | Quantum Alloy + Order Steel (Black Hole)   | Superposed | 4     | 5    | 3     | 6    |
| **Singular Alloy**   | Superposed + Dechorent (Black Hole)        | Singular   | 6     | 6    | 6     | 6    |

> **v10.1 correction:** **Entangled Steel**, **Chaotic Steel**, and **Order Steel** were consolidated as **Superposed (3)** tier alloys, all produced in the **Quantum Forge**.

### Uses by Alloy

| Alloy                | Main uses                                         |
| :------------------- | :------------------------------------------------ |
| **Quantum Steel**    | T1 cables, parts, machines, decorative blocks     |
| **Entangled Steel**  | T2 cables, Entanglers, Signatures, T2 parts       |
| **Chaotic Steel**    | Chaos Circuit, Chaos Injector, generation Addons  |
| **Order Steel**      | Order Circuit, Order Stabilizer, stability Addons |
| **Quantum Alloy**    | T3 cables, parts, Terminal, Mainframe             |
| **Superposed Alloy** | Splitters, Beam Filter, T3 Assembly components    |
| **Dechorent Alloy**  | Sensors, Monitors, precision parts                |
| **Singular Alloy**   | T4 cables, parts, Singularity components, endgame |

---

## 🔧 8. ADDONS

### Slots by Tier

| Tier           | Slots |
| :------------- | :---- |
| **Decoherent** | 2     |
| **Entangled**  | 3     |
| **Superposed** | 4     |
| **Singular**   | 6     |

*Maximum 4 Addons of the same type per slot.*

### Available Addons

| Addon                | Effect                               | Special Material                         |
| :------------------- | :----------------------------------- | :--------------------------------------- |
| **Speed Coil**       | +20% speed, +25% energy cost         | Quantum Coil + Redstone                  |
| **Energy Coil**      | -20% energy cost                     | Quantum Capacitor + Redstone             |
| **Stabilizer Coil**  | -5% failure                          | —                                        |
| **Quantum Lock**     | -10% failure                         | —                                        |
| **Observer Link**    | -15% failure                         | Observer Lens                            |
| **Chaos Overdrive**  | +50% speed, +25% failure             | Chaotic Coil (craft with Chaos Fragment) |
| **Chaos Injector**   | +50% generation, +25% risk (reactor) | Chaos Fragment                           |
| **Order Stabilizer** | -30% risk, -10% generation (reactor) | Essence of Order                         |
| **Yield Addon**      | +15% secondary output                | —                                        |
| **Fluid Addon**      | Processes liquids/gases              | —                                        |

---

## 🛠️ 9. STATIONS

> *Full reference: `STATIONS.md`*

The mod has 3 crafting stations:

### 9.1 Quantum Circuit Etcher

Transforms components into Circuits with stats.

* **GUI:**
  * Blueprint Slot (Silk)
  * Etchant Slot (Liquid Order/Chaos/Quanta/Essence/Stabilized)
  * $3\times 3$ component grid
  * Board Slot (Quantum Circuit Board)
  * Output

#### Circuitos

| Circuit        | Components                                         | Stats                            | Use               |
| :------------- | :------------------------------------------------- | :------------------------------- | :---------------- |
| **Basic**      | 2 Diode + 2 Resistor                               | +1 slot, 0 bonus                 | T1 machines       |
| **Processing** | 2 Capacitor + 2 Transistor                         | +2 slots, +10% speed             | T2 machines       |
| **Quantum**    | 1 Coil + 2 Diode + 1 Capacitor                     | +3 slots, +20% speed, +10% yield | T3 machines       |
| **Order**      | 2 Diode + 1 Resistor + 1 Coil + Essence of Order   | +0 failure                       | Safe Addons       |
| **Chaos**      | 2 Chaos Fragment + 1 Coil + Essence of Chaos       | +50% speed, +25% failure         | Aggressive Addons |
| **Singular**   | 1 Quantum + 1 Order + 1 Chaos + 1 Superposed Alloy | +5 slots, maximum stats          | Endgame           |
| **Resonance**  | 2 Coil + 1 Capacitor + 1 Observer Lens             | +2 network range                 | Entanglers        |

* **Sub-circuits (nesting):**
  * $4\text{ Basic} \rightarrow 1\text{ Advanced}$
  * $4\text{ Advanced} \rightarrow 1\text{ Processing}$
  * $4\text{ Processing} \rightarrow 1\text{ Quantum}$
  * $4\text{ Quantum} \rightarrow 1\text{ Singular}$
* **Silk:** Blank Silk (Quantum Pallet + Paper) $\rightarrow$ Circuit Silk (with recipe). Consumed when crafting. Stored in Silk Vault.
* **Modes:** Order (1 craft, 0% failure) / Chaos (batch of 4, +15% failure, -25% time/craft)
* **Energy:** $20\text{ Q/t}$ (Order), $40\text{ Q/t}$ (Chaos)

### 9.2 Quantum Fabrication Rig

Assembles tools, armor, and implants from parts. Tinker's/Silent Gear style.

* **GUI:**
  * Tabs: Tool / Armor / Implant / Special
  * Preview + Stats
  * Slots: Skeleton, Head/Blade, Guard, Handle, Wrap, Pommel, Motor, Battery
  * Modifier Slots (2/3/4/6 per tier)
  * Trait Slots (0/2)
  * Assemble / Disassemble

> **v10.1 note:** **Quantum Upgrade Table** was removed in favor of the **Quantum Fabrication Rig**, which handles assembly, upgrades, and disassembly.

#### Parts by Tool

| Tool         | Parts                                   |
| :----------- | :-------------------------------------- |
| **Pickaxe**  | Skeleton, Head, Handle, Wrap            |
| **Axe**      | Skeleton, Head, Handle                  |
| **Shovel**   | Skeleton, Head, Handle                  |
| **Sword**    | Skeleton, Blade, Guard, Handle, Pommel  |
| **Katana**   | Skeleton, Blade, Guard, Handle, Wrap    |
| **Drill**    | Skeleton, Head, Motor, Handle, Battery  |
| **Saw**      | Skeleton, Blade, Guard, Handle          |
| **Cannon**   | Skeleton, Barrel, Core, Handle, Battery |
| **Grappler** | Skeleton, Hook, Wire, Handle            |
| **Shield**   | Skeleton, Plate, Plate, Handle          |

#### Armor

| Part           | Slots                           |
| :------------- | :------------------------------ |
| **Helmet**     | Skeleton, Visor, Plate, Padding |
| **Chestplate** | Skeleton, Plate, Plate, Padding |
| **Leggings**   | Skeleton, Plate, Plate, Padding |
| **Boots**      | Skeleton, Plate, Plate, Padding |

#### Traits (by material + part)

| Trait        | Source                | Effect                       |
| :----------- | :-------------------- | :--------------------------- |
| **Resonant** | Quantum Alloy Head    | +10% yield                   |
| **Chaotic**  | Chaotic Steel Blade   | +25% damage, 10% self-damage |
| **Stable**   | Order Steel Handle    | -20% breakage                |
| **Phase**    | Entangled Steel Blade | Passes through 1 block       |
| **Singular** | Singular Alloy part   | +1 trait slot                |

#### Leveling (XP)

| Level | XP      | Bonus                      |
| :---- | :------ | :------------------------- |
| **1** | 0       | —                          |
| **2** | 1.000   | +1 modifier                |
| **3** | 5.000   | +1 trait slot              |
| **4** | 20.000  | +1 native enchantment      |
| **5** | 100.000 | +1 modifier, +1 trait slot |

* **Disassemble:** 50 Q, returns parts (modules preserved, traits lost unless level 5).
* **Modes:** Order (0% failure) / Chaos (15% failure, extra random trait)
* **Energy:** 50 Q/craft (assemble and disassemble)

### 9.3 Black Board

Station for creating Equations (magic). Does not program assemblies.

* **GUI:**
  * Blank Equation Slot
  * Result Preview
  * Symbols: Self / Projectile / Touch / Area / Order / Chaos / Singularity / Damage / Heal / Teleport / Acceleration / Infusion / Revelation / Stabilization
  * Saved Equations
  * Quantum Codex Slot
* **Fluxo:**
  1. Place Blank Equation in the slot
  2. Drag symbols (Form + Element + Effect)
  3. Preview the Q cost + stat effect
  4. Confirm $\rightarrow$ Equation is saved
  5. Drag it to the Quantum Codex (equip)
* **Cost:** 50 Q (basic) to 400 Q (Singularity)
* **Rules:**
  * Black Board does not program assemblies — Equations only (design decision)
  * Black Board does not replace Circuit Etcher or Fabrication Rig
  * It is the "magic" station, separate from the "science" stations

---

## 🗡️ 10. MODULAR EQUIPMENT

### Equipment Tiers

| Tier         | Name       | Material                       | Tool Capacity | Armor Capacity  | Slots |
| :----------- | :--------- | :----------------------------- | :------------ | :-------------- | :---- |
| **1**        | Decoherent | Quantum Steel                  | 10.000 Q      | 5.000-15.000 Q  | 3-6   |
| **2**        | Entangled  | Quantum Alloy                  | 25.000 Q      | 10.000-30.000 Q | 4-8   |
| **3**        | Superposed | Alloy + Entangled Core         | 50.000 Q      | 20.000-50.000 Q | 5-10  |
| **Ultimate** | Singular   | Alloy + Stabilized Singularity | 100.000 Q     | 25.000-75.000 Q | 6-12  |

### Base Tools

* Pickaxe, Axe, Shovel, Sword, Katana, Drill, Saw, Cannon, Grappler, Shield.
* **Slots:** 3-6 | **Base Capacity:** 10.000-100.000 Q

### Base Armor

| Equipment              | Slots | Base Capacity   |
| :--------------------- | :---- | :-------------- |
| **Quantum Helmet**     | 4-8   | 5.000-25.000 Q  |
| **Quantum Chestplate** | 6-12  | 15.000-75.000 Q |
| **Quantum Leggings**   | 4-8   | 8.000-40.000 Q  |
| **Quantum Boots**      | 4-8   | 5.000-25.000 Q  |

### Modules (installed in the Fabrication Rig)

#### Mining

| Module            | Effect        | Tier I      | II              | III             |
| :---------------- | :------------ | :---------- | :-------------- | :-------------- |
| **Tunnel**        | Mines an area | $2\times 2$ | $3\times 3$     | $5\times 5$     |
| **Vein Miner**    | Mines a vein  | 16 blocks   | 32              | 64              |
| **Quantum Sight** | Reveals ores  | 8 blocks    | 16              | 32              |
| **Auto-Smelt**    | Smelts        | $1\times$   | $2\times$       | $3\times$       |
| **Silk Touch**    | Silk Touch    | $1\times$   | $1\times$+speed | $2\times$+speed |

#### Combat

| Module           | Effect                  | I    | II   | III  |
| :--------------- | :---------------------- | :--- | :--- | :--- |
| **Chaos Strike** | Tripled damage (chance) | 15%  | 25%  | 35%  |
| **Order Strike** | Consistent damage       | +25% | +50% | +75% |
| **Leech**        | Steals health           | 10%  | 20%  | 30%  |

#### Utility

| Module         | Effect      | I        | II   | III  |
| :------------- | :---------- | :------- | :--- | :--- |
| **Magnet**     | Pulls items | 4 blocks | 8    | 16   |
| **Speed**      | Speed       | +20%     | +35% | +50% |
| **Experience** | XP bonus    | +25%     | +50% | +75% |

#### Defense

| Module             | Effect         | I    | II   | III  |
| :----------------- | :------------- | :--- | :--- | :--- |
| **Quantum Shield** | Absorbs damage | 20%  | 40%  | 80%  |
| **Anti-Knockback** | No knockback   | 60%  | 80%  | 100% |

#### Special

| Module          | Effect                      | Tier       |
| :-------------- | :-------------------------- | :--------- |
| **Radar**       | Detects entities            | Entangled  |
| **Step Assist** | Automatically climbs blocks | Decoherent |

### Equipment Upgrades (Fabrication Rig)

* Preserves modules and enchantments.
* Requires Upgrade Kit (Decoherent $\rightarrow$ Entangled $\rightarrow$ Superposed $\rightarrow$ Singular).
* Consumes Quanta.

---

## 🦾 11. CYBERNETIC IMPLANTS

Obtained in the **Quantum Cyber Chamber** (a $3x3x3$ multiblock with Chaos Gas and Order Gas inputs).

### Quantum Cyber Chamber (3×3×3 multiblock)

**Structure:**

- Base: 3×3 Quantum Casing
- Walls: Quantum Glass Panel (transparent — you can see inside)
- Ceiling: Quantum Casing with a 1-block opening (entrance)
- Controller: 1 block at the center of the front wall
- Fluid ports: Chaos Gas (left) + Order Gas (right)

**How to use:**

1. Put the gases in the tanks (Chaos + Order)
2. Enter the chamber (the door opens as you approach)
3. Place the implant components in the Controller slot
4. The Controller consumes Q + gases and installs the implant
5. Animation: purple/cyan particles circulate around your body
6. You exit with the implant installed

**Required multiblock:**

- Incomplete structure = does not work
- The Controller only accepts implants if the multiblock is formed
- Breaking a wall during installation = implant fails (50% chance of losing components)

* **Characteristics:**
  * Permanent (can be deactivated at no cost in the player menu)
  * Passive and free after installation
  * No quantity limit (endgame goal: become a "god")
  * Visible on the player's model
  * Implant menu appears only after installing **Quantum Cortex** (the first implant)

| Implant                  | Effect                                                        | Tier       | Cost     | Components                                                |
| :----------------------- | :------------------------------------------------------------ | :--------- | :------- | :-------------------------------------------------------- |
| **Quantum Sight**        | See system connections and cables through walls (8-32 blocks) | Entangled  | 500 Q    | Neural Interface + Optical Core + Lens                    |
| **Quantum Reach**        | Extended reach (+3 to +8 blocks)                              | Entangled  | 500 Q    | Neural Interface + Core + Core                            |
| **Quantum Speed**        | +20% to +50% speed                                            | Entangled  | 500 Q    | Neural Interface + Motor Implant + Speed Coil             |
| **Quantum Strength**     | +20% to 50% strength                                          | Entangled  | 500 Q    | Neural Interface + Motor Implant + Chaos Fragment         |
| **Quantum Resistance**   | +20% to 50% resistance                                        | Entangled  | 500 Q    | Neural Interface + Armor Core + Alloy                     |
| **Quantum Lungs**        | Infinite breathing                                            | Entangled  | 500 Q    | Neural Interface + Fluid Core + Liquid Quanta             |
| **Quantum Night Vision** | Permanent night vision                                        | Entangled  | 500 Q    | Neural Interface + Optical Core + Essence of Order        |
| **Quantum Tunneling**    | Pass through walls while sneaking                             | Superposed | 2.000 Q  | Neural Interface + Tunneling Core + Chaos Fragment        |
| **Quantum Flight**       | Creative flight                                               | Superposed | 2.000 Q  | Neural Interface + Flight Core + Stabilized Singularity   |
| **Quantum Buffer**       | +10.000 to +50.000 Q reserve                                  | Superposed | 2.000 Q  | Neural Interface + Energy Core + Capacitor                |
| **Quantum Metabolism**   | Boosts metabolism and passive recovery                        | Superposed | 2.000 Q  | Neural Interface + Bio Core + Essence of Order            |
| **Quantum Reflex**       | Instant reaction and passive dodge                            | Superposed | 2.000 Q  | Neural Interface + Reflex Core + Entangled Alloy          |
| **Quantum Storage**      | Expands internal storage capacity                             | Superposed | 2.000 Q  | Neural Interface + Storage Core + Quantum Storage Crystal |
| **Quantum Interface**    | Connects inventory, machines, and network wirelessly          | Superposed | 2.000 Q  | Neural Interface + Interface Core + Quantum Link          |
| **Quantum Anchor**       | Resists pull/teleport and stabilizes positioning              | Superposed | 2.000 Q  | Neural Interface + Anchor Core + Order Fragment           |
| **Quantum Cortex**       | Integrated Codex + remote crafting + knowledge bonus          | Singular   | 10.000 Q | Neural Interface + Processing Core + Quantum Processor    |

> **v10.1 correction:** the 5 additional implants (**Metabolism, Reflex, Storage, Interface, Anchor**) were incorporated into the main masterlist section, aligning with the summarized list of machines and equipment.

---

## 📖 12. QUANTUM CODEX & EQUATIONS

### Quantum Codex

Equippable item (like an Ars Nouveau book). 10,000 Q buffer. Gains *knowledge* (XP) by observing machines ($1\text{ knowledge}$ per tick). Holds up to 6 equations.

### Black Board

Block in the world where the player creates Equations. Symbols (Form, Element, Effect) are on the side; the player drags them to the center, connecting them. Equations are saved on the Black Board (right side of the GUI). The player drags them to the Codex "inventory" (bottom of the GUI) to equip them.

Single block (3×3×1 visual). Not a multiblock.

### Equations

* **Structure:** `Form + Element + Effect`
* **Forms (4):** Self, Projectile, Touch, Area
* **Elements (3):**

| Element         | Cost  | Failure | Power |
| :-------------- | :---- | :------ | :---- |
| **Order**       | +25%  | 0%      | Base  |
| **Chaos**       | -25%  | +15%    | +25%  |
| **Singularity** | +100% | 0%      | +100% |

* **Effects (7):** Damage, Heal, Teleport, Acceleration, Infusion, Revelation, Stabilization

* **Total combinations:** $4 \times 3 \times 7 = 84$

### Effects Table (summary)

#### Self (affects the player)

| Effect            | Order                              | Chaos                              | Singularity                                     |
| :---------------- | :--------------------------------- | :--------------------------------- | :---------------------------------------------- |
| **Damage**        | +75% damage, +20% resistance (30s) | +100% damage, +25% damage received | +200% damage, invulnerable, burns targets (15s) |
| **Heal**          | Heal 12 + regen (10s)              | Heal 4 + absorption 8              | Full heal, Regen V, absorption 20 (20s)         |
| **Teleport**      | Teleport + slow motion             | Random teleport + nausea           | Teleport to any coordinates                     |
| **Acceleration**  | +75% speed, step assist            | +125% speed, blurred vision        | +300% speed, passes through blocks              |
| **Infusion**      | +100% Quanta regen, -25% cost      | +200% regen, 25% failure           | Creative flight, indestructible, pulls in items |
| **Revelation**    | 32-block vision + entities         | 64-block vision + tinnitus         | 100-block vision, machines, liquids             |
| **Stabilization** | Removes effects + immunity (30s)   | Removes + transfers                | Removes everything + total immunity (120s)      |

#### Projectile (fires a projectile)

| Effect            | Order                              | Chaos                                    | Singularity                               |
| :---------------- | :--------------------------------- | :--------------------------------------- | :---------------------------------------- |
| **Damage**        | Piercing (20 damage, 3 targets)    | Explosive (30 damage, radius 3)          | Black hole (50 damage, pulls in items)    |
| **Heal**          | Heal 15 + shield 10                | Heal 5 + area regen 2                    | Heal 50 + healing field (20/s, 5s)        |
| **Teleport**      | Target $\rightarrow$ your position | Target $\rightarrow$ void (or 50 damage) | Dimensional rift (target to the void)     |
| **Acceleration**  | Fast, paralyzes (3s)               | Erratic, reverses controls               | 90% slow motion (15s)                     |
| **Infusion**      | 50% chance to infuse item          | Infuses + drops Chaos                    | 100% chance to infuse with Singularity    |
| **Revelation**    | Reveals area + marks target        | Reveals + nausea                         | Reveals everything within 20 blocks (60s) |
| **Stabilization** | Removes + resistance (30s)         | Removes + transfers                      | Removes all within 5 blocks               |

#### Touch (touches the target)

| Effect            | Order                              | Chaos                           | Singularity                              |
| :---------------- | :--------------------------------- | :------------------------------ | :--------------------------------------- |
| **Damage**        | 30 damage + knockback              | 15-60 random                    | 150 damage, ignores armor                |
| **Heal**          | Heal 30 + regen (20s)              | 10-50 random                    | Heal 100 + removes all negative effects  |
| **Teleport**      | Target $\rightarrow$ your position | Target $\rightarrow$ Nether/End | Instantly kills mobs (void)              |
| **Acceleration**  | +100% speed, step assist           | +200% speed + fatigue damage    | Global 80% slow motion (15s)             |
| **Infusion**      | 75% chance to infuse item          | Infuses + 10 damage             | Infuses + duplicates item                |
| **Revelation**    | Marks + reveals inventory          | Marks + particles               | Reveals everything (HP, items, location) |
| **Stabilization** | Removes + resistance (60s)         | Removes + transfers             | Removes all within 10 blocks             |

#### Area (surrounding area)

| Effect            | Order                                          | Chaos                            | Singularity                              |
| :---------------- | :--------------------------------------------- | :------------------------------- | :--------------------------------------- |
| **Damage**        | 20 damage, radius 6 (hostiles only)            | 25 damage, radius 8 (all)        | Black hole, 100 damage, radius 10        |
| **Heal**          | Heal 15 + regen, radius 6                      | Heal 5 + 5 damage to hostiles    | Heal 50 + field 20/s, radius 6           |
| **Teleport**      | Everyone in radius 4 $\rightarrow$ safe points | 30% go to the void, radius 5     | Everyone in radius 8 $\rightarrow$ void  |
| **Acceleration**  | +75% speed, radius 6                           | +125% speed + nausea, radius 8   | Reverse gravity, float for 15s           |
| **Infusion**      | 50% chance to infuse ground items, radius 6    | 100% but 20% disappear, radius 8 | All items infused + duplicated           |
| **Revelation**    | Ores + entities, radius 12                     | Everything + nausea              | Omni scan, radius 30 (60s)               |
| **Stabilization** | Removes + immunity, radius 8                   | Removes + transfers mobs         | Removes black holes + effects, radius 32 |

* **Quanta Cost:** varies by effect (50-400 Q). Modifiers: Order +25%, Chaos -25%, Singularity +100%.

---

## 🔌 13. QUANTUM AUTOMATION BLOCKS

| Block                       | Function                                |
| :-------------------------- | :-------------------------------------- |
| **Quantum Redstone Relay**  | Quanta (0-100) ↔ Redstone (0-15)        |
| **Quantum Energy Monitor**  | Monitors network energy, emits redstone |
| **Quantum Storage Monitor** | Monitors items/liquids in the system    |
| **Quantum Mode Switcher**   | Switches Chaos/Order via redstone       |

---

## 🔧 14. CONFIGURATION TOOLS

| Tool                | Function                            | Indestructible |
| :------------------ | :---------------------------------- | :------------- |
| **Quantum Wrench**  | Machine configuration, entanglement | ✅             |
| **Quantum Tester**  | Experiment testing                  | ✅             |
| **Quantum Scanner** | Scans machines and items            | ✅             |

---

## 🎨 15. VISUALS

### Color Palette by Tier

| Tier           | Color                                                 | Visual Effect            |
| :------------- | :---------------------------------------------------- | :----------------------- |
| **Decoherent** | `#3c7d7d`, `#09000c`, `#ac01e4`, `#720098`, `#410156` | Soft glow                |
| **Entangled**  | `#023436`, `#09000c`, `#c67cfc`, `#590199`, `#200136` | Pulsating glow           |
| **Superposed** | `#011e1f`, `#09000c`, `#dd87fd`, `#3d0168`, `#150123` | Purple light + particles |
| **Singular**   | `#b1fafa`, `#3ff9f9`, `#fafafa`, `#e6a4ff`, `#c67cfc` | Soft rainbow effect      |

### Liquids

| Liquid                 | Color                                                      |
| :--------------------- | :--------------------------------------------------------- |
| **Liquid Quanta**      | `#e6a4ff`                                                  |
| **Liquid Chaos**       | `#3d0168`                                                  |
| **Liquid Order**       | `#3ff9f9`                                                  |
| **Stabilized Liquid**  | `#b1fafa`                                                  |
| **Entangled Liquid**   | `#200136`                                                  |
| **Liquid Singularity** | `#fafafa` (alternating in RGB from `#b1fafa` to `#3d0168`) |

### Special Effects

* **Chaos mode:** duplicated particles, pulsating light, blurred aura
* **Order mode:** stable particles, constant light
* **Entanglement:** particle line, rotating ring
* **Visible Addons:** 3D components on the machine
* **Reactor Explosion:** purple screen, nausea, slower world

### Cable Particles

| State                      | Appearance                                      |
| :------------------------- | :---------------------------------------------- |
| **1 type in block**        | Single orbital ring                             |
| **2 types**                | Two rings on different planes                   |
| **3 types**                | Three rings                                     |
| **4 types**                | Four rings (atom)                               |
| **Buffer < 25%**           | Sparse, slow particles                          |
| **Buffer 25-75%**          | Normal flow                                     |
| **Buffer > 75%**           | Dense, bright, pulsating                        |
| **Buffer 100% (clogged)**  | Red particles, shaking                          |
| **Order mode**             | Perfect trajectory                              |
| **Chaos mode**             | Sinusoidal noise, 10% chance of a particle twin |
| **Isolated (no network)**  | Slow, erratic orbit                             |
| **Overload (>90% qubits)** | Sparks fall                                     |
| **Decohered**              | Erratic red particles, static                   |

### Beam Particles (Assemblies)

| State               | Appearance                |
| :------------------ | :------------------------ |
| **Normal**          | Pink line `#e6a4ff`      |
| **Heat Coil**       | Orange, particles rise    |
| **Chaos Resonator** | Purple `#c67cfc`, erratic |
| **Order Resonator** | Cyan `#3ff9f9`, linear    |
| **Focus Lens**      | Thin, bright              |
| **Splitter**        | Opens into N              |
| **Receptor full**   | Reverses, red, shaking    |
| **No Q**            | Gray, stopped             |
| **Overload**        | Sparks fall               |

### Building/Decorative Blocks

* **Structural:**
  * Quantum Steel Block
  * Quantum Steel Tiles (2 variants: light/dark)
  * Quantum Steel Pillar
  * Quantum Steel Beam
  * Reinforced Steel Casing
  * Singular Steel Casing
* **Glass and Panels:**
  * Quantum Glass Panel
  * Tinted Quantum Glass
  * Glowing Quantum Glass
  * Quantum Grid Glass
  * Quantum Circuit Tile (animated)
* **Lighting:**
  * Quantum Lamp
  * Quanta Crystal Block (light level 15, pink hue)
  * Chaos Crystal Cluster (purple light)
  * Order Crystal Cluster (cyan light)
  * Quantum Beacon (beam vertical)
* **Industrial:**
  * Quantum Scaffold
  * Quantum Coil Block (decorative)
  * Quantum Capacitor Bank (decorative)
  * Quantum Pipe (decorative)
  * Quantum Crate (industrial chest, 1 slot)
  * Quantum Vent (cosmetic)
* **Magic/High-tech:**
  * Ritual Circle (a $3\times 3$ multiblock, amplifies cast Equations)
  * Quantum Observation Lens (wall-mounted, decorative)

> **v10.1 correction:** the redundant **Quantum Altar** was removed; **Ritual Circle** was explicitly redefined as an amplifier for cast Equations, not a crafting station.

---

## 📋 16. COMPATIBILITY (Planned)

* JEI/REI, The One Probe/Jade
* AE2, Mekanism, Create, Thermal, XNet
* Curios API
* KubeJS / CraftTweaker
* UniDict

---

## 📊 17. BENCHMARKS (balance vs. other mods)

| Machine                | Q/t                               | FE/t equivalent                         | Comparison                                           |
| :--------------------- | :-------------------------------- | :-------------------------------------- | :--------------------------------------------------- |
| **Combustor**          | $100\text{ FE/t}$                 | $100\text{ FE/t}$                       | Stirling Generator (Mekanism: $50\text{ FE/t}$)      |
| **Quanta Engine**      | $50\text{-}250\text{ Q/t}$        | $5.000\text{-}25.000\text{ FE/t}$       | Advanced Generator (Mekanism: $5.000\text{-}20.000$) |
| **Void Generator**     | $500\text{-}1.000\text{ Q/t}$     | $50.000\text{-}100.000\text{ FE/t}$     | Fusion Reactor (Mekanism: $\sim 25.000.000$) — lower |
| **Superposed Reactor** | $2.000\text{-}10.000+\text{ Q/t}$ | $200.000\text{-}1.000.000+\text{ FE/t}$ | Draconic Reactor ($\sim 1.000.000+$) — similar       |

---

## 🚀 IMPLEMENTATION ORDER

### Phase 1 — Foundation (MVP)

Tier 0 + 3 Quanta machines + 4 cables + Codex + 10-20 Equations.
**Not included:** Network, Assemblies, Stations, implants.

### Phase 2 — Processing and Assemblies

Complete Tier 1 + standalone Assemblies (Emitter + Receptor + Core) + Fabrication Rig with 3 tools.
**Not included:** Network, Circuit Etcher, implants.

### Phase 3 — Network and Implants

Basic Network (Entangler + Signature + qubits) + Circuit Etcher + Cyber Chamber (multiblock) + first implants.
**Not included:** Tier 3+, Auto-crafting.

### Phase 4 — Expansion

Tier 3 (Superposed) + Auto-crafting (Quantum Assembler + Pattern Bench) + complete Circuit Etcher.

### Phase 5 — Endgame

Tier 4 (Singular) + large multiblocks (Forge, Black Hole, Graviton) + Superposed Reactor + all implants.

---

## 🧾 CHANGELOG v10 → v10.1

- Updated header to **v10.1 / 2026-11-13**
- Fixed progression loop between **Graviton Stabilizer** and **Black Hole Forge**
- Adjusted the tier progression of **Entangled Steel / Chaotic Steel / Order Steel** alloys to tier **Superposed (3)**
- Renamed **Quantum Assembler** to **Quantum Auto-Fabricator**
- Renamed network item **Quantum Cell** to **Quantum Storage Crystal**
- Adjusted the behavior of **Quantum Server Rack / Mainframe / Supercomputer** as network items
- Removed the redundant **Quantum Altar** and redefined **Ritual Circle**
- Standardized the names **Superposed Reactor** and **Equations**
- Updated the implant section to include **Metabolism, Reflex, Storage, Interface, Anchor**
- Clarified the distinction between **Quanta Cell** (battery) and **Quantum Storage Crystal** (network storage)

---

## ✅ CONSOLIDATED DECISIONS (v10.2)

1. **MVP:** Phase 1 = Foundation (Tier 0 + 3 machines + 4 cables + Codex)
2. **Assemblies vs Network:** Standalone Assemblies first (network later)
3. **Preassembled vs DIY:** Preassembled only in v1 (DIY in mid game)
4. **Codex:** Item with NBT (Ars Nouveau-style spellbook)
5. **Cyber Chamber:** 3×3×3 multiblock (a chamber you can enter)

---

> *This document is the source of truth for Quanta Mod v10.1 development.*
> **Reference documents:** `LORE.md`, `ASSEMBLIES.md`, `CABLES.md`, `NETWORK.md`, `STATIONS.md`, `METALLURGY.md`, `MACHINES.md`, `MATERIALS_AND_CRAFTS.md`
