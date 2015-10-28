package com.github.gherkin;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.log.NullLogChute;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FrontServletTest {

    @Mock
    javax.servlet.ServletConfig servletConfig;

    private CMClient cmClient;

    @BeforeClass
    public static void initAll() {
        initVelocity();

    }

    FrontServlet servlet;

    @Before
    public void init() {
        cmClient = mock(CMClient.class);
        servlet = new FrontServlet();
        servlet.SetCMClient(cmClient);
    }

    @Test
    public void testService() throws Exception {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getMethod()).thenReturn("GET");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.service(request, response);

        System.out.println("status = " + response.getStatus());
        writer.flush();

        assertTrue("response is empty", !stringWriter.toString().equals(null));
    }

    private static void initVelocity() {
        Velocity.setProperty("resource.loader", "class");
        Velocity.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, NullLogChute.class.getName());
        Velocity.setProperty("class.resource.loader.class", ClasspathResourceLoader.class.getName());
        Velocity.init();
    }

}