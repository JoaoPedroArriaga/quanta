# QUANTA MOD - MASTERLIST OFICIAL (v6.0 - FINAL COMPLETA)

> Documento consolidado do mod Quanta para Minecraft NeoForge 1.21.1
> **Última atualização: 2026-04-07 (VERSÃO FINAL COM LORE COMPLETA)**

---

## 🎯 CONCEITO CENTRAL

Um mod techno-mágico onde mecânica quântica e magia arcana são a mesma coisa vista de ângulos diferentes. Cientistas chamariam de "entrelaçamento", magos chamariam de "ligação etérea".

**Inspirações:** Mekanism, Oritech, Create, EnderIO, Ars Nouveau.

---

## 📖 LORE FUNDAMENTAL

### A Natureza da Realidade

> *"A realidade é uma equação. Magia é um atalho. Tecnologia é outra rota. O Quantum Codex ensina o caminho do meio: a matemática que ambos usam sem saber."*

Neste universo, o mundo não é feito apenas de matéria e energia. Ele é feito de **informação quântica** - uma equação fundamental que descreve cada partícula, cada força, cada possibilidade.

- **Ordem (Decoerência)** é o colapso da probabilidade em realidade definida.
- **Caos (Superposição)** é a existência de múltiplas possibilidades simultâneas.

As máquinas Quanta manipulam diretamente essa equação. Os spells a executam como código. A cibernética a reescreve permanentemente no corpo do jogador.

---

## ⚡ 1. SISTEMA DE ENERGIA

### Unidade: Quanta (Q)

| Tier | Capacidade típica | Exemplo |
|------|-------------------|---------|
| Basic | 10.000 Q | Primeiras máquinas Quanta, cabos |
| Advanced | 100.000 Q | Máquinas avançadas |
| Elite | 1.000.000 Q | Multiblocks, reator |
| Ultimate | 10.000.000 Q | Endgame |

### Relação com FE (Forge Energy)

- **1 Quanta = 100 FE** (configurável)
- Máquinas Quanta NÃO aceitam FE diretamente
- Máquinas Mechanical (Tier 0) usam FE diretamente

### Conversores FE ↔ Quanta

| Máquina | Entrada | Saída | Taxa | Tier |
|---------|---------|-------|------|------|
| Energy Quantifier | FE | Quanta | 100 FE → 1 Q | Basic |
| Quanta Engine | Quanta | FE | 1 Q → 100 FE | Basic |

### Geração de Quanta

| Gerador | Fonte | Taxa | Tier |
|---------|-------|------|------|
| Thermal Engine | Combustível (carvão, lava, blaze) | Gera FE (não Quanta) | Tier 0 |
| Quantum Engine | Queima Fluid Quanta | 50-200 Q/t | Tier 2 |
| Quantum Gas Burner | Queima Quanta Vapor | 50-200 Q/t | Tier 2 |
| Vacuum Generator | Flutuações do vácuo (passivo) | 500-1.000 Q/t | Tier 2.5 |
| Entanglement Reactor | Entrelaçamento dimensional | 2.000-8.000+ Q/t | Tier 3 |

#### Vacuum Generator - Variação por local

| Local | Geração (Q/t) |
|-------|---------------|
| Overworld superfície | 500 |
| Overworld Deepslate (Y<0) | 650 |
| Nether | 600 |
| End (ilhas principais) | 800 |
| End (vazio/void próximo) | 1.000 |

---

## 💧 2. FLUIDOS E GASES QUÂNTICOS

### Fluidos

| Fluido | Obtenção | Cor | Justificativa |
|--------|----------|-----|---------------|
| Fluid Quanta | Quantum Dust + Água | 💙 Ciano claro | Quanta em estado líquido |
| Fluid Chaos | Quantum Dust + Lava (50% chance) | 💜 Roxo/Vermelho | Quanta infundida com Caos |
| Fluid Order | Quantum Crystal + Água (lento) | 💙 Azul forte | Quanta infundida com Ordem |
| Stabilized Fluid | Fluid Order + Essence of Order | 💙 Azul ciano | Ordem pura líquida |
| Entangled Fluid | Fluid Chaos + Essence of Chaos | 💜 Roxo escuro | Caos puro líquido |
| Singularity Fluid | Stabilized + Entangled + Nether Star | 💛 Dourado | A base da singularidade |

### Gases

| Gás | Obtenção | Cor | Justificativa |
|-----|----------|-----|---------------|
| Quanta Vapor | Fluid Quanta evaporado | 💙 Ciano transparente | Quanta em estado gasoso |
| Chaos Gas | Fluid Chaos evaporado | 💜 Roxo transparente | Caos gasoso |
| Order Gas | Fluid Order evaporado | 💙 Azul transparente | Ordem gasosa |
| Entangled Gas | Entangled Fluid evaporado | 💜 Roxo cintilante | Caos gasoso avançado |
| Stabilized Gas | Stabilized Fluid evaporado | 💙 Azul estável | Ordem gasosa avançada |
| Singularity Gas | Singularity Fluid evaporado | 💛 Dourado cintilante | A base da singularidade em gás |

