package com.javamastery.collections.linkedHashMap;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapLRUCache {

    private final int capacity;

    private final LinkedHashMap<Integer, String> cache;

    public LinkedHashMapLRUCache(int capacity) {
        this.capacity = capacity;
        cache = new LinkedHashMap<>(16, 0.75f, true);
    }

    public String get(int key) {
        return cache.get(key);
    }

    public void put(int key, String value) {
        cache.put(key, value);

        if (cache.size() > capacity) {
            Integer leastRecentlyUsedKey = cache.entrySet()
                    .iterator()
                    .next()
                    .getKey();

            cache.remove(leastRecentlyUsedKey);
        }
    }

    public void display() {
        for (Map.Entry<Integer, String> entry : cache.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}