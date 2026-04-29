package com.quanta.block.cable;

import com.quanta.Quanta;
import com.quanta.blockentity.cable.QuantumCableBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import java.util.*;

public class CableNetworkManager {
    
    private static final Map<Level, Map<BlockPos, UUID>> LEVEL_NETWORKS = new HashMap<>();
    private static final Map<UUID, CableNetwork> NETWORKS = new HashMap<>();
    
    public static void markDirty(Level level, BlockPos pos) {
        if (level.isClientSide) return;
        rebuildNetwork(level, pos);
    }
    
    public static CableNetwork getNetwork(Level level, BlockPos pos) {
        Map<BlockPos, UUID> levelMap = LEVEL_NETWORKS.get(level);
        if (levelMap == null) return null;
        UUID networkId = levelMap.get(pos);
        if (networkId == null) return null;
        return NETWORKS.get(networkId);
    }
    
    public static void rebuildNetwork(Level level, BlockPos startPos) {
        if (level.isClientSide) return;
        
        Map<BlockPos, UUID> levelMap = LEVEL_NETWORKS.computeIfAbsent(level, k -> new HashMap<>());
        
        UUID oldId = levelMap.get(startPos);
        if (oldId != null) {
            CableNetwork oldNet = NETWORKS.get(oldId);
            if (oldNet != null) {
                oldNet.removeCable(startPos);
                if (oldNet.getCableCount() == 0) {
                    NETWORKS.remove(oldId);
                }
            }
        }
        
        Set<BlockPos> connected = new HashSet<>();
        Queue<BlockPos> queue = new ArrayDeque<>();
        queue.add(startPos);
        connected.add(startPos);
        
        while (!queue.isEmpty()) {
            BlockPos current = queue.poll();
            BlockEntity be = level.getBlockEntity(current);
            if (!(be instanceof QuantumCableBE cableBE)) continue;
            
            int presentMask = cableBE.getPresentCablesMask();
            
            for (Direction dir : Direction.values()) {
                BlockPos neighbor = current.relative(dir);
                if (connected.contains(neighbor)) continue;
                
                BlockEntity neighborBE = level.getBlockEntity(neighbor);
                if (neighborBE instanceof QuantumCableBE neighborCable) {
                    int neighborMask = neighborCable.getPresentCablesMask();
                    if ((presentMask & neighborMask) != 0) {
                        connected.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
        }
        
        UUID networkId = null;
        for (BlockPos pos : connected) {
            UUID existingId = levelMap.get(pos);
            if (existingId != null && NETWORKS.containsKey(existingId)) {
                networkId = existingId;
                break;
            }
        }
        
        if (networkId == null) {
            networkId = UUID.randomUUID();
            NETWORKS.put(networkId, new CableNetwork(networkId));
        }
        
        CableNetwork network = NETWORKS.get(networkId);
        
        for (BlockPos pos : connected) {
            levelMap.put(pos, networkId);
            network.addCable(pos);
        }
    }
    
    public static void registerCable(Level level, BlockPos pos) {
        if (level.isClientSide) return;
        rebuildNetwork(level, pos);
    }
    
    public static void removeCable(Level level, BlockPos pos) {
        if (level.isClientSide) return;
        
        Map<BlockPos, UUID> levelMap = LEVEL_NETWORKS.get(level);
        if (levelMap != null) {
            UUID oldId = levelMap.remove(pos);
            if (oldId != null) {
                CableNetwork oldNet = NETWORKS.get(oldId);
                if (oldNet != null) {
                    oldNet.removeCable(pos);
                    if (oldNet.getCableCount() == 0) {
                        NETWORKS.remove(oldId);
                    }
                }
            }
        }
        
        for (Direction dir : Direction.values()) {
            BlockPos neighborPos = pos.relative(dir);
            rebuildNetwork(level, neighborPos);
        }
    }
    
    public static void tick(Level level) {
        if (level.isClientSide) return;
        for (CableNetwork network : NETWORKS.values()) {
            network.tick();
        }
    }
    
    public static void clear() {
        LEVEL_NETWORKS.clear();
        NETWORKS.clear();
    }
}