### Tanques

| Tanque | Capacidade | Tipo |
|--------|------------|------|
| Quantum Tank (Basic) | 16.000 mB | Fluido |
| Quantum Tank (Advanced) | 64.000 mB | Fluido |
| Quantum Tank (Elite) | 256.000 mB | Fluido |
| Quantum Tank (Ultimate) | 1.024.000 mB | Fluido |
| Quantum Gas Tank (Basic) | 16.000 mB | Gás |
| Quantum Gas Tank (Advanced) | 64.000 mB | Gás |
| Quantum Gas Tank (Elite) | 256.000 mB | Gás |
| Quantum Gas Tank (Ultimate) | 1.024.000 mB | Gás |

---

## 🏭 3. MÁQUINAS POR TIER

### Tier 0 (Pré-Quanta - FE apenas)

| Máquina | Função | Justificativa |
|---------|--------|---------------|
| Mechanical Crusher | Pulveriza minérios e Ender Pearl | Processamento mecânico básico |
| Mechanical Press | Pressiona metais em placas | Moldagem física |
| Mechanical Wire Drawer | Transforma placas em fios | Estiramento mecânico |
| Thermal Engine | Gera FE a partir de combustível | Conversão térmica básica |

### Tiers de Máquinas Quanta

| Tier | Processos | Slots addon | Consumo | Velocidade |
|------|-----------|-------------|---------|------------|
| Basic | 1 | 2 | 100% | 1x |
| Advanced | 2 | 3 | 150% | 2x |
| Elite | 4 | 4 | 200% | 4x |
| Ultimate | 8 | 6 | 300% | 8x |

**Upgrade:** Upgrade Kit na máquina existente

### Tier 1 (Quanta Básico)

| Máquina | Função | Consumo (Basic) | Justificativa |
|---------|--------|-----------------|---------------|
| Particule Reconstructor | Duplica minérios | 10 Q/t | Recombina partículas via superposição |
| Quanta Infuser | Infunde Quanta em materiais | 20 Q/t | Insere Quanta na estrutura molecular |
| Quanta Collector | Gera energia + Essências | 5 Q/t (gera) | Coleta flutuações do vácuo quântico |
| Quantum Assembler | Crafting table do mod | 15 Q/t | Executa crafts com eficiência quântica |
| Quantum Fluid Infuser | Produz fluidos quânticos | 20 Q/t | Infunde Quanta em fluidos base |

### Tier 1.5

| Máquina | Função | Consumo (Basic) | Justificativa |
|---------|--------|-----------------|---------------|
| Quanta Enhancer | Enhanced de materiais | 25 Q/t | Aumenta densidade de Quanta |
| Quantum Evaporador | Fluido → Gás | 15 Q/t | Transição de fase quântica |
| Quantum Condenser | Gás → Fluido | 15 Q/t | Condensação quântica |

### Tier 2

| Máquina | Função | Consumo (Basic) | Justificativa |
|---------|--------|-----------------|---------------|
| Quanta Press | Press eficiente | 30 Q/t | Pressão com Quanta |
| Quantum Wire Drawer | Wire eficiente | 25 Q/t | Estiramento assistido por Quanta |
| Quantum Engine | Queima Fluid Quanta | 0 (gera) | Conversão fluido → energia |
| Quantum Gas Burner | Queima Quanta Vapor | 0 (gera) | Conversão gás → energia |
| Entangler | Entrelaça máquinas | 40 Q/t + custo | Cria links quânticos entre blocos |
| Quantum Tuneler | Atravessa blocos | 20 Q/t + (5/bloco) | Tunelamento quântico através da matéria |
| Quantum Observer | Manipula superposição | 15 Q/t | Observador que colapsa estados |

### Tier 2.5

| Máquina | Função | Consumo (Basic) | Justificativa |
|---------|--------|-----------------|---------------|
| Vacuum Generator | Passivo (500-1.000 Q/t) | 0 (gera) | Coleta energia do vácuo quântico |
| Quantum Fluid Mixer | Mistura fluidos | 30 Q/t | Combina propriedades quânticas de fluidos |

### Tier 3 (Elite)

| Máquina | Função | Consumo (Basic) | Justificativa |
|---------|--------|-----------------|---------------|
| Quantum Forge | Processamento avançado | 100 Q/t | Forja que manipula matéria em nível quântico |
| Quantum Gas Centrifuge | Separa gases | 40 Q/t | Separa isótopos quânticos |
| Quantum Plasma Forge | Processa gases em materiais | 100 Q/t | Plasma quântico para criação de matéria |
| Black Hole Forge | Cria Stabilized Singularity | 1.000 Q/t | Forja que simula condições de singularidade |
| Entanglement Reactor | Geração massiva | 0 (gera 2.000-8.000+) | Reator de entrelaçamento dimensional |

### Rede/Computadores

| Máquina | Função | Tier | Justificativa |
|---------|--------|------|---------------|
| Quantum Link | Extensor de alcance | Basic | Repetidor de sinal quântico |
| Entanglement Hub | Central de conexões | Advanced | Central de entrelaçamento |
| Quantum Terminal | Interface da rede | Basic | Terminal de controle |
| Quantum Computer | Processamento central | Advanced | Computador quântico |
| Quantum Mainframe | Processamento avançado | Elite | Mainframe quântico |
| Quantum Supercomputer | Processamento massivo | Ultimate | Supercomputador quântico |

