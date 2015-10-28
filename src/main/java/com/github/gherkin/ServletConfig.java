package com.github.gherkin;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.log.NullLogChute;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import javax.servlet.ServletContextEvent;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ServletConfig extends GuiceServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        initVelocity();
        CMClient cmClient = new CMClient();
        servletContextEvent.getServletContext().setAttribute("cmClient", cmClient);

        Map<String, Content> cache = Collections.synchronizedMap(new HashMap<>());

        CacheManager cacheManager = new CacheManager(cache, cmClient);
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(cacheManager, 1, TimeUnit.SECONDS);
    }

    private void initVelocity() {
        Velocity.setProperty("resource.loader", "class");
        Velocity.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, NullLogChute.class.getName());
        Velocity.setProperty("class.resource.loader.class", ClasspathResourceLoader.class.getName());
        Velocity.init();
    }


    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {

            @Override
            protected void configureServlets() {
                serve("*").with(FrontServlet.class);
            }

        });
    }
}
