package com.quanta.machine.Tuneler;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class TunelerBlock extends Block {
    public TunelerBlock() {
        super(BlockBehaviour.Properties.of().strength(3.0f));
    }
}
