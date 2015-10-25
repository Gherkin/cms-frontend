package com.github.gherkin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public class CacheManager implements Runnable {

    private Map<String, Content> cache;
    private CMClient cmClient;

    public CacheManager(Map<String, Content> cache, CMClient cmClient) {
        this.cache = cache;
        this.cmClient = cmClient;
    }

    @Override
    public void run() {
        Iterator<Content> iterator = cache.values().iterator();
        while(iterator.hasNext()) {
            Content content = iterator.next();
            String version = cmClient.getContent(content.get("id")).get("version");
            if(content.get("version") != version) {
                iterator.remove();
                cache.put()
            }

        }
    }
}
