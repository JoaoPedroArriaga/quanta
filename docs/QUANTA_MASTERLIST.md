# QUANTA MOD - MASTERLIST OFICIAL (v7.0)

> Documento consolidado do mod Quanta para Minecraft NeoForge 1.21.1
> **Última atualização: 2026-04-12**

---

## 🎯 CONCEITO CENTRAL

Um mod techno-mágico onde mecânica quântica e magia arcana são a mesma coisa vista de ângulos diferentes. Quanta é uma **partícula que altera informação** - pode reescrever a realidade.

**Inspirações:** Mekanism, Oritech, Create, EnderIO, Ars Nouveau, AE2, Modular Powersuits, Draconic Evolution, Thaumcraft.

---

## 📊 PROGRESSÃO DE TIERS

| Tier | Nome | Cor | Característica |
|------|------|-----|----------------|
| 0 | Industrial | Cinza | FE-based, pré-Quanta |
| 1 | Decoherent | Roxo médio | Estável, previsível (Decoerência) |
| 2 | Entangled | Roxo + Ciano | Entrelaçado, conectado |
| 3 | Superposed | Roxo + Ciano + Dourado | Múltiplos estados simultâneos (Superposição) |
| Ultimate | Singular | Ciano + Dourado + Branco | Ponto de equilíbrio absoluto |

---

## ⚡ 1. SISTEMA DE ENERGIA

### Unidade: Quanta (Q)
- **1 Quanta = 100 FE** (configurável)
- Máquinas Quanta NÃO aceitam FE diretamente
- Máquinas Industrial (Tier 0) usam FE diretamente

### Conversores FE ↔ Quanta

| Máquina | Entrada | Saída | Taxa | Tier |
|---------|---------|-------|------|------|
| Energy Quantifier | FE | Quanta | 100 FE → 1 Q | Decoherent |
| Quanta Collapser | Quanta | FE | 1 Q → 100 FE | Decoherent |

### Geração de Quanta

| Gerador | Fonte | Taxa | Tier |
|---------|-------|------|------|
| Combustor | Carvão, Lava, Blaze | Gera FE | Industrial |
| Quanta Engine | Liquid Quanta/Chaos | 50-400 Q/t | Entangled |
| Gas Burner | Quanta Gas/Chaos Gas | 50-400 Q/t | Entangled |
| Void Generator | Flutuações do vácuo | 500-1.000 Q/t | Superposed |
| Entanglement Reactor | Entrelaçamento dimensional | 2.000-10.000+ Q/t | Superposed |

**Void Generator - por local:**

| Local | Geração (Q/t) |
|-------|---------------|
| Overworld superfície | 500 |
| Bedrock (Overworld) | 650 |
| Nether | 600 |
| End (ilhas) | 800 |
| End (void) | 1.000 |

---

## 💧 2. LÍQUIDOS E GASES QUÂNTICOS

### Líquidos - Cores e Propriedades

| Líquido | Cor | Obtenção | Propriedade Especial |
|---------|-----|----------|---------------------|
| **Liquid Quanta** | 💜 Roxo claro brilhante | Quantum Dust + Água | Base, neutro |
| **Liquid Chaos** | 💜 Roxo escuro profundo | Essence of Chaos + Lava (50%) | Balde quebra após 30s, dobra geração |
| **Liquid Order** | 💙 Ciano brilhante | Essence of Order + Água | Refrigeração eficiente |
| **Stabilized Liquid** | 💙 Ciano + partículas brancas | Liquid Order + Essence of Order | Refrigeração premium |
| **Entangled Liquid** | 💜 Roxo + partículas ciano | Liquid Chaos + Essence of Chaos | Balde teleporta, cross-dimensional |
| **Liquid Singularity** | ⚪ Branco + efeito arco-íris | Stabilized + Entangled + Nether Star | Cria singularidade temporária |

### Estabilização do Entanglement Reactor

| Líquido | Efeito | Consumo | Estabilidade |
|---------|--------|---------|--------------|
| Água | +5% estabilidade | 10 mB/t | Baixa |
| Liquid Quanta | +10% estabilidade, +5% geração | 5 mB/t | Média |
| Liquid Order | +20% estabilidade | 2 mB/t | Alta |
| Stabilized Liquid | +30% estabilidade | 1 mB/t | Muito Alta |
| Liquid Chaos | **-30% estabilidade, DOBRA geração** | 5 mB/t | Caótica |

