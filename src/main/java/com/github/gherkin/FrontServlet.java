package com.github.gherkin;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

public class FrontServlet extends javax.servlet.http.HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {
        Template template = Velocity.getTemplate("site.vm");
        VelocityContext context = new VelocityContext();
        context.put("name", "matte");
        template.merge(context, resp.getWriter());


    }

}
