package com.andreapivetta.drome;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by andrea on 5/13/17.
 */
final class FIFOCache<K, V> implements Cache<K, V> {

    private final int maxSize;
    private final LinkedHashMap<K, V> cache;

    FIFOCache(final int maxSize) {
        this.maxSize = maxSize;
        this.cache = new LinkedHashMap<K, V>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > maxSize;
            }
        };
    }

    @Override
    public void put(K key, V value) {
        cache.put(key, value);
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Max size: ")
                .append(maxSize)
                .append("\nCurrent Size: ")
                .append(size())
                .append("\n\n");

        for (K key : cache.keySet()) {
            builder.append(key)
                    .append(':')
                    .append(cache.get(key))
                    .append('\n');
        }

        return builder.toString().trim();
    }
}
