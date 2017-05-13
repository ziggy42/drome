package com.andreapivetta.drome;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by andrea on 5/13/17.
 */
public class FIFOCacheTest {

    private static final int MAX_SIZE = 300;
    private final FIFOCache<Integer, String> fullCache = new FIFOCache<>(MAX_SIZE);
    private final FIFOCache<Integer, String> emptyCache = new FIFOCache<>(MAX_SIZE);

    @Before
    public void setUp() throws Exception {
        for (int i = 1; i <= 300; i++)
            fullCache.put(i, String.valueOf(i));
    }

    @Test
    public void put() throws Exception {
        fullCache.put(MAX_SIZE + 1, String.valueOf(MAX_SIZE + 1));

        assertEquals(MAX_SIZE, fullCache.size());
        assertEquals(String.valueOf(MAX_SIZE + 1), fullCache.get(MAX_SIZE + 1));

        int size = 10;
        for (int i = 0; i < size; i++)
            emptyCache.put(i, String.valueOf(i));
        assertEquals(size, emptyCache.size());
    }

    @Test
    public void get() throws Exception {
        assertEquals(String.valueOf(1), fullCache.get(1));
        assertEquals(String.valueOf(MAX_SIZE / 2), fullCache.get(MAX_SIZE / 2));
        assertEquals(String.valueOf(MAX_SIZE), fullCache.get(MAX_SIZE));
        assertNull(fullCache.get(MAX_SIZE + 1));
    }

    @Test
    public void clear() throws Exception {
        fullCache.clear();
        assertEquals(0, fullCache.size());
        assertNull(String.valueOf(1), fullCache.get(1));
    }

    @Test
    public void size() throws Exception {
        assertEquals(MAX_SIZE, fullCache.size());
        assertEquals(0, emptyCache.size());
    }

    @Test
    public void containsKey() throws Exception {
        assertTrue(fullCache.containsKey(MAX_SIZE));
        assertFalse(fullCache.containsKey(MAX_SIZE * 2));
    }

    @Test
    public void FIFO() throws Exception {
        FIFOCache<Integer, String> cache = new FIFOCache<>(3);

        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");

        cache.get(1);
        cache.put(4, "Four");

        assertNull(cache.get(1));
        assertNotNull(cache.get(2));
    }
}