---

## 📦 4. MATERIAIS E RECEITAS

### Tier 0 - Coleta

| Material | Obtenção |
|----------|----------|
| Ender Dust | Mechanical Crusher (Ender Pearl) |

### Tier 1 - Pó Quântico

| Material | Obtenção |
|----------|----------|
| Quantum Dust | Crafting: Ender Dust + Redstone |
| Quantum Dust (eficiente) | Particule Reconstructor: Ender Dust + Redstone |

### Tier 1.5 - Pallet

| Material | Obtenção |
|----------|----------|
| Quantum Pallet | Quanta Enhancer (Quantum Dust) |

### Tier 2 - Metal e Componentes

| Material | Obtenção |
|----------|----------|
| Quantum Steel | Quanta Infuser (Quantum Dust/Pallet + Iron) |
| Quantum Steel Sheet | Mechanical Press / Quanta Press (Quantum Steel) |
| Quantum Steel Wire | Mechanical Wire Drawer / Quantum Wire Drawer (Sheet) |
| Quantum Coil | Quantum Assembler (Wire + Copper) |
| Quantum Circuit | Quanta Press (Steel + Gold + Redstone) |

### Tier 3 - Cristais e Essências

| Material | Obtenção |
|----------|----------|
| Quantum Crystal | Quanta Infuser (Quantum Dust/Pallet + Diamond) |
| Essence of Order | Quanta Collector (Modo Decoerência) |
| Essence of Chaos | Quanta Collector (Modo Superposição) |

### Tier 4 - Avançado

| Material | Obtenção |
|----------|----------|
| Quantum Processor | Quantum Assembler (Crystal + Circuit) |
| Entangled Core | Quantum Assembler (Crystal + Ender Pearl + Essence of Chaos) |
| Observer Lens | Quantum Assembler (Crystal + Essence of Order + Glass) |
| Chaos Fragment | Quantum Assembler (Crystal + Essence of Chaos) |

### Tier 5 - Elite

| Material | Obtenção |
|----------|----------|
| Quantum Alloy | Quanta Infuser (Quantum Dust/Pallet + Netherite) |
| Stabilized Singularity | Black Hole Forge (Nether Star + Entangled Core) |

### Componentes Estruturais

| Material | Receita (Quantum Assembler) |
|----------|------------------------------|
| Quantum Frame | 4 Steel + 4 Circuit + 1 Core |
| Quantum Casing | 8 Sheet + 1 Coil |
| Quantum Glass | 4 Wire + 4 Glass + 1 Dust |
| Quantum Panel | 4 Sheet |
| Quantum Gear | 4 Steel + 2 Coil |

### Componentes Elétricos

| Material | Receita (Quantum Assembler) |
|----------|------------------------------|
| Quantum Diode | 2 Wire + 1 Circuit + 1 Redstone |
| Quantum Capacitor | 4 Coil + 1 Crystal |
| Quantum Resistor | 2 Wire + 1 Dust |
| Quantum Transistor | Diode + Circuit + Order |

### Computadores

| Material | Receita (Quantum Assembler) |
|----------|------------------------------|
| Quantum CPU | Processor + Circuit + Order |
| Quantum RAM | Crystal + Coil + Redstone |
| Quantum Storage | Steel + Circuit + Pallet |
| Quantum Motherboard | Frame + CPU + 4 RAM |
| Quantum Network Card | Motherboard + Core |
| Quantum Terminal | Motherboard + 2 RAM + 1 Card + 4 Glass |
| Quantum Computer | Terminal + 2 CPU + 4 RAM + 1 Core |
| Quantum Mainframe | Computer + 4 CPU + 8 RAM + 2 Core + 4 Frame |
| Quantum Supercomputer | Mainframe + 8 CPU + 16 RAM + 4 Core + 8 Frame + 1 Singularity |
| Parallel Processor | 4 CPU + 4 RAM + 1 Core |

### Quantum Logic Blocks

| Bloco | Receita (Quantum Assembler) |
|-------|------------------------------|
| Quantum Comparator | Circuit + Redstone + Comparator |
| Quantum Logic Gate AND | 2 Circuit + Redstone |
| Quantum Logic Gate OR | 2 Circuit + Redstone |
| Quantum Logic Gate NOT | Circuit + Redstone + Torch |
| Quantum Timer | Circuit + Clock + Redstone |
| Quantum Redstone Relay | Circuit + Redstone + Comparator |

### Ferramentas

| Ferramenta | Receita (Quantum Assembler) |
|------------|------------------------------|
| Quantum Wrench | 4 Steel + 2 Circuit |
| Quantum Multitool | Wrench + Processor |
| Quantum Screwdriver | 2 Steel + 1 Circuit |
| Quantum Tester | Circuit + Redstone + Glass |
| Quantum Scanner | Tester + Processor + Lens |

---

