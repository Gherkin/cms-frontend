package com.github.gherkin;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Cache {
    private static Map<String, Content> cache = Collections.synchronizedMap(new HashMap<String, Content>());

    public Content get(String key) {
        return cache.get(key);
    }

    public void put(String key, Content value) {
        cache.put(key, value);
    }

    public boolean containsKey(String key) {
        return cache.containsKey(key);
    }
}
