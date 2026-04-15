package com.quanta.block.base;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * Base class for all Quanta blocks.
 * Provides common properties and performance optimizations.
 */
public abstract class QuantaBlock extends Block {
    
    protected QuantaBlock(Properties properties) {
        super(properties);
    }
    
    /**
     * Creates default properties for Quanta blocks.
     * Override if different behavior is needed.
     */
    public static Properties defaultProperties() {
        return BlockBehaviour.Properties.of()
            .strength(3.0f)
            .requiresCorrectToolForDrops();
    }
}