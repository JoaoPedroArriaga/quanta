# QUANTA MOD - MASTERLIST OFICIAL (v5.0 - FINAL)

> Documento consolidado do mod Quanta para Minecraft NeoForge 1.21.1
> **Última atualização: 2026-04-07 (VERSÃO FINAL COMPLETA)**

---

## 🎯 CONCEITO CENTRAL

Um mod techno-mágico onde mecânica quântica e magia arcana são a mesma coisa vista de ângulos diferentes. Cientistas chamariam de "entrelaçamento", magos chamariam de "ligação etérea".

**Inspirações:** Mekanism, Oritech, Create, EnderIO, Ars Nouveau.

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

| Fluido | Obtenção | Cor |
|--------|----------|-----|
| Fluid Quanta | Quantum Dust + Água | 💙 Ciano claro |
| Fluid Chaos | Quantum Dust + Lava (50% chance) | 💜 Roxo/Vermelho |
| Fluid Order | Quantum Crystal + Água (lento) | 💙 Azul forte |
| Stabilized Fluid | Fluid Order + Essence of Order | 💙 Azul ciano |
| Entangled Fluid | Fluid Chaos + Essence of Chaos | 💜 Roxo escuro |
| Singularity Fluid | Stabilized + Entangled + Nether Star | 💛 Dourado |

### Gases

| Gás | Obtenção | Cor |
|-----|----------|-----|
| Quanta Vapor | Fluid Quanta evaporado | 💙 Ciano transparente |
| Chaos Gas | Fluid Chaos evaporado | 💜 Roxo transparente |
| Order Gas | Fluid Order evaporado | 💙 Azul transparente |
| Entangled Gas | Entangled Fluid evaporado | 💜 Roxo cintilante |
| Stabilized Gas | Stabilized Fluid evaporado | 💙 Azul estável |
| Singularity Gas | Singularity Fluid evaporado | 💛 Dourado cintilante |

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

### Refrigeração do Entanglement Reactor

| Fluido | Efeito | Consumo (mB/t) | Estabilidade |
|--------|--------|----------------|--------------|
| Água | -5% calor | 10 | Baixa |
| Fluid Quanta | -10% calor, +5% geração | 5 | Média |
| Fluid Order | -20% calor | 2 | Alta |
| Stabilized Fluid | -30% calor, +10% estabilidade | 1 | Muito Alta |

**Níveis de estabilidade:**
- Baixa (0-30%): Risco de explosão +50%
- Média (30-60%): Risco normal
- Alta (60-90%): Risco -25%, +10% geração
- Muito Alta (90%+): Risco -50%, +20% geração, +10% sucesso na superposição

---

## 🏭 3. MÁQUINAS POR TIER

### Tier 0 (Pré-Quanta - FE apenas)

| Máquina | Função |
|---------|--------|
| Mechanical Crusher | Pulveriza minérios e Ender Pearl |
| Mechanical Press | Pressiona metais em placas |
| Mechanical Wire Drawer | Transforma placas em fios |
| Thermal Engine | Gera FE a partir de combustível |

### Tiers de Máquinas Quanta

| Tier | Processos | Slots addon | Consumo | Velocidade |
|------|-----------|-------------|---------|------------|
| Basic | 1 | 2 | 100% | 1x |
| Advanced | 2 | 3 | 150% | 2x |
| Elite | 4 | 4 | 200% | 4x |
| Ultimate | 8 | 6 | 300% | 8x |

**Upgrade:** Upgrade Kit na máquina existente

### Tier 1 (Quanta Básico)

| Máquina | Função | Consumo (Basic) |
|---------|--------|-----------------|
| Particule Reconstructor | Duplica minérios | 10 Q/t |
| Quanta Infuser | Infunde Quanta em materiais | 20 Q/t |
| Quanta Collector | Gera energia + Essências | 5 Q/t (gera) |
| Quantum Assembler | Crafting table do mod | 15 Q/t |
| Quantum Fluid Infuser | Produz fluidos quânticos | 20 Q/t |

### Tier 1.5

| Máquina | Função | Consumo (Basic) |
|---------|--------|-----------------|
| Quanta Enhancer | Enhanced de materiais | 25 Q/t |
| Quantum Evaporator | Fluido → Gás | 15 Q/t |
| Quantum Condenser | Gás → Fluido | 15 Q/t |

### Tier 2

| Máquina | Função | Consumo (Basic) |
|---------|--------|-----------------|
| Quanta Press | Press eficiente | 30 Q/t |
| Quantum Wire Drawer | Wire eficiente | 25 Q/t |
| Quantum Engine | Queima Fluid Quanta | 0 (gera) |
| Quantum Gas Burner | Queima Quanta Vapor | 0 (gera) |
| Entangler | Entrelaça máquinas | 40 Q/t + custo |
| Quantum Tuneler | Atravessa blocos | 20 Q/t + (5/bloco) |
| Quantum Observer | Manipula superposição | 15 Q/t |

