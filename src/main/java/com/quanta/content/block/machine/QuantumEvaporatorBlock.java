package com.quanta.content.block.machine;

import com.mojang.serialization.MapCodec;
import com.quanta.content.block.base.QuantaMachineBlock;
import com.quanta.content.blockentity.machine.QuantumEvaporatorBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class QuantumEvaporatorBlock extends QuantaMachineBlock {

    public static final MapCodec<QuantumEvaporatorBlock> CODEC = simpleCodec(QuantumEvaporatorBlock::new);

    public QuantumEvaporatorBlock(Properties properties) { super(properties); }
    public QuantumEvaporatorBlock() { this(Properties.of().strength(3.0f).requiresCorrectToolForDrops()); }

    @Override
    protected MapCodec<? extends QuantaMachineBlock> codec() { return CODEC; }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new QuantumEvaporatorBE(pos, state); }
}