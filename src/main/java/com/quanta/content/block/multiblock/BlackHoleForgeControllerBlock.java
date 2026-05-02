package com.quanta.content.block.multiblock;

import com.mojang.serialization.MapCodec;
import com.quanta.content.block.base.QuantaMachineBlock;
import com.quanta.content.blockentity.multiblock.BlackHoleForgeControllerBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BlackHoleForgeControllerBlock extends QuantaMachineBlock {

    public static final MapCodec<BlackHoleForgeControllerBlock> CODEC = simpleCodec(BlackHoleForgeControllerBlock::new);

    public BlackHoleForgeControllerBlock(Properties properties) { super(properties); }
    public BlackHoleForgeControllerBlock() { this(Properties.of().strength(5.0f).requiresCorrectToolForDrops()); }

    @Override
    protected MapCodec<? extends QuantaMachineBlock> codec() { return CODEC; }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new BlackHoleForgeControllerBE(pos, state); }
}