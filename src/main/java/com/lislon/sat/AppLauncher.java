package com.lislon.sat;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * Created by ele on 30.08.2018.
 */
public class AppLauncher {

    public static void main(String[] args) throws Exception {
        new AppLauncher().start();
    }

    void start() throws Exception {
        Server server = new Server(8080);

        server.setHandler(new HandlerList(
                getStaticResourceHandler(),
                getApiResourceHandler()
        ));

        server.start();
        server.join();
    }

    /**
     * Servlet for serving /api requests.
     */
    private ServletContextHandler getApiResourceHandler() {
        ServletContainer servletContainer = new ServletContainer(new JerseyConfiguration());
        ServletHolder servletHolder = new ServletHolder(servletContainer);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        contextHandler.setContextPath("/");
        contextHandler.addServlet(servletHolder, "/api/*");
        return contextHandler;
    }

    /**
     * Servlet for serving static requests (api documentation).
     */
    private ResourceHandler getStaticResourceHandler() {
        ResourceHandler staticResourceHandler = new ResourceHandler();
        staticResourceHandler.setDirectoriesListed(true);
        staticResourceHandler.setResourceBase(
                this.getClass().getClassLoader().getResource("static").toExternalForm()
        );
        return staticResourceHandler;
    }
}
