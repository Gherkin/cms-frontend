package com.github.gherkin;

import com.google.gson.Gson;
import com.google.inject.Inject;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import java.util.Map;

public class CacheManager implements Runnable {

    @Inject
    Cache cache;
    private ChangeLog changeLog;
    private CMClient cmClient;
    private final Gson GSON = new Gson();

    public CacheManager(Map<String, Content> cache, CMClient cmClient) {
        this.cmClient = cmClient;
        changeLog = new ChangeLog();
    }

    @Override
    public void run() {
        String url = String.format("http://192.168.122.82:8080/cms-backend/content/changelog/%s", changeLog.size());

        try(InputStream inputStream = new URL(url).openStream()) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            ChangeLog serverLog = GSON.fromJson(inputStreamReader, ChangeLog.class);
            for(String id : serverLog) {
                Content content = cmClient.getContent(id);
                cache.put(id, content);
                changeLog.add(id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
