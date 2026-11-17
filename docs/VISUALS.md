# QUANTA MOD - VISUALS

> **Document based on QUANTA_MASTERLIST v10.6**
> **Last updated:** 2026-11-16
> **Scope:** Quanta Mod visuals (particles, animations, colors, effects)

## 🎯 OVERVIEW

This document specifies **all Quanta Mod visuals**:

- Particles (cables, beams, machines).
- Animations (machines, implants).
- Colors (by tier, by state).
- Models (blocks, items).
- Effects (flash, explosion, decoherence).

**Philosophy:** visuals should be **readable** (you can tell what's happening at a glance) and **satisfying** (clear feedback).

---

## 🎨 1. COLOR PALETTE

### 1.1 By Tier

| Tier            | Primary color | Secondary color | Effect                |
| --------------- | ------------ | -------------- | --------------------- |
| Industrial (T0) | `#434343`    | `#e75605`      | Metallic, opaque       |
| Decoherent (T1) | `#3c7d7d`    | `#ac01e4`      | Soft glow          |
| Entangled (T2)  | `#023436`    | `#c67cfc`      | Pulsating glow       |
| Superposed (T3) | `#011e1f`    | `#dd87fd`      | Purple light + particles |
| Singular (T4)   | `#b1fafa`    | `#e6a4ff`      | Soft rainbow       |

### 1.2 By State

| State      | Color                     | Use                  |
| ---------- | ------------------------- | -------------------- |
| Active     | Tier color                | Machines running |
| Inactive   | Gray `#666666`            | Machines stopped     |
| Saturated  | Tier color + glow         | Buffer >75%          |
| Clogged    | Red `#ff5533`             | Buffer 100%          |
| Error      | Dark red `#aa0000`        | Craft failure       |
| Decohered  | Purple `#c67cfc` + static | Server collapsed   |

### 1.3 By Mode

| Mode  | Color           | Use                 |
| ----- | --------------- | ------------------- |
| Order | Cyan `#3ff9f9`  | Stable particles |
| Chaos | Purple `#c67cfc` | Erratic particles |

---

## 🔌 2. CABLES (PARTICLES)

### 2.1 Orbits by Type

Each type present in the block occupies its own **orbital radius**:

| Type   | Radius | Plane           | Color          |
| ------ | ---- | --------------- | -------------- |
| Quanta | 0.15 | XZ (horizontal) | `#e6a4ff`      |
| Item   | 0.22 | XY (vertical)   | `#c67cfc`      |
| Fluid  | 0.29 | YZ (vertical)   | Fluid color    |
| Gas    | 0.36 | XZ (horizontal) | Gas color      |

**Visual:** 4 concentric rings on alternating planes (like an atom).

### 2.2 States

| State         | Particles                           |
| ------------- | ----------------------------------- |
| 1 type        | Single orbital ring                  |
| 2 types       | Two rings on different planes       |
| 3 types       | Three rings                          |
| 4 types       | Four rings (complete atom)       |
| Buffer <25%   | Sparse, slow, dim particles |
| Buffer 25-75% | Normal flow                        |
| Buffer >75%   | Dense, bright, pulsating particles       |
| Buffer 100%   | Stop, turn red, and shake            |

### 2.3 Order/Chaos Mode

| Mode      | Particles                                   |
| --------- | ------------------------------------------- |
| Order     | Perfect trajectory, no deviation             |
| Chaos     | Sinusoidal noise in the radius, 10% chance of a particle twin |
| Transition | Brief flash (2 ticks)                       |

### 2.4 Network State

| State                        | Particles                                       |
| ---------------------------- | ----------------------------------------------- |
| Isolated                     | Slow, erratic orbit (searching for a connection) |
| Connected                    | Normal orbit                                    |
| Overloaded (>90% qubits)      | Sparks fall                                     |
| Decohered                    | Erratic red particles, static sound |

### 2.5 Direction Indicators

`IndicatorParticleRenderer` draws on the faces:

- **Insert:** aquamarine, arrow pointing inward
- **Extract:** pink, arrow pointing outward
- **Disabled:** gray X

### 2.6 Sparks

`SparkParticleRenderer` — particles that fall from the beam under overload (>90% of qubits).

- Color: `#ff5533`
- Gravity: fall downward
- Duration: 10-20 ticks

---

## ⚙️ 3. BEAMS (ASSEMBLIES)

### 3.1 Render-only (not an entity)

The beam is **client-side**, calculated from the Emitter.

- `BeamParticleRenderer` draws the line.
- The Receptor "knows" whether it receives a beam through logic (no entity required).
- Line-of-sight is checked on the server.

### 3.2 Base Color

**Quanta:** pink `#e6a4ff`. Always visible, regardless of the modifier.

### 3.3 Modifiers (add extra particles)

| Modifier       | Extra particles                | Color             |
| --------------- | ----------------------------- | ----------------- |
| Heat Coil       | Particles rise (heat)         | Orange `#ff8800` |
| Chaos Resonator | Erratic particles (noise)     | Purple `#c67cfc` |
| Order Resonator | Perfectly linear particles    | Cyan `#3ff9f9`   |
| Focus Lens      | Thinner, brighter beam        | White `#ffffff`  |
| Amplifier       | Thicker beam                  | More intense pink |
| Splitter        | Beam splits into N            | Same base color    |

**Rule:** the base color is always Quanta. Modifiers add extra particles.

### 3.4 States

| State          | Visual                                     |
| -------------- | ------------------------------------------ |
| Normal         | Pink line `#e6a4ff`, constant speed         |
| Saturated      | Dense particles, increased glow             |
| Clogged        | Particles reverse, turn red, and shake      |
| No Q           | Gray, slow particles                         |
| Broken beam    | Particles fall (do not reach the Receptor)  |
| Overload       | Sparks fall from the beam                    |

### 3.5 Splitter

- 1 beam enters, N beams exit.
- Each output has 1/N of the intensity.
- Visual: "opens" into N lines.
- N = 2 (T3), 4 (T4).

### 3.6 Redirector

- Beam "reflects" (turns 90°).
- Visual: particles bounce (like a mirror).

---

## 🏭 4. MACHINES (ANIMATIONS)

### 4.1 Tier 0 Machines

| Machine                | Animation                        |
| ---------------------- | -------------------------------- |
| Combustor              | Pistons pumping                  |
| Industrial Crusher     | Two rotating cylinders           |
| Industrial Furnace     | Flames at the top                |
| Industrial Press       | Piston pumping                   |
| Industrial Wire Drawer | Wire emerging ("pulling" animation) |
| Industrial Saw         | Rotating saw                     |
| Industrial Harvester   | Robotic arm                      |
| Industrial Mixer       | Rotating blades                  |

### 4.2 Quanta Machines

| Machine              | Animation                        |
| -------------------- | -------------------------------- |
| Quanta Infuser       | Chamber with bubbling liquid     |
| Thermal Infuser      | Flames at the top (tier color)   |
| Quanta Densifier     | Visible compression              |
| Quanta Collapser     | Particle vortex                  |
| Energy Quantifier    | Small portal                     |
| Quanta Separator     | Robotic arms sorting items       |
| Quanta Crusher       | Cylinders (tier color)           |
| Quanta Press         | Piston (tier color)              |
| Quanta Wire Drawer   | Wire emerging                    |
| Quanta Mixer         | Rotating blades                  |
| Fluid Infuser        | Chamber with bubbling liquid     |
| Phase Evaporator     | Steam emerging                   |
| Phase Condenser      | Falling droplets                 |
| Fluid Mixer          | Mixer                            |
| Gas Centrifuge       | Rotating centrifuge              |
| Essence Collector    | Crystals growing                 |
| Quanta Purifier      | Bubbling water                   |
| Matter Rewriter      | Pulsating particle ring          |
| Nexus Assembler      | Robotic arm assembling           |
| Pattern Encoder      | Table with floating symbols      |
| Void Generator       | Particle vortex                  |
| Quanta Engine        | Pistons pumping                  |
| Gas Burner           | Blue flames                      |
| Particle Collider    | Light beams                      |
| Tunneling Teleporter | Particle portal                  |
| Observator Scanner   | Floating eye                     |
| Quanta Weaver        | Distortion, ores "appearing"     |

### 4.3 Multiblocks

| Multiblock          | Animation                          |
| ------------------- | ---------------------------------- |
| Graviton Stabilizer | Orbiting rings                     |
| Superposed Reactor  | Pulsating spheres                  |
| Cyber Chamber       | Purple/cyan particles circulating  |
| Nexus Supercomputer | Glowing connections                |

### 4.4 States

| State      | Visual                                            |
| ---------- | ------------------------------------------------- |
| Active     | Normal animation + tier particles                |
| Inactive   | No animation + gray particles                    |
| Order mode | Stable particles, constant light                 |
| Chaos mode | Duplicated particles, pulsating light, blurred aura |
| Saturated  | Dense particles                                  |
| Clogged    | Red particles                                    |

---

## ✨ 5. EFFECTS

### 5.1 Craft Completion

- **Flash** of light at the Receptor (2 ticks).
- **Item pops out** of the Receptor (animation).
- **Sound:** `block.anvil.use` (soft).
- **Duration:** 10 ticks.

### 5.2 Craft Failure (Chaos)

- **Small explosion** at the Receptor.
- **Gray smoke**.
- **Sound:** `block.fire.extinguish`.
- **Red particles**.
- **Duration:** 20 ticks.

### 5.3 Decoherence

- The player's **screen** turns purple for 1s.
- **Nausea** for 3s.
- **Sound:** static (`block.beacon.deactivate`).
- **Red particles** at the server.

### 5.4 Implant Installation

- **Purple/cyan particles** circulate around the player's body.
- White **flash** on completion.
- **Sound:** `block.beacon.activate`.
- **Duration:** 100 ticks (5s).

### 5.5 Action Wheel

- **Background:** dark, semi-transparent.
- **Icons:** installed implants.
- **Cortex:** gray, not clickable (hub).
- **Others:** clickable (ON/OFF).
- **Animation:** wheel opens in 0.2s, closes in 0.1s.

---

## 🖼️ 6. MODELS

### 6.1 Black Board (3×3×1 Visual)

- **VoxelShape:** 1 block (6×6×6 from the center).
- **Model:** 3×3×1 (visually occupies 9 blocks).
- **Texture:** blackboard with Equation symbols.
- **Animation:** symbols glow when in use.

### 6.2 Cyber Chamber (3×3×3 Multiblock)

- **Base:** 3×3 Singular Casing.
- **Walls:** Singular Glass Panel (transparent).
- **Ceiling:** Singular Casing with a 1×1 opening.
- **Controller:** block at the center of the front wall.
- **Fluid ports:** Chaos Gas (left) + Order Gas (right).
- **Animation:** particles circulate when in use.

### 6.3 Implants (Visible on the Player)

- **Quantum Sight:** glowing eye (cyan).
- **Quantum Reach:** arms with glowing lines.
- **Quantum Speed:** legs with particles.
- **Quantum Flight:** translucent wings.
- **Quantum Tunneling:** translucent body while sneaking.
- **Quantum Cortex:** chip on the forehead (always visible).

**Rule:** all implants are visible, even when turned off (they appear gray).

---

## 🎬 7. IMPLEMENTATION ORDER (VISUALS)

### Phase 1 — Foundation

- Cable particles (4 types, orbiting).
- Tier 0 machine animations.
- Animations for 3 Quanta machines.
- Slim anchors (Seam, Flow, Energy Quantifier).
- Codex (item) + Black Board (3×3×1 visuals).

### Phase 2 — Processing

- Beams (Emitter → Modifier → Receptor).
- Modifier particles.
- Fabrication Rig animations.

### Phase 3 — Network

- Network particles (entanglers, servers).
- Decoherence (visuals + sound).
- Cyber Chamber (multiblock).
- Implants visible on the player.
- Action wheel.

### Phase 4 — Expansion

- Auto-crafting (Pattern Array).
- Circuit Etcher (animations).
- Nexus Anchor (universal).

### Phase 5 — Endgame

- Multiblocos (Graviton Stabilizer, Superposed Reactor).
- All implants.

**This document covers 100% of the Quanta Mod's visuals.**
