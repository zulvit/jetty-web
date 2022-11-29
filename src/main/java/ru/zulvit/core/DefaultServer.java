package ru.zulvit.core;

import org.eclipse.jetty.server.*;

public class DefaultServer {
    private final Server server = new Server();
    private static final int DEFAULT_PORT = 3466;

    public Server build() {
        return build(DEFAULT_PORT);
    }

    public Server build(int port) {
        final ServerConnector serverConnector = new ServerConnector(server);
        serverConnector.setHost("localhost");
        serverConnector.setPort(port);
        server.setConnectors(new Connector[]{serverConnector});
        return server;
    }
}
