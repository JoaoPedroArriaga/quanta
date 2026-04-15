# QUANTA MOD - MATERIAIS E CRAFTS

> **Documento baseado na QUANTA_MASTERLIST v7.0**
> **Última atualização: 2026-04-12**

---

## 🎯 VISÃO GERAL

Este documento contém TODOS os materiais, componentes e receitas do Quanta Mod.
Crafts são abstrações visuais do item final - usam de 2 a 9 slots conforme necessidade.

---

## 📊 PROGRESSÃO HIERÁRQUICA
NÍVEL 1: MATERIAIS BASE
↓
NÍVEL 2: COMPONENTES SIMPLES (Coil, Gear, Circuit, Diode, Capacitor, Resistor, Transistor)
↓
NÍVEL 3: COMPONENTES ESTRUTURAIS (Frame, Casing, Glass Panel)
↓
NÍVEL 4: NÚCLEOS FUNCIONAIS (Processing, Energy, Fluid, Network, Control)
↓
NÍVEL 5: COMPONENTES DE EQUIPAMENTOS (Tool Core, Armor Plate, Neural Interface, etc.)
↓
NÍVEL 6: ITENS FINAIS (Máquinas, Ferramentas, Armadura, Implantes)


---

## 📦 1. MATERIAIS BASE

### Tier 0 - Industrial (FE)

| Material | Obtenção | Máquina | Input |
|----------|----------|---------|-------|
| Ender Dust | Pulverizar | Industrial Crusher | Ender Pearl |

### Tier 1 - Decoherent

| Material | Obtenção | Máquina | Input | Obs |
|----------|----------|---------|-------|-----|
| Quantum Dust | Crafting | Crafting Table | Ender Dust + Redstone | Início |
| Quantum Dust | Duplicar | Particle Reconstructor | Qualquer Dust | 2x-8x por tier |
| Quantum Pallet | Enhance | Quanta Enhancer | Quantum Dust | - |
| Quantum Steel | Infundir | Quanta Infuser | Quantum Dust/Pallet + Iron Ingot | - |
| Quantum Steel Sheet | Pressionar | Industrial Press / Quantum Press | Quantum Steel | - |
| Quantum Steel Wire | Estirar | Industrial Wire Drawer / Quantum Wire Drawer | Quantum Steel Sheet | - |
| Quantum Coil | Montar | Quantum Assembler | Quantum Steel Wire + Copper Ingot | - |
| Quantum Circuit | Pressionar | Quanta Press | Quantum Steel + Gold Ingot + Redstone | - |
| Quantum Crystal | Infundir | Quanta Infuser | Quantum Dust/Pallet + Diamond | - |
| Essence of Order | Coletar | Quanta Collector | Quantum Crystal + Água | Modo Decoerência |
| Essence of Chaos | Coletar | Quanta Collector | Quantum Crystal + Lava | Modo Superposição |

### Tier 2 - Entangled

| Material | Obtenção | Máquina | Input |
|----------|----------|---------|-------|
| Quantum Processor | Montar | Quantum Assembler | Quantum Crystal + Quantum Circuit |
| Entangled Core | Montar | Quantum Assembler | Quantum Crystal + Ender Pearl + Essence of Chaos |
| Observer Lens | Montar | Quantum Assembler | Quantum Crystal + Essence of Order + Glass |
| Chaos Fragment | Montar | Quantum Assembler | Quantum Crystal + Essence of Chaos |

### Tier 3 - Superposed

| Material | Obtenção | Máquina | Input |
|----------|----------|---------|-------|
| Quantum Alloy | Infundir | Quanta Infuser | Quantum Dust/Pallet + Netherite Ingot |
| Quantum Alloy Sheet | Pressionar | Quantum Press | Quantum Alloy |
| Quantum Alloy Wire | Estirar | Quantum Wire Drawer | Quantum Alloy Sheet |

### Tier 4 - Singular

| Material | Obtenção | Máquina | Input |
|----------|----------|---------|-------|
| Stabilized Singularity | Forjar | Black Hole Forge | Nether Star + Entangled Core + Gases |

