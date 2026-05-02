package com.quanta.core.network;

import com.quanta.content.blockentity.cable.CableBlockEntity;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.UUID;
import java.util.Set;
import java.util.Queue;

public class NetworkManager {

    private static final Map<Integer, Long2ObjectOpenHashMap<UUID>> DIMENSION_NETWORKS = new Object2ObjectOpenHashMap<>();
    private static final Map<UUID, CableNetwork> NETWORKS = new Object2ObjectOpenHashMap<>();

    public static CableNetwork getNetwork(Level level, BlockPos pos) {
        if (level.isClientSide) return null;
        var dimMap = DIMENSION_NETWORKS.get(dimensionKey(level));
        if (dimMap == null) return null;
        UUID id = dimMap.get(pos.asLong());
        return id != null ? NETWORKS.get(id) : null;
    }

    public static void rebuildNetwork(Level level, BlockPos startPos) {
        if (level.isClientSide) return;
        Set<BlockPos> connected = findConnectedCables(level, startPos);
        if (connected.isEmpty()) return;

        int dimKey = dimensionKey(level);
        var dimMap = DIMENSION_NETWORKS.computeIfAbsent(dimKey, k -> new Long2ObjectOpenHashMap<>());

        UUID networkId = null;
        for (BlockPos pos : connected) {
            UUID existing = dimMap.get(pos.asLong());
            if (existing != null) {
                networkId = existing;
                break;
            }
        }

        if (networkId == null) {
            networkId = UUID.randomUUID();
            NETWORKS.put(networkId, new CableNetwork(networkId));
        }

        CableNetwork network = NETWORKS.get(networkId);
        for (BlockPos pos : connected) {
            dimMap.put(pos.asLong(), networkId);
            network.addCable(pos);
        }
    }

    public static void removeCable(Level level, BlockPos pos) {
        if (level.isClientSide) return;
        var dimMap = DIMENSION_NETWORKS.get(dimensionKey(level));
        if (dimMap == null) return;

        UUID id = dimMap.remove(pos.asLong());
        if (id != null) {
            CableNetwork network = NETWORKS.get(id);
            if (network != null) {
                network.removeCable(pos);
                if (network.isEmpty()) NETWORKS.remove(id);
            }
        }

        for (Direction dir : Direction.values()) {
            BlockPos neighbor = pos.relative(dir);
            if (level.getBlockEntity(neighbor) instanceof CableBlockEntity) {
                rebuildNetwork(level, neighbor);
            }
        }
    }

    private static int dimensionKey(Level level) {
        return level.dimension().location().hashCode();
    }

    private static Set<BlockPos> findConnectedCables(Level level, BlockPos start) {
        Set<BlockPos> visited = new ObjectOpenHashSet<>();
        Queue<BlockPos> queue = new ArrayDeque<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            BlockPos current = queue.poll();
            if (!(level.getBlockEntity(current) instanceof CableBlockEntity cable)) continue;
            int mask = cable.getTypeMask();
            if (mask == 0) continue;

            for (Direction dir : Direction.values()) {
                BlockPos neighbor = current.relative(dir);
                if (visited.contains(neighbor)) continue;
                if (level.getBlockEntity(neighbor) instanceof CableBlockEntity neighborCable) {
                    if ((mask & neighborCable.getTypeMask()) != 0) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
        }
        return visited;
    }

    public static void onChunkUnload(ServerLevel level, ChunkPos chunk) {
        var dimMap = DIMENSION_NETWORKS.get(dimensionKey(level));
        if (dimMap == null) return;
        dimMap.long2ObjectEntrySet().removeIf(entry -> {
            BlockPos pos = BlockPos.of(entry.getLongKey());
            return chunk.equals(new ChunkPos(pos));
        });
        NETWORKS.values().removeIf(CableNetwork::isEmpty);
    }

    public static void onLevelUnload(Level level) {
        var dimMap = DIMENSION_NETWORKS.remove(dimensionKey(level));
        if (dimMap != null) {
            Set<UUID> ids = new ObjectOpenHashSet<>(dimMap.values());
            NETWORKS.keySet().removeAll(ids);
        }
    }

    public static void clearAll() {
        DIMENSION_NETWORKS.clear();
        NETWORKS.clear();
    }
}