### Gases (evaporados dos líquidos)

| Gás | Cor |
|-----|-----|
| Quanta Gas | Roxo claro transparente |
| Chaos Gas | Roxo escuro transparente |
| Order Gas | Ciano transparente |
| Entangled Gas | Roxo + partículas ciano |
| Stabilized Gas | Ciano + partículas brancas |
| Singularity Gas | Branco transparente cintilante |

### Tanques

| Tanque | Capacidade | Tipo |
|--------|------------|------|
| Quantum Tank (Decoherent) | 16.000 mB | Líquido |
| Quantum Tank (Entangled) | 64.000 mB | Líquido |
| Quantum Tank (Superposed) | 256.000 mB | Líquido |
| Quantum Tank (Singular) | 1.024.000 mB | Líquido |
| Containment Tank | 16.000 mB | Líquidos perigosos |
| Pressurized Tank (Decoherent) | 16.000 mB | Gás |
| Pressurized Tank (Entangled) | 64.000 mB | Gás |
| Pressurized Tank (Superposed) | 256.000 mB | Gás |
| Pressurized Tank (Singular) | 1.024.000 mB | Gás |
| Pressurized Containment Tank | 16.000 mB | Gases perigosos |

---

## 🏭 3. MÁQUINAS POR TIER

### Tier 0 - Industrial (FE)

| Máquina | Função | Receita |
|---------|--------|---------|
| Industrial Crusher | Pulveriza minérios, Ender Pearl, Blaze Rod | `[Frame] [Frame] [Frame]` / `[Piston] [StoneCuter] [Piston]` / `[Gear] [Redstone] [Gear]` |
| Industrial Press | Pressiona metais em placas | `[Frame] [Piston] [Frame]` / `[Piston] [ ] [Piston]` / `[Gear] [Redstone] [Gear]` |
| Industrial Wire Drawer | Transforma placas em fios | `[Frame] [Frame] [Frame]` / `[WireHook] [StoneCuter] [WireHook]` / `[Gear] [Redstone] [Gear]` |
| Combustor | Gera FE a partir de combustível | `[Frame] [Frame] [Frame]` / `[Frame] [Blast Furnace] [Frame]` / `[Gear] [Frame] [Gear]` |

### Tier 1 - Decoherent

| Máquina | Função | Consumo | Receita |
|---------|--------|---------|---------|
| Particle Reconstructor | Duplica dusts (2x-8x por tier) | 10 Q/t | `[Frame] [Processing] [Frame]` / `[Control] [] [Control]` / `[Frame] [Energy] [Frame]` |
| Quanta Infuser | Infunde materiais (Infusion system) | 20 Q/t | `[Frame] [Fluid] [Frame]` / `[Control] [Furnace] [Control]` / `[Frame] [Processing] [Frame]` |
| Quanta Collector | Gera Essences (passivo) | 0 (gera 5 Q/t) | `[Frame] [Fluid] [Frame]` / `[Control] [Dispenser] [Control]` / `[Frame] [Energy] [Frame]` |
| Quantum Assembler | Crafting table do mod | 15 Q/t | `[Frame] [Processing] [Frame]` / `[Control] [Crafting] [Control]` / `[Frame] [Energy] [Frame]` |
| Quantum Liquid Infuser | Produz líquidos quânticos | 20 Q/t | `[Frame] [Fluid] [Frame]` / `[Control] [Furnace] [Control]` / `[Frame] [Energy] [Frame]` |
| Energy Quantifier | FE → Quanta | 0 (converte) | `[Frame] [Energy] [Frame]` / `[Coil] [Redstone] [Coil]` / `[Frame] [Energy] [Frame]` |
| Quanta Collapser | Quanta → FE | 0 (converte) | `[Frame] [Energy] [Frame]` / `[Coil] [Redstone] [Coil]` / `[Frame] [Energy] [Frame]` |
| Quanta Enhancer | Quantum Dust → Quantum Pallet | 25 Q/t | `[Frame] [Processing] [Frame]` / `[Control] [Furnace] [Control]` / `[Frame] [Energy] [Frame]` |
| Quantum Evaporator | Líquido → Gás | 15 Q/t | `[Frame] [Fluid] [Frame]` / `[Control] [Furnace] [Control]` / `[Frame] [Energy] [Frame]` |
| Quantum Condenser | Gás → Líquido | 15 Q/t | `[Frame] [Fluid] [Frame]` / `[Control] [Furnace] [Control]` / `[Frame] [Energy] [Frame]` |

