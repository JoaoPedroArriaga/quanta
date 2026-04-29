package com.quanta.blockentity;

import com.quanta.Quanta;
import com.quanta.block.ModBlocks;
import com.quanta.blockentity.machine.EnergyQuantifierBE;
import com.quanta.blockentity.machine.ParticleReconstructorBE;
import com.quanta.blockentity.machine.QuantaCollapserBE;
import com.quanta.blockentity.machine.QuantaInfuserBE;
import com.quanta.blockentity.machine.TunelRelayBE;
import com.quanta.blockentity.cable.QuantumCableBE;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
        DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Quanta.MOD_ID);
    
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ParticleReconstructorBE>> PARTICLE_RECONSTRUCTOR =
        BLOCK_ENTITIES.register("particle_reconstructor",
            () -> BlockEntityType.Builder.of(ParticleReconstructorBE::new,
                ModBlocks.PARTICLE_RECONSTRUCTOR.get()).build(null));
    
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EnergyQuantifierBE>> ENERGY_QUANTIFIER =
        BLOCK_ENTITIES.register("energy_quantifier",
            () -> BlockEntityType.Builder.of(EnergyQuantifierBE::new,
                ModBlocks.ENERGY_QUANTIFIER.get()).build(null));
    
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<QuantaCollapserBE>> QUANTA_COLLAPSER =
        BLOCK_ENTITIES.register("quanta_collapser",
            () -> BlockEntityType.Builder.of(QuantaCollapserBE::new,
                ModBlocks.QUANTA_COLLAPSER.get()).build(null));
    
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<QuantaInfuserBE>> QUANTA_INFUSER =
        BLOCK_ENTITIES.register("quanta_infuser",
            () -> BlockEntityType.Builder.of(QuantaInfuserBE::new,
                ModBlocks.QUANTA_INFUSER.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TunelRelayBE>> TUNEL_RELAY =
        BLOCK_ENTITIES.register("tunel_relay",
            () -> BlockEntityType.Builder.of(TunelRelayBE::new,
                ModBlocks.TUNEL_RELAY.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<QuantumCableBE>> QUANTUM_CABLE =
        BLOCK_ENTITIES.register("quantum_cable",
            () -> BlockEntityType.Builder.of(QuantumCableBE::new,
                ModBlocks.QUANTUM_CABLE.get()).build(null));
}