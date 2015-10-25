package com.github.gherkin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CachingCMClient extends CMClient {

    private Map<String, Content> cache = new HashMap<>();

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
