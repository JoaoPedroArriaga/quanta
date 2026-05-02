package com.quanta.content.block.automation;

import com.mojang.serialization.MapCodec;
import com.quanta.content.block.base.QuantaMachineBlock;
import com.quanta.content.blockentity.automation.QuantumStorageMonitorBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class QuantumStorageMonitorBlock extends QuantaMachineBlock {

    public static final MapCodec<QuantumStorageMonitorBlock> CODEC = simpleCodec(QuantumStorageMonitorBlock::new);

    public QuantumStorageMonitorBlock(Properties properties) { super(properties); }
    public QuantumStorageMonitorBlock() { this(Properties.of().strength(3.0f).requiresCorrectToolForDrops()); }

    @Override
    protected MapCodec<? extends QuantaMachineBlock> codec() { return CODEC; }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new QuantumStorageMonitorBE(pos, state); }
}