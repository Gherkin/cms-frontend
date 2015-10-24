package com.github.gherkin;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.log.NullLogChute;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class FrontListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Velocity.setProperty("resource.loader", "class");
        Velocity.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, NullLogChute.class.getName());
        Velocity.setProperty("class.resource.loader.class", ClasspathResourceLoader.class.getName());
        Velocity.init();

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
