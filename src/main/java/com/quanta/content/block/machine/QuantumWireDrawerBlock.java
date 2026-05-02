package com.quanta.content.block.machine;

import com.mojang.serialization.MapCodec;
import com.quanta.content.block.base.QuantaMachineBlock;
import com.quanta.content.blockentity.machine.QuantumWireDrawerBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class QuantumWireDrawerBlock extends QuantaMachineBlock {

    public static final MapCodec<QuantumWireDrawerBlock> CODEC = simpleCodec(QuantumWireDrawerBlock::new);

    public QuantumWireDrawerBlock(Properties properties) { super(properties); }
    public QuantumWireDrawerBlock() { this(Properties.of().strength(3.0f).requiresCorrectToolForDrops()); }

    @Override
    protected MapCodec<? extends QuantaMachineBlock> codec() { return CODEC; }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new QuantumWireDrawerBE(pos, state); }
}