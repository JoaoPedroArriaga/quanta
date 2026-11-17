---
canvas:
  - "[[STRUCTURE.canvas]]"
parent: "[[MACHINES]]"
toc-order: 8
STRUCTURE:
  - "[[MACHINES]]"
---
# QUANTA MOD - QUANTUM ASSEMBLIES

> **Document based on QUANTA_MASTERLIST v10.6**
> **Last updated:** 2026-11-16
> **Scope:** Assemblies (Emitter + Modifiers + Receptor)

## 🎯 OVERVIEW

A **Quantum Assembly** is an experiment built in the world: a **Quantum Emitter** projects a Quanta beam in a straight line, **Modifiers** alter the beam along its path, and a **Quantum Receptor** applies the modified beam to a material. An **Assembly Core** anchors the setup and connects it to the network.

This is the mod's most distinctive system. It literally embodies the lore: "Quanta is a particle that alters information" — the beam *is* information in transit.

---

## ⚙️ 1. BEAM RULES

| Rule                                  | Value                               | Rationale                                   |
| ------------------------------------- | ----------------------------------- | ------------------------------------------- |
| Maximum range                         | **10 blocks** (straight line)       | Quanta loses coherence; the experiment is local |
| Passes through blocks?                | **No** — requires line of sight     | Quanta is physical, not wireless            |
| Can it turn?                           | **Only via Redirector**             | Quantum mirror                              |
| Multiple Emitters on the same Receptor? | **Yes**                           | Superposition (T3+)                         |
| Beam color                            | `#e6a4ff` (base)                    | Modified by modifiers                       |

---

## 🧩 2. ASSEMBLY BLOCKS

### 2.1 Quantum Emitter

Emits a beam in one direction (6 orientations). Determines tier, throughput, and range.

| Tier            | Emitters/Assembly | Modifiers     | Redirectors | Q/t emitted |
| --------------- | ----------------- | ------------- | ----------- | ----------- |
| T1 (Decoherent) | 1                 | 1             | 0           | 10          |
| T2 (Entangled)  | 2                 | 2             | 1           | 50          |
| T3 (Superposed) | 4                 | 3             | 2           | 200         |
| T4 (Singular)   | 8                 | 5             | 4           | 1.000       |

### 2.2 Quantum Receptor

Receives the beam, holds items/fluids/gases, and processes the recipe.

| Tier            | Slots | Speed         |
| --------------- | ----- | ------------- |
| T1 (Decoherent) | 1     | 1 craft / 40t |
| T2 (Entangled)  | 3     | 1 craft / 20t |
| T3 (Superposed) | 6     | 1 craft / 10t |
| T4 (Singular)   | 12    | 1 craft / 5t  |

### 2.3 Quantum Assembly Core (required)

Anchors the assembly, manages energy, stores configuration, and connects to the network.
**Must be next to the Emitter** (adjacent). Without a Core, the Emitter does not emit a beam.

| Tier            | Managed assemblies    | Connection radius |
| --------------- | ---------------------- | --------------- |
| T1 (Decoherent) | 1                      | 10 blocks       |
| T2 (Entangled)  | 2                      | 15 blocks       |
| T3 (Superposed) | 4                      | 20 blocks       |
| T4 (Singular)   | 8                      | 30 blocks       |

**Rules:**

- The Core **must** be adjacent to the Emitter (right next to it).
- The Core has **3 connection faces**: Emitter (internal link), Cable (Quanta), Entangler (network).
- Without a Core, the Emitter is decorative (it does not emit).
- Without an Emitter, the Core is orphaned (it does not work).
- Breaking the Core or Emitter breaks the assembly (any craft in progress is lost).

### 2.4 Modifiers (alter the beam)

