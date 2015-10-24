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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FrontServlet extends javax.servlet.http.HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {

        Template template = Velocity.getTemplate("site.vm");
        VelocityContext context = new VelocityContext();

        Gson gson = new Gson();

        InputStream inputStream = new URL("http://192.168.122.82:8080/cms-backend/content").openStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        ContentList strings = gson.fromJson(inputStreamReader, ContentList.class);

        context.put("articles", strings);

        template.merge(context, resp.getWriter());
    }

    public static class ContentList extends ArrayList<HashMap<String, String>> {}
}
