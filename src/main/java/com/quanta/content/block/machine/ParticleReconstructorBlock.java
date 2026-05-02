package com.quanta.content.block.machine;

import com.mojang.serialization.MapCodec;
import com.quanta.content.block.base.QuantaMachineBlock;
import com.quanta.content.blockentity.ModBlockEntities;
import com.quanta.content.blockentity.machine.ParticleReconstructorBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ParticleReconstructorBlock extends QuantaMachineBlock {

    public static final MapCodec<ParticleReconstructorBlock> CODEC = simpleCodec(ParticleReconstructorBlock::new);

    public ParticleReconstructorBlock(Properties properties) { super(properties); }

    public ParticleReconstructorBlock() { this(Properties.of().strength(3.0f).requiresCorrectToolForDrops().noOcclusion()); }

    @Override
    protected MapCodec<? extends QuantaMachineBlock> codec() { return CODEC; }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new ParticleReconstructorBE(pos, state); }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide) return null;
        return createTickerHelper(type, ModBlockEntities.PARTICLE_RECONSTRUCTOR.get(),
            (lvl, pos, st, be) -> ((ParticleReconstructorBE) be).tickServer());
    }
}