package com.andreapivetta.drome;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

/**
 * Created by andrea on 5/13/17.
 */
public class CacheFactoryTest {

    @Test
    public void getCache() throws Exception {
        assertThat(CacheFactory.getCache(), instanceOf(LRUCache.class));
        assertThat(CacheFactory.getCache(Cache.Algorithm.FIFO), instanceOf(FIFOCache.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCacheWrongMaxSize() throws Exception {
        CacheFactory.getCache(0);
    }
}