---

## ⚙️ 2. COMPONENTES SIMPLES

### Quantum Steel Gear

`[ ] [Steel] [ ]` / `[Steel] [ ] [Steel]` / `[ ] [Steel] [ ]` (5 slots, padrão +)

### Quantum Alloy Gear

`[ ] [Alloy] [ ]` / `[Alloy] [ ] [Alloy]` / `[ ] [Alloy] [ ]` (5 slots, padrão +)

### Quantum Diode

`[Wire] [Dust] [Wire]` / `[ ] [Redstone] [ ]` / `[Wire] [Dust] [Wire]` (7 slots)

### Quantum Capacitor

`[Coil] [Crystal] [Coil]` / `[ ] [Steel] [ ]` / `[Coil] [Crystal] [Coil]` (7 slots)

### Quantum Resistor

`[Wire] [Dust] [Wire]` / `[Steel] [Redstone] [Steel]` / `[Wire] [Dust] [Wire]` (9 slots)

### Quantum Transistor

`[Diode] [Order] [Circuit]` / `[Steel] [Crystal] [Steel]` / `[Steel] [Steel] [Steel]` (9 slots)

---

## 🏭 3. COMPONENTES ESTRUTURAIS

### Quantum Frame

`[Sheet] [Sheet] [Sheet]` / `[Sheet] [Core] [Sheet]` / `[Sheet] [Sheet] [Sheet]` (9 slots)

### Quantum Casing

`[Sheet] [Sheet] [Sheet]` / `[Sheet] [ ] [Sheet]` / `[Sheet] [Sheet] [Sheet]` (8 slots)

### Quantum Glass Panel

`[Glass] [Glass] [Glass]` / `[Glass] [Steel] [Glass]` / `[Glass] [Glass] [Glass]` (9 slots)

### Reinforced Frame (Superposed)

`[Frame] [Alloy] [Frame]` / `[Alloy] [Core] [Alloy]` / `[Frame] [Alloy] [Frame]` (9 slots)

### Singular Frame (Singular)

`[Reinforced] [Singularity] [Reinforced]` / `[Singularity] [Alloy] [Singularity]` / `[Reinforced] [Singularity] [Reinforced]` (9 slots)

---

## ⚡ 4. NÚCLEOS FUNCIONAIS (2×2)

### Processing Core

`[Coil] [Circuit]` / `[Steel] [Steel]` (4 slots)

### Energy Core

`[Coil] [Capacitor]` / `[Steel] [Steel]` (4 slots)

### Fluid Core

`[Port] [Tank]` / `[Steel] [Steel]` (4 slots)

### Network Core

`[Circuit] [Core]` / `[Steel] [Steel]` (4 slots)

### Control Core

`[Circuit] [Screen]` / `[Steel] [Steel]` (4 slots)

### Advanced Cores (Entangled → Superposed → Singular)

- Advanced Processing Core: `[Basic] [Basic]` / `[Basic] [Basic]` (4 Basic Cores)
- Quantum Processing Core: `[Advanced] [Advanced]` / `[Advanced] [Order]`
- Parallel Processing Core: `[Quantum] [Quantum]` / `[Quantum] [Chaos]`

---

## 🗡️ 5. COMPONENTES DE FERRAMENTAS

### Tool Core (Decoherent)

`[Processor] [Alloy]` / `[Circuit] [Alloy]` (4 slots)

### Tool Core (Entangled)

`[Decoherent] [Decoherent]` / `[Decoherent] [Core]` (4 slots)

### Tool Core (Superposed)

`[Entangled] [Entangled]` / `[Entangled] [Alloy]` (4 slots)

### Tool Core (Singular)

`[Superposed] [Superposed]` / `[Superposed] [Singularity]` (4 slots)

### Tool Head (Pick)

`[Alloy] [Alloy] [Alloy]` (3 slots, 1×3)

### Tool Head (Axe)

`[Alloy] [Alloy]` / `[Alloy] [ ]` (3 slots, 2×2 assimétrico)

### Tool Head (Shovel)

