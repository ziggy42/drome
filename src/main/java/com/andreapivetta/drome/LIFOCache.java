package com.andreapivetta.drome;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Cache implementation that uses LIFO algorithm.
 *
 * @author Andrea Pivetta
 * @see com.andreapivetta.drome.Cache
 */
final class LIFOCache<K, V> implements Cache<K, V> {

    private final int maxSize;
    private final Map<K, V> cache = new HashMap<>();
    private final Stack<K> stack = new Stack<>();

    LIFOCache(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public void put(K key, V value) {
        if (cache.size() == maxSize)
            cache.remove(stack.pop());

        cache.put(key, value);
        stack.push(key);
    }

    @Override
    public V get(K key) {
        stack.push(key);
        return cache.get(key);
    }

    @Override
    public void clear() {
        stack.clear();
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
                    .append(get(key))
                    .append('\n');
        }

        return builder.toString().trim();
    }
}
