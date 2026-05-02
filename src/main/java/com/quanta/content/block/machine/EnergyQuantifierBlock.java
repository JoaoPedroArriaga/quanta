package com.quanta.content.block.machine;

import com.mojang.serialization.MapCodec;
import com.quanta.content.block.base.QuantaMachineBlock;
import com.quanta.content.blockentity.machine.EnergyQuantifierBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class EnergyQuantifierBlock extends QuantaMachineBlock {

    public static final MapCodec<EnergyQuantifierBlock> CODEC = simpleCodec(EnergyQuantifierBlock::new);

    public EnergyQuantifierBlock(Properties properties) { super(properties); }

    public EnergyQuantifierBlock() { this(Properties.of().strength(2.0f).requiresCorrectToolForDrops()); }

    @Override
    protected MapCodec<? extends QuantaMachineBlock> codec() { return CODEC; }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new EnergyQuantifierBE(pos, state); }
}