package com.quanta.content.block.machine;

import com.mojang.serialization.MapCodec;
import com.quanta.content.block.base.QuantaMachineBlock;
import com.quanta.content.blockentity.machine.QuantumAssemblerBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class QuantumAssemblerBlock extends QuantaMachineBlock {

    public static final MapCodec<QuantumAssemblerBlock> CODEC = simpleCodec(QuantumAssemblerBlock::new);

    public QuantumAssemblerBlock(Properties properties) { super(properties); }
    public QuantumAssemblerBlock() { this(Properties.of().strength(3.0f).requiresCorrectToolForDrops()); }

    @Override
    protected MapCodec<? extends QuantaMachineBlock> codec() { return CODEC; }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new QuantumAssemblerBE(pos, state); }
}