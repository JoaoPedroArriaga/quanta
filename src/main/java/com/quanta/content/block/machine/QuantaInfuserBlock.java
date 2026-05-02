package com.quanta.content.block.machine;

import com.mojang.serialization.MapCodec;
import com.quanta.content.block.base.QuantaMachineBlock;
import com.quanta.content.blockentity.ModBlockEntities;
import com.quanta.content.blockentity.machine.QuantaInfuserBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class QuantaInfuserBlock extends QuantaMachineBlock {

    public static final MapCodec<QuantaInfuserBlock> CODEC = simpleCodec(QuantaInfuserBlock::new);

    public QuantaInfuserBlock(Properties properties) { super(properties); }

    public QuantaInfuserBlock() { this(Properties.of().strength(3.0f).requiresCorrectToolForDrops().noOcclusion()); }

    @Override
    protected MapCodec<? extends QuantaMachineBlock> codec() { return CODEC; }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new QuantaInfuserBE(pos, state); }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide) return null;
        return createTickerHelper(type, ModBlockEntities.QUANTA_INFUSER.get(),
            (lvl, pos, st, be) -> ((QuantaInfuserBE) be).tickServer());
    }
}