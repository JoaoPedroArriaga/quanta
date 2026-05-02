package com.quanta.client.renderer.state;

import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.core.BlockPos;

public class RenderStateCache {

    private final Long2ObjectOpenHashMap<CableRenderState> cache = new Long2ObjectOpenHashMap<>();

    public CableRenderState get(BlockPos pos) {
        return cache.get(pos.asLong());
    }

    public void put(BlockPos pos, CableRenderState state) {
        cache.put(pos.asLong(), state);
    }

    public void remove(BlockPos pos) {
        cache.remove(pos.asLong());
    }

    public void clear() {
        cache.clear();
    }
}