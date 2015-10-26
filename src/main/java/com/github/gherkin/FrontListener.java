package com.github.gherkin;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.log.NullLogChute;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FrontListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        initVelocity();
        CachingCMClient cmClient = new CachingCMClient();
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

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
