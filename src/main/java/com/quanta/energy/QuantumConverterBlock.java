package com.quanta.energy;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class QuantumConverterBlock extends Block {
    public QuantumConverterBlock() {
        super(BlockBehaviour.Properties.of().strength(2.0f));
    }
}
