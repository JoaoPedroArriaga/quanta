package com.quanta.content.block.machine;

import com.mojang.serialization.MapCodec;
import com.quanta.content.block.base.QuantaMachineBlock;
import com.quanta.content.blockentity.machine.QuantumLiquidMixerBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class QuantumLiquidMixerBlock extends QuantaMachineBlock {

    public static final MapCodec<QuantumLiquidMixerBlock> CODEC = simpleCodec(QuantumLiquidMixerBlock::new);

    public QuantumLiquidMixerBlock(Properties properties) { super(properties); }
    public QuantumLiquidMixerBlock() { this(Properties.of().strength(3.0f).requiresCorrectToolForDrops()); }

    @Override
    protected MapCodec<? extends QuantaMachineBlock> codec() { return CODEC; }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new QuantumLiquidMixerBE(pos, state); }
}