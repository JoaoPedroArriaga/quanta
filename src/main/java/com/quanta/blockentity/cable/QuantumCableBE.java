package com.quanta.blockentity.cable;

import com.quanta.Quanta;
import com.quanta.block.cable.CableNetworkManager;
import com.quanta.block.cable.CableType;
import com.quanta.block.cable.QuantumCableBlock;
import com.quanta.blockentity.ModBlockEntities;
import com.quanta.client.ClientAnimationHandler;
import com.quanta.client.renderer.state.CableRenderState;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuantumCableBE extends BlockEntity {

    private final ObjectList<CableType> presentCables = new ObjectArrayList<>();
    private final int[] connectionMasks = new int[6];
    private boolean needsUpdate = true;
    private CableRenderState renderState;

    public QuantumCableBE(BlockPos pos, BlockState state) {
        super(ModBlockEntities.QUANTUM_CABLE.get(), pos, state);
    }

    public boolean hasCable(CableType type) {
        return presentCables.contains(type);
    }
    
    public CableType getBaseType() {
        return presentCables.isEmpty() ? CableType.ENERGY : presentCables.get(0);
    }
    
    public List<CableType> getParticles() {
        if (presentCables.size() <= 1) return List.of();
        return presentCables.subList(1, presentCables.size());
    }
    
    public List<CableType> getPresentCables() {
        return new ArrayList<>(presentCables);
    }
    
    public int getPresentCablesMask() {
        int mask = 0;
        for (int i = 0; i < presentCables.size(); i++) {
            mask |= presentCables.get(i).getBitMask();
        }
        return mask;
    }

    public void addCable(CableType type) {
        if (presentCables.contains(type)) return;

        presentCables.add(type);
        
        setChanged();
        needsUpdate = true;
        requestModelDataUpdate();

        Quanta.LOGGER.info("ADDED CABLE {} at {} - order: {}", type.name, worldPosition, presentCables);

        if (level != null && !level.isClientSide) {
            updateAllConnections();
            syncToClient();
            CableNetworkManager.registerCable(level, worldPosition);
        } else if (level != null && level.isClientSide) {
            if (renderState == null) renderState = new CableRenderState();
            renderState.update(presentCables, connectionMasks);
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            requestModelDataUpdate();
        }
    }

    public void removeCable(CableType type) {
        if (!presentCables.remove(type)) return;

        int bit = type.getBitMask();
        for (int i = 0; i < 6; i++) {
            connectionMasks[i] &= ~bit;
        }

        setChanged();
        needsUpdate = true;
        requestModelDataUpdate();

        if (level != null && !level.isClientSide) {
            updateAllConnections();
            syncToClient();
            CableNetworkManager.removeCable(level, worldPosition);
        } else if (level != null && level.isClientSide) {
            if (renderState == null) renderState = new CableRenderState();
            renderState.update(presentCables, connectionMasks);
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            requestModelDataUpdate();
        }
    }

    public void refreshConnections() {
        if (level != null && !level.isClientSide) {
            updateAllConnections();
            syncToClient();
            CableNetworkManager.rebuildNetwork(level, worldPosition);
            requestModelDataUpdate();
        }
    }

    private void updateAllConnections() {
        if (level == null) return;
        
        int[] oldMasks = connectionMasks.clone();
        
        for (int i = 0; i < 6; i++) {
            connectionMasks[i] = 0;
        }
        
        for (int i = 0; i < presentCables.size(); i++) {
            CableType type = presentCables.get(i);
            updateConnectionsForType(type);
        }
        
        boolean changed = false;
        for (int i = 0; i < 6; i++) {
            if (oldMasks[i] != connectionMasks[i]) {
                changed = true;
                break;
            }
        }
        
        if (changed) {
            updateBlockState();
            CableNetworkManager.rebuildNetwork(level, worldPosition);
            
            for (Direction dir : Direction.values()) {
                BlockPos neighborPos = worldPosition.relative(dir);
                if (level.getBlockEntity(neighborPos) instanceof QuantumCableBE neighbor) {
                    neighbor.requestModelDataUpdate();
                    neighbor.syncToClient();
                }
            }
        }

        needsUpdate = false;
    }

    private void updateConnectionsForType(CableType type) {
        int bit = type.getBitMask();

        for (Direction dir : Direction.values()) {
            int connectedMask = 0;
            BlockPos neighborPos = worldPosition.relative(dir);
            BlockEntity neighborBE = level.getBlockEntity(neighborPos);

            if (neighborBE instanceof QuantumCableBE neighborCable) {
                if (neighborCable.hasCable(type)) {
                    connectedMask = bit;
                    
                    int[] neighborMasks = neighborCable.connectionMasks;
                    Direction opposite = dir.getOpposite();
                    if ((neighborMasks[opposite.ordinal()] & bit) == 0) {
                        neighborMasks[opposite.ordinal()] |= bit;
                        neighborCable.setChanged();
                        neighborCable.requestModelDataUpdate();
                    }
                }
            }

            int idx = dir.ordinal();
            connectionMasks[idx] |= connectedMask;
        }
    }

    private void updateBlockState() {
        BlockState current = getBlockState();
        BlockState newState = current;
        
        for (Direction dir : Direction.values()) {
            boolean hasConnection = connectionMasks[dir.ordinal()] != 0;
            newState = newState.setValue(QuantumCableBlock.getProperty(dir), hasConnection);
        }

        if (newState != current && level != null) {
            level.setBlock(worldPosition, newState, 3);
        }
    }

    public void syncToClient() {
        if (level != null && !level.isClientSide) {
            setChanged();
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    public void tick() {
        if (level == null) return;
        
        if (!level.isClientSide) {
            if (needsUpdate) {
                updateAllConnections();
                syncToClient();
            }
        }
    }

    public int getConnectionMask(Direction dir) {
        return connectionMasks[dir.ordinal()];
    }
    
    public boolean hasConnection(Direction dir, CableType type) {
        int mask = connectionMasks[dir.ordinal()];
        return (mask & type.getBitMask()) != 0;
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = super.getUpdateTag(registries);
        saveAdditional(tag, registries);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider registries) {
        super.handleUpdateTag(tag, registries);
        loadAdditional(tag, registries);
        if (level != null && level.isClientSide) {
            if (renderState == null) renderState = new CableRenderState();
            renderState.update(presentCables, connectionMasks);
            requestModelDataUpdate();
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt, HolderLookup.Provider registries) {
        CompoundTag tag = pkt.getTag();
        if (tag != null) {
            loadAdditional(tag, registries);
            if (level != null && level.isClientSide) {
                if (renderState == null) renderState = new CableRenderState();
                renderState.update(presentCables, connectionMasks);
                requestModelDataUpdate();
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        int[] typeIds = new int[presentCables.size()];
        for (int i = 0; i < presentCables.size(); i++) {
            typeIds[i] = presentCables.get(i).id;
        }
        tag.putIntArray("cable_types", typeIds);
        tag.putIntArray("conn_masks", connectionMasks);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        
        presentCables.clear();
        int[] typeIds = tag.getIntArray("cable_types");
        for (int id : typeIds) {
            presentCables.add(CableType.fromId(id));
        }

        if (tag.contains("conn_masks")) {
            int[] saved = tag.getIntArray("conn_masks");
            if (saved.length == 6) {
                System.arraycopy(saved, 0, connectionMasks, 0, 6);
            }
        }

        needsUpdate = true;

        if (level != null && level.isClientSide) {
            if (renderState == null) renderState = new CableRenderState();
            renderState.update(presentCables, connectionMasks);
            requestModelDataUpdate();
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (level != null && level.isClientSide) {
            ClientAnimationHandler.registerAnimatedBlock(worldPosition);
            if (renderState == null) renderState = new CableRenderState();
            renderState.update(presentCables, connectionMasks);
            requestModelDataUpdate();
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        } else if (level != null && !level.isClientSide) {
            level.scheduleTick(worldPosition, getBlockState().getBlock(), 2);
        }
    }
    
    @Override
    public void setRemoved() {
        if (level != null && level.isClientSide) {
            ClientAnimationHandler.unregisterAnimatedBlock(worldPosition);
        }
        super.setRemoved();
    }

    @Override
    public ModelData getModelData() {
        if (renderState == null) renderState = new CableRenderState();
        renderState.update(presentCables, connectionMasks);
        return ModelData.builder()
                .with(CableRenderState.PROPERTY, renderState)
                .build();
    }
}