### Tier 2 - Entangled

| Máquina | Função | Consumo | Receita |
|---------|--------|---------|---------|
| Quantum Press | Prensagem eficiente | 30 Q/t | `[Frame] [Processing] [Frame]` / `[Control] [Press] [Control]` / `[Frame] [Energy] [Frame]` |
| Quantum Wire Drawer | Fios eficientes | 25 Q/t | `[Frame] [Processing] [Frame]` / `[Control] [Wire] [Control]` / `[Frame] [Energy] [Frame]` |
| Quanta Engine | Queima Liquid Quanta/Chaos | 0 (gera) | `[Frame] [Energy] [Frame]` / `[Control] [Furnace] [Control]` / `[Frame] [Fluid] [Frame]` |
| Gas Burner | Queima Quanta Gas/Chaos Gas | 0 (gera) | `[Frame] [Energy] [Frame]` / `[Control] [Furnace] [Control]` / `[Frame] [Fluid] [Frame]` |
| Entangler | Entrelaça máquinas ao sistema (wireless) | 40 Q/t | `[Frame] [Network] [Frame]` / `[Control] [Core] [Control]` / `[Frame] [Energy] [Frame]` |
| Tunnel Relay | Cabos atravessam blocos (P2P) | 0 Q/t | `[Frame] [Network] [Frame]` / `[Control] [Core] [Control]` / `[Frame] [Energy] [Frame]` |
| Void Generator | Geração passiva por local | 0 (gera) | `[Frame] [Energy] [Frame]` / `[Control] [Void] [Control]` / `[Frame] [Energy] [Frame]` |
| Quantum Liquid Mixer | Mistura líquidos | 30 Q/t | `[Frame] [Fluid] [Frame]` / `[Control] [Mixer] [Control]` / `[Frame] [Energy] [Frame]` |
| Quantum Forge | Processamento avançado de ligas | 3x3x3 | 100 Q/t |

### Tier 3 - Superposed

| Máquina | Função | Tamanho | Consumo |
|---------|--------|---------|---------|
| Quantum Gas Centrifuge | Separa gases em componentes | 1 bloco | 40 Q/t |
| Black Hole Forge | Cria Stabilized Singularity | 7x7x7 | 1.000 Q/t |
| Entanglement Reactor | Geração massiva de Quanta | 5x5x5 a 21x21x21 | 0 (gera 2k-10k+ Q/t) |

### Tier Ultimate - Singular

| Máquina | Função | Tamanho | Consumo |
|---------|--------|---------|---------|
| Quantum Plasma Forge | Processamento de ligas Endgame | 5x5x5 | 200 Q/t |
| Quantum Supercomputer | Rede global (256 conexões) | 1 bloco | 640 Q/t |

---

## 🔧 4. MÁQUINAS DE REDE (Entanglement Network)

| Componente | Função | Tier | Conexões |
|------------|--------|------|----------|
| Quantum Cables | Transporte físico de energia/items/líquidos/gás | Decoherent | - |
| Entangled Cables | Transporte de sinal de rede (interface com sistema) | Entangled | - |
| Signal Repeater | Extensor de alcance do Entangled Cable (+16 blocos) | Entangled | - |
| Quantum Link | Extensor de alcance wireless (+16 blocos) | Decoherent | - |
| Entangler | Conexão wireless de máquinas ao sistema | Entangled | - |
| Entanglement Switch | Central de conexões | Entangled | 32 |
| Quantum Terminal | Interface da rede (consome 1 slot) | Decoherent | - |
| Quantum Computer | Gerencia rede (consome 1 slot) | Entangled | - |
| Quantum Mainframe | Gerencia rede grande (consome 1 slot) | Superposed | - |
| Quantum Supercomputer | Rede global cross-dimensional (consome 1 slot) | Singular | - |

**Regras da Rede:**
- Múltiplos Switch adjacentes somam slots (multibloco: 5X5X5) 
- Entangled Cables têm limite de 16 blocos sem Signal Repeater
- Quantum Links podem ser colocados em série
- Quantum Cable NÃO canais

---

## 🔧 5. ADDONS

### Slots por tier

| Tier | Slots |
|------|-------|
| Decoherent | 2 |
| Entangled | 3 |
| Superposed | 4 |
| Singular | 6 |

