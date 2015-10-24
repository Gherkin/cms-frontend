package com.github.gherkin;

import com.google.gson.Gson;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

public class FrontServlet extends javax.servlet.http.HttpServlet {

    public static final Gson GSON = new Gson();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {

        Template template = Velocity.getTemplate("site.vm");
        VelocityContext context = new VelocityContext();

        List<Content> articles = getArticles();

        context.put("articles", articles);

        template.merge(context, resp.getWriter());
    }

    private List<Content> getArticles() throws IOException {
        Gson gson = new Gson();

        Content page = getContent(1);

        List<Content> articles = new ArrayList<Content>();
        for(String str : page.get("articles").split(",")) {
            articles.add(getContent(Integer.parseInt(str)));
        }

        return articles;
    }

    private Content getContent(int id) {
        String url = String.format("http://192.168.122.82:8080/cms-backend/content/%d", id);

        try (InputStream inputStream = new URL(url).openStream()) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            return GSON.fromJson(inputStreamReader, Content.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static class Content extends HashMap<String, String> {

    }
}