## 🔋 5. QUANTUM BATTERY (ARMAZENAMENTO)

### Itens Portáteis

| Bateria | Capacidade | Tier |
|---------|------------|------|
| Basic Quantum Battery | 100.000 Q | Basic |
| Advanced Quantum Battery | 500.000 Q | Advanced |
| Elite Quantum Battery | 2.500.000 Q | Elite |
| Ultimate Quantum Battery | 10.000.000 Q | Ultimate |

**Características:**
- Funciona em slots Curios (se disponível)
- Ferramentas consomem PRIMEIRO da bateria equipada

### Blocos de Armazenamento

| Bloco | Capacidade | Tier |
|-------|------------|------|
| Quantum Cell | 10.000.000 Q | Advanced |
| Entangled Cell | 100.000.000 Q | Elite |
| Singularity Cell | 1.000.000.000 Q | Ultimate |

---

## 📡 6. ENTANGLEMENT NETWORK (WIRELESS)

### Progressão

| Fase | Sistema |
|------|---------|
| Early Game | Cabos físicos (Quantum Cable) |
| Mid Game | Tunelamento (atravessa paredes) |
| Late Game | Wireless local (com Quantum Links) |
| Endgame | Full Wireless global (cross-dimensional) |

### Componentes

| Componente | Função | Tier |
|------------|--------|------|
| Quantum Link | Extensor de alcance | Basic |
| Entanglement Hub | Central de conexões | Advanced |
| Quantum Antenna | Longo alcance | Elite |

### Computadores de Rede

| Computador | Função | Slots | Consumo |
|------------|--------|-------|---------|
| Quantum Terminal | Interface | 4 conexões | 10 Q/t |
| Quantum Computer | Gerencia rede | 16 conexões | 40 Q/t |
| Quantum Mainframe | Gerencia rede grande | 64 conexões | 160 Q/t |
| Quantum Supercomputer | Rede global | 256 conexões | 640 Q/t |

---

## 🔬 7. MECÂNICA QUÂNTICA

### Superposição (Caos)

- Máquina opera em múltiplos estados simultaneamente
- **Risco de falha:** diminui com tier

**Chances de sucesso:**

| Tier | Sucesso Total (2x output) | Sucesso Parcial (1x output) | Falha (0 output + dano) |
|------|---------------------------|-----------------------------|-------------------------|
| Basic | 45% | 35% | 20% |
| Advanced | 50% | 35% | 15% |
| Elite | 60% | 30% | 10% |
| Ultimate | 75% | 20% | 5% |

**Resultados:**
- **Sucesso total:** 2 operações, energia normal
- **Sucesso parcial:** 1 operação, energia normal
- **Falha:** Perde tudo, consome energia, dano à máquina

### Decoerência (Ordem)

- Colapso da superposição para um estado definido
- Resultado previsível e garantido
- Sem riscos, mas sem bônus

### Entrelaçamento

- Duas máquinas conectadas à distância sem cabos
- Aplicações: Energia, Itens, Informação
- Distância máxima: 16/32/64 blocos (upgradável)
- Custo de manutenção: 10/20/40 Q/t

### Tunelamento Quântico

- Atravessar blocos sólidos
- Tipos: Energia (transmissão wireless), Itens (transmissão wireless), Pessoal (teletransporte)

### Observador

- Manipula superposição
- Modos: Estabilizador (+10% velocidade), Seletor (escolhe resultado), Medidor (mostra estado)

---

## 🔧 8. ADDONS

### Slots por tier

| Tier | Slots |
|------|-------|
| Basic | 2 |
| Advanced | 3 |
| Elite | 4 |
| Ultimate | 6 |

### Addons disponíveis

| Addon | Efeito | Valor | Aplica-se a |
|-------|--------|-------|-------------|
| Stabilizer Coil | Reduz falha | -5% | Todas Quanta |
| Quantum Lock | Reduz falha | -10% | Todas Quanta |
| Observer Link | Reduz falha | -15% (requer Lens) | Todas Quanta |
| Order Stabilizer | Reduz falha e velocidade | -10% falha, -10% speed | Todas Quanta |
| Chaos Overdrive | Aumenta performance | +50% speed, +25% falha | Todas Quanta |
| Speed Coil | Aumenta velocidade | +20% | Processamento |
| Energy Coil | Reduz consumo | -20% | Todas Quanta |
| Quanta Generation Coil | Aumenta geração | +25% | Geradores Quanta |
| Efficiency Coil | Reduz consumo combustível | -20% | Thermal Engine, Quantum Engine |
| Fluid Addon | Processa líquidos/gases | N/A | Quanta Infuser, Fluid Infuser |
| Cooling Coil | Reduz consumo refrigeração | -20% | Entanglement Reactor |
| Chaos Injector | +geração, +risco | +50% geração, +25% risco | Entanglement Reactor |
| Order Stabilizer (fluido) | Estabiliza | -30% risco, -10% geração | Entanglement Reactor |
| Efficiency Vent | Reduz consumo gás | -20% | Quantum Gas Burner |

**Regras:**
- Máximo 4 do mesmo tipo
- Limite máximo de sucesso total da superposição: 90%

