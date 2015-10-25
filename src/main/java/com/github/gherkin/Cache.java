package com.github.gherkin;

import java.util.HashMap;
import java.util.Map;

public class Cache {
    private final Map<Integer, Content> contentCache = new HashMap<>();
    private final Object[] mutex = new Object[0];

    public void put(Integer id, Content content) {
        synchronized(mutex) {
            contentCache.put(id, content);
        }
    }

    public void remove(Integer id, Content content) {
        synchronized(mutex) {
            contentCache.remove(id, content);
        }
    }

    public Content get(Integer id) {
        synchronized(mutex) {
            return contentCache.get(id);
        }
    }

    public boolean containsKey(Integer id) {
        synchronized(mutex) {
            return contentCache.containsKey(id);
        }
    }
}