### Tier 2.5

| Máquina | Função | Consumo (Basic) |
|---------|--------|-----------------|
| Vacuum Generator | Passivo (500-1.000 Q/t) | 0 (gera) |
| Quantum Fluid Mixer | Mistura fluidos | 30 Q/t |

### Tier 3 (Elite)

| Máquina | Função | Consumo (Basic) |
|---------|--------|-----------------|
| Quantum Forge | Processamento avançado | 100 Q/t |
| Quantum Gas Centrifuge | Separa gases | 40 Q/t |
| Quantum Plasma Forge | Processa gases em materiais | 100 Q/t |
| Black Hole Forge | Cria Stabilized Singularity | 1.000 Q/t |
| Entanglement Reactor | Geração massiva | 0 (gera 2.000-8.000+) |

### Rede/Computadores

| Máquina | Função | Tier |
|---------|--------|------|
| Quantum Link | Extensor de alcance | Basic |
| Entanglement Hub | Central de conexões | Advanced |
| Quantum Terminal | Interface da rede | Basic |
| Quantum Computer | Processamento central | Advanced |
| Quantum Mainframe | Processamento avançado | Elite |
| Quantum Supercomputer | Processamento massivo | Ultimate |

---

## 📦 4. MATERIAIS E RECEITAS

### Tier 0 - Coleta

| Material | Máquina | Método |
|----------|---------|--------|
| Ender Dust | Mechanical Crusher | Pulverizar Ender Pearl |

### Tier 1 - Pó Quântico

| Material | Máquina | Método |
|----------|---------|--------|
| Quantum Dust | Crafting Table | Ender Dust + Redstone |
| Quantum Dust (eficiente) | Particule Reconstructor | Ender Dust + Redstone |

### Tier 1.5 - Pallet

| Material | Máquina | Método |
|----------|---------|--------|
| Quantum Pallet | Quanta Enhancer | Quantum Dust → Quantum Pallet |

### Tier 2 - Metal e Componentes

| Material | Máquina | Método |
|----------|---------|--------|
| Quantum Steel | Quanta Infuser | Quantum Dust/Pallet + Iron Ingot |
| Quantum Steel Sheet | Mechanical Press / Quanta Press | Quantum Steel |
| Quantum Steel Wire | Mechanical Wire Drawer / Quantum Wire Drawer | Quantum Steel Sheet |
| Quantum Coil | Quantum Assembler | Quantum Steel Wire + Copper Ingot |
| Quantum Circuit | Quanta Press | Quantum Steel + Gold + Redstone |

### Tier 3 - Cristais e Essências

| Material | Máquina | Método |
|----------|---------|--------|
| Quantum Crystal | Quanta Infuser | Quantum Dust/Pallet + Diamond |
| Essence of Order | Quanta Collector | Modo Decoerência |
| Essence of Chaos | Quanta Collector | Modo Superposição |

### Tier 4 - Avançado

| Material | Máquina | Método |
|----------|---------|--------|
| Quantum Processor | Quantum Assembler | Quantum Crystal + Quantum Circuit |
| Entangled Core | Quantum Assembler | Quantum Crystal + Ender Pearl + Essence of Chaos |
| Observer Lens | Quantum Assembler | Quantum Crystal + Essence of Order + Glass |
| Chaos Fragment | Quantum Assembler | Quantum Crystal + Essence of Chaos |

### Tier 5 - Elite

| Material | Máquina | Método |
|----------|---------|--------|
| Quantum Alloy | Quanta Infuser | Quantum Dust/Pallet + Netherite Ingot |
| Stabilized Singularity | Black Hole Forge | Nether Star + Entangled Core |

### Componentes Estruturais

| Material | Máquina | Método |
|----------|---------|--------|
| Quantum Frame | Quantum Assembler | 4 Steel + 4 Circuit + 1 Core |
| Quantum Casing | Quantum Assembler | 8 Sheet + 1 Coil |
| Quantum Glass | Quantum Assembler | 4 Wire + 4 Glass + 1 Dust |
| Quantum Panel | Quantum Assembler | 4 Sheet |
| Quantum Gear | Quantum Assembler | 4 Steel + 2 Coil |

### Componentes Elétricos

| Material | Máquina | Método |
|----------|---------|--------|
| Quantum Diode | Quantum Assembler | 2 Wire + 1 Circuit + 1 Redstone |
| Quantum Capacitor | Quantum Assembler | 4 Coil + 1 Crystal |
| Quantum Resistor | Quantum Assembler | 2 Wire + 1 Dust |
| Quantum Transistor | Quantum Assembler | Diode + Circuit + Order |

