package com.quanta.machine.QuantumInfuser;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class QuantumInfuserBlock extends Block {
    public QuantumInfuserBlock() {
        super(BlockBehaviour.Properties.of().strength(3.0f).requiresCorrectToolForDrops());
    }
}