`[ ] [Alloy]` / `[ ] [Alloy]` (2 slots, 2×1)

### Tool Head (Drill)

`[Alloy] [Gear] [Alloy]` (3 slots, 1×3)

### Tool Head (Saw)

`[Alloy] [Alloy] [Alloy]` / `[ ] [Gear] [ ]` (4 slots, 2×2)

### Tool Blade (Sword)

`[ ] [Alloy]` / `[ ] [Alloy]` / `[ ] [Alloy]` (3 slots, 3×1)

### Tool Blade (Katana)

`[ ] [Alloy]` / `[Alloy] [ ]` / `[ ] [Alloy]` (3 slots, diagonal)

### Tool Blade (Claymore)

`[Alloy] [Alloy]` / `[Alloy] [Alloy]` / `[ ] [ ]` (4 slots, 2×2)

### Tool Handle

`[Alloy] [Circuit] [Alloy]` (3 slots, 1×3)

---

## 🛡️ 6. COMPONENTES DE ARMADURA

### Armor Plate (Decoherent)

`[Alloy] [Alloy]` / `[Alloy] [Alloy]` (4 slots, 2×2)

### Armor Plate (Entangled)

`[Decoherent] [Decoherent]` / `[Decoherent] [Core]` (4 slots)

### Armor Plate (Superposed)

`[Entangled] [Entangled]` / `[Entangled] [Alloy]` (4 slots)

### Armor Plate (Singular)

`[Superposed] [Superposed]` / `[Superposed] [Singularity]` (4 slots)

### Armor Core (Decoherent)

`[Processor] [Circuit]` / `[Alloy] [Alloy]` (4 slots)

### Armor Core (Entangled)

`[Decoherent] [Decoherent]` / `[Decoherent] [Core]` (4 slots)

### Armor Core (Superposed)

`[Entangled] [Entangled]` / `[Entangled] [Alloy]` (4 slots)

### Armor Core (Singular)

`[Superposed] [Superposed]` / `[Superposed] [Singularity]` (4 slots)

### Armor Trim (Decoherent)

`[Wire] [Circuit]` / `[Alloy] [ ]` (3 slots, 2×2 incompleto)

---

## 🦾 7. COMPONENTES DE IMPLANTES

### Neural Interface

`[Circuit] [Processor]` / `[Order] [Crystal]` (4 slots)

### Optical Core

`[Crystal] [Lens]` / `[Order] [Crystal]` (4 slots)

### Motor Implant

`[Coil] [Gear]` / `[Chaos] [Alloy]` (4 slots)

### Energy Buffer

`[Battery] [Capacitor]` / `[Alloy] [Alloy]` (4 slots)

### Tunneling Core

`[Core] [Chaos]` / `[Void] [Alloy]` (4 slots)

### Flight Core

`[Stabilized] [Singularity]` / `[Elytra] [Alloy]` (4 slots)

---

## 🏗️ 8. MÁQUINAS COMPLETAS

### Particle Reconstructor

`[Frame] [Processing] [Frame]` / `[Control] [Furnace] [Control]` / `[Frame] [Energy] [Frame]` (9 slots)

### Quanta Infuser

`[Frame] [Fluid] [Frame]` / `[Control] [Furnace] [Control]` / `[Frame] [Processing] [Frame]` (9 slots)

### Quanta Collector

`[Frame] [Fluid] [Frame]` / `[Control] [Dispenser] [Control]` / `[Frame] [Energy] [Frame]` (9 slots)

### Quantum Assembler

`[Frame] [Processing] [Frame]` / `[Control] [Crafting] [Control]` / `[Frame] [Energy] [Frame]` (9 slots)

### Quantum Liquid Infuser

`[Frame] [Fluid] [Frame]` / `[Control] [Furnace] [Control]` / `[Frame] [Energy] [Frame]` (9 slots)

### Energy Quantifier / Quanta Collapser

`[Frame] [Energy] [Frame]` / `[Coil] [Redstone] [Coil]` / `[Frame] [Energy] [Frame]` (9 slots)

### Quanta Enhancer / Evaporator / Condenser

