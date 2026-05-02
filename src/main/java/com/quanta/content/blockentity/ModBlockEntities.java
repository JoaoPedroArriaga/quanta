package com.quanta.content.blockentity;

import com.quanta.Quanta;
import com.quanta.content.block.ModBlocks;
import com.quanta.content.blockentity.automation.*;
import com.quanta.content.blockentity.cable.CableBlockEntity;
import com.quanta.content.blockentity.calculus.BlackBoardBE;
import com.quanta.content.blockentity.machine.*;
import com.quanta.content.blockentity.multiblock.*;
import com.quanta.content.blockentity.network.*;
import com.quanta.content.blockentity.tank.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
        DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Quanta.MOD_ID);

    // Cable
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CableBlockEntity>> CABLE =
        BLOCK_ENTITIES.register("cable",
            () -> BlockEntityType.Builder.of(CableBlockEntity::new, ModBlocks.CABLE.get()).build(null));

    // Machines
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EnergyQuantifierBE>> ENERGY_QUANTIFIER =
        BLOCK_ENTITIES.register("energy_quantifier",
            () -> BlockEntityType.Builder.of(EnergyQuantifierBE::new, ModBlocks.ENERGY_QUANTIFIER.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ParticleReconstructorBE>> PARTICLE_RECONSTRUCTOR =
        BLOCK_ENTITIES.register("particle_reconstructor",
            () -> BlockEntityType.Builder.of(ParticleReconstructorBE::new, ModBlocks.PARTICLE_RECONSTRUCTOR.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<QuantaCollapserBE>> QUANTA_COLLAPSER =
        BLOCK_ENTITIES.register("quanta_collapser",
            () -> BlockEntityType.Builder.of(QuantaCollapserBE::new, ModBlocks.QUANTA_COLLAPSER.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<QuantaInfuserBE>> QUANTA_INFUSER =
        BLOCK_ENTITIES.register("quanta_infuser",
            () -> BlockEntityType.Builder.of(QuantaInfuserBE::new, ModBlocks.QUANTA_INFUSER.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<QuantaCollectorBE>> QUANTA_COLLECTOR =
        BLOCK_ENTITIES.register("quanta_collector",
            () -> BlockEntityType.Builder.of(QuantaCollectorBE::new, ModBlocks.QUANTA_COLLECTOR.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<QuantaEnhancerBE>> QUANTA_ENHANCER =
        BLOCK_ENTITIES.register("quanta_enhancer",
            () -> BlockEntityType.Builder.of(QuantaEnhancerBE::new, ModBlocks.QUANTA_ENHANCER.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<QuantumAssemblerBE>> QUANTUM_ASSEMBLER =
        BLOCK_ENTITIES.register("quantum_assembler",
            () -> BlockEntityType.Builder.of(QuantumAssemblerBE::new, ModBlocks.QUANTUM_ASSEMBLER.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<QuantumEvaporatorBE>> QUANTUM_EVAPORATOR =
        BLOCK_ENTITIES.register("quantum_evaporator",
            () -> BlockEntityType.Builder.of(QuantumEvaporatorBE::new, ModBlocks.QUANTUM_EVAPORATOR.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<QuantumCondenserBE>> QUANTUM_CONDENSER =
        BLOCK_ENTITIES.register("quantum_condenser",
            () -> BlockEntityType.Builder.of(QuantumCondenserBE::new, ModBlocks.QUANTUM_CONDENSER.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<QuantumPressBE>> QUANTUM_PRESS =
        BLOCK_ENTITIES.register("quantum_press",
            () -> BlockEntityType.Builder.of(QuantumPressBE::new, ModBlocks.QUANTUM_PRESS.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<QuantumWireDrawerBE>> QUANTUM_WIRE_DRAWER =
        BLOCK_ENTITIES.register("quantum_wire_drawer",
            () -> BlockEntityType.Builder.of(QuantumWireDrawerBE::new, ModBlocks.QUANTUM_WIRE_DRAWER.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<QuantaEngineBE>> QUANTA_ENGINE =
        BLOCK_ENTITIES.register("quanta_engine",
            () -> BlockEntityType.Builder.of(QuantaEngineBE::new, ModBlocks.QUANTA_ENGINE.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GasBurnerBE>> GAS_BURNER =
        BLOCK_ENTITIES.register("gas_burner",
            () -> BlockEntityType.Builder.of(GasBurnerBE::new, ModBlocks.GAS_BURNER.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EntanglerBE>> ENTANGLER =
        BLOCK_ENTITIES.register("entangler",
            () -> BlockEntityType.Builder.of(EntanglerBE::new, ModBlocks.ENTANGLER.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<VoidGeneratorBE>> VOID_GENERATOR =
        BLOCK_ENTITIES.register("void_generator",
            () -> BlockEntityType.Builder.of(VoidGeneratorBE::new, ModBlocks.VOID_GENERATOR.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<QuantumLiquidMixerBE>> QUANTUM_LIQUID_MIXER =
        BLOCK_ENTITIES.register("quantum_liquid_mixer",
            () -> BlockEntityType.Builder.of(QuantumLiquidMixerBE::new, ModBlocks.QUANTUM_LIQUID_MIXER.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TunelRelayBE>> TUNEL_RELAY =
        BLOCK_ENTITIES.register("tunel_relay",
            () -> BlockEntityType.Builder.of(TunelRelayBE::new, ModBlocks.TUNEL_RELAY.get()).build(null));

    // Multiblocks
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<QuantumForgeControllerBE>> QUANTUM_FORGE_CONTROLLER =
        BLOCK_ENTITIES.register("quantum_forge_controller",
            () -> BlockEntityType.Builder.of(QuantumForgeControllerBE::new, ModBlocks.QUANTUM_FORGE_CONTROLLER.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BlackHoleForgeControllerBE>> BLACK_HOLE_FORGE_CONTROLLER =
        BLOCK_ENTITIES.register("black_hole_forge_controller",
            () -> BlockEntityType.Builder.of(BlackHoleForgeControllerBE::new, ModBlocks.BLACK_HOLE_FORGE_CONTROLLER.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EntanglementReactorCoreBE>> ENTANGLEMENT_REACTOR_CORE =
        BLOCK_ENTITIES.register("entanglement_reactor_core",
            () -> BlockEntityType.Builder.of(EntanglementReactorCoreBE::new, ModBlocks.ENTANGLEMENT_REACTOR_CORE.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PlasmaForgeControllerBE>> PLASMA_FORGE_CONTROLLER =
        BLOCK_ENTITIES.register("plasma_forge_controller",
            () -> BlockEntityType.Builder.of(PlasmaForgeControllerBE::new, ModBlocks.PLASMA_FORGE_CONTROLLER.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CyberChamberControllerBE>> CYBER_CHAMBER_CONTROLLER =
        BLOCK_ENTITIES.register("cyber_chamber_controller",
            () -> BlockEntityType.Builder.of(CyberChamberControllerBE::new, ModBlocks.CYBER_CHAMBER_CONTROLLER.get()).build(null));

    // Network
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<QuantumTerminalBE>> QUANTUM_TERMINAL =
        BLOCK_ENTITIES.register("quantum_terminal",
            () -> BlockEntityType.Builder.of(QuantumTerminalBE::new, ModBlocks.QUANTUM_TERMINAL.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EntanglementSwitchBE>> ENTANGLEMENT_SWITCH =
        BLOCK_ENTITIES.register("entanglement_switch",
            () -> BlockEntityType.Builder.of(EntanglementSwitchBE::new, ModBlocks.ENTANGLEMENT_SWITCH.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<QuantumComputerBE>> QUANTUM_COMPUTER =
        BLOCK_ENTITIES.register("quantum_computer",
            () -> BlockEntityType.Builder.of(QuantumComputerBE::new, ModBlocks.QUANTUM_COMPUTER.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<QuantumMainframeBE>> QUANTUM_MAINFRAME =
        BLOCK_ENTITIES.register("quantum_mainframe",
            () -> BlockEntityType.Builder.of(QuantumMainframeBE::new, ModBlocks.QUANTUM_MAINFRAME.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<QuantumSupercomputerBE>> QUANTUM_SUPERCOMPUTER =
        BLOCK_ENTITIES.register("quantum_supercomputer",
            () -> BlockEntityType.Builder.of(QuantumSupercomputerBE::new, ModBlocks.QUANTUM_SUPERCOMPUTER.get()).build(null));

    // Automation
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<QuantumRedstoneRelayBE>> QUANTUM_REDSTONE_RELAY =
        BLOCK_ENTITIES.register("quantum_redstone_relay",
            () -> BlockEntityType.Builder.of(QuantumRedstoneRelayBE::new, ModBlocks.QUANTUM_REDSTONE_RELAY.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<QuantumEnergyMonitorBE>> QUANTUM_ENERGY_MONITOR =
        BLOCK_ENTITIES.register("quantum_energy_monitor",
            () -> BlockEntityType.Builder.of(QuantumEnergyMonitorBE::new, ModBlocks.QUANTUM_ENERGY_MONITOR.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<QuantumStorageMonitorBE>> QUANTUM_STORAGE_MONITOR =
        BLOCK_ENTITIES.register("quantum_storage_monitor",
            () -> BlockEntityType.Builder.of(QuantumStorageMonitorBE::new, ModBlocks.QUANTUM_STORAGE_MONITOR.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<QuantumModeSwitcherBE>> QUANTUM_MODE_SWITCHER =
        BLOCK_ENTITIES.register("quantum_mode_switcher",
            () -> BlockEntityType.Builder.of(QuantumModeSwitcherBE::new, ModBlocks.QUANTUM_MODE_SWITCHER.get()).build(null));

    // Calculus
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BlackBoardBE>> BLACK_BOARD =
        BLOCK_ENTITIES.register("black_board",
            () -> BlockEntityType.Builder.of(BlackBoardBE::new, ModBlocks.BLACK_BOARD.get()).build(null));

    // Tanks
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<QuantumTankBE>> QUANTUM_TANK =
        BLOCK_ENTITIES.register("quantum_tank",
            () -> BlockEntityType.Builder.of(QuantumTankBE::new, ModBlocks.QUANTUM_TANK.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ContainmentTankBE>> CONTAINMENT_TANK =
        BLOCK_ENTITIES.register("containment_tank",
            () -> BlockEntityType.Builder.of(ContainmentTankBE::new, ModBlocks.CONTAINMENT_TANK.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PressurizedTankBE>> PRESSURIZED_TANK =
        BLOCK_ENTITIES.register("pressurized_tank",
            () -> BlockEntityType.Builder.of(PressurizedTankBE::new, ModBlocks.PRESSURIZED_TANK.get()).build(null));
}