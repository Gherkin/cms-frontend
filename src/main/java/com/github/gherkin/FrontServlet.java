package com.github.gherkin;

import com.google.inject.Inject;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.util.List;

public class FrontServlet extends javax.servlet.http.HttpServlet {

    private CMClient cmClient;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        cmClient = (CMClient) servletConfig.getServletContext().getAttribute("cmClient");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {

        Template template = Velocity.getTemplate("site.vm");
        VelocityContext context = new VelocityContext();

        List<Content> articles = cmClient.getArticles();

        context.put("articles", articles);

        template.merge(context, resp.getWriter());
    }

}