`[Frame] [Processing] [Frame]` / `[Control] [Furnace] [Control]` / `[Frame] [Energy] [Frame]` (9 slots)

### Quantum Press / Wire Drawer

`[Frame] [Processing] [Frame]` / `[Control] [Press/Wire] [Control]` / `[Frame] [Energy] [Frame]` (9 slots)

### Quanta Engine / Gas Burner

`[Frame] [Energy] [Frame]` / `[Control] [Furnace] [Control]` / `[Frame] [Fluid] [Frame]` (9 slots)

### Entangler / Tunnel Relay

`[Frame] [Network] [Frame]` / `[Control] [Core] [Control]` / `[Frame] [Energy] [Frame]` (9 slots)

### Void Generator

`[Frame] [Energy] [Frame]` / `[Control] [Void] [Control]` / `[Frame] [Energy] [Frame]` (9 slots)

### Quantum Liquid Mixer

`[Frame] [Fluid] [Frame]` / `[Control] [Mixer] [Control]` / `[Frame] [Energy] [Frame]` (9 slots)

### Quantum Computer

`[Reinforced] [Processing] [Reinforced]` / `[Memory] [Control] [Memory]` / `[Reinforced] [Network] [Reinforced]` (9 slots)

### Quantum Mainframe

`[Frame] [Computer] [Frame]` / `[Processing] [Control] [Processing]` / `[Frame] [Network] [Frame]` (9 slots)

### Quantum Supercomputer

`[Mainframe] [Processing] [Mainframe]` / `[Processing] [Singularity] [Processing]` / `[Frame] [Network] [Frame]` (9 slots)

---

## 🗡️ 9. FERRAMENTAS COMPLETAS

### Decoherent Pickaxe

`[Head]` / `[Core]` / `[Handle]` (3 slots, 3×1)

### Decoherent Axe

`[Head]` / `[Core]` / `[Handle]` (3 slots, 3×1)

### Decoherent Shovel

`[Head]` / `[Core]` / `[Handle]` (3 slots, 3×1)

### Decoherent Sword

`[Blade]` / `[Core]` / `[Handle]` (3 slots, 3×1)

### Entangled Drill

`[Head]` / `[Core]` / `[Motor]` / `[Handle]` (4 slots, 4×1)

### Entangled Saw

`[Head]` / `[Core]` / `[Gear]` / `[Handle]` (4 slots, 4×1)

### Superposed Katana

`[Blade]` / `[Core]` / `[Handle]` (3 slots, 3×1)

### Superposed Claymore

`[Blade]` / `[Blade]` / `[Core]` / `[Handle]` (4 slots, 4×1)

### Singular Cannon

`[Barrel]` / `[Core]` / `[Energy]` / `[Handle]` (4 slots, 4×1)

### Singular Grappler

`[Hook]` / `[Core]` / `[Coil]` / `[Handle]` (4 slots, 4×1)

### Singular Shield

`[Plate] [Plate]` / `[Plate] [Core]` / `[ ] [Handle]` (5 slots, 3×2 assimétrico)

---

## 🛡️ 10. ARMADURA COMPLETA

### Decoherent Helmet

`[Plate] [Plate] [Plate]` / `[Plate] [Core] [Plate]` / `[ ] [ ] [ ]` (6 slots)

### Decoherent Chestplate

`[Plate] [ ] [Plate]` / `[Plate] [Plate] [Plate]` / `[Plate] [Plate] [Plate]` (8 slots)

### Decoherent Leggings

`[Plate] [Plate] [Plate]` / `[Plate] [ ] [Plate]` / `[Plate] [ ] [Plate]` (7 slots)

### Decoherent Boots

`[ ] [ ] [ ]` / `[Plate] [ ] [Plate]` / `[Plate] [ ] [Plate]` (4 slots)

### Entangled Helmet (upgrade via Entangled Kit)

### Entangled Chestplate (upgrade via Entangled Kit)

### Entangled Leggings (upgrade via Entangled Kit)

### Entangled Boots (upgrade via Entangled Kit)

