package com.easidiomas.auth;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * The auth server encapsulates all the functionality regarding the execution of
 * the gRPC server itself.
 */
public class AuthServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServer.class);
    // Port where the server will be listening for gRPC calls.
    private static final int PORT = Integer.parseInt(System.getProperty("SERVER_PORT", "5000"));

    // Google gRPC server obeject.
    private Server server;

    public static void main(String[] args) throws InterruptedException, IOException {
        LOGGER.info("Server Starting");
        AuthServer server = new AuthServer();
        server.start();
        LOGGER.info("Server Started");
        server.blockUntilShutdown();
    }

    private void start() throws IOException {
        server = ServerBuilder.forPort(PORT).addService(new AuthServiceImpl()).build().start();
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server == null) {
            return;
        }
        server.awaitTermination();
    }
}
