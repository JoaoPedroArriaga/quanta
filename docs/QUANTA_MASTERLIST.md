# QUANTA MOD - MASTERLIST OFICIAL (v8.1)

> Documento consolidado do mod Quanta para Minecraft NeoForge 1.21.1
> **Última atualização: 2026-06-06**

---

## 🎯 CONCEITO CENTRAL

Um mod techno-mágico onde mecânica quântica e magia arcana são a mesma coisa vista de ângulos diferentes. Quanta é uma **partícula que altera informação** - pode reescrever a realidade.

**Inspirações:** Mekanism, Oritech, Create, EnderIO, Ars Nouveau, AE2, Modular Powersuits, Draconic Evolution, Thaumcraft.

**Mecânica central:** Chaos / Order - alternância de modo nas máquinas (risco vs recompensa)

| Modo | Característica | Failure Chance | Bônus |
|------|----------------|----------------|-------|
| **Order** | Estável, previsível (Decoerência) | 0% | Estabilidade máxima |
| **Chaos** | Instável, superposição | +15% a +25% | +50% speed/geração, dobra geração em geradores |

---

## 📊 PROGRESSÃO DE TIERS

| Tier | Nome | Cor | Característica |
|------|------|-----|----------------|
| 0 | Industrial | Cinza | FE-based, pré-Quanta |
| 1 | Decoherent | Roxo médio | Estável, previsível |
| 2 | Entangled | Roxo + Ciano | Entrelaçado, conectado |
| 3 | Superposed | Roxo + Ciano + Dourado | Múltiplos estados simultâneos |
| Ultimate | Singular | Ciano + Dourado + Branco | Ponto de equilíbrio absoluto |

**Progressão paralela** - jogador pode pular tiers com criatividade.

Toda maquinaria Quanta usa o sistema de tiers e pode ser atualizada com Upgrade Kits.

---

## ⚡ 1. SISTEMA DE ENERGIA

### Unidade: Quanta (Q)
- **1 Quanta = 100 FE** (configurável)
- Máquinas Quanta NÃO aceitam FE diretamente
- Máquinas Industrial (Tier 0) usam FE diretamente

### Conversores FE ↔ Quanta

| Máquina | Entrada | Saída | Taxa |
|---------|---------|-------|------|
| Energy Quantifier | FE | Quanta | 100 FE → 1 Q |
| Quanta Collapser | Quanta | FE | 1 Q → 100 FE |

### Geração de Quanta

| Gerador | Fonte | Taxa |
|---------|-------|------|
| Combustor | Carvão, Lava, Blaze | Gera FE |
| Quanta Engine | Liquid Quanta/Chaos | 50-400 Q/t |
| Gas Burner | Quanta Gas/Chaos Gas | 50-400 Q/t |
| Void Generator | Flutuações do vácuo | 500-1.000 Q/t |
| Entanglement Reactor | Entrelaçamento dimensional | 2.000-10.000+ Q/t |

**Void Generator - por local:**

| Local | Geração (Q/t) |
|-------|---------------|
| Overworld superfície | 500 |
| Bedrock (Overworld) | 650 |
| Nether | 600 |
| End (ilhas) | 800 |
| End (void) | 1.000 |

### Quanta Storage

| Bateria | Storage |
|---------|---------|
| Quanta Cell (T1) | 10.000 Q |
| Quanta Cell (T2) | 1.000.000 Q |
| Quanta Cell (T3) | 100.000.000 Q |
| Quanta Cell (T4) | 10.000.000.000 Q |
| Entangled Cell (T1) | 10.000 Q |
| Entangled Cell (T2) | 1.000.000 Q |
| Entangled Cell (T3) | 100.000.000 Q |
| Entangled Cell (T4) | 10.000.000.000 Q |

---

## 💧 2. LÍQUIDOS E GASES QUÂNTICOS

### Líquidos - Cores e Propriedades