### Addons disponíveis

| Addon | Efeito | Material Especial |
|-------|--------|-------------------|
| Speed Coil | +20% speed, +25% energy cost | - |
| Energy Coil | -20% energy cost | - |
| Stabilizer Coil | -5% failure | - |
| Quantum Lock | -10% failure | - |
| Observer Link | -15% failure | Observer Lens |
| Chaos Overdrive | +50% speed, +25% failure | Chaos Fragment |
| Chaos Injector | +50% geração, +25% risco (reator) | Chaos Fragment |
| Order Stabilizer | -30% risco, -10% geração (reator) | Essence of Order |
| Yield Addon | +15% secondary output | - |
| Fluid Addon | Processa líquidos/gases | - |

---

## 🗡️ 6. EQUIPAMENTOS MODULARES

### Tiers de Equipamentos

| Tier | Nome | Material | Capacidade Ferramenta | Capacidade Armadura | Slots |
|------|------|----------|----------------------|---------------------|-------|
| 1 | Decoherent | Quantum Steel | 10.000 Q | 5.000-15.000 Q | 3-6 |
| 2 | Entangled | Quantum Alloy | 25.000 Q | 10.000-30.000 Q | 4-8 |
| 3 | Superposed | Alloy + Entangled Core | 50.000 Q | 20.000-50.000 Q | 5-10 |
| Ultimate | Singular | Alloy + Stabilized Singularity | 100.000 Q | 25.000-75.000 Q | 6-12 |

### Ferramentas Base

| Equipamento | Slots | Capacidade Base |
|-------------|-------|-----------------|
| Quantum Pickaxe | 3-6 | 10.000-100.000 Q |
| Quantum Axe | 3-6 | 10.000-100.000 Q |
| Quantum Drill | 3-6 | 10.000-100.000 Q |
| Quantum Saw | 3-6 | 10.000-100.000 Q |
| Quantum Shovel | 3-6 | 10.000-100.000 Q |
| Quantum Sword | 3-6 | 10.000-100.000 Q |
| Quantum Katana | 3-6 | 10.000-100.000 Q |
| Quantum Claymore | 3-6 | 10.000-100.000 Q |
| Quantum Cannon | 3-6 | 10.000-100.000 Q |
| Quantum Grappler | 3-6 | 10.000-100.000 Q |
| Quantum Shield | 3-6 | 10.000-100.000 Q |

### Armadura Base

| Equipamento | Slots | Capacidade Base |
|-------------|-------|-----------------|
| Quantum Helmet | 4-8 | 5.000-25.000 Q |
| Quantum Chestplate | 6-12 | 15.000-75.000 Q |
| Quantum Leggings | 4-8 | 8.000-40.000 Q |
| Quantum Boots | 4-8 | 5.000-25.000 Q |

### Quantum Upgrade Table

Máquina específica para upgrade de equipamentos. Preserva módulos instalados.

**Receita:**
[Frame] [Processing] [Frame]
[Control] [Crafting] [Control]
[Frame] [Energy] [Frame]



**Upgrade Kits:**

| Kit | Receita (Quantum Assembler) | Uso |
|-----|----------------------------|-----|
| Decoherent Kit | `[Plate] [Core] [Plate]` / `[Circuit] [Alloy] [Circuit]` / `[Plate] [Core] [Plate]` | Base |
| Entangled Kit | `[Decoherent Kit] [Core] [Decoherent Kit]` / `[Core] [Alloy] [Core]` / `[Decoherent Kit] [Core] [Decoherent Kit]` | Decoherent → Entangled |
| Superposed Kit | `[Entangled Kit] [Alloy] [Entangled Kit]` / `[Alloy] [Chaos] [Alloy]` / `[Entangled Kit] [Alloy] [Entangled Kit]` | Entangled → Superposed |
| Singular Kit | `[Superposed Kit] [Singularity] [Superposed Kit]` / `[Singularity] [Alloy] [Singularity]` / `[Superposed Kit] [Singularity] [Superposed Kit]` | Superposed → Singular |

**Funcionamento do Upgrade:**
- Coloque ferramenta/armadura no slot de input
- Coloque o Upgrade Kit correspondente
- Coloque os materiais necessários
- Consome 5.000 Q
- Preserva módulos, encantamentos e nome

---

## 📦 7. MÓDULOS

### Módulos de Mineração

