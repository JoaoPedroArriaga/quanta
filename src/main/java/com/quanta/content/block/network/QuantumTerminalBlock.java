package com.quanta.content.block.network;

import com.mojang.serialization.MapCodec;
import com.quanta.content.block.base.QuantaMachineBlock;
import com.quanta.content.blockentity.network.QuantumTerminalBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class QuantumTerminalBlock extends QuantaMachineBlock {

    public static final MapCodec<QuantumTerminalBlock> CODEC = simpleCodec(QuantumTerminalBlock::new);

    public QuantumTerminalBlock(Properties properties) { super(properties); }
    public QuantumTerminalBlock() { this(Properties.of().strength(3.0f).requiresCorrectToolForDrops()); }

    @Override
    protected MapCodec<? extends QuantaMachineBlock> codec() { return CODEC; }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new QuantumTerminalBE(pos, state); }
}