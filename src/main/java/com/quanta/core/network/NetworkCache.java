package com.quanta.core.network;

import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;

import java.util.Map;
import java.util.UUID;

/**
 * Caches cable network lookups per chunk for fast access.
 * Invalidates when chunks unload.
 */
public class NetworkCache {

    private final Map<Long, UUID> chunkCache = new Long2ObjectOpenHashMap<>();
    private final Level level;
    private int missCount;
    private int hitCount;

    public NetworkCache(Level level) {
        this.level = level;
    }

    public UUID getCached(BlockPos pos) {
        long chunkKey = ChunkPos.asLong(pos);
        UUID result = chunkCache.get(chunkKey);
        if (result != null) {
            hitCount++;
        } else {
            missCount++;
        }
        return result;
    }

    public void put(BlockPos pos, UUID networkId) {
        long chunkKey = ChunkPos.asLong(pos);
        chunkCache.put(chunkKey, networkId);
    }

    public void invalidate(ChunkPos chunk) {
        chunkCache.remove(chunk.toLong());
    }

    public void clear() {
        chunkCache.clear();
        hitCount = 0;
        missCount = 0;
    }

    public int getHitCount() { return hitCount; }
    public int getMissCount() { return missCount; }
    public double getHitRate() {
        int total = hitCount + missCount;
        return total > 0 ? (double) hitCount / total : 0;
    }
}