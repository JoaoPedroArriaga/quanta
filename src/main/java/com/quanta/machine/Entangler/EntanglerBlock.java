package com.quanta.machine.Entangler;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class EntanglerBlock extends Block {
    public EntanglerBlock() {
        super(BlockBehaviour.Properties.of().strength(3.0f));
    }
}