| Módulo | Efeito | Tier I | Tier II | Tier III |
|--------|--------|--------|---------|----------|
| Tunnel | Mina área | 2x2 | 3x3 | 5x5 |
| Vein Miner | Mina veio | 16 blocos | 32 blocos | 64 blocos |
| Quantum Sight | Revela minérios | 8 blocos | 16 blocos | 32 blocos |
| Auto-Smelt | Funde | 1x | 2x | 3x |
| Silk Touch | Toque suave | 1x | 1x + speed | 2x + speed |

### Módulos de Combate

| Módulo | Efeito | Tier I | Tier II | Tier III |
|--------|--------|--------|---------|----------|
| Chaos Strike | Dano triplicado (chance) | 15% | 25% | 35% |
| Order Strike | Dano consistente | +25% | +50% | +75% |
| Leech | Rouba vida | 10% | 20% | 30% |

### Módulos de Utilidade

| Módulo | Efeito | Tier I | Tier II | Tier III |
|--------|--------|--------|---------|----------|
| Magnet | Puxa itens | 4 blocos | 8 blocos | 16 blocos |
| Speed | Velocidade | +20% | +35% | +50% |
| Experience | Bônus XP | +25% | +50% | +75% |

### Módulos de Defesa

| Módulo | Efeito | Tier I | Tier II | Tier III |
|--------|--------|--------|---------|----------|
| Quantum Shield | Absorve dano | 20% | 40% | 80% |
| Anti-Knockback | Sem knockback | 60% | 80% | 100% |

### Módulos Especiais

| Módulo | Efeito | Tier |
|--------|--------|------|
| Radar | Detecta entidades | Entangled |
| Step Assist | Sobe blocos automaticamente | Decoherent |

### Upgrade de Módulos (Quantum Upgrade Table)

- 1 módulo anterior + 4 materiais do tier + Quantum Processor
- **NÃO** precisa de 9 módulos do mesmo tipo

---

## 🔌 8. QUANTUM AUTOMATION BLOCKS

Blocos específicos que resolvem problemas reais do Quanta.

| Bloco | Função | Order/Chaos |
|-------|--------|-------------|
| Quantum Redstone Relay | Quanta (0-100) ↔ Redstone (0-15) | ✅ Order |
| Quantum Energy Monitor | Monitora energia da rede, emite redstone | ✅ Order |
| Quantum Storage Monitor | Monitora itens/líquidos no sistema | ✅ Order |
| Quantum Mode Switcher | Alterna Chaos/Order de máquinas via redstone | ⚠️ Ambos |

**Receitas:**

**Quantum Redstone Relay:**
[ ] [Wire] [ ]
[Circuit] [Comparator] [Circuit]
[ ] [Redstone] [ ]



**Quantum Energy Monitor:**
[ ] [Tester] [ ]
[Circuit] [Redstone] [Circuit]
[ ] [Redstone] [ ]



**Quantum Storage Monitor:**
[ ] [Scanner] [ ]
[Circuit] [Redstone] [Circuit]
[ ] [Redstone] [ ]



**Quantum Mode Switcher:**
[ ] [Wire] [ ]
[Order] [Circuit] [Chaos]
[ ] [Redstone] [ ]



---

## 🦾 9. IMPLANTES CIBERNÉTICOS

Obtidos na **Quantum Cyber Chamber** (multibloco 3x3x3)

**Quantum Cyber Chamber:**
- Centro (em baixo): Cyber Chamber Controller
- Estrutura: Quantum Casing (26 blocos), Quantum Glass Pane (8 Blocos)
- Consome 100 Q/t quando em uso

**Receita do Controller:**
[Frame] [Processing] [Frame]
[Control] [ ] [Control]
[Frame] [Energy] [Frame]



### Implantes por Tier

