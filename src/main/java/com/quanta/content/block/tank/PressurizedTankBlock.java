package com.quanta.content.block.tank;

import com.mojang.serialization.MapCodec;
import com.quanta.content.block.base.QuantaMachineBlock;
import com.quanta.content.blockentity.tank.PressurizedTankBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class PressurizedTankBlock extends QuantaMachineBlock {

    public static final MapCodec<PressurizedTankBlock> CODEC = simpleCodec(PressurizedTankBlock::new);

    public PressurizedTankBlock(Properties properties) { super(properties); }
    public PressurizedTankBlock() { this(Properties.of().strength(3.0f).requiresCorrectToolForDrops().noOcclusion()); }

    @Override
    protected MapCodec<? extends QuantaMachineBlock> codec() { return CODEC; }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new PressurizedTankBE(pos, state); }
}
