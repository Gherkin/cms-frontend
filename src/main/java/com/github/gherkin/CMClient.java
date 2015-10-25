package com.github.gherkin;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CMClient {

    private final Gson GSON = new Gson();

    public List<Content> getArticles() throws IOException {
        Gson gson = new Gson();

        Content page = getContent("1");

        List<Content> articles = new ArrayList<Content>();
        for(String str : page.get("articles").split(",")) {
            articles.add(getContent(str));
        }

        return articles;
    }

    Content getContent(String id) {
        String url = String.format("http://192.168.122.82:8080/cms-backend/content/%s", id);

        try (InputStream inputStream = new URL(url).openStream()) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            return GSON.fromJson(inputStreamReader, Content.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