| Implante | Efeito | Tier | Custo | Componentes |
|----------|--------|------|-------|-------------|
| Quantum Sight | Ver conexões do sistema e cabos pela parede (8-32 blocos) | Entangled | 500 Q | Neural Interface + Optical Core + Lens |
| Quantum Reach | Alcance estendido (+3 a +8 blocos) | Entangled | 500 Q | Neural Interface + Core + Core |
| Quantum Speed | +20% a +50% velocidade | Entangled | 500 Q | Neural Interface + Motor Implant + Speed Coil |
| Quantum Strength | +20% a 50% força | Entangled | 500 Q | Neural Interface + Motor Implant + Chaos Fragment |
| Quantum Resistance | +20% a 50% resistência | Entangled | 500 Q | Neural Interface + Armor Core + Alloy |
| Quantum Lungs | Respiração infinita | Entangled | 500 Q | Neural Interface + Fluid Core + Liquid Quanta |
| Quantum Night Vision | Visão noturna permanente (Evolução do Sight) | Entangled | 500 Q | Neural Interface + Optical Core + Essence of Order |
| Quantum Tunneling | Atravessar paredes no shift | Superposed | 2.000 Q | Neural Interface + Tunneling Core + Chaos Fragment |
| Quantum Flight | Voo criativo | Superposed | 2.000 Q | Neural Interface + Flight Core + Stabilized Singularity |
| Quantum Buffer | +10.000 a +50.000 Q de reserva | Superposed | 2.000 Q | Neural Interface + Energy Core + Capacitor |
| Quantum Cortex | Codex integrado + craft remoto + knowledge bônus | Singular | 10.000 Q | Neural Interface + Processing Core + Quantum Processor |

---

## 📖 10. QUANTUM CODEX & SPELLS

### Quantum Codex

**Receita:**
`[ ] [ ] [ ]` / `[Quantum Dust] [Book] [ ]` / `[ ] [ ] [ ]`



**Propriedades:**
- Buffer de 10.000 Q
- Ganha knowledge (XP) observando máquinas
- Equipa até 6 spells

### Spells Base

| Spell | Combinação | Custo | Cooldown | Efeito |
|-------|------------|-------|----------|--------|
| Infuse | Self + Neutral + Infusion | 200 Q | 5s | Infunde item com 1.000 Q |
| Reveal | Self + Order + Revelation | 75 Q | 10s | Revela entidades escondidas |
| Accelerate | Touch + Neutral + Acceleration | 150 Q | 20s | Acelera máquina por 30s |
| Stabilize | Touch + Order + Stabilization | 200 Q | 30s | -10% falha por 60s |
| Teleport | Self + Chaos + Teleport | 250 Q | 15s | Teletransporte curto |
| Link | Self + Chaos + Link | 500 Q | 30s | Conexão temporária na rede |

### Equations para Black Board

| Tipo | Opções | Custo Base |
|------|--------|------------|
| Forma | Self, Projectile, Touch, Area | 50-200 Q |
| Elemento | Neutral, Order (+25% custo, -10% falha), Chaos (-25% custo, +15% falha), Singularity (+100% custo, 0% falha, +100% poder) | 0-100% |
| Efeito | Damage, Heal, Teleport, Acceleration, Infusion, Revelation, Stabilization | 50-250 Q |

### Singularity Spells

| Forma + Efeito | Resultado |
|----------------|-----------|
| Self + Damage | Buff: +200% dano corpo a corpo (30s) |
| Self + Heal | Buff: Cura 10/s (10s), imortal |
| Self + Teleport | Teletransporte seguro (cama/spawn) |
| Self + Acceleration | Buff: +300% speed (20s) |
| Self + Infusion | Buff: Indestrutível, voo, suga itens (30s) |
| Self + Revelation | Visão 100 blocos (60s) |
| Self + Stabilization | Remove efeitos negativos, imunidade (120s) |
| Projectile + Damage | Projétil perfurante (dano 50) + buraco negro (100 dano) |
| Projectile + Heal | Projétil cura 50 + campo de cura (20/s por 5s) |
| Projectile + Teleport | Projétil envia alvo ao void |
| Projectile + Acceleration | Projétil rápido, alvo fica lento (-80% speed 10s) |
| Projectile + Infusion | Projétil infunde item do alvo com Singularity |
| Projectile + Revelation | Projétil revela área ao redor (20 blocos, 30s) |
| Projectile + Stabilization | Projétil remove buracos negros no impacto |
| Touch + Damage | Alvo sofre 150 de dano |
| Touch + Heal | Alvo cura 100 de dano |
| Touch + Teleport | Alvo enviado ao void |
| Touch + Acceleration | Alvo ganha +200% speed (30s) ou fica lento |
| Touch + Infusion | Item do alvo infundido com Singularity |
| Touch + Revelation | Alvo fica marcado (rastreável por 60s) |
| Touch + Stabilization | Alvo tem efeitos negativos removidos |
| Area + Damage | Buraco negro (100 dano) em todos no raio |
| Area + Heal | Cura 50 + campo de cura em todos no raio |
| Area + Teleport | Rasgo dimensional: todos no raio vão ao void |
| Area + Acceleration | Inversão gravitacional: todos flutuam 10s |
| Area + Infusion | Todos itens no chão infundidos |
| Area + Revelation | Revela entidades/minérios no raio 30 blocos |
| Area + Stabilization | Remove buracos negros em 32 blocos |

