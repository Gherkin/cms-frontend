package com.github.gherkin;

import com.google.inject.Inject;

public class CachingCMClient extends CMClient {

    @Inject
    Cache cache;

    @Override
    Content getContent(String id) {
        if(cache.containsKey(id)) {
            return cache.get(id);
        }

        Content content = super.getContent(id);
        cache.put(content.get("id"), content);

        return content;
    }
}