| Modifier                 | Effect                              | Minimum tier |
| ------------------------ | ----------------------------------- | ----------- |
| **Heat Coil**            | Adds heat → smelting                | T1          |
| **Grinder Ring**         | Adds friction → crushing            | T1          |
| **Press Plate**          | Adds pressure → pressing            | T1          |
| **Nozzle**               | Injects fluid → liquid infusion     | T2          |
| **Gas Nozzle**           | Injects gas → gas infusion          | T2          |
| **Infusion Ring**        | Adds resonance → infusing           | T2          |
| **Focus Lens**           | Focuses → +25% yield, -10% speed    | T2          |
| **Amplifier**            | Amplifies → +50% speed, +20% Q cost | T2          |
| **Chaos Resonator**      | Adds chaos → Chaos recipes          | T2          |
| **Order Resonator**      | Adds order → Order recipes          | T2          |
| **Etcher**               | Adds precision → circuit etching    | T2          |
| **Pattern Array**        | Adds logic → auto-crafting          | T3          |
| **Purifier**             | Removes impurities → +15% yield     | T2          |
| **Superposed Resonator** | Superposition → tier 3 recipes      | T3          |
| **Singularity Vessel**   | Convergence point → tier 4          | T4          |

### 2.5 Redirectors and Splitters

| Block             | Function                          | Minimum tier |
| ----------------- | --------------------------------- | ----------- |
| **Redirector**    | Redirects the beam (90° turn)     | T1          |
| **Beam Splitter** | Splits into N outputs (2/4 by tier) | T3        |
| **Beam Filter**   | Passes only if the item/recipe matches | T2     |

---

## 🏭 3. CANONICAL ASSEMBLIES

### 3.1 Infuser (basic)

[Emitter] ─── [Receptor]

- Recipe: item in the Receptor + beam = infusion.
- Uses: Ferrum Conducens, Adamas Resonans.
- Equivalent pre-built block: **Quanta Infuser**.

### 3.2 Smelter

[Emitter] ─── [Heat Coil] ─── [Receptor]

- Equivalent: Thermal Infuser.
- +Heat Coil = smelting.

### 3.3 Crusher

[Emitter] ─── [Grinder Ring] ─── [Receptor]

- Equivalent: Quanta Crusher.
- Uses: ores → dust.

### 3.4 Press

[Emitter] ─── [Press Plate] ─── [Receptor]

- Equivalent: Quanta Press.
- Uses: metals → plates.

### 3.5 Liquid Infuser

[Emitter] ─── [Nozzle] ─── [Receptor]
│
[Tank]

- Equivalent: Fluid Infuser.
- Uses: item + fluid = result.

### 3.6 Circuit Etcher

[Emitter] ─── [Etcher] ─── [Receptor]

- Equivalent: Quantum Circuit Etcher (see STATIONS.md).
- Uses: Board + Components = Circuit.

### 3.7 Chaos Infuser

[Emitter] ─── [Chaos Resonator] ─── [Receptor]

- Produces: Chaotic Steel, Chaos Fragment.
- 15% failure chance (Chaos mode).

### 3.8 Order Infuser

[Emitter] ─── [Order Resonator] ─── [Receptor]

- Produces: Order Steel, Order Fragment.
- 0% failure (Order mode).

### 3.9 Precision Infuser

[Emitter] ─── [Focus Lens] ─── [Purifier] ─── [Receptor]

- +40% total yield, -20% speed.
- Best for rare materials.

### 3.10 Superposed Forge (T3)

[Emitter A] ─── [Focus] ─── [Superposed Resonator] ───┐
▼
[Singularity Vessel]
▲
[Emitter B] ─── [Focus] ─── [Superposed Resonator] ───┘

- **Two Emitters** converging.
- Produces: Superpositum, Dechoerentium.
- Requires: Singularity Vessel + 2 T3+ Emitters.

### 3.11 Parallel Infuser (escala)

┌── [Receptor 1]
[Emitter] ── [Splitter] ── [Receptor 2]
└── [Receptor 3]

- Beam Splitter splits into 2 (T3) or 4 (T4).
- 1 Emitter processes N receptors.
- Requires: a tier-matched Splitter and N receptors of that tier.

### 3.12 Auto-Crafting (network)

[Emitter] ─── [Pattern Array] ─── [Receptor]

- Pattern Array receives a **Pattern** (encoded recipe).
- Executes the craft when requested by the network.
- **It is the "Nexus Assembler in assembly form."**
- Equivalent pre-built block: **Nexus Assembler**.

