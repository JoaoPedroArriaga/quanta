package com.quanta.content.block.machine;

import com.mojang.serialization.MapCodec;
import com.quanta.content.block.base.QuantaMachineBlock;
import com.quanta.content.blockentity.ModBlockEntities;
import com.quanta.content.blockentity.machine.QuantaCollapserBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class QuantaCollapserBlock extends QuantaMachineBlock {

    public static final MapCodec<QuantaCollapserBlock> CODEC = simpleCodec(QuantaCollapserBlock::new);

    public QuantaCollapserBlock(Properties properties) { super(properties); }

    public QuantaCollapserBlock() { this(Properties.of().strength(3.0f).requiresCorrectToolForDrops()); }

    @Override
    protected MapCodec<? extends QuantaMachineBlock> codec() { return CODEC; }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new QuantaCollapserBE(pos, state); }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide) return null;
        return createTickerHelper(type, ModBlockEntities.QUANTA_COLLAPSER.get(),
            (lvl, pos, st, be) -> ((QuantaCollapserBE) be).tickServer());
    }
}