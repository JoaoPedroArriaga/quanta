# QUANTA MOD - METALLURGY

> **Document based on QUANTA_MASTERLIST v10.6**
> **Last updated:** 2026-11-16
> **Scope:** Alloys, stats, and uses

## 🎯 OVERVIEW

All Quanta Mod alloys have **4 stats**: Conductivity, Resistance, Resonance, and Stability. These stats affect **all systems** — they are not cosmetic.

---

## 📊 1. STATS

| Stat              | Affects                                        | Where it matters                 |
| ----------------- | -------------------------------------------- | ---------------------------- |
| **Conductivity** | Cable throughput                          | CABLES, NETWORK              |
| **Resistance**   | Part durability, machine HP        | FABRICATION RIG, MACHINES    |
| **Resonance**    | Equation power, tool traits        | BLACK BOARD, FABRICATION RIG |
| **Stability**  | Machine failure, resistance to decoherence | MACHINES, NETWORK            |

**Scale:** 1 (terrible) to 6 (perfect).

---

## 🔩 2. ALLOYS

| Alloy                   | Composition                                      | Tier | Conduct. | Res. | Reson. | Stab. |
| ---------------------- | ----------------------------------------------- | ---- | ----- | ---- | ----- | ---- |
| **Ferrum Conducens**   | Iron + Quanta                                   | T1   | 3     | 3    | 3     | 3    |
| **Adamas Resonans**    | Diamond + Quanta                                | T1   | 4     | 4    | 4     | 4    |
| **Cuprum Transmissor** | Copper + Quanta                                 | T1   | 5     | 2    | 4     | 3    |
| **Aurum Nobilis**      | Gold + Quanta                                   | T1   | 4     | 3    | 5     | 1    |
| **Aeternum**           | Netherite + Quanta                              | T2   | 4     | 4    | 4     | 4    |
| **Superpositum**       | Aeternum + Chaotic Steel (Black Hole Forge)     | T3   | 5     | 4    | 5     | 4    |
| **Dechoerentium**      | Aeternum + Order Steel (Black Hole Forge)       | T3   | 4     | 5    | 3     | 6    |
| **Singular Alloy**     | Superpositum + Dechoerentium (Black Hole Forge) | T4   | 6     | 6    | 6     | 6    |

**Note:** *Ferrum Conducens* is the "workhorse" (T1). *Aeternum* is the "transition metal" (T2). *Singular Alloy* is the "all 6s" alloy (T4).

---

## ⚒️ 3. ACQUISITION

### 3.1 Ferrum Conducens

**Machine:** Quanta Infuser.
**Input:** Iron Ingot + Quanta.
**Output:** 1 Ferrum Conducens.

### 3.2 Adamas Resonans

**Machine:** Quanta Infuser.
**Input:** Diamond + Quanta.
**Output:** 1 Adamas Resonans.

### 3.3 Cuprum Transmissor

**Machine:** Quanta Infuser.
**Input:** Copper Ingot + Quanta.
**Output:** 1 Cuprum Transmissor.

### 3.4 Aurum Nobilis

**Machine:** Quanta Infuser.
**Input:** Gold Ingot + Quanta.
**Output:** 1 Aurum Nobilis.

### 3.5 Aeternum

**Machine:** Quanta Infuser.
**Input:** Netherite Ingot + Quanta.
**Output:** 1 Aeternum.

### 3.6 Superpositum

**Machine:** Black Hole Forge (5×5×5).
**Input 1:** Aeternum.
**Input 2:** Chaotic Steel.
**Output:** 1 Superpositum.

### 3.7 Dechoerentium

**Machine:** Black Hole Forge (5×5×5).
**Input 1:** Aeternum.
**Input 2:** Order Steel.
**Output:** 1 Dechoerentium.

### 3.8 Singular Alloy

**Machine:** Black Hole Forge (5×5×5).
**Input 1:** Superpositum.
**Input 2:** Dechoerentium.
**Output:** 1 Singular Alloy.

---

## 🎯 4. USES BY ALLOY

### 4.1 Ferrum Conducens (T1)

- T1 cables.
- T1 Parts (Fabrication Rig).
- T1 machines (Casing).
- Decorative blocks.

### 4.2 Adamas Resonans (T1)

- Electronic components (Quantum Crystal).
- T1 Parts.
- Decorative blocks.

### 4.3 Cuprum Transmissor (T1)

- Cables (Quanta Cable).
- Components (Quantum Coil).
- Decorative blocks.

### 4.4 Aurum Nobilis (T1)

- Electronic components (Quantum Circuit).
- T1 Parts.
- Decorative blocks.

### 4.5 Aeternum (T2)

- T2 cables.
- Entanglers.
- T2 Parts.
- T2 machines.

### 4.6 Superpositum (T3)

- T3 cables.
- T3 Parts.
- Splitters, Beam Filter.
- T3 Assembly components.

### 4.7 Dechoerentium (T3)

- Sensors, Monitors.
- "Precision" Parts.
- T3 Assembly components (stability).

### 4.8 Singular Alloy (T4)

- T4 cables.
- T4 Parts.
- Singularity components.
- Endgame.

---

## 📈 5. HOW STATS AFFECT THINGS

### 5.1 Conductivity

- **Cables:** throughput = base × (conductivity / 3).
  - Ex: Ferrum Conducens (3) = 1×. Cuprum Transmissor (5) = 1.67×.
- **Network:** qubits per server × (conductivity / 3).
- **Assembly:** Q/t output × (conductivity / 3).

### 5.2 Resistance

- **Parts:** durability = base × (resistance / 3).
- **Machines:** HP (for explosions).
- **Armor:** durability + damage resistance.

### 5.3 Resonance

- **Equations:** power = base × (resonance / 3).
- **Tools:** traits based on resonance.
- **Assembly:** processing speed × (resonance / 3).

### 5.4 Stability

- **Machines:** failure = base - (stability × 2).
  - Ex: Ferrum Conducens (3) = -6% failure. Dechoerentium (6) = -12% failure.
- **Network:** resistance to decoherence = stability × 5%.
- **Assembly:** success chance in Chaos mode = 15% - (stability × 2).

---

## 🔄 6. SPECIAL COMBINATIONS

Stats are not linear. Some combinations are special:

| Combination                           | Effect                     |
| ------------------------------------ | -------------------------- |
| Cuprum Transmissor + Order Resonator | 20-block beam (doubles) |
| Aurum Nobilis + Chaos Resonator      | +30% speed, +20% failure chance     |
| Singular Alloy + Focus Lens          |  +50% yield (from Focus)      |
| Dechoerentium + Purifier             | +25% additional yield       |

---

## 📋 7. RECIPES (SUMMARY)

| Alloy               | Where       | Input 1         | Input 2       |
| ------------------ | ---------- | --------------- | ------------- |
| Ferrum Conducens   | Infuser    | Iron Ingot      | Quanta        |
| Adamas Resonans    | Infuser    | Diamond         | Quanta        |
| Cuprum Transmissor | Infuser    | Copper Ingot    | Quanta        |
| Aurum Nobilis      | Infuser    | Gold Ingot      | Quanta        |
| Aeternum           | Infuser    | Netherite Ingot | Quanta        |
| Superpositum       | Black Hole | Aeternum        | Chaotic Steel |
| Dechoerentium      | Black Hole | Aeternum        | Order Steel   |
| Singular Alloy     | Black Hole | Superpositum    | Dechoerentium |

---

## 🔗 8. INTEGRATION

- **CABLES.md:** alloys define throughput.
- **NETWORK.md:** alloys define qubits and resistance to decoherence.
- **ASSEMBLIES.md:** alloys define speed and failure.
- **STATIONS.md:** alloys define part quality.
- **ARMORS.md / TOOLS.md:** alloys define tool/armor stats.

---

## ⚠️ 9. BALANCING NOTES

- **Ferrum Conducens** should be the "workhorse" — easy to obtain and balanced.
- **Aeternum** is the "transition metal" (T2) — balanced, but more expensive.
- **Superpositum/Dechoerentium** are the T3 "extremes."
- **Singular Alloy** is the "all 6s" alloy — endgame, expensive, and rare.

**This document covers 100% of Quanta Mod's alloys.**
