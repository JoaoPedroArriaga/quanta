# QUANTA MOD - TASKS

> Roteiro de desenvolvimento do mod Quanta para Minecraft NeoForge 1.21.1
> Status: 🔴 Pendente | 🟡 Em andamento | 🟢 Concluído | ⚠️ Bloqueado

---

## 🎯 MILESTONE 0: CONFIGURAÇÃO INICIAL (Setup)

- [X] 🔴 Criar `QuantaConfig.java` com valores básicos (FE per Quanta, custos)
- [X] 🔴 Registrar config no `Quanta.java`
- [X] 🔴 Criar `QuantaCapabilities.java` com BlockCapability para Quanta Energy
- [ ] 🔴 Configurar pacotes e estrutura de diretórios
- [ ] 🔴 Verificar build.gradle com dependências corretas

**Checkpoint:** Mod carrega sem erros ✅

---

## 🎯 MILESTONE 1: CORE ENERGY (MVP Base)

### 1.1 Capabilities
- [ ] 🔴 Ajustar `IQuantumEnergyStorage.java` (adicionar métodos simulate, getTier)
- [ ] 🔴 Ajustar `QuantumEnergyStorage.java` (adicionar onEnergyChanged, validações)
- [ ] 🔴 Criar `QuantaEnergyCapability.java` (Attacher para BlockEntities)
- [ ] 🔴 Testar capability em um bloco temporário

### 1.2 Configuração
- [ ] 🔴 Adicionar valores configuráveis:
  - `fePerQuanta` (padrão: 100)
  - `quantaPerFe` (padrão: 0)
  - `cableMaxTransfer` (padrão: 1000)

### 1.3 Registro Base
- [ ] 🔴 Criar `ModBlocks.java`
- [ ] 🔴 Criar `ModBlockEntities.java`
- [ ] 🔴 Expandir `ModItems.java` (adicionar itens que faltam)
- [ ] 🔴 Registrar tudo no `Quanta.java`

**Checkpoint:** Capacidade de energia funciona, mod registra blocos/itens ✅

---

## 🎯 MILESTONE 2: CONVERSORES E CABOS

### 2.1 Energy Quantifier (FE → Quanta)
- [ ] 🔴 Criar `EnergyQuantifierBlock.java`
- [ ] 🔴 Criar `EnergyQuantifierBE.java`
- [ ] 🔴 Implementar lógica: recebe FE, gera Quanta
- [ ] 🔴 Criar container/menu (se aplicável)
- [ ] 🔴 Adicionar recipe

### 2.2 Quanta Engine (Quanta → FE)
- [ ] 🔴 Criar `QuantaEngineBlock.java`
- [ ] 🔴 Criar `QuantaEngineBE.java`
- [ ] 🔴 Implementar lógica: consome Quanta, produz FE
- [ ] 🔴 Adicionar recipe

### 2.3 Cabos Quanta
- [ ] 🔴 Criar `QuantumCableBlock.java` (com VoxelShape de conexão)
- [ ] 🔴 Criar `CableNetwork.java` (gerenciamento de rede)
- [ ] 🔴 Criar `CableNetworkManager.java`
- [ ] 🔴 Implementar conexões entre cabos (BlockState com direções)
- [ ] 🔴 Conectar cabos a máquinas via capability

**Checkpoint:** FE ↔ Quanta funciona, energia flui por cabos ✅

---

## 🎯 MILESTONE 3: PRIMEIRA MÁQUINA - PARTICLE RECONSTRUCTOR

### 3.1 Bloco e BlockEntity
- [ ] 🔴 Criar `ParticleReconstructorBlock.java` (BaseEntityBlock)
- [ ] 🔴 Criar `ParticleReconstructorBE.java`
- [ ] 🔴 Implementar inventário (ItemStackHandler, 2 slots: input/output)
- [ ] 🔴 Conectar energy capability
- [ ] 🔴 Implementar tickServer() com lógica de processamento

### 3.2 Lógica de Duplicação
- [ ] 🔴 Implementar `isProcessable(ItemStack)` (minérios + ender pearl)
- [ ] 🔴 Implementar `getResult(ItemStack, int tier)` (multiplicador)
- [ ] 🔴 Configurar custo por operação (10 Q/t padrão)
- [ ] 🔴 Adicionar progresso (0-100 ticks)

