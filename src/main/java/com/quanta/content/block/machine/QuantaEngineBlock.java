package com.quanta.content.block.machine;

import com.mojang.serialization.MapCodec;
import com.quanta.content.block.base.QuantaMachineBlock;
import com.quanta.content.blockentity.machine.QuantaEngineBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class QuantaEngineBlock extends QuantaMachineBlock {

    public static final MapCodec<QuantaEngineBlock> CODEC = simpleCodec(QuantaEngineBlock::new);

    public QuantaEngineBlock(Properties properties) { super(properties); }
    public QuantaEngineBlock() { this(Properties.of().strength(3.0f).requiresCorrectToolForDrops()); }

    @Override
    protected MapCodec<? extends QuantaMachineBlock> codec() { return CODEC; }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new QuantaEngineBE(pos, state); }
}