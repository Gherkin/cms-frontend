package com.github.gherkin;

import com.github.gherkin.content.Content;
import com.google.inject.Singleton;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class Cache {
    private Map<String, Content> cache;// = Collections.synchronizedMap(new HashMap<String, Content>();

    public Cache() {
        cache = new HashMap<>();
    }

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