| Líquido | Obtenção | Propriedade Especial |
|---------|----------|---------------------|
| **Liquid Quanta** | Quantum Dust + Água | Base, neutro |
| **Liquid Chaos** | Essence of Chaos + Lava (50%) ou Quantum Liquid Mixer (Quantum Dust + Chaos Fragment) | Balde quebra após 30s, dobra geração |
| **Liquid Order** | Essence of Order + Água | Refrigeração eficiente |
| **Stabilized Liquid** | Liquid Order + Essence of Order | Refrigeração premium |
| **Entangled Liquid** | Liquid Chaos + Essence of Chaos | Balde teleporta, cross-dimensional |
| **Liquid Singularity** | Stabilized + Entangled + Nether Star | Cria singularidade temporária |

### Gases (evaporados dos líquidos)

| Gás |
|-----|
| Quanta Gas |
| Chaos Gas |
| Order Gas |
| Entangled Gas |
| Stabilized Gas |
| Singularity Gas |

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

## 🏭 3. MÁQUINAS

### Industrial (FE)

| Máquina | Função | Receita (resumo) |
|---------|--------|------------------|
| Industrial Crusher | Pulveriza minérios, Ender Pearl, Blaze Rod | Frame + Piston + Stonecutter + Gear + Redstone |
| Industrial Press | Pressiona metais em placas | Frame + Piston + Gear + Redstone |
| Industrial Wire Drawer | Transforma placas em fios | Frame + WireHook + Stonecutter + Gear + Redstone |
| Combustor | Gera FE a partir de combustível | Frame + Blast Furnace + Gear |

### Máquinas Quanta (Tier 1-4)

| Máquina | Função | Consumo | Tier |
|---------|--------|---------|------|
| Matter Rewriter (antigo Particle Reconstructor) | Duplica dusts (2x-8x por tier) | 10 Q/t | Decoherent |
| Quanta Infuser | Infunde materiais | 20 Q/t | Decoherent |
| Essence Collector | Gera Essences (passivo) | 0 (gera 5 Q/t) | Decoherent |
| Quantum Assembler | Crafting table do mod | 15 Q/t | Decoherent |
| Circuit Assembler | Crafting de circuitos avançados | 15 Q/t | Decoherent |
| Quantum Liquid Infuser | Produz líquidos quânticos | 20 Q/t | Decoherent |
| Energy Quantifier | FE → Quanta | 0 | Decoherent |
| Quanta Collapser | Quanta → FE | 0 | Decoherent |
| Quanta Enhancer | Quantum Dust → Quantum Pallet | 25 Q/t | Decoherent |
| Quantum Evaporator | Líquido → Gás | 15 Q/t | Decoherent |
| Quantum Condenser | Gás → Líquido | 15 Q/t | Decoherent |
| Quantum Press | Prensagem eficiente | 30 Q/t | Decoherent |
| Quantum Wire Drawer | Fios eficientes | 25 Q/t | Decoherent |
| Quanta Engine | Queima Liquid Quanta/Chaos | 0 (gera) | Decoherent |
| Gas Burner | Queima Quanta Gas/Chaos Gas | 0 (gera) | Decoherent |
| Entangler | Entrelaça máquinas ao sistema (wireless) | 40 Q/t | Entangled |
| Tunnel Relay | Cabos atravessam blocos (P2P) | 0 Q/t | Entangled |
| Void Generator | Geração passiva por local | 0 (gera) | Entangled |
| Quantum Liquid Mixer | Mistura líquidos | 30 Q/t | Entangled |
| Quantum Forge | Processamento avançado de ligas (3x3x3) | 100 Q/t | Superposed |
| Quantum Gas Centrifuge | Separa gases em componentes | 40 Q/t | Entangled |
| Graviton Stabilizer | Cria Stabilized Singularity (7x7x7) | 1.000 Q/t | Singular |
| Entanglement Reactor | Geração massiva de Quanta (5x5x5 a 21x21x21) | 0 (gera) | Superposed |
| Black Hole Forge | Processamento Endgame (5x5x5) | 200 Q/t | Singular |

### Máquinas de Experimento