*Mesmo padrão para Superposed e Singular*

---

## 🦾 11. IMPLANTES COMPLETOS

### Quantum Sight

`[Neural] [Optical]` / `[Lens] [ ]` (3 slots, 2×2 incompleto)

### Quantum Reach

`[Neural] [Core]` / `[Core] [ ]` (3 slots, 2×2 incompleto)

### Quantum Speed

`[Neural] [Motor]` / `[Coil] [ ]` (3 slots, 2×2 incompleto)

### Quantum Strength

`[Neural] [Motor]` / `[Fragment] [ ]` (3 slots, 2×2 incompleto)

### Quantum Resistance

`[Neural] [Armor]` / `[Alloy] [ ]` (3 slots, 2×2 incompleto)

### Quantum Lungs

`[Neural] [Fluid]` / `[Quanta] [ ]` (3 slots, 2×2 incompleto)

### Quantum Night Vision

`[Neural] [Optical]` / `[Order] [ ]` (3 slots, 2×2 incompleto)

### Quantum Tunneling

`[Neural] [Tunneling]` / `[Fragment] [ ]` (3 slots, 2×2 incompleto)

### Quantum Flight

`[Neural] [Flight]` / `[Stabilized] [ ]` (3 slots, 2×2 incompleto)

### Quantum Buffer

`[Neural] [Energy]` / `[Capacitor] [ ]` (3 slots, 2×2 incompleto)

### Quantum Cortex

`[Neural] [Processor]` / `[Memory] [Core]` (4 slots, 2×2)

---

## 🔧 12. UPGRADE KITS

### Decoherent Kit

`[Plate] [Core] [Plate]` / `[Circuit] [Alloy] [Circuit]` / `[Plate] [Core] [Plate]` (9 slots)

### Entangled Kit

`[Decoherent Kit] [Core] [Decoherent Kit]` / `[Core] [Alloy] [Core]` / `[Decoherent Kit] [Core] [Decoherent Kit]` (9 slots)

### Superposed Kit

`[Entangled Kit] [Alloy] [Entangled Kit]` / `[Alloy] [Chaos] [Alloy]` / `[Entangled Kit] [Alloy] [Entangled Kit]` (9 slots)

### Singular Kit

`[Superposed Kit] [Singularity] [Superposed Kit]` / `[Singularity] [Alloy] [Singularity]` / `[Superposed Kit] [Singularity] [Superposed Kit]` (9 slots)

---

## 🔧 13. FERRAMENTAS DE CONFIGURAÇÃO

### Quantum Wrench

`[Head]` / `[Core]` / `[Handle]` (3 slots, 3×1)

### Quantum Screwdriver

`[ ] [Head] [ ]` / `[ ] [Core] [ ]` / `[ ] [Handle] [ ]` (3 slots, 3×1 com espaços)

### Quantum Multitool

`[Wrench] [Processor] [Wrench]` / `[Steel] [Crystal] [Steel]` / `[Steel] [Steel] [Steel]` (9 slots)

### Quantum Tester

`[Circuit] [Redstone] [Circuit]` / `[Glass] [Redstone] [Glass]` / `[Steel] [Redstone] [Steel]` (9 slots)

### Quantum Scanner

`[Tester] [Processor] [Tester]` / `[Lens] [Steel] [Lens]` / `[Steel] [Steel] [Steel]` (9 slots)

---

## 🔌 14. QUANTUM AUTOMATION BLOCKS

### Quantum Redstone Relay

`[ ] [Wire] [ ]` / `[Circuit] [Comparator] [Circuit]` / `[ ] [Wire] [ ]` (5 slots)

### Quantum Energy Monitor

`[ ] [Tester] [ ]` / `[Circuit] [Redstone] [Circuit]` / `[ ] [Tester] [ ]` (5 slots)

### Quantum Storage Monitor

`[ ] [Scanner] [ ]` / `[Circuit] [Redstone] [Circuit]` / `[ ] [Scanner] [ ]` (5 slots)

### Quantum Mode Switcher