---

## 🗡️ 9. EQUIPAMENTOS MODULARES

### Equipamentos base

| Equipamento | Slots | Capacidade |
|-------------|-------|------------|
| Quantum Pickaxe | 3 | 10.000 Q |
| Quantum Axe | 3 | 10.000 Q |
| Quantum Shovel | 3 | 10.000 Q |
| Quantum Sword | 3 | 10.000 Q |
| Quantum Helmet | 4 | 5.000 Q |
| Quantum Chestplate | 6 | 15.000 Q |
| Quantum Leggings | 4 | 8.000 Q |
| Quantum Boots | 4 | 5.000 Q |

**Características:** Indestrutíveis, não encantáveis, consomem Quanta para funcionar.

### Consumo de energia

| Ação | Consumo |
|------|---------|
| Quebrar 1 bloco (ferramenta) | 10 Q |
| Dano causado (espada) | 5 Q por coração |
| Módulo Tunnel (bloco extra) | +5 Q |
| Módulo Vein Miner (por bloco) | +15 Q |
| Módulo Magnet (por tick) | 1 Q/t |
| Módulo Speed (por tick) | 2 Q/t |

### Módulos (com tiers I/II/III)

| Categoria | Módulo | Efeito |
|-----------|--------|--------|
| Minerador | Tunnel | Mina 2x2 |
| Minerador | Vein Miner | Mina veio inteiro |
| Minerador | Quantum Sight | Mostra minérios |
| Minerador | Auto-Smelt | Funde automaticamente |
| Minerador | Silk Touch | Toque suave |
| Combate | Chaos Strike | % dano triplicado |
| Combate | Order Strike | Dano consistente |
| Combate | Leech | Rouba vida |
| Utilidade | Magnet | Puxa itens |
| Utilidade | Speed | Aceleração |
| Utilidade | Experience | +% XP |
| Defesa | Quantum Shield | Absorve dano |
| Defesa | Anti-Knockback | Sem knockback |
| Defesa | Regeneration | Regenera vida |

---

## 📖 10. SISTEMA DE SPELLS (QUANTUM CODEX)

### Conceito e Lore

> *"O Quantum Codex não é um grimório de magia. É um caderno de anotações.*

> *Cada máquina Quanta que você observa revela um pedaço da equação fundamental que rege nossa realidade. O Codex registra essas observações como Equations - fórmulas matemáticas que descrevem como transformar o mundo ao seu redor.*

> *No Black Board, você combina essas Equations como um programador combina funções. Uma Forma define COMO a transformação é aplicada. Um Elemento define o TIPO de energia usada. Um Efeito define O QUE muda.*

> *Quando você lança um spell, o Codex executa a equação. A realidade obedece não porque você é mágico, mas porque a matemática está correta.*

> *O custo em Quanta é a energia necessária para forçar o colapso da função de onda no estado desejado. O cooldown é o tempo que a realidade leva para se estabilizar novamente.*

> *Você não está conjurando magia. Você está fazendo matemática aplicada em tempo real."*

### Justificativa Quântica

| Conceito real | Tradução no mod |
|---------------|-----------------|
| Equação de Schrödinger | A "fórmula" que descreve como a realidade evolui |
| Operador quântico | Uma ação matemática que transforma um estado em outro |
| Observável | Uma propriedade que pode ser medida/alterada |
| Entrelaçamento | Conectar dois pontos no espaço-tempo |

**Por que consome Quanta?** Executar uma equação na realidade REAL requer energia para "forçar" o colapso da função de onda.

**Por que tem cooldown?** A realidade precisa de tempo para "relaxar" após uma modificação.

---

### Quantum Codex - O Item

| Propriedade | Valor |
|-------------|-------|
| Tipo | Item segurável (off-hand ou main-hand) |
| Durabilidade | Infinita |
| Energia | 10.000 Q (buffer próprio) |
| Recarga | Passiva perto de máquinas Quanta (5 Q/t) |

**Funcionalidades:**
- Abre GUI com spells equipados
- Mostra progressão de XP e Equations desbloqueadas
- Permite equipar até 6 spells simultaneamente

---

### Progressão - Como Aprender

| Ação | Ganho de XP |
|------|-------------|
| Observar máquina Basic (1 tick) | 1 XP |
| Observar máquina Advanced | 2 XP |
| Observar máquina Elite | 4 XP |
| Observar máquina Ultimate | 8 XP |
| Observar Entanglement Reactor | 16 XP |
| Completar craft no Quantum Assembler | 5 XP |
| Criar Stabilized Singularity | 50 XP |

**Níveis do Codex:**

| Nível | XP necessário | Desbloqueia |
|-------|---------------|-------------|
| 1 | 0 | Forma Self, Elemento Neutro |
| 2 | 100 | Forma Projectile |
| 3 | 250 | Elemento Order |
| 4 | 500 | Forma Touch |
| 5 | 1.000 | Elemento Chaos |
| 6 | 2.000 | Forma Area |
| 7 | 5.000 | Efeito: Dano |
| 8 | 10.000 | Efeito: Cura |
| 9 | 20.000 | Efeito: Teletransporte |
| 10 | 50.000 | Efeito: Aceleração |
| 11 | 100.000 | Efeito: Infusão |
| 12 | 200.000 | Efeito: Estabilização |
| 13+ | 500.000+ | Combinações avançadas |

