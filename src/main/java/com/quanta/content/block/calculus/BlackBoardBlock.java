package com.quanta.content.block.calculus;

import com.mojang.serialization.MapCodec;
import com.quanta.content.block.base.QuantaMachineBlock;
import com.quanta.content.blockentity.calculus.BlackBoardBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BlackBoardBlock extends QuantaMachineBlock {

    public static final MapCodec<BlackBoardBlock> CODEC = simpleCodec(BlackBoardBlock::new);

    public BlackBoardBlock(Properties properties) { super(properties); }
    public BlackBoardBlock() { this(Properties.of().strength(3.0f).requiresCorrectToolForDrops().noOcclusion()); }

    @Override
    protected MapCodec<? extends QuantaMachineBlock> codec() { return CODEC; }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new BlackBoardBE(pos, state); }
}