### 3.3 GUI
- [ ] 🔴 Criar `ParticleReconstructorMenu.java` (AbstractContainerMenu)
- [ ] 🔴 Criar `ParticleReconstructorScreen.java`
- [ ] 🔴 Registrar MenuType
- [ ] 🔴 Adicionar barra de progresso e energia na GUI

### 3.4 Receitas
- [ ] 🔴 Adicionar recipe para o bloco (Quantum Dust + Iron + Ender Dust)
- [ ] 🔴 Adicionar recipe para processamento (datagen)

**Checkpoint:** Particle Reconstructor duplica minérios com Quanta ✅

---

## 🎯 MILESTONE 4: QUANTA INFUSER E MATERIAIS

### 4.1 Quanta Infuser
- [ ] 🔴 Criar `QuantaInfuserBlock.java`
- [ ] 🔴 Criar `QuantaInfuserBE.java`
- [ ] 🔴 Implementar infusão de materiais (Quantum Steel, Crystal, Alloy)
- [ ] 🔴 Criar GUI com slots (input, output, Quanta storage)

### 4.2 Materiais (já no ModItems)
- [ ] 🔴 Quantum Dust ✓ (já existe)
- [ ] 🔴 Quantum Steel ✓ (já existe)
- [ ] 🔴 Quantum Coil ✓ (já existe)
- [ ] 🔴 Quantum Circuit ✓ (já existe)
- [ ] 🔴 Quantum Crystal ✓ (já existe)
- [ ] 🔴 Essence of Order ✓ (já existe)
- [ ] 🔴 Essence of Chaos ✓ (já existe)

### 4.3 Receitas de Infusão
- [ ] 🔴 Quantum Dust + Iron → Quantum Steel
- [ ] 🔴 Quantum Dust + Diamond → Quantum Crystal
- [ ] 🔴 Quantum Dust + Netherite → Quantum Alloy
- [ ] 🔴 Essence of Order (via Quanta Collector - depois)

**Checkpoint:** Materiais Tier 1-2 são craftáveis ✅

---

## 🎯 MILESTONE 5: QUANTUM ASSEMBLER

### 5.1 Bloco e BlockEntity
- [ ] 🔴 Criar `QuantumAssemblerBlock.java`
- [ ] 🔴 Criar `QuantumAssemblerBE.java`
- [ ] 🔴 Implementar crafting grid (3x3 ou 5x5?)
- [ ] 🔴 Consumir Quanta por craft (15 Q/t)

### 5.2 GUI
- [ ] 🔴 Criar `QuantumAssemblerMenu.java` (com grid)
- [ ] 🔴 Criar `QuantumAssemblerScreen.java`
- [ ] 🔴 Mostrar energia disponível

### 5.3 Receitas do Mod
- [ ] 🔴 Adicionar todas as receitas da MASTERLIST:
  - [ ] Quantum Frame
  - [ ] Quantum Casing
  - [ ] Quantum Glass
  - [ ] Quantum Coil
  - [ ] Quantum Circuit
  - [ ] Entangled Core
  - [ ] Chaos Fragment
  - [ ] Observer Lens
  - [ ] Quantum Processor
  - [ ] Baterias (Basic, Advanced, Elite, Ultimate)

**Checkpoint:** Quantum Assembler crafta todos componentes do mod ✅

---

## 🎯 MILESTONE 6: FERRAMENTAS MODULARES

### 6.1 Classes Base
- [ ] 🔴 Criar `IModularEquipment.java` (interface)
- [ ] 🔴 Criar `ModularToolItem.java` (classe base para pickaxe, axe, shovel, sword)
- [ ] 🔴 Criar `ModularArmorItem.java` (classe base para armadura)

### 6.2 Ferramentas
- [ ] 🔴 Quantum Pickaxe (3 slots, 10.000 Q)
- [ ] 🔴 Quantum Axe (3 slots, 10.000 Q)
- [ ] 🔴 Quantum Shovel (3 slots, 10.000 Q)
- [ ] 🔴 Quantum Sword (3 slots, 10.000 Q)

