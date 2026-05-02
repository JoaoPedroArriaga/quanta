package com.quanta.core.util;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

import java.util.Map;
import java.util.function.Function;

public class CacheHelper<K, V> {

    private final Map<K, CacheEntry<V>> cache;
    private final long ttlMillis;

    public CacheHelper(long ttlMillis) {
        this.cache = new Object2ObjectOpenHashMap<>();
        this.ttlMillis = ttlMillis;
    }

    public V get(K key, Function<K, V> loader) {
        CacheEntry<V> entry = cache.get(key);
        long now = System.currentTimeMillis();

        if (entry != null && (now - entry.timestamp) < ttlMillis) {
            return entry.value;
        }

        V value = loader.apply(key);
        cache.put(key, new CacheEntry<>(value, now));
        return value;
    }

    public void invalidate(K key) {
        cache.remove(key);
    }

    public void clear() {
        cache.clear();
    }

    public int size() {
        return cache.size();
    }

    private record CacheEntry<V>(V value, long timestamp) {}
}