`[ ] [Wire] [ ]` / `[Order] [Circuit] [Chaos]` / `[ ] [Wire] [ ]` (5 slots)

---

## 📖 15. QUANTUM CODEX & BLACK BOARD

### Quantum Codex

`[Frame] [Processing] [Frame]` / `[Control] [Book] [Control]` / `[Frame] [Energy] [Frame]` (9 slots)

### Black Board

`[Frame] [Processing] [Frame]` / `[Control] [Blackboard] [Control]` / `[Frame] [Energy] [Frame]` (9 slots)

---

## 💧 16. LÍQUIDOS E GASES

### Quantum Liquid Infuser

`[Frame] [Fluid] [Frame]` / `[Control] [Furnace] [Control]` / `[Frame] [Energy] [Frame]` (9 slots)

### Quantum Liquid Mixer

`[Frame] [Fluid] [Frame]` / `[Control] [Mixer] [Control]` / `[Frame] [Energy] [Frame]` (9 slots)

### Quantum Evaporator

`[Frame] [Fluid] [Frame]` / `[Control] [Furnace] [Control]` / `[Frame] [Energy] [Frame]` (9 slots)

### Quantum Condenser

`[Frame] [Fluid] [Frame]` / `[Control] [Furnace] [Control]` / `[Frame] [Energy] [Frame]` (9 slots)

### Tanques

- Quantum Tank: `[Glass] [Glass] [Glass]` / `[Glass] [Tank] [Glass]` / `[Glass] [Glass] [Glass]`
- Containment Tank: `[Alloy] [Alloy] [Alloy]` / `[Alloy] [Tank] [Alloy]` / `[Alloy] [Alloy] [Alloy]`
- Pressurized Tank: `[Glass] [Glass] [Glass]` / `[Glass] [Gas] [Glass]` / `[Glass] [Glass] [Glass]`

---

## 🏭 17. MULTIBLOCOS

### Quantum Forge Controller

`[Processing] [Processing] [Processing]` / `[Processing] [Blast Furnace] [Processing]` / `[Alloy] [Alloy] [Alloy]` (9 slots)

### Black Hole Forge Controller

`[Stabilized Casing] [Stabilized Casing] [Stabilized Casing]` / `[Stabilized Casing] [Nether Star] [Stabilized Casing]` / `[Stabilized Casing] [Stabilized Casing] [Stabilized Casing]` (9 slots)

### Entanglement Reactor Core

`[Reactor Casing] [Singularity] [Reactor Casing]` / `[Singularity] [Alloy] [Singularity]` / `[Reactor Casing] [Singularity] [Reactor Casing]` (9 slots)

### Quantum Plasma Forge Controller

`[Processing] [Singularity] [Processing]` / `[Singularity] [Forge Controller] [Singularity]` / `[Alloy] [Alloy] [Alloy]` (9 slots)

### Quantum Cyber Chamber Controller

`[Frame] [Processing] [Frame]` / `[Control] [Anvil] [Control]` / `[Frame] [Energy] [Frame]` (9 slots)

### Quantum Upgrade Table

`[Frame] [Processing] [Frame]` / `[Control] [Crafting] [Control]` / `[Frame] [Energy] [Frame]` (9 slots)

---

## ✅ 18. VERIFICAÇÃO RÁPIDA DE PROGRESSÃO

- [ ] Industrial Crusher (Ender Dust)
- [ ] Quantum Dust
- [ ] Quantum Steel
- [ ] Quantum Crystal
- [ ] Essence of Order/Chaos
- [ ] Entangled Core
- [ ] Quantum Processor
- [ ] Quantum Alloy
- [ ] Stabilized Singularity
- [ ] Decoherent Tools/Armor
- [ ] Entangled Tools/Armor (upgrade)
- [ ] Superposed Tools/Armor (upgrade)
- [ ] Singular Tools/Armor (upgrade)
- [ ] Quantum Supercomputer
- [ ] Singularity Cell
- [ ] All Implants

---

*Este documento é sincronizado com a QUANTA_MASTERLIST v7.0*
*Para dúvidas, consulte o documento principal.*