### Computadores

| Material | Máquina | Método |
|----------|---------|--------|
| Quantum CPU | Quantum Assembler | Processor + Circuit + Order |
| Quantum RAM | Quantum Assembler | Crystal + Coil + Redstone |
| Quantum Storage | Quantum Assembler | Steel + Circuit + Pallet |
| Quantum Motherboard | Quantum Assembler | Frame + CPU + 4 RAM |
| Quantum Network Card | Quantum Assembler | Motherboard + Core |

### Computadores (completos)

| Computador | Máquina | Método |
|------------|---------|--------|
| Quantum Terminal | Quantum Assembler | Motherboard + 2 RAM + 1 Card + 4 Glass |
| Quantum Computer | Quantum Assembler | Terminal + 2 CPU + 4 RAM + 1 Core |
| Quantum Mainframe | Quantum Assembler | Computer + 4 CPU + 8 RAM + 2 Core + 4 Frame |
| Quantum Supercomputer | Quantum Assembler | Mainframe + 8 CPU + 16 RAM + 4 Core + 8 Frame + 1 Singularity |
| Parallel Processor | Quantum Assembler | 4 CPU + 4 RAM + 1 Core |

### Ferramentas

| Ferramenta | Máquina | Método |
|------------|---------|--------|
| Quantum Wrench | Quantum Assembler | 4 Steel + 2 Circuit |
| Quantum Multitool | Quantum Assembler | Wrench + Processor |
| Quantum Screwdriver | Quantum Assembler | 2 Steel + 1 Circuit |
| Quantum Tester | Quantum Assembler | Circuit + Redstone + Glass |
| Quantum Scanner | Quantum Assembler | Tester + Processor + Lens |

### Quantum Logic Blocks

| Bloco | Máquina | Método |
|-------|---------|--------|
| Quantum Comparator | Quantum Assembler | Circuit + Redstone + Comparator |
| Quantum Logic Gate AND | Quantum Assembler | 2 Circuit + Redstone |
| Quantum Logic Gate OR | Quantum Assembler | 2 Circuit + Redstone |
| Quantum Logic Gate NOT | Quantum Assembler | Circuit + Redstone + Torch |
| Quantum Timer | Quantum Assembler | Circuit + Clock + Redstone |
| Quantum Redstone Relay | Quantum Assembler | Circuit + Redstone + Comparator |

### Baterias

| Bateria | Capacidade | Método |
|---------|------------|--------|
| Basic Quantum Battery | 100.000 Q | Quantum Assembler (4 Steel + 2 Crystal) |
| Advanced Quantum Battery | 500.000 Q | Quantum Assembler (Basic + 2 Core) |
| Elite Quantum Battery | 2.500.000 Q | Quantum Assembler (Advanced + 2 Alloy) |
| Ultimate Quantum Battery | 10.000.000 Q | Quantum Assembler (Elite + Singularity) |

### Blocos de Bateria

| Bloco | Capacidade | Método |
|-------|------------|--------|
| Quantum Cell | 10.000.000 Q | Quantum Assembler (4 Casing + 4 Battery Basic) |
| Entangled Cell | 100.000.000 Q | Quantum Assembler (4 Frame + 4 Battery Advanced + 2 Core) |
| Singularity Cell | 1.000.000.000 Q | Quantum Assembler (4 Frame + 4 Battery Elite + Singularity) |

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

### Quantum Logic Blocks (alternativa a scripts)

| Bloco | Função |
|-------|--------|
| Quantum Comparator | Compara valores (>, <, =) |
| Quantum Logic Gate AND | E lógico |
| Quantum Logic Gate OR | OU lógico |
| Quantum Logic Gate NOT | NÃO lógico |
| Quantum Timer | Temporizador |
| Quantum Redstone Relay | Converte Quanta ↔ Redstone |

---

## 🔬 7. MECÂNICA QUÂNTICA

### Superposição (Chaos)

**Chances de sucesso:**

| Tier | Sucesso Total | Sucesso Parcial | Falha |
|------|---------------|-----------------|-------|
| Basic | 45% | 35% | 20% |
| Advanced | 50% | 35% | 15% |
| Elite | 60% | 30% | 10% |
| Ultimate | 75% | 20% | 5% |

### Decoerência (Order)

- Resultado previsível e garantido
- Sem riscos, sem bônus

### Entrelaçamento

- Distância máxima: 16/32/64 blocos (upgradável)
- Custo de manutenção: 10/20/40 Q/t

### Tunelamento Quântico

- Atravessa blocos sólidos
- Tipos: Energia, Itens, Pessoal (teletransporte)

### Observador

- Modos: Estabilizador (+10% velocidade), Seletor, Medidor

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

