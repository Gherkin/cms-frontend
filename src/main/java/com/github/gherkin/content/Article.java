package com.github.gherkin.content;

public class Article extends Content {
    private String title;
    private String body;

    public Article(Content content) {
        title = content.get("title");
        body = content.get("text");
        this.putAll(content);
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
        this.put("text", body);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.put("title", title);
    }

}
