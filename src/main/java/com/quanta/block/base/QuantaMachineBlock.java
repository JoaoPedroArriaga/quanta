package com.quanta.block.base;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public abstract class QuantaMachineBlock extends BaseEntityBlock {
    
    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    
    protected QuantaMachineBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(LIT, false));
    }
    
    // Propriedades comuns de QuantaBlock (copiadas)
    public static Properties defaultProperties() {
        return Properties.of()
            .strength(3.0f)
            .requiresCorrectToolForDrops();
    }
    
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<net.minecraft.world.level.block.Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(LIT);
    }
    
    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
    
    @Override
    protected abstract MapCodec<? extends BaseEntityBlock> codec();
}