package com.quanta.block;

import com.quanta.Quanta;
import com.quanta.block.cable.QuantumCableBlock;
import com.quanta.block.machine.EnergyQuantifierBlock;
import com.quanta.block.machine.ParticleReconstructorBlock;
import com.quanta.block.machine.QuantaCollapserBlock;
import com.quanta.block.machine.QuantaInfuserBlock;
import com.quanta.block.machine.TunelRelayBlock;

import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
        DeferredRegister.createBlocks(Quanta.MOD_ID);
    
    // Usando Supplier com lambda - sem ambiguidade
    public static final DeferredBlock<Block> ENERGY_QUANTIFIER = BLOCKS.register(
        "energy_quantifier", () -> new EnergyQuantifierBlock());
    
    public static final DeferredBlock<Block> PARTICLE_RECONSTRUCTOR = BLOCKS.register(
        "particle_reconstructor", () -> new ParticleReconstructorBlock());
    
    public static final DeferredBlock<Block> QUANTUM_CABLE = BLOCKS.register(
        "quantum_cable", () -> new QuantumCableBlock());
    
    public static final DeferredBlock<Block> QUANTA_COLLAPSER = BLOCKS.register(
        "quanta_collapser", () -> new QuantaCollapserBlock());
    
    public static final DeferredBlock<Block> QUANTA_INFUSER = BLOCKS.register(
        "quanta_infuser", () -> new QuantaInfuserBlock());

    public static final DeferredBlock<Block> TUNEL_RELAY = BLOCKS.register(
        "tunel_relay", () -> new TunelRelayBlock());
}