---

## 🔧 11. FERRAMENTAS DE CONFIGURAÇÃO

| Ferramenta | Função | Indestrutível | Receita |
|------------|--------|---------------|---------|
| Quantum Wrench | Configuração de máquinas, entrelaçamento | ✅ | `[Head]` / `[Core]` / `[Handle]` (padrão ferramenta) |
| Quantum Screwdriver | Ajustes finos (velocidade, modo Chaos/Order) | ✅ | `[ ] [Head] [ ]` / `[ ] [Core] [ ]` / `[ ] [Handle] [ ]` |
| Quantum Multitool | Combina Wrench + Screwdriver | ✅ | `[Wrench] [Screwdriver] [ ]` / `[ ] [ ] [ ]` / `[ ] [ ] [ ]` |
| Quantum Tester | Mede energia, falha, addons | ✅ | `[Circuit] [Redstone] [Circuit]` / `[Glass] [Redstone] [Glass]` / `[Steel] [Redstone] [Steel]` |
| Quantum Scanner | Análise avançada de redes | ✅ | `[ ] [ ] [ ]` / `[Tester] [Lens] [ ]` / `[ ] [ ] [ ]` |

---

## 🎨 12. VISUAL

### Paleta de cores por Tier

| Tier | Cor | Efeito Visual |
|------|-----|---------------|
| Decoherent | Roxo médio (#9D4EDD) | Brilho suave |
| Entangled | Roxo + Ciano | Brilho pulsante |
| Superposed | Roxo + Ciano + Dourado | Luz ciano + partículas |
| Singular | Ciano + Dourado + Branco | Efeito arco-íris suave |

### Líquidos

| Líquido | Cor |
|---------|-----|
| Liquid Quanta | 💜 Roxo claro brilhante (#B464FF) |
| Liquid Chaos | 💜 Roxo escuro profundo (#4B0082) |
| Liquid Order | 💙 Ciano brilhante (#00FFFF) |
| Stabilized Liquid | 💙 Ciano + partículas brancas |
| Entangled Liquid | 💜 Roxo + partículas ciano |
| Liquid Singularity | ⚪ Branco + transições arco-íris |

### Efeitos especiais

- **Superposição (Chaos):** partículas duplicadas, luz pulsante, ura borrada
- **Decoerência (Order):** partículas estáveis, luz constante
- **Entrelaçamento:** linha de partículas, anel giratório
- **Addons visíveis:** componentes 3D na máquina
- **Explosão do Reator:** tela roxa, efeito de nausea, mundo mais lento

---

## ✅ 13. VERIFICAÇÃO RÁPIDA DE PROGRESSÃO

- [ ] Ender Pearl → Ender Dust (Industrial Crusher)
- [ ] Quantum Dust (Ender Dust + Redstone)
- [ ] Quantum Steel (Quanta Infuser)
- [ ] Quantum Crystal (Quanta Infuser + Diamond)
- [ ] Essence of Order/Chaos (Quanta Collector)
- [ ] Entangled Core (Quantum Assembler)
- [ ] Quantum Processor (Quantum Assembler)
- [ ] Stabilized Singularity (Black Hole Forge)
- [ ] Quantum Alloy (Quanta Infuser + Netherite)
- [ ] Decoherent Tools/Armor (craft base)
- [ ] Entangled Tools/Armor (upgrade via Entangled Kit)
- [ ] Superposed Tools/Armor (upgrade via Superposed Kit)
- [ ] Singular Tools/Armor (upgrade via Singular Kit)
- [ ] Quantum Supercomputer
- [ ] Singularity Cell
- [ ] All Cybernetic Implants

---

## 📋 14. COMPATIBILIDADE (Planejada)

- JEI/REI, The One Probe/Jade
- AE2, Mekanism, Create, Thermal, XNet
- Immersive, Refined, Pipez, LaserIO
- Curios API
- KubeJS / CraftTweaker
- UniDict (padronização de minérios)

---

*Este documento é a fonte de verdade para o desenvolvimento do Quanta Mod*