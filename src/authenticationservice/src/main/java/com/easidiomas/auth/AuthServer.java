package com.easidiomas.auth;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class AuthServer {
    private static final int PORT = 5000;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServer.class);
    private Server server;

    public static void main(String[] args) throws InterruptedException, IOException {
        LOGGER.info("Server Starting");
        AuthServer server = new AuthServer();
        server.start();
        LOGGER.info("Server Started");
        server.blockUntilShutdown();
    }

    public void start() throws IOException {
        server = ServerBuilder.forPort(PORT)
                .addService(new AuthServiceImpl())
                .build()
                .start();
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server == null) {
            return;
        }
        server.awaitTermination();
    }
}