---

### Equations - Os Componentes

**Forma (como o spell é lançado):**

| Forma | Custo (Q) | Cooldown | Descrição |
|-------|-----------|----------|-----------|
| Self | 50 | 1s | Afeta o próprio jogador |
| Projectile | 100 | 3s | Projétil arremessado (alcance 16 blocos) |
| Touch | 75 | 2s | Contato direto (entidade/bloco) |
| Area | 200 | 8s | Raio de 5 blocos ao redor |

**Elemento (fonte do poder):**

| Elemento | Custo extra | Efeito especial |
|----------|-------------|-----------------|
| Neutro | 0 | Padrão, sem bônus/penalidade |
| Order | +25% | Mais estável, -10% chance de falha |
| Chaos | -25% | Menos estável, +15% chance de falha, +25% potência |

**Efeito (o que o spell faz):**

| Efeito | Custo base | Descrição |
|--------|------------|-----------|
| Dano | 100 | Causa dano mágico (5♥ base) |
| Cura | 100 | Cura vida (5♥ base) |
| Teletransporte | 250 | Teleporta para local alvo (16 blocos) |
| Aceleração | 150 | Aplica Haste II por 30s |
| Infusão | 200 | Infunde item com 1.000 Q |
| Revelação | 50 | Mostra entidades/blocos escondidos (30s) |
| Estabilização | 150 | Reduz falha da máquina em -10% por 60s |

---

### Black Board - Máquina de Criação de Spells

| Propriedade | Valor |
|-------------|-------|
| Tier | Advanced |
| Consumo | 20 Q/t |
| Slots | 3 de addon |
| Função | Combina Equations para criar spells personalizados |

**Processo de criação:**
1. Colocar até 3 Equations no Black Board
2. O sistema mostra: Custo total, Cooldown, Efeito combinado
3. Confirma criação (consome Equations)
4. Spell é adicionado ao Codex

**Exemplos de combinações:**

| Combinação | Resultado | Custo | Cooldown |
|------------|-----------|-------|----------|
| Self + Neutro + Cura | Cura pessoal | 100 Q | 5s |
| Self + Order + Cura | Cura pessoal forte (+20%) | 125 Q | 6s |
| Projectile + Chaos + Dano | Bola de fogo caótica | 75 Q | 4s |
| Touch + Chaos + Teletransporte | Teleporta entidade tocada | 250 Q | 15s |
| Area + Neutro + Aceleração | Acelera máquinas na área | 200 Q | 30s |
| Projectile + Order + Revelação | Revela área do impacto | 75 Q | 10s |

---

### Spells Pré-definidos (Base)

| Spell | Combinação | Custo | Cooldown | Descrição |
|-------|------------|-------|----------|-----------|
| Infuse | Self + Neutro + Infusão | 200 Q | 5s | Infunde item segurado com 1.000 Q |
| Reveal | Self + Order + Revelação | 75 Q | 10s | Revela entidades/blocos escondidos |
| Accelerate | Touch + Neutro + Aceleração | 150 Q | 20s | Acelera máquina tocada por 30s |
| Stabilize | Touch + Order + Estabilização | 200 Q | 30s | Estabiliza máquina tocada (-10% falha, 60s) |

---

## 🦾 11. SISTEMA DE CIBERNÉTICA (QUANTUM CYBER CHAMBER)

### Conceito e Lore

> *"A Quantum Cyber Chamber não adiciona nada ao seu corpo. Ela reescreve o que seu corpo É.*

> *Antes da câmara, seu corpo existia em um único estado - o natural. Dentro da câmara, você existe em todos os estados possíveis simultaneamente. A câmara então entrelaça uma nova propriedade - visão noturna, alcance estendido, voo - à sua função de onda.*

> *Quando você sai, a função de onda colapsa. Você não 'tem visão noturna'. Você É alguém que SEMPRE teve visão noturna.*

> *O custo não é de manutenção. É o preço de reescrever a realidade do seu corpo."*

### Justificativa Quântica

**O problema:** Implantes normais rejeitam, causam inflamação, precisam de energia, quebram com o tempo.

**A solução quântica:** Em vez de adicionar algo ao corpo, a Quantum Cyber Chamber **reescreve a função de onda do seu corpo** em um estado específico.

| Conceito real | Tradução no mod |
|---------------|-----------------|
| Função de onda | Seu corpo existe como uma nuvem de probabilidades |
| Entrelaçamento | Partes do seu corpo ficam "ligadas" a propriedades quânticas |
| Decoerência | Seu corpo "colapsa" em um estado específico (o implante) |
| Superposição | Você pode ter múltiplas propriedades ao mesmo tempo |

**Por que não consome energia para usar?** Porque seus olhos AGORA são quânticos. Eles **produzem** sua própria "luz" via flutuações de vácuo. O novo estado é estável para sempre.

---