| Máquina | Tier | Tamanho | Função | Consumo |
|---------|------|---------|--------|---------|
| Quantum Tester | Decoherent | 1 bloco | Testar compatibilidade de materiais | 10 Q/t |
| Particle Collider | Entangled | Multibloco (círculo ou linha, sem limite) | Colidir materiais (2+ injectors) | Variável |
| Wave Function Collapser | Superposed | 3x3x3 | Colapsar superposição | 100 Q/t |
| Quantum Analyzer | Entangled | 1 bloco | Analisar item/receitas (opcional) | 5 Q/t |

**Detalhe do Particle Collider:**
- Geometrias: Circular (Synchrotron) ou Linear (LINAC)
- Mínimo: raio 3 (circular) ou 5 blocos (linear)
- Suporta **múltiplos injectors** (fusão ternária, quaternária, etc.)
- Collision Chamber pode ser posicionada em diferentes locais para alterar resultados
- Partículas sempre na cor ciano (#00F5FF) independente do material
- **Não gera energia** - apenas experimentos e descobertas

### Quantum Forge (Superposed, 3x3x3)

**Combustível:** Liquid Chaos (~10 mB por craft) + Quanta (100 Q/t)
**Inputs:** 2 slots de item
**Output:** 1 slot

**Receitas (com lore):**

| Input 1 | Input 2 | Output |
|---------|---------|--------|
| Quantum Steel | Ender Pearl | Entangled Steel |
| Quantum Alloy | Chaos Fragment | Chaotic Alloy |
| Netherite Ingot | Quantum Crystal | Quantum Netherite |
| Diamond | Liquid Quanta (via tank) | Quantum Diamond |

### Black Hole Forge (Singular, 5x5x5)

**Catalisador permanente:** Stabilized Singularity (não consome)
**Energia:** Quanta (200 Q/t)
**Inputs:** 2 slots de item
**Output:** 1 slot

**Receitas (com lore):**

| Input 1 | Input 2 | Output |
|---------|---------|--------|
| Entangled Steel | Stabilized Essence | Singular Steel |
| Quantum Netherite | Observer Lens | Observer's Alloy |
| Chaotic Alloy | Order Essence | Stabilized Alloy |
| Nether Star | Entangled Core | Stabilized Singularity |

---

## 🔧 4. MÁQUINAS DE REDE (Entanglement Network)

| Componente | Função | Conexões |
|------------|--------|----------|
| Entangler | Conexão wireless de máquinas ao sistema (bloco separado adjacente) | - |
| Entanglement Switch | Central de conexões | 32 |
| Quantum Terminal | Interface da rede (consome 1 slot) | - |
| Quantum Link | Extensor de alcance wireless (+16 blocos) | - |
| Quantum Computer | Gerencia rede (consome 1 slot) | - |
| Quantum Mainframe | Gerencia rede grande (consome 1 slot) | - |
| Quantum Supercomputer | Rede global cross-dimensional (consome 1 slot) | - |

**Regras da Rede:**
- Rede é **obrigatória** para automação avançada (pode ser substituída por AE2/RS)
- Sem cabos físicos - entanglers ligam máquinas ao switch
- Alcance limitado, aumentado por computadores
- Transmissão: Quanta, itens, líquidos, gases, dados (redstone)
- Terminal tem abas: craft/inventário (AE2-style), gerenciamento de conexões, crafts automáticos

---

## 🔧 5. ADDONS

### Slots por tier

| Tier | Slots |
|------|-------|
| Decoherent | 2 |
| Entangled | 3 |
| Superposed | 4 |
| Singular | 6 |

Máximo 4 addons do mesmo tipo por slot.

### Addons disponíveis

| Addon | Efeito | Material Especial |
|-------|--------|-------------------|
| Speed Coil | +20% speed, +25% energy cost | Quantum Coil + Redstone |
| Energy Coil | -20% energy cost | Quantum Capacitor + Redstone |
| Stabilizer Coil | -5% failure | - |
| Quantum Lock | -10% failure | - |
| Observer Link | -15% failure | Observer Lens |
| Chaos Overdrive | +50% speed, +25% failure | Chaotic Coil (craft com Chaos Fragment) |
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

### Ferramentas Base (craft direto com placas e núcleos)

| Equipamento | Slots | Capacidade Base |
|-------------|-------|-----------------|
| Quantum Pickaxe, Axe, Drill, Saw, Shovel, Sword, Katana, Claymore, Cannon, Grappler, Shield | 3-6 | 10.000-100.000 Q |

### Armadura Base

| Equipamento | Slots | Capacidade Base |
|-------------|-------|-----------------|
| Quantum Helmet | 4-8 | 5.000-25.000 Q |
| Quantum Chestplate | 6-12 | 15.000-75.000 Q |
| Quantum Leggings | 4-8 | 8.000-40.000 Q |
| Quantum Boots | 4-8 | 5.000-25.000 Q |

### Módulos (encaixáveis na Quantum Upgrade Table)

**Mineração:**

| Módulo | Efeito | Tier I | II | III |
|--------|--------|--------|----|-----|
| Tunnel | Mina área | 2x2 | 3x3 | 5x5 |
| Vein Miner | Mina veio | 16 blocos | 32 | 64 |
| Quantum Sight | Revela minérios | 8 blocos | 16 | 32 |
| Auto-Smelt | Funde | 1x | 2x | 3x |
| Silk Touch | Toque suave | 1x | 1x+speed | 2x+speed |

**Combate:**

| Módulo | Efeito | I | II | III |
|--------|--------|---|---|-----|
| Chaos Strike | Dano triplicado (chance) | 15% | 25% | 35% |
| Order Strike | Dano consistente | +25% | +50% | +75% |
| Leech | Rouba vida | 10% | 20% | 30% |

**Utilidade:**

| Módulo | Efeito | I | II | III |
|--------|--------|---|---|-----|
| Magnet | Puxa itens | 4 blocos | 8 | 16 |
| Speed | Velocidade | +20% | +35% | +50% |
| Experience | Bônus XP | +25% | +50% | +75% |

**Defesa:**

| Módulo | Efeito | I | II | III |
|--------|--------|---|---|-----|
| Quantum Shield | Absorve dano | 20% | 40% | 80% |
| Anti-Knockback | Sem knockback | 60% | 80% | 100% |

**Especiais:**

| Módulo | Efeito | Tier |
|--------|--------|------|
| Radar | Detecta entidades | Entangled |
| Step Assist | Sobe blocos automaticamente | Decoherent |

### Upgrade de Equipamentos (Quantum Upgrade Table)

- Preserva módulos e encantamentos
- Requer Upgrade Kit (Decoherent → Entangled → Superposed → Singular)
- Consome Quanta

---

## 🦾 7. IMPLANTES CIBERNÉTICOS

Obtidos na **Quantum Cyber Chamber** (multibloco 3x3x3 com entradas de Chaos Gas e Order Gas).

**Características:**
- **Permanentes** (podem ser desativados sem custo no menu do jogador)
- **Passivos e gratuitos** após instalados
- **Sem limite de quantidade** (objetivo endgame: tornar-se um "deus")
- **Visíveis** no modelo do jogador
- Menu de implantes só aparece após instalar **Quantum Cortex** (primeiro implante)

| Implante | Efeito | Tier | Custo | Componentes |
|----------|--------|------|-------|-------------|
| Quantum Sight | Ver conexões do sistema e cabos pela parede (8-32 blocos) | Entangled | 500 Q | Neural Interface + Optical Core + Lens |
| Quantum Reach | Alcance estendido (+3 a +8 blocos) | Entangled | 500 Q | Neural Interface + Core + Core |
| Quantum Speed | +20% a +50% velocidade | Entangled | 500 Q | Neural Interface + Motor Implant + Speed Coil |
| Quantum Strength | +20% a 50% força | Entangled | 500 Q | Neural Interface + Motor Implant + Chaos Fragment |
| Quantum Resistance | +20% a 50% resistência | Entangled | 500 Q | Neural Interface + Armor Core + Alloy |
| Quantum Lungs | Respiração infinita | Entangled | 500 Q | Neural Interface + Fluid Core + Liquid Quanta |
| Quantum Night Vision | Visão noturna permanente | Entangled | 500 Q | Neural Interface + Optical Core + Essence of Order |
| Quantum Tunneling | Atravessar paredes no shift | Superposed | 2.000 Q | Neural Interface + Tunneling Core + Chaos Fragment |
| Quantum Flight | Voo criativo | Superposed | 2.000 Q | Neural Interface + Flight Core + Stabilized Singularity |
| Quantum Buffer | +10.000 a +50.000 Q de reserva | Superposed | 2.000 Q | Neural Interface + Energy Core + Capacitor |
| Quantum Cortex | Codex integrado + craft remoto + knowledge bônus | Singular | 10.000 Q | Neural Interface + Processing Core + Quantum Processor |

---

## 📖 8. QUANTUM CODEX & EQUATIONS

### Quantum Codex

**Item segurável** (como livro do Ars Nouveau). Buffer de 10.000 Q. Ganha knowledge (XP) observando máquinas (1 knowledge por tick). Equipa até **6 equations**.

### Black Board

**Bloco no mundo** onde o jogador cria Equations. Símbolos (Forma, Elemento, Efeito) na lateral; jogador arrasta para o centro, conectando-os. Equations ficam salvas no Black Board (lado direito da GUI). Jogador arrasta para o "inventário" do Codex (parte inferior da GUI) para equipar.

### Equations

**Estrutura:** Forma + Elemento + Efeito

**Formas (4):** Self, Projectile, Touch, Area

**Elementos (3):**

| Elemento | Custo | Failure | Poder |
|----------|-------|---------|-------|
| Order | +25% | 0% | Base |
| Chaos | -25% | +15% | +25% |
| Singularity | +100% | 0% | +100% |

**Efeitos (7):** Damage, Heal, Teleport, Acceleration, Infusion, Revelation, Stabilization

**Total de combinações:** 4 × 3 × 7 = **84**

### Tabela de Efeitos (resumo)

#### Self (afeta o próprio jogador)

| Efeito | Order | Chaos | Singularity |
|--------|-------|-------|-------------|
| Damage | +75% dano, +20% resistência (30s) | +100% dano, +25% dano recebido | +200% dano, imortal, queima alvos (15s) |
| Heal | Cura 12 + regen (10s) | Cura 4 + absorção 8 | Cura total, regen V, absorção 20 (20s) |
| Teleport | Teleporte + câmera lenta | Teleporte aleatório + nausea | Teleporte para qualquer coordenada |
| Acceleration | +75% speed, step assist | +125% speed, visão borrada | +300% speed, passa por blocos |
| Infusion | +100% Quanta regen, -25% custo | +200% regen, 25% falha | Voo criativo, indestrutível, suga itens |
| Revelation | Visão 32 blocos + entidades | Visão 64 blocos + tinnitus | Visão 100 blocos, máquinas, líquidos |
| Stabilization | Remove efeitos + imunidade (30s) | Remove + transfere | Remove tudo + imunidade total (120s) |

#### Projectile (dispara projétil)

| Efeito | Order | Chaos | Singularity |
|--------|-------|-------|-------------|
| Damage | Perfurante (dano 20, 3 alvos) | Explosivo (dano 30, raio 3) | Buraco negro (dano 50, suga itens) |
| Heal | Cura 15 + escudo 10 | Cura 5 + regen área 2 | Cura 50 + campo de cura (20/s, 5s) |
| Teleport | Alvo → sua posição | Alvo → void (ou dano 50) | Rasgo dimensional (alvo ao void) |
| Acceleration | Rápido, paralisa (3s) | Errante, inverte controles | Câmera lenta 90% (15s) |
| Infusion | 50% infundir item | Infunde + dropa Chaos | 100% infunde com Singularity |
| Revelation | Revela área + marca alvo | Revela + nausea | Revela tudo em 20 blocos (60s) |
| Stabilization | Remove + resistência (30s) | Remove + transfere | Remove todos em 5 blocos |

#### Touch (toque no alvo)

| Efeito | Order | Chaos | Singularity |
|--------|-------|-------|-------------|
| Damage | 30 dano + knockback | 15-60 aleatório | 150 dano, ignora armadura |
| Heal | Cura 30 + regen (20s) | 10-50 aleatório | Cura 100 + remove todos negativos |
| Teleport | Alvo → sua posição | Alvo → Nether/End | Insta-kill mobs (void) |
| Acceleration | +100% speed, step assist | +200% speed + dano fadiga | Câmera lenta global 80% (15s) |
| Infusion | 75% infundir item | Infunde + dano 10 | Infunde + duplica item |
| Revelation | Marca + revela inventário | Marca + partículas | Revela tudo (HP, itens, localização) |
| Stabilization | Remove + resistência (60s) | Remove + transfere | Remove todos em 10 blocos |

#### Area (área ao redor)

| Efeito | Order | Chaos | Singularity |
|--------|-------|-------|-------------|
| Damage | Dano 20, raio 6 (só hostis) | Dano 25, raio 8 (todos) | Buraco negro dano 100, raio 10 |
| Heal | Cura 15 + regen, raio 6 | Cura 5 + dano 5 hostis | Cura 50 + campo 20/s, raio 6 |
| Teleport | Todos raio 4 → pontos seguros | 30% vão ao void, raio 5 | Todos raio 8 → void |
| Acceleration | +75% speed, raio 6 | +125% speed + nausea, raio 8 | Gravidade reversa, flutuar 15s |
| Infusion | 50% infundir itens chão, raio 6 | 100% mas 20% sumir, raio 8 | Todos itens infundidos + duplicados |
| Revelation | Minérios + entidades, raio 12 | Tudo + nausea | Omni scan raio 30 (60s) |
| Stabilization | Remove + imunidade, raio 8 | Remove + transfere mobs | Remove buracos negros + efeitos raio 32 |

**Custo de Quanta:** varia conforme efeito (50-400 Q). Modificadores: Order +25%, Chaos -25%, Singularity +100%.

---

## 🔌 9. QUANTUM AUTOMATION BLOCKS

| Bloco | Função |
|-------|--------|
| Quantum Redstone Relay | Quanta (0-100) ↔ Redstone (0-15) |
| Quantum Energy Monitor | Monitora energia da rede, emite redstone |
| Quantum Storage Monitor | Monitora itens/líquidos no sistema |
| Quantum Mode Switcher | Alterna Chaos/Order via redstone |

---

## 🔧 10. FERRAMENTAS DE CONFIGURAÇÃO

| Ferramenta | Função | Indestrutível |
|------------|--------|---------------|
| Quantum Wrench | Configuração de máquinas, entrelaçamento | ✅ |
| Quantum Tester | Testes de experimento | ✅ |
| Quantum Scanner | Escaneia máquinas e itens | ✅ |

---

## 🎨 11. VISUAL

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
| Liquid Quanta | Roxo claro brilhante (#B464FF) |
| Liquid Chaos | Roxo escuro profundo (#4B0082) |
| Liquid Order | Ciano brilhante (#00FFFF) |
| Stabilized Liquid | Ciano + partículas brancas |
| Entangled Liquid | Roxo + partículas ciano |
| Liquid Singularity | Branco + transições arco-íris |

### Efeitos especiais

- **Chaos mode:** partículas duplicadas, luz pulsante, aura borrada
- **Order mode:** partículas estáveis, luz constante
- **Entrelaçamento:** linha de partículas, anel giratório
- **Addons visíveis:** componentes 3D na máquina
- **Explosão do Reator:** tela roxa, nausea, mundo mais lento

---

## 📋 12. COMPATIBILIDADE (Planejada)

- JEI/REI, The One Probe/Jade
- AE2, Mekanism, Create, Thermal, XNet
- Curios API
- KubeJS / CraftTweaker
- UniDict

---

*Este documento é a fonte de verdade para o desenvolvimento do Quanta Mod v8.1*