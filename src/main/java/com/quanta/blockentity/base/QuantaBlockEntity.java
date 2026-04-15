package com.quanta.blockentity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public abstract class QuantaBlockEntity extends BlockEntity {
    
    // Packed flags in a single byte
    private byte flags = 0;
    private static final byte FLAG_DIRTY = 0b00000001;
    private static final byte FLAG_SYNC_PENDING = 0b00000010;
    
    public QuantaBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
    
    protected void setChangedAndSync() {
        if ((flags & FLAG_DIRTY) == 0) {
            flags |= FLAG_DIRTY;
            setChanged();
            if (level != null && !level.isClientSide) {
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
                flags |= FLAG_SYNC_PENDING;
            }
        }
    }
    
    protected void clearDirty() {
        flags &= ~FLAG_DIRTY;
        flags &= ~FLAG_SYNC_PENDING;
    }
    
    protected boolean isDirty() {
        return (flags & FLAG_DIRTY) != 0;
    }
    
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = super.getUpdateTag(registries);
        saveAdditional(tag, registries);
        return tag;
    }
    
    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider registries) {
        loadAdditional(tag, registries);
        clearDirty();
    }
    
    @Override
    public @Nullable ClientboundBlockEntityDataPacket getUpdatePacket() {
        if ((flags & FLAG_SYNC_PENDING) != 0) {
            flags &= ~FLAG_SYNC_PENDING;
            return ClientboundBlockEntityDataPacket.create(this);
        }
        return null;
    }
    
    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt, HolderLookup.Provider registries) {
        CompoundTag tag = pkt.getTag();
        if (tag != null) {
            loadAdditional(tag, registries);
            clearDirty();
        }
    }
}