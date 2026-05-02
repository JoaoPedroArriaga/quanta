package com.quanta.content.block;

import com.quanta.Quanta;
import com.quanta.content.block.automation.*;
import com.quanta.content.block.cable.CableBlock;
import com.quanta.content.block.calculus.BlackBoardBlock;
import com.quanta.content.block.machine.*;
import com.quanta.content.block.multiblock.*;
import com.quanta.content.block.network.*;
import com.quanta.content.block.tank.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Quanta.MOD_ID);

    // Cable
    public static final DeferredBlock<Block> CABLE = BLOCKS.register("quantum_cable",
        () -> new CableBlock(BlockBehaviour.Properties.of().strength(1.5f).noOcclusion()));

    // Machines
    public static final DeferredBlock<Block> ENERGY_QUANTIFIER = BLOCKS.register("energy_quantifier",
        () -> new EnergyQuantifierBlock(BlockBehaviour.Properties.of().strength(2.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> PARTICLE_RECONSTRUCTOR = BLOCKS.register("particle_reconstructor",
        () -> new ParticleReconstructorBlock(BlockBehaviour.Properties.of().strength(3.0f).requiresCorrectToolForDrops().noOcclusion()));
    public static final DeferredBlock<Block> QUANTA_COLLAPSER = BLOCKS.register("quanta_collapser",
        () -> new QuantaCollapserBlock(BlockBehaviour.Properties.of().strength(3.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> QUANTA_INFUSER = BLOCKS.register("quanta_infuser",
        () -> new QuantaInfuserBlock(BlockBehaviour.Properties.of().strength(3.0f).requiresCorrectToolForDrops().noOcclusion()));
    public static final DeferredBlock<Block> QUANTA_COLLECTOR = BLOCKS.register("quanta_collector",
        () -> new QuantaCollectorBlock(BlockBehaviour.Properties.of().strength(3.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> QUANTA_ENHANCER = BLOCKS.register("quanta_enhancer",
        () -> new QuantaEnhancerBlock(BlockBehaviour.Properties.of().strength(3.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> QUANTUM_ASSEMBLER = BLOCKS.register("quantum_assembler",
        () -> new QuantumAssemblerBlock(BlockBehaviour.Properties.of().strength(3.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> QUANTUM_EVAPORATOR = BLOCKS.register("quantum_evaporator",
        () -> new QuantumEvaporatorBlock(BlockBehaviour.Properties.of().strength(3.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> QUANTUM_CONDENSER = BLOCKS.register("quantum_condenser",
        () -> new QuantumCondenserBlock(BlockBehaviour.Properties.of().strength(3.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> QUANTUM_PRESS = BLOCKS.register("quantum_press",
        () -> new QuantumPressBlock(BlockBehaviour.Properties.of().strength(3.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> QUANTUM_WIRE_DRAWER = BLOCKS.register("quantum_wire_drawer",
        () -> new QuantumWireDrawerBlock(BlockBehaviour.Properties.of().strength(3.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> QUANTA_ENGINE = BLOCKS.register("quanta_engine",
        () -> new QuantaEngineBlock(BlockBehaviour.Properties.of().strength(3.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> GAS_BURNER = BLOCKS.register("gas_burner",
        () -> new GasBurnerBlock(BlockBehaviour.Properties.of().strength(3.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> ENTANGLER = BLOCKS.register("entangler",
        () -> new EntanglerBlock(BlockBehaviour.Properties.of().strength(3.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> VOID_GENERATOR = BLOCKS.register("void_generator",
        () -> new VoidGeneratorBlock(BlockBehaviour.Properties.of().strength(3.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> QUANTUM_LIQUID_MIXER = BLOCKS.register("quantum_liquid_mixer",
        () -> new QuantumLiquidMixerBlock(BlockBehaviour.Properties.of().strength(3.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> TUNEL_RELAY = BLOCKS.register("tunel_relay",
        () -> new TunelRelayBlock(BlockBehaviour.Properties.of().strength(3.0f).requiresCorrectToolForDrops().noOcclusion()));

    // Multiblocks
    public static final DeferredBlock<Block> QUANTUM_FORGE_CONTROLLER = BLOCKS.register("quantum_forge_controller",
        () -> new QuantumForgeControllerBlock(BlockBehaviour.Properties.of().strength(5.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> BLACK_HOLE_FORGE_CONTROLLER = BLOCKS.register("black_hole_forge_controller",
        () -> new BlackHoleForgeControllerBlock(BlockBehaviour.Properties.of().strength(5.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> ENTANGLEMENT_REACTOR_CORE = BLOCKS.register("entanglement_reactor_core",
        () -> new EntanglementReactorCoreBlock(BlockBehaviour.Properties.of().strength(5.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> PLASMA_FORGE_CONTROLLER = BLOCKS.register("plasma_forge_controller",
        () -> new PlasmaForgeControllerBlock(BlockBehaviour.Properties.of().strength(5.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> CYBER_CHAMBER_CONTROLLER = BLOCKS.register("cyber_chamber_controller",
        () -> new CyberChamberControllerBlock(BlockBehaviour.Properties.of().strength(5.0f).requiresCorrectToolForDrops()));

    // Network
    public static final DeferredBlock<Block> QUANTUM_TERMINAL = BLOCKS.register("quantum_terminal",
        () -> new QuantumTerminalBlock(BlockBehaviour.Properties.of().strength(3.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> ENTANGLEMENT_SWITCH = BLOCKS.register("entanglement_switch",
        () -> new EntanglementSwitchBlock(BlockBehaviour.Properties.of().strength(3.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> QUANTUM_COMPUTER = BLOCKS.register("quantum_computer",
        () -> new QuantumComputerBlock(BlockBehaviour.Properties.of().strength(3.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> QUANTUM_MAINFRAME = BLOCKS.register("quantum_mainframe",
        () -> new QuantumMainframeBlock(BlockBehaviour.Properties.of().strength(3.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> QUANTUM_SUPERCOMPUTER = BLOCKS.register("quantum_supercomputer",
        () -> new QuantumSupercomputerBlock(BlockBehaviour.Properties.of().strength(5.0f).requiresCorrectToolForDrops()));

    // Automation
    public static final DeferredBlock<Block> QUANTUM_REDSTONE_RELAY = BLOCKS.register("quantum_redstone_relay",
        () -> new QuantumRedstoneRelayBlock(BlockBehaviour.Properties.of().strength(3.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> QUANTUM_ENERGY_MONITOR = BLOCKS.register("quantum_energy_monitor",
        () -> new QuantumEnergyMonitorBlock(BlockBehaviour.Properties.of().strength(3.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> QUANTUM_STORAGE_MONITOR = BLOCKS.register("quantum_storage_monitor",
        () -> new QuantumStorageMonitorBlock(BlockBehaviour.Properties.of().strength(3.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> QUANTUM_MODE_SWITCHER = BLOCKS.register("quantum_mode_switcher",
        () -> new QuantumModeSwitcherBlock(BlockBehaviour.Properties.of().strength(3.0f).requiresCorrectToolForDrops()));

    // Calculus
    public static final DeferredBlock<Block> BLACK_BOARD = BLOCKS.register("black_board",
        () -> new BlackBoardBlock(BlockBehaviour.Properties.of().strength(3.0f).requiresCorrectToolForDrops().noOcclusion()));

    // Tanks
    public static final DeferredBlock<Block> QUANTUM_TANK = BLOCKS.register("quantum_tank",
        () -> new QuantumTankBlock(BlockBehaviour.Properties.of().strength(3.0f).requiresCorrectToolForDrops().noOcclusion()));
    public static final DeferredBlock<Block> CONTAINMENT_TANK = BLOCKS.register("containment_tank",
        () -> new ContainmentTankBlock(BlockBehaviour.Properties.of().strength(3.0f).requiresCorrectToolForDrops().noOcclusion()));
    public static final DeferredBlock<Block> PRESSURIZED_TANK = BLOCKS.register("pressurized_tank",
        () -> new PressurizedTankBlock(BlockBehaviour.Properties.of().strength(3.0f).requiresCorrectToolForDrops().noOcclusion()));
}