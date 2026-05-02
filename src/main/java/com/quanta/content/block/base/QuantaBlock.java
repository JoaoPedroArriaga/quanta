package com.quanta.content.block.base;

import net.minecraft.world.level.block.Block;

public abstract class QuantaBlock extends Block {

    protected QuantaBlock(Properties properties) {
        super(properties);
    }

    public static Properties defaultProperties() {
        return Properties.of()
            .strength(3.0f)
            .requiresCorrectToolForDrops();
    }
}