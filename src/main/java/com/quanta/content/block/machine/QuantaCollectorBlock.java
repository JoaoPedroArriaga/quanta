package com.quanta.content.block.machine;

import com.mojang.serialization.MapCodec;
import com.quanta.content.block.base.QuantaMachineBlock;
import com.quanta.content.blockentity.machine.QuantaCollectorBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class QuantaCollectorBlock extends QuantaMachineBlock {

    public static final MapCodec<QuantaCollectorBlock> CODEC = simpleCodec(QuantaCollectorBlock::new);

    public QuantaCollectorBlock(Properties properties) { super(properties); }
    public QuantaCollectorBlock() { this(Properties.of().strength(3.0f).requiresCorrectToolForDrops()); }

    @Override
    protected MapCodec<? extends QuantaMachineBlock> codec() { return CODEC; }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new QuantaCollectorBE(pos, state); }
}