---

## 🔌 4. PRE-BUILT VS DIY

| Aspect                    | Pre-built                 | DIY (Assembly)                   |
| ------------------------ | ------------------------- | -------------------------------- |
| **Cost**                  | ~1.5× more expensive      | Cheaper per block                |
| **Space**                 | 1 block                   | 2+ blocks                        |
| **Flexibility**           | Fixed (tier upgrade only) | Modular (add modifiers)          |
| **Speed**                 | Standard                  | Adjustable (Focus, Amplifier)    |
| **Scaling**               | Vertical (tier)           | Horizontal (splitters)           |
| **Learning curve**        | None                      | Requires understanding the system |
| **When to use**           | Early/mid game            | Mid/end game                     |

**Rule:** any pre-built block can be **disassembled** in the Assembly Core into its equivalent DIY parts (with a 10% material loss).

---

## 🎨 5. BEAM VISUALS

Based on the existing particle system (`ParticleManager`, `OrbitalParticleRenderer`):

| State               | Appearance                                               |
| ------------------- | -------------------------------------------------------- |
| Normal              | Line of pink particles `#e6a4ff`, constant speed        |
| With Heat Coil       | Transitions to orange; particles rise (heat)            |
| With Chaos Resonator | Purple `#c67cfc`, erratic particles (sinusoidal noise)  |
| With Order Resonator | Cyan `#3ff9f9`, perfectly linear particles              |
| With Focus Lens      | Thinner, brighter beam                                   |
| With Splitter        | Beam splits into N                                       |
| Receptor full        | Particles reverse, turn red, and shake                  |
| No Q                 | Gray, nearly motionless particles                       |
| Overload             | Sparks (`SparkParticle`) fall from the beam             |

---

## 📊 6. TIERS AND PROGRESSION

| Tier            | Recipes unlocked                            | Typical Q/t |
| --------------- | ------------------------------------------- | ---------- |
| T1 (Decoherent) | Infuser, Smelter, Crusher, Press            | 10 Q/t     |
| T2 (Entangled)  | Liquid Infuser, Chaos/Order, Circuit Etcher | 50 Q/t     |
| T3 (Superposed) | Superposed Forge, Parallel, Auto-Crafting   | 200 Q/t    |
| T4 (Singular)   | Singularity Forge, endgame recipes           | 1,000 Q/t  |

**Upgrade rule:** the Emitter determines the assembly's tier. To upgrade, replace the Emitter with one of a higher tier (apply an Upgrade Kit to the Emitter).

---

## 🔗 7. INTEGRATION WITH OTHER SYSTEMS

- **CABLES.md:** the Assembly Core connects to the network via Quanta Cable or Entangler.
- **NETWORK.md:** a connected Assembly uses **qubits** (not Q) for operations; without a network, it operates in standalone mode (1 craft/tick).
- **METALLURGY.md:** alloys determine the stats of Emitter/Receptor parts.
- **STATIONS.md:** Nexus Assembler (block) is the pre-built version of Auto-Crafting; Circuit Etcher is the pre-built version of Etching.

---

## 🧪 8. EXAMPLE: ENDGAME (SINGULARITY FORGE)

**Goal:** produce Singular Alloy from Superpositum + Dechoerentium.

**Layout (top view, 10x10):**

[Emitter A] ── [Focus] ── [Superposed Resonator] ──┐
│
▼
[Singularity Vessel]
▲
│
[Emitter B] ── [Focus] ── [Superposed Resonator] ──┘
│
▼
[Assembly Core]
│
[Entangler] ──► [Network]

- 2 Emitters (T3+, 200 Q/t each).
- 2 Focus Lenses (converging).
- 2 Superposed Resonators.
- 1 Singularity Vessel.
- 1 Assembly Core (T3+).
- 1 Entangler for the network.

**Cost per craft:** 400 Q + 2 qubits + 100 ticks.
**Output:** 1 Singular Alloy.
**Recipe:** Superpositum + Dechoerentium → Singular Alloy.

**This document covers 100% of the assemblies in Quanta Mod.**
