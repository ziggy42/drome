package com.andreapivetta.drome;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Cache implementation that uses FIFO algorithm.
 *
 * @author Andrea Pivetta
 * @see com.andreapivetta.drome.Cache
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
    synchronized public void put(K key, V value) {
        cache.put(key, value);
    }

    @Override
    synchronized public V get(K key) {
        return cache.get(key);
    }

    @Override
    synchronized public void clear() {
        cache.clear();
    }

    @Override
    synchronized public int size() {
        return cache.size();
    }

    @Override
    synchronized public boolean containsKey(K key) {
        return cache.containsKey(key);
    }

    @Override
    synchronized public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Max size: ")
                .append(maxSize)
                .append("\nCurrent Size: ")
                .append(size())
                .append("\n\n");

        for (K key : cache.keySet()) {
            builder.append(key)
                    .append(':')
                    .append(get(key))
                    .append('\n');
        }

        return builder.toString().trim();
    }
}
