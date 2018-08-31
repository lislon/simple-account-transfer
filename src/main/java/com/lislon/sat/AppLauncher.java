package com.lislon.sat;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * Created by ele on 30.08.2018.
 */
public class AppLauncher {

    private static final String JERSEY_SERVLET_NAME = "jersey-container-servlet";

    public static void main(String[] args) throws Exception {
        new AppLauncher().start();
    }

    void start() throws Exception {

        String port = System.getenv("PORT");
        if (port == null || port.isEmpty()) {
            port = "8080";
        }

        String contextPath = "";
        String appBase = ".";

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(Integer.valueOf(port));
        tomcat.getHost().setAppBase(appBase);

        Context context = tomcat.addContext(contextPath, appBase);
        Tomcat.addServlet(context, JERSEY_SERVLET_NAME,
                new ServletContainer(new JerseyConfiguration()));
        context.addServletMappingDecoded("/api/*", JERSEY_SERVLET_NAME);

        tomcat.start();
        tomcat.getServer().await();
    }
}