### 6.3 Armadura
- [ ] 🔴 Quantum Helmet (4 slots, 5.000 Q)
- [ ] 🔴 Quantum Chestplate (6 slots, 15.000 Q)
- [ ] 🔴 Quantum Leggings (4 slots, 8.000 Q)
- [ ] 🔴 Quantum Boots (4 slots, 5.000 Q)

### 6.4 Sistema de Módulos
- [ ] 🔴 Criar `Module.java` (type, tier, cost)
- [ ] 🔴 Criar `ModuleType.java` (TUNNEL, VEIN_MINER, SPEED, etc)
- [ ] 🔴 Criar `ModuleTier.java` (I, II, III)
- [ ] 🔴 Criar `ModuleItem.java` (item que se insere na ferramenta)
- [ ] 🔴 Implementar slot de módulos na GUI da ferramenta

### 6.5 Módulos Básicos (Tier I)
- [ ] 🔴 Tunnel I (mina 2x2)
- [ ] 🔴 Vein Miner I (mina veio inteiro)
- [ ] 🔴 Speed I (+20% movimento)
- [ ] 🔴 Magnet I (puxa itens)
- [ ] 🔴 Chaos Strike I (15% chance de dano triplicado)

**Checkpoint:** Ferramentas modulares com módulos funcionam ✅

---

## 🎯 MILESTONE 7: QUANTUM CODEX E SPELLS

### 7.1 Quantum Codex
- [ ] 🔴 Criar `QuantumCodexItem.java`
- [ ] 🔴 Implementar armazenamento de Quanta (10.000 Q)
- [ ] 🔴 Implementar sistema de XP (ganha perto de máquinas)
- [ ] 🔴 Implementar armazenamento de spells equipados (max 6)
- [ ] 🔴 Criar GUI do Codex (equipar spells, ver progresso)

### 7.2 Spells Base
- [ ] 🔴 Criar `SpellDefinition.java`
- [ ] 🔴 Criar `QuantaSpellRegistry.java`
- [ ] 🔴 Implementar cast com teclas (1-6)
- [ ] 🔴 Spells iniciais:
  - [ ] Infuse (infunde item com Quanta, 100 Q)
  - [ ] Reveal (revela entidades escondidas, 50 Q)
  - [ ] Accelerate (acelera máquina, 150 Q)
  - [ ] Stabilize (reduz falha, 150 Q)
  - [ ] Teleport (teletransporte curto, 250 Q)

### 7.3 Equations e Black Board
- [ ] 🔴 Criar `Equation.java` (Forma + Elemento + Efeito)
- [ ] 🔴 Criar `BlackBoardBlock.java`
- [ ] 🔴 Criar `BlackBoardMenu.java` (combinação de equations)
- [ ] 🔴 Implementar desbloqueio de equations via experimentos

**Checkpoint:** Codex lança spells, Black Board cria novos spells ✅

---

## 🎯 MILESTONE 8: QUANTA COLLECTOR E ESSÊNCIAS

### 8.1 Quanta Collector
- [ ] 🔴 Criar `QuantaCollectorBlock.java`
- [ ] 🔴 Criar `QuantaCollectorBE.java`
- [ ] 🔴 Implementar modos: Decoerência (Order) e Superposição (Chaos)
- [ ] 🔴 Gerar Essence of Order/Chaos baseado no modo
- [ ] 🔴 Consumir Quanta para gerar essências

### 8.2 Essências
- [ ] 🔴 Essence of Order (crafting para componentes estabilizadores)
- [ ] 🔴 Essence of Chaos (crafting para componentes caóticos)

**Checkpoint:** Essências são geradas e usadas em crafts ✅

---

## 🎯 MILESTONE 9: GERADORES DE QUANTA

### 9.1 Vacuum Generator (Tier 2.5)
- [ ] 🔴 Criar `VacuumGeneratorBlock.java`
- [ ] 🔴 Criar `VacuumGeneratorBE.java`
- [ ] 🔴 Implementar geração passiva baseada no local (Overworld/Nether/End)
- [ ] 🔴 500-1.000 Q/t dependendo da dimensão/bioma

### 9.2 Quantum Engine (Tier 2)
- [ ] 🔴 Criar `QuantumEngineBlock.java`
- [ ] 🔴 Criar `QuantumEngineBE.java`
- [ ] 🔴 Queimar Fluid Quanta para gerar Quanta (50-200 Q/t)

