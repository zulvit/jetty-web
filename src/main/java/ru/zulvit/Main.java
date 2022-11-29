package ru.zulvit;

import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import ru.zulvit.authorization.SecurityHandlerBuilder;
import ru.zulvit.core.DefaultServer;
import ru.zulvit.flyway.FlywayInitializer;
import ru.zulvit.servlets.AddEntityServlet;
import ru.zulvit.servlets.GetEntityServlet;

import java.util.Objects;

public class Main {
    public static void main(String[] args) throws Exception {
        FlywayInitializer.initDb();
        final var server = new DefaultServer().build();

        final var contextHandler = new ServletContextHandler(server, "/");
        contextHandler.setBaseResource(Resource.newResource(Main.class.getResource("/static")));

        contextHandler.addServlet(new ServletHolder(DefaultServlet.class), "/"); //штука для статики, дефолтный сервлет
        contextHandler.addServlet(AddEntityServlet.class, "/add"); //самописный сервлет
        contextHandler.addServlet(GetEntityServlet.class, "/get"); //самописный сервлет

        final var loginService = new HashLoginService("login", Objects.requireNonNull(Main.class.getResource("/hash_config")).toExternalForm());
        contextHandler.setHandler(new SecurityHandlerBuilder().build(loginService));

        server.setHandler(contextHandler);
        server.start();
    }
}