### Quantum Cyber Chamber - A Máquina

| Propriedade | Valor |
|-------------|-------|
| Tamanho | 3x3x3 (expansível) |
| Blocos | 4 Quantum Casing + 4 Quantum Glass + 1 Quantum Core |
| Tier | Advanced (upgradável para Elite/Ultimate) |
| Consumo | 500-10.000 Q por aplicação (não por tick) |

**Visual:**
- Câmara de vidro roxo com luzes ciano pulsantes
- Porta selada que se abre quando o processo termina
- Partículas de "infusão" envolvendo o jogador
- Som de zumbido quântico durante o processo

---

### Processo de Aplicação

1. **Construir a Quantum Cyber Chamber** (multibloco 3x3x3)
2. **Alimentar com Quanta** (via Energy Quantifier ou Quantum Battery)
3. **Entrar na câmara** (direito do mouse na porta)
4. **Selecionar implante** na GUI
5. **Iniciar processo** (consome Quanta e materiais)
6. **Aguardar** (10-30 segundos, barra de progresso)
7. **Sair melhorado** - efeito permanente!

---

### Progressão da Chamber

| Tier da Chamber | Implantes disponíveis | Slots totais | Custo por aplicação |
|----------------|----------------------|--------------|---------------------|
| Basic | Nenhum (não funciona) | - | - |
| Advanced | Sight, Reach, Speed, Lungs, Night Vision | 3 slots | 500 Q |
| Elite | + Tunneling, Flight, Buffer | 5 slots | 2.000 Q |
| Ultimate | + Processor | 7 slots | 10.000 Q |

---

### Implantes Disponíveis

| Implante | Efeito | Tier | Custo | Materiais |
|----------|--------|------|-------|-----------|
| **Quantum Sight** | Ver máquinas/energia através de blocos (8-32 blocos) | Advanced | 500 Q | Observer Lens |
| **Quantum Reach** | Alcance estendido (+3 a +8 blocos) | Advanced | 500 Q | Entangled Core |
| **Quantum Speed** | +20% a +50% velocidade de movimento | Advanced | 500 Q | Speed Coil |
| **Quantum Lungs** | Respiração underwater infinita | Advanced | 500 Q | Fluid Quanta (bucket) |
| **Quantum Night Vision** | Visão noturna permanente | Advanced | 500 Q | Essence of Order |
| **Quantum Tunneling** | Teletransporte curto (8-16 blocos, cd 10-5s) | Elite | 2.000 Q | Chaos Fragment |
| **Quantum Flight** | Voo criativo (no Overworld/End) | Elite | 2.000 Q | Stabilized Singularity |
| **Quantum Buffer** | +10.000 a +50.000 Q de reserva corporal | Elite | 2.000 Q | Quantum Capacitor |
| **Quantum Processor** | Codex integrado + craft remoto + XP bônus | Ultimate | 10.000 Q | Quantum Processor |

---

### Remoção de Implantes

| Método | Custo | Descrição |
|--------|-------|-----------|
| Chamber (Remover) | 100 Q | Entrar na chamber e remover implante específico |
| Quantum Reset | 5.000 Q | Remove TODOS os implantes de uma vez |
| Death | N/A | Implantes permanecem após morte (configurável) |

---

## 🔗 12. CONEXÃO ENTRE OS SISTEMAS

### Tabela Comparativa Final

| Sistema | É... | Lore | Consome? | É... |
|---------|------|------|----------|------|
| **Equipamentos** | Ferramentas | Manipulação direta de Quanta | ✅ Quanta (por ação) | Ativo |
| **Spells** | Magia matemática | Equações executadas na realidade | ✅ Quanta (por uso) | Ativo (temporário) |
| **Cibernética** | Transformação corporal | Função de onda reprogramada | ❌ Nada (custo único) | Passivo (permanente) |

---

### Interações entre Sistemas

| Interação | Efeito |
|-----------|--------|
| Quantum Buffer (Cibernética) + Equipamentos | Equipamentos consomem do Buffer primeiro |
| Quantum Processor (Cibernética) + Codex | Codex fica permanentemente ativo (não precisa segurar) |
| Quantum Processor + Quantum Assembler | Craft remoto de qualquer lugar (100 Q por craft) |
| Quantum Flight + Elytra | Pode alternar entre voo e planagem |
| Quantum Speed + Speed module | Aceleração acumulativa (multiplicativo) |
| Braços (alcance) + Spell Tunnel | Teletransporte mais longo |
| Olhos (visão) + Spell Reveal | Revela ainda mais coisas |

---

### O Fio Condutor: Ordem e Caos

| Elemento | Papel nas Máquinas | Papel nos Spells | Papel na Cibernética |
|----------|-------------------|------------------|----------------------|
| **Essence of Order** | Estabiliza processos | Usada em spells de cura/proteção | Usada em implantes estáveis |
| **Essence of Chaos** | Potencializa com risco | Usada em spells de dano/teletransporte | Usada em implantes instáveis |
| **Decoerência** | Estado final definido | Estado após o spell | Estado fixado na chamber |
| **Superposição** | Múltiplos processos | Estado durante o spell | Estado dentro da chamber |

