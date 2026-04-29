package com.quanta.block.machine;

import com.mojang.serialization.MapCodec;
import com.quanta.block.base.QuantaMachineBlock;
import com.quanta.blockentity.machine.TunelRelayBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import net.minecraft.world.level.Level;

public class TunelRelayBlock extends QuantaMachineBlock {
    
    public static final MapCodec<TunelRelayBlock> CODEC = 
        simpleCodec(TunelRelayBlock::new);
    
    public TunelRelayBlock(Properties properties) {
        super(properties);
    }
    
    public TunelRelayBlock() {
        this(Properties.of().strength(3.0f).requiresCorrectToolForDrops().noOcclusion());
    }
    
    @Override
    protected MapCodec<? extends QuantaMachineBlock> codec() {
        return CODEC;
    }
    
    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TunelRelayBE(pos, state);
    }
    
    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return null; // Sem ticker por enquanto
    }
}