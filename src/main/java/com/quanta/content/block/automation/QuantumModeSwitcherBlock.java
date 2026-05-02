package com.quanta.content.block.automation;

import com.mojang.serialization.MapCodec;
import com.quanta.content.block.base.QuantaMachineBlock;
import com.quanta.content.blockentity.automation.QuantumModeSwitcherBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class QuantumModeSwitcherBlock extends QuantaMachineBlock {

    public static final MapCodec<QuantumModeSwitcherBlock> CODEC = simpleCodec(QuantumModeSwitcherBlock::new);

    public QuantumModeSwitcherBlock(Properties properties) { super(properties); }
    public QuantumModeSwitcherBlock() { this(Properties.of().strength(3.0f).requiresCorrectToolForDrops()); }

    @Override
    protected MapCodec<? extends QuantaMachineBlock> codec() { return CODEC; }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new QuantumModeSwitcherBE(pos, state); }
}