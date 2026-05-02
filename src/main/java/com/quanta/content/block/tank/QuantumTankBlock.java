package com.quanta.content.block.tank;

import com.mojang.serialization.MapCodec;
import com.quanta.content.block.base.QuantaMachineBlock;
import com.quanta.content.blockentity.tank.QuantumTankBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class QuantumTankBlock extends QuantaMachineBlock {

    public static final MapCodec<QuantumTankBlock> CODEC = simpleCodec(QuantumTankBlock::new);

    public QuantumTankBlock(Properties properties) { super(properties); }
    public QuantumTankBlock() { this(Properties.of().strength(3.0f).requiresCorrectToolForDrops().noOcclusion()); }

    @Override
    protected MapCodec<? extends QuantaMachineBlock> codec() { return CODEC; }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new QuantumTankBE(pos, state); }
}