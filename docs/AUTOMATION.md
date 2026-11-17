# QUANTA MOD - AUTOMATION

> **Document based on QUANTA_MASTERLIST v10.6**
> **Last updated:** 2026-11-16
> **Scope:** Automation blocks (redstone, monitors, relays) in Quanta Mod

## 🎯 OVERVIEW

**Automation blocks** allow the player to **control** and **monitor** the Quanta base using redstone, without opening each machine's GUI.

**Philosophy:** automation is optional, but rewards players who want **efficiency** and **remote control**.

---

## 🔌 1. QUANTUM REDSTONE RELAY

**Function:** Converts Q ↔ Redstone (0-100 Q = 0-15 Redstone).
**Tier:** T1+.
**Cost:** 2 Q/t.

### 1.1 Modes

| Mode             | Effect                                       |
| ---------------- | -------------------------------------------- |
| **Q → Redstone** | Converts Q to a redstone signal (0-15).      |
| **Redstone → Q** | Converts a redstone signal to Q (0-100 Q/t). |

### 1.2 Craft (5 slots)

[ ] [Ferrum Wire] [ ]
[Quantum Circuit] [Comparator] [Quantum Circuit]
[ ] [Ferrum Wire] [ ]

**Output:** 1 Quantum Redstone Relay

### 1.3 Configuration

- Open the GUI to choose the mode (Q → Redstone or Redstone → Q).
- Set the **threshold** (e.g., "if Q > 50, emit redstone 15").

---

## ⚡ 2. QUANTUM ENERGY MONITOR

**Function:** Monitors the network's energy (Q) and emits redstone.
**Tier:** T1+.
**Cost:** 2 Q/t.

### 2.1 How It Works

- Shows the network's **Q level** (0-100%).
- Emits **redstone** if the level is below/above a configurable threshold.

### 2.2 Craft (5 slots)

[ ] [Quantum Tester] [ ]
[Quantum Circuit] [Redstone] [Quantum Circuit]
[ ] [Quantum Tester] [ ]

**Output:** 1 Quantum Energy Monitor

### 2.3 Configuration

- Open the GUI to configure:
  - **Threshold** (e.g., "if Q < 50%, emit redstone 15").
  - **Mode** (normal or inverted).

---

## 📦 3. QUANTUM STORAGE MONITOR

**Function:** Monitors the network's item/fluid/gas stock and emits redstone.
**Tier:** T2+.
**Cost:** 2 Q/t.

### 3.1 How It Works

- Shows the **stock** of a specific item (e.g., "Iron Ingot").
- Emits **redstone** if the stock is below/above a configurable threshold.

### 3.2 Craft (5 slots)

[ ] [Quantum Scanner] [ ]
[Quantum Circuit] [Redstone] [Quantum Circuit]
[ ] [Quantum Scanner] [ ]

**Output:** 1 Quantum Storage Monitor

### 3.3 Configuration

- Open the GUI to configure:
  - **Item** (which item to monitor).
  - **Threshold** (e.g., "if Iron Ingot < 64, emit redstone 15").
  - **Mode** (normal or inverted).

---

## 🔄 4. QUANTUM MODE SWITCHER

**Function:** Switches a machine's Order/Chaos mode via redstone.
**Tier:** T1+.
**Cost:** 2 Q/t.

### 4.1 How It Works

- When it receives **redstone 15**, it activates **Chaos**.
- When it receives **redstone 0**, it activates **Order**.
- The target machine must be **adjacent** to the Mode Switcher.

### 4.2 Craft (5 slots)

[ ] [Ferrum Wire] [ ]
[Order Fragment] [Quantum Circuit] [Chaos Fragment]
[ ] [Ferrum Wire] [ ]

**Output:** 1 Quantum Mode Switcher

### 4.3 Configuration

- Open the GUI to configure:
  - **Target machine** (which machine to control).
  - **Mode** (normal or inverted).

---

## 📊 5. AUTOMATION BLOCK SUMMARY

| Block                   | Function            | Tier | Cost  |
| ----------------------- | ------------------- | ---- | ----- |
| Quantum Redstone Relay  | Q ↔ Redstone        | T1   | 2 Q/t |
| Quantum Energy Monitor  | Monitors Q          | T1   | 2 Q/t |
| Quantum Storage Monitor | Monitors stock      | T2   | 2 Q/t |
| Quantum Mode Switcher   | Switches Order/Chaos | T1  | 2 Q/t |

---

## ⚠️ 6. BALANCE NOTES

- **Quantum Redstone Relay:** essential for automation (converts Q to redstone).
- **Quantum Energy Monitor:** useful for monitoring the network (prevents Q shortages).
- **Quantum Storage Monitor:** useful for monitoring stock (prevents item shortages).
- **Quantum Mode Switcher:** useful for switching modes (Order/Chaos).

---

## 🔗 7. INTEGRATION

- **REDSTONE:** automation blocks use vanilla redstone.
- **MACHINES:** monitors/switchers connect to adjacent machines.
- **NETWORK:** monitors/switchers connect to the Quanta network.

**This document covers 100% of the automation blocks in Quanta Mod.**