| Addon | Efeito | Valor |
|-------|--------|-------|
| Stabilizer Coil | Reduz falha | -5% |
| Quantum Lock | Reduz falha | -10% |
| Observer Link | Reduz falha | -15% (requer Lens) |
| Order Stabilizer | Reduz falha e velocidade | -10% falha, -10% speed |
| Chaos Overdrive | Aumenta performance | +50% speed, +25% falha |
| Speed Coil | Aumenta velocidade | +20% |
| Energy Coil | Reduz consumo | -20% |
| Quanta Generation Coil | Aumenta geração | +25% |
| Efficiency Coil | Reduz consumo combustível | -20% |
| Fluid Addon | Processa líquidos/gases | N/A |
| Cooling Coil | Reduz consumo refrigeração | -20% |
| Chaos Injector | +geração, +risco | +50% geração, +25% risco |
| Order Stabilizer (fluido) | Estabiliza | -30% risco, -10% geração |
| Efficiency Vent | Reduz consumo gás | -20% |

**Regras:**
- Máximo 4 do mesmo tipo
- Limite de 90% de sucesso total na superposição

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

### Quantum Codex

- Ganha XP perto de máquinas
- Desbloqueia **Equations** (componentes de spells)

### Equations

**Forma:** Self, Projectile, Touch, Area
**Elemento:** Order, Chaos, Neutro
**Efeito:** Dano, Cura, Aceleração, Teletransporte, Infusão, Revelação, Estabilização

### Black Board

- Combina Equations para criar spells personalizados

### Implante Quantum Processor (Cérebro)

- Permite usar o Codex (obrigatório para spells)
- Ganha XP em dobro
- Conecta ao Quantum Assembler para crafts remotos

### Spells base

| Spell | Efeito | Custo | Cooldown |
|-------|--------|-------|----------|
| Infuse | Infunde item com Quanta | 100 Q | 5s |
| Link | Conexão temporária | 500 Q | 30s |
| Tunnel | Teleporte curto | 250 Q | 10s |
| Reveal | Mostra escondidos | 50 Q | 1s |
| Accelerate | Acelera máquina | 200 Q | 20s |
| Stabilize | Reduz falha | 150 Q | 15s |

---

## 🦾 11. MELHORIAS CIBERNÉTICAS

| Slot | Upgrade | Efeito | Consumo |
|------|---------|--------|---------|
| Olhos | Quantum Sight | Ver através de blocos | 5 Q/t |
| Braços | Quantum Reach | Alcance +3 blocos | 10 Q/t |
| Pernas | Quantum Tunneling | Teletransporte curto | 20 Q/t |
| Torso | Quantum Buffer | Armazena 10.000 Q | 0 Q/t |
| Cérebro | Quantum Processor | Codex + XP + automação | 15 Q/t |

---

## 🎨 12. VISUAL

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

### Progressão visual

| Tier | Estilo |
|------|--------|
| Basic | Roxo escuro, partículas simples |
| Advanced | Roxo + Ciano, partículas ativas |
| Elite | Roxo + Ciano + Dourado, luz pulsante |
| Ultimate | Ciano + Dourado + Branco, efeitos intensos |

### Efeitos especiais

- **Superposição:** partículas duplicadas, luz pulsante, textura borrada
- **Decoerência:** partículas estáveis, luz constante
- **Entrelaçamento:** linha de partículas, anel giratório
- **Addons visíveis:** componentes 3D na máquina

---

## 📊 13. PROGRESSÃO SUGERIDA
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
↓
TIER 2.5
├── Vacuum Generator (passivo 500-1.000 Q/t)
├── Quantum Fluid Mixer
↓
TIER 3 (Elite)
├── Upgrade Kits
├── Quantum Forge, Gas Centrifuge, Plasma Forge
├── Black Hole Forge, Entanglement Reactor (5x5x5)
├── Quantum Alloy, Stabilized Singularity
↓
TIER ULTIMATE (Endgame)
├── Upgrade Kits
├── Entanglement Reactor (20x20x20, 8.000+ Q/t)
├── Quantum Supercomputer (rede global)
├── Singularity Cell (1B Q)

---

## 📋 14. PENDENTE / NOTAS

### Entanglement Reactor
- Geração: 2.000 Q/t (5x5x5) a 8.000 Q/t (20x20x20)
- Requer 1 Stabilized Singularity (não consumida)
- Precisa de addons estabilizadores
- Chaos Overdrive aumenta risco

### Black Hole Forge
- Multibloco 7x7x7
- Consome 1.000 Q/t

### Por definir
- Valores exatos de módulos (tiers I/II/III)
- Modelos 3D específicos

### Compatibilidade
- JEI/REI, The One Probe/Jade
- AE2, Mekanism, Create, Thermal, XNet
- Curios API

---

*Documento mantido como fonte única de verdade para desenvolvimento do Quanta Mod*