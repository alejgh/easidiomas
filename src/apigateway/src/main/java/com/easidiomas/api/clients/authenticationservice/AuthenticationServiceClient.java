package com.easidiomas.api.clients.authenticationservice;

import com.easidiomas.api.controllers.AuthenticationServiceController;
import com.easidiomas.auth.AuthenticationServiceGrpc;
import com.easidiomas.auth.Authservice;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticationServiceClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceController.class);

    private final String serviceTarget = System.getenv("AUTH_SERVICE_ADDRESS")!=null ? System.getenv("AUTH_SERVICE_ADDRESS"): "localhost:5000";
    private final AuthenticationServiceGrpc.AuthenticationServiceBlockingStub authService;

    public AuthenticationServiceClient() {
        LOGGER.info(String.format("Connecting to authentication service on address [%s]", serviceTarget));
        ManagedChannel channel = ManagedChannelBuilder.forTarget(serviceTarget)
                .usePlaintext()
                .build();
        authService = AuthenticationServiceGrpc.newBlockingStub(channel);
        LOGGER.info(String.format("Connected to authentication service on address [%s]", serviceTarget));
    }

    public Authservice.TokenResponse requestToken(String username, String password) {
        Authservice.TokenRequest tokenRequest = Authservice.TokenRequest.newBuilder()
                .setUsername(username)
                .setPassword(password)
                .build();
        return authService.requestToken(tokenRequest);
    }

    public Authservice.PassportResponse requestPassport(String stringToken) {
        Authservice.PassportRequest token = Authservice.PassportRequest.newBuilder()
                .setToken(stringToken)
                .build();
        return authService.requestPassport(token);
    }
}
