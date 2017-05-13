package com.andreapivetta.drome;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by andrea on 5/13/17.
 */
public class LRUCacheTest {

    private static final int MAX_SIZE = 300;
    private final LRUCache<Integer, String> fullCache = new LRUCache<>(MAX_SIZE);
    private final LRUCache<Integer, String> emptyCache = new LRUCache<>(MAX_SIZE);

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
        assertNull(fullCache.get(MAX_SIZE + 1));
    }

    @Test
    public void clear() throws Exception {
        fullCache.clear();
        assertEquals(0, fullCache.size());
    }

    @Test
    public void size() throws Exception {
        assertEquals(MAX_SIZE, fullCache.size());
        assertEquals(0, emptyCache.size());
    }

}