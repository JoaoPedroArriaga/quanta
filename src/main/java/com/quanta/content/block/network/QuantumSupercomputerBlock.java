package com.quanta.content.block.network;

import com.mojang.serialization.MapCodec;
import com.quanta.content.block.base.QuantaMachineBlock;
import com.quanta.content.blockentity.network.QuantumSupercomputerBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class QuantumSupercomputerBlock extends QuantaMachineBlock {

    public static final MapCodec<QuantumSupercomputerBlock> CODEC = simpleCodec(QuantumSupercomputerBlock::new);

    public QuantumSupercomputerBlock(Properties properties) { super(properties); }
    public QuantumSupercomputerBlock() { this(Properties.of().strength(5.0f).requiresCorrectToolForDrops()); }

    @Override
    protected MapCodec<? extends QuantaMachineBlock> codec() { return CODEC; }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new QuantumSupercomputerBE(pos, state); }
}