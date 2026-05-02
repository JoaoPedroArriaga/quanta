package com.quanta.content.block.machine;

import com.mojang.serialization.MapCodec;
import com.quanta.content.block.base.QuantaMachineBlock;
import com.quanta.content.blockentity.machine.TunelRelayBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class TunelRelayBlock extends QuantaMachineBlock {

    public static final MapCodec<TunelRelayBlock> CODEC = simpleCodec(TunelRelayBlock::new);

    public TunelRelayBlock(Properties properties) { super(properties); }

    public TunelRelayBlock() { this(Properties.of().strength(3.0f).requiresCorrectToolForDrops().noOcclusion()); }

    @Override
    protected MapCodec<? extends QuantaMachineBlock> codec() { return CODEC; }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new TunelRelayBE(pos, state); }
}