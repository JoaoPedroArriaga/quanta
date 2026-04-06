package com.quanta.cable;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class MultiCableBlock extends Block {
    public MultiCableBlock() {
        super(BlockBehaviour.Properties.of().strength(1.5f).noOcclusion());
    }
}
