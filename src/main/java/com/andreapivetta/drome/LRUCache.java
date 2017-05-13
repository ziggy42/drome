package com.andreapivetta.drome;

import java.util.HashMap;

/**
 * Cache implementation that use LRU algorithm.
 *
 * @author Andrea Pivetta
 * @see com.andreapivetta.drome.Cache
 */
final class LRUCache<K, V> implements Cache<K, V> {

    class Node<T, U> {
        Node<T, U> previous;
        Node<T, U> next;
        T key;
        U value;

        Node(Node<T, U> previous, Node<T, U> next, T key, U value) {
            this.previous = previous;
            this.next = next;
            this.key = key;
            this.value = value;
        }
    }

    private final int maxSize;
    private final HashMap<K, Node<K, V>> cache;
    private Node<K, V> leastRecentlyUsed;
    private Node<K, V> mostRecentlyUsed;

    LRUCache(int maxSize) {
        this.maxSize = maxSize;
        leastRecentlyUsed = new Node<>(null, null, null, null);
        mostRecentlyUsed = leastRecentlyUsed;
        cache = new HashMap<>();
    }

    @Override
    public void put(K key, V value) {
        if (cache.containsKey(key))
            return;

        Node<K, V> node = new Node<>(mostRecentlyUsed, null, key, value);
        mostRecentlyUsed.next = node;
        mostRecentlyUsed = node;

        if (cache.size() == maxSize) {
            cache.remove(leastRecentlyUsed.key);
            Node<K, V> nextLSUNode = leastRecentlyUsed.next;
            nextLSUNode.previous = null;
            leastRecentlyUsed = nextLSUNode;
        } else if (cache.size() == 0)
            leastRecentlyUsed = node;

        cache.put(key, node);
    }

    @Override
    public V get(K key) {
        Node<K, V> node = cache.get(key);
        if (node == null)
            return null;

        if (node.key == mostRecentlyUsed.key)
            return mostRecentlyUsed.value;

        Node<K, V> nextNode = node.next;
        Node<K, V> previousNode = node.previous;

        if (node.key == leastRecentlyUsed.key) {
            nextNode.previous = null;
            leastRecentlyUsed = nextNode;
        } else {
            previousNode.next = nextNode;
            nextNode.previous = previousNode;
        }

        node.previous = mostRecentlyUsed;
        mostRecentlyUsed.next = node;
        mostRecentlyUsed = node;
        mostRecentlyUsed.next = null;

        return node.value;
    }

    @Override
    public void clear() {
        cache.clear();
        leastRecentlyUsed = null;
        mostRecentlyUsed = null;
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
                    .append(cache.get(key).value)
                    .append('\n');
        }

        return builder.toString().trim();
    }
}