### 9.3 Quantum Gas Burner (Tier 2)
- [ ] 🔴 Criar `QuantumGasBurnerBlock.java`
- [ ] 🔴 Queimar Quanta Vapor para gerar Quanta

**Checkpoint:** Múltiplas formas de gerar Quanta ✅

---

## 🎯 MILESTONE 10: FLUIDOS E GASES

### 10.1 Fluidos
- [ ] 🔴 Criar `ModFluids.java`
- [ ] 🔴 Criar `FluidQuanta.java`
- [ ] 🔴 Criar `FluidChaos.java`
- [ ] 🔴 Criar `FluidOrder.java`
- [ ] 🔴 Criar `QuantumFluidInfuserBlock.java` (produz fluidos)

### 10.2 Gases
- [ ] 🔴 Criar `QuantaVapor.java` (gás)
- [ ] 🔴 Criar `QuantumEvaporatorBlock.java` (fluido → gás)
- [ ] 🔴 Criar `QuantumCondenserBlock.java` (gás → fluido)

### 10.3 Tanques
- [ ] 🔴 Criar `QuantumTankBlock.java`
- [ ] 🔴 Suporte a fluidos e gases (4 tiers: Basic/Advanced/Elite/Ultimate)

**Checkpoint:** Fluidos e gases quânticos funcionam ✅

---

## 🎯 MILESTONE 11: CYBER IMPLANTES

### 11.1 Quantum Cyber Chamber
- [ ] 🔴 Criar `QuantumCyberChamberBlock.java` (multibloco 3x3x3)
- [ ] 🔴 Criar `CyberChamberMenu.java` (GUI para instalar implantes)
- [ ] 🔴 Implementar salvamento de implantes no jogador (capability)

### 11.2 Implantes Base (Advanced Tier)
- [ ] 🔴 Quantum Sight (ver através de blocos, 5 Q/t)
- [ ] 🔴 Quantum Reach (alcance +3 blocos, 10 Q/t)
- [ ] 🔴 Quantum Speed (+30% movimento, 10 Q/t)
- [ ] 🔴 Quantum Lungs (respiração infinita, 5 Q/t)
- [ ] 🔴 Quantum Buffer (10.000 Q reserva, 0 Q/t)

### 11.3 Implantes Elite
- [ ] 🔴 Quantum Tunneling (teletransporte curto, 20 Q/t)
- [ ] 🔴 Quantum Flight (voo criativo, 50 Q/t)

### 11.4 Implante Ultimate
- [ ] 🔴 Quantum Processor (Codex integrado + craft remoto, 15 Q/t)

**Checkpoint:** Implantes funcionam e salvam no jogador ✅

---

## 🎯 MILESTONE 12: SISTEMA DE EXPERIMENTOS

### 12.1 Equipamentos de Pesquisa
- [ ] 🔴 Criar `QuantumTesterItem.java` (mede assinatura quântica)
- [ ] 🔴 Criar `QuantumScannerItem.java` (registra experimentos)
- [ ] 🔴 Criar `QuantumAnalyzerBlock.java` (converte data em knowledge)

### 12.2 Lógica de Experimentos
- [ ] 🔴 Criar `ExperimentData.java` (salva experimentos)
- [ ] 🔴 Criar `ExperimentHypothesis.java` (condições para desbloquear equations)
- [ ] 🔴 Criar `ExperimentContext.java` (escaneia surroundings)
- [ ] 🔴 Implementar sistema de confiança (0-100%)

**Checkpoint:** Jogador descobre equations fazendo experimentos ✅

---

## 🎯 MILESTONE 13: MÁQUINAS AVANÇADAS (Tier 2-3)

### 13.1 Tier 2
- [ ] 🔴 Quanta Press (pressiona metais eficientemente)
- [ ] 🔴 Quantum Wire Drawer (fios eficientes)
- [ ] 🔴 Quantum Observer (manipula superposição)
- [ ] 🔴 Quantum Fluid Mixer (mistura fluidos)

### 13.2 Tier 3 (Elite)
- [ ] 🔴 Quantum Forge (processamento avançado)
- [ ] 🔴 Quantum Gas Centrifuge (separa gases)
- [ ] 🔴 Quantum Plasma Forge (processa gases em materiais)