---

### Diagrama de Conexão
QUANTA MOD
│
┌────────────────┼────────────────┐
│ │ │
▼ ▼ ▼
MÁQUINAS SPELLS CIBERNÉTICA
(Tecnologia) (Magia) (Transformação)
│ │ │
│ │ │
Manipulam Executam Reescrevem
Quanta Equações Função de Onda
│ │ │
└────────────────┼────────────────┘
│
▼
ORDEM (Decoerência)
vs
CAOS (Superposição)

---

## 🎨 13. VISUAL

### Paleta de cores

| Elemento | Cor |
|----------|-----|
| Máquinas base | Roxo escuro (#3C096C) |
| Energia ativa | Ciano (#00F5FF) |
| Essence of Order | 💙 Ciano |
| Essence of Chaos | 💜 Roxo |
| Itens Basic | Roxo médio (#9D4EDD) |
| Itens Advanced | Ciano + Roxo |
| Itens Elite | Ciano + Dourado |
| Itens Ultimate | Ciano + Dourado + Branco |

### Progressão visual por tier

| Tier | Estilo |
|------|--------|
| Basic | Roxo escuro, partículas simples, textura básica |
| Advanced | Roxo + Ciano, partículas mais ativas, detalhes extras |
| Elite | Roxo + Ciano + Dourado, luz pulsante, textura elaborada |
| Ultimate | Ciano + Dourado + Branco, partículas intensas, efeitos de borda |

### Efeitos especiais

- **Modo Superposição:** partículas roxas duplicadas, luz pulsante ciano/roxo, textura borrada
- **Modo Decoerência:** partículas ciano estáveis, luz constante
- **Entrelaçamento:** linha de partículas entre máquinas, anel giratório
- **Addons visíveis:** addons aparecem como componentes 3D na máquina
- **Spells:** partículas coloridas baseadas no elemento (ciano para Order, roxo para Chaos)
- **Cibernética:** efeitos visuais sutis nos olhos (brilho), braços (linhas de luz), pernas (rastro)

---

## 📊 14. PROGRESSÃO SUGERIDA
TIER 0 (FE)
├── Thermal Engine, Mechanical Crusher, Press, Wire Drawer
├── Ender Dust → Crafting → Quantum Dust
↓
TIER 1 (Quanta Básico)
├── Energy Quantifier (FE→Q), Quanta Engine (Q→FE)
├── Particule Reconstructor, Quanta Infuser, Quanta Collector
├── Quantum Assembler, Quantum Fluid Infuser
├── Quantum Steel → Sheet → Wire → Coil → Circuit
↓
TIER 1.5
├── Quanta Enhancer (Pallet)
├── Quantum Evaporator, Quantum Condenser
↓
TIER 2 (Avançado)
├── Quanta Press, Quantum Wire Drawer
├── Quantum Engine (Fluid Quanta), Quantum Gas Burner
├── Entangler, Tuneler, Observer
├── Quantum Crystal, Essências, Entangled Core, Chaos Fragment
├── Quantum Codex (começa a aprender spells)
├── Quantum Cyber Chamber (Advanced - implantes básicos)
↓
TIER 2.5
├── Vacuum Generator (passivo 500-1.000 Q/t)
├── Quantum Fluid Mixer
├── Black Board (criação de spells personalizados)
↓
TIER 3 (Elite)
├── Upgrade Kits
├── Quantum Forge, Gas Centrifuge, Plasma Forge
├── Black Hole Forge, Entanglement Reactor (5x5x5)
├── Quantum Alloy, Stabilized Singularity
├── Quantum Cyber Chamber (Elite - implantes avançados)
├── Computadores de rede (Mainframe)
↓
TIER ULTIMATE (Endgame)
├── Upgrade Kits
├── Entanglement Reactor (20x20x20, 8.000+ Q/t)
├── Quantum Supercomputer (rede global)
├── Singularity Cell (1B Q)
├── Quantum Cyber Chamber (Ultimate - implante cerebral)
├── Spells avançados (combinações poderosas)

---

## 📋 15. PENDENTE / NOTAS

### Entanglement Reactor
- Geração: 2.000 Q/t (5x5x5) a 8.000 Q/t (20x20x20)
- Requer 1 Stabilized Singularity (não consumida)
- Precisa de addons estabilizadores
- Chaos Overdrive aumenta risco

### Black Hole Forge
- Multibloco 7x7x7
- Consome 1.000 Q/t

### Quantum Cyber Chamber
- Multibloco 3x3x3 (expansível)
- Consumo único por aplicação (não por tick)
- Implantes são permanentes e não consomem energia

### Por definir
- Valores exatos de módulos (tiers I/II/III)
- Modelos 3D específicos
- Efeitos visuais detalhados para spells

### Compatibilidade
- JEI/REI, The One Probe/Jade
- AE2, Mekanism, Create, Thermal, XNet
- Curios API (para baterias)

---

*Documento mantido como fonte única de verdade para desenvolvimento do Quanta Mod*

**"A realidade é uma equação. Aprenda a lê-la. Aprenda a escrevê-la."**
