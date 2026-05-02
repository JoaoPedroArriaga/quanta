package com.quanta.content.block.multiblock;

import com.mojang.serialization.MapCodec;
import com.quanta.content.block.base.QuantaMachineBlock;
import com.quanta.content.blockentity.multiblock.QuantumForgeControllerBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class QuantumForgeControllerBlock extends QuantaMachineBlock {

    public static final MapCodec<QuantumForgeControllerBlock> CODEC = simpleCodec(QuantumForgeControllerBlock::new);

    public QuantumForgeControllerBlock(Properties properties) { super(properties); }
    public QuantumForgeControllerBlock() { this(Properties.of().strength(5.0f).requiresCorrectToolForDrops()); }

    @Override
    protected MapCodec<? extends QuantaMachineBlock> codec() { return CODEC; }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new QuantumForgeControllerBE(pos, state); }
}