### 13.3 Black Hole Forge
- [ ] 🔴 Criar `BlackHoleForgeBlock.java` (multibloco 7x7x7)
- [ ] 🔴 Implementar criação de Stabilized Singularity (Nether Star + Entangled Core)
- [ ] 🔴 Consome 1.000 Q/t

**Checkpoint:** Máquinas Tier 2-3 funcionam ✅

---

## 🎯 MILESTONE 14: ENTANGLEMENT REACTOR (ENDGAME)

### 14.1 Estrutura do Reator
- [ ] 🔴 Criar `EntanglementReactorCoreBlock.java`
- [ ] 🔴 Criar blocos de estrutura (ReactorCasing, ReactorGlass)
- [ ] 🔴 Implementar validação de multibloco (5x5x5 a 20x20x20)

### 14.2 Mecânica Dual-Dimensional
- [ ] 🔴 Implementar entrelaçamento entre dois reatores
- [ ] 🔴 Detectar dimensão de cada reator
- [ ] 🔴 Calcular geração baseada na combinação dimensional
- [ ] 🔴 Sistema de estabilidade (risco de explosão)

### 14.3 Refrigeração
- [ ] 🔴 Suporte a fluidos refrigerantes (Água, Fluid Quanta, Fluid Order, Stabilized Fluid)
- [ ] 🔴 Consumo de fluido por tick
- [ ] 🔴 Efeitos na estabilidade e geração

### 14.4 Addons
- [ ] 🔴 Implementar sistema de addons (slots na máquina)
- [ ] 🔴 Chaos Overdrive (+50% speed, +25% risco)
- [ ] 🔴 Order Stabilizer (-30% risco, -10% geração)
- [ ] 🔴 Speed Coil (+20% velocidade)

**Checkpoint:** Entanglement Reactor gera 2.000-8.000+ Q/t ✅

---

## 🎯 MILESTONE 15: ENTANGLEMENT NETWORK (WIRELESS)

### 15.1 Componentes Base
- [ ] 🔴 Criar `QuantumEntanglerBlock.java` (entrelaça máquinas)
- [ ] 🔴 Criar `QuantumLinkBlock.java` (extensor de alcance)
- [ ] 🔴 Criar `EntanglementHubBlock.java` (central de conexões)

### 15.2 Computadores de Rede
- [ ] 🔴 Quantum Terminal (4 conexões, 10 Q/t)
- [ ] 🔴 Quantum Computer (16 conexões, 40 Q/t)
- [ ] 🔴 Quantum Mainframe (64 conexões, 160 Q/t)
- [ ] 🔴 Quantum Supercomputer (256 conexões, 640 Q/t)

### 15.3 Funcionalidades
- [ ] 🔴 Transferência wireless de energia
- [ ] 🔴 Transferência wireless de itens (com Quantum Tuner)
- [ ] 🔴 Cross-dimensional (custo maior)

**Checkpoint:** Rede wireless funciona como AE2 ✅

---

## 🎯 MILESTONE 16: QUALIDADE DE VIDA E OTIMIZAÇÃO

### 16.1 Compatibilidade
- [ ] 🔴 Integração com JEI/REI (mostrar receitas)
- [ ] 🔴 Integração com The One Probe/Jade (info das máquinas)
- [ ] 🔴 Integração com Curios API (baterias no slot)
- [ ] 🔴 Compatibilidade com Mekanism, Create, Thermal (opcional)

### 16.2 Performance
- [ ] 🔴 Cache de redes de cabos (não recalcular toda rede a cada tick)
- [ ] 🔴 Validação lazy de multiblocos
- [ ] 🔴 Tick eficiente (máquinas só tickam se ativas)
- [ ] 🔴 Packet otimizado (sincronizar só quando necessário)

### 16.3 Configuração
- [ ] 🔴 Tornar todos os valores configuráveis (geração, consumo, chances)
- [ ] 🔴 Adicionar comentários no arquivo de config

### 16.4 Localização
- [ ] 🔴 Criar arquivos .json para tradução (pt_br, en_us)
- [ ] 🔴 Adicionar tooltips descritivos

**Checkpoint:** Mod estável, otimizado e compatível ✅

---

## 🎯 MILESTONE 17: POLIMENTO FINAL

### 17.1 Arte e Texturas
- [ ] 🔴 Texturas de todos os blocos (por tier)
- [ ] 🔴 Modelos 3D das máquinas
- [ ] 🔴 Partículas (energia, superposição, entrelaçamento)
- [ ] 🔴 Sons (máquinas, spells, implantes)

### 17.2 Documentação
- [ ] 🔴 Atualizar README.md
- [ ] 🔴 Atualizar MATERIAIS_E_CRAFTS.md
- [ ] 🔴 Criar wiki (opcional)
- [ ] 🔴 Criar vídeo de demonstração (opcional)

### 17.3 Release
- [ ] 🔴 Build final
- [ ] 🔴 Teste em servidor dedicado
- [ ] 🔴 Publicar no CurseForge/Modrinth
- [ ] 🔴 Criar post de lançamento

**Checkpoint:** MOD PRONTO PARA LANÇAMENTO 🚀

---

## 📊 RESUMO DE PROGRESSO

| Milestone | Total Tarefas | 🔴 Pendente | 🟡 Em andamento | 🟢 Concluído |
|-----------|---------------|-------------|-----------------|--------------|
| 0 - Setup | 4 | 4 | 0 | 0 |
| 1 - Core Energy | 8 | 8 | 0 | 0 |
| 2 - Conversores e Cabos | 10 | 10 | 0 | 0 |
| 3 - Particle Reconstructor | 12 | 12 | 0 | 0 |
| 4 - Quanta Infuser | 9 | 9 | 0 | 0 |
| 5 - Quantum Assembler | 9 | 9 | 0 | 0 |
| 6 - Ferramentas Modulares | 15 | 15 | 0 | 0 |
| 7 - Codex e Spells | 11 | 11 | 0 | 0 |
| 8 - Quanta Collector | 6 | 6 | 0 | 0 |
| 9 - Geradores | 7 | 7 | 0 | 0 |
| 10 - Fluidos e Gases | 8 | 8 | 0 | 0 |
| 11 - Cyber Implantes | 10 | 10 | 0 | 0 |
| 12 - Experimentos | 7 | 7 | 0 | 0 |
| 13 - Máquinas Avançadas | 9 | 9 | 0 | 0 |
| 14 - Entanglement Reactor | 10 | 10 | 0 | 0 |
| 15 - Entanglement Network | 8 | 8 | 0 | 0 |
| 16 - QoL e Otimização | 10 | 10 | 0 | 0 |
| 17 - Polimento | 9 | 9 | 0 | 0 |
| **TOTAL** | **162** | **162** | **0** | **0** |

---

## 📝 NOTAS E OBSERVAÇÕES

### Dependências entre Milestones
- Milestone 1 é pré-requisito para tudo
- Milestone 2 precisa do Milestone 1
- Milestone 3 precisa do Milestone 2 (energia)
- Milestone 4 precisa do Milestone 3 (infuser usa mesma lógica)
- Milestone 5 precisa do Milestone 4 (materiais)
- Milestone 6 pode ser feito em paralelo com Milestone 5
- Milestone 7 precisa do Milestone 1 (energia) apenas
- Milestone 8 pode ser feito depois do Milestone 4

### Decisões Técnicas Importantes
- Usar `BlockCapability` do NeoForge para energia (não o antigo CapabilityDispatcher)
- Usar `DeferredRegister` para todos os registros
- Datagen para todas as receitas (evitar arquivos manuais)
- Salvar dados do jogador com `AttachedData` (implantes, codex)

### Arquivos que já existem (manter)
- `IQuantumEnergyStorage.java` ✅
- `QuantumEnergyStorage.java` ✅
- `ModItems.java` ✅ (expandir)
- `CableType.java` ✅
- `CyberPart.java` ✅ (renomear para QuantumImplant)
- `CyberSlot.java` ✅ (renomear para ImplantSlot)

### Arquivos para renomear
- `QuantumConverterBlock.java` → `EnergyQuantifierBlock.java`
- `EntanglerBlock.java` → `QuantumEntanglerBlock.java`
- `TunelerBlock.java` → `QuantumTunelerBlock.java`
- `MultiCableBlock.java` → `QuantumCableBlock.java`

---

*Última atualização: 2026-04-10*