# drome
LRU, FIFO and LIFO caches.

```java
Cache<Integer, String> cache = CacheFactory.getCache(Cache.Algorithm.LRU);
        
cache.put(1, "Apple");
String first = cache.get(1);
```