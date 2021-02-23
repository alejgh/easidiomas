package com.easidiomas.api.clients.authenticationservice;

import com.easidiomas.auth.AuthenticationServiceGrpc;
import com.easidiomas.auth.Authservice;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class AuthenticationServiceClient {

    private final String serviceTarget = System.getProperty("AUTH_SERVICE_ADDRESS", "156.35.82.22:5000");
    private final AuthenticationServiceGrpc.AuthenticationServiceBlockingStub authService;

    public AuthenticationServiceClient() {
        ManagedChannel channel = ManagedChannelBuilder.forTarget(serviceTarget)
                .usePlaintext()
                .build();
        authService = AuthenticationServiceGrpc.newBlockingStub(channel);
    }

    public Authservice.Token requestToken(String username, String password) {
        Authservice.LoginInfo loginInfo = Authservice.LoginInfo.newBuilder()
                .setUsername(username)
                .setPassword(password)
                .build();
        return authService.generateToken(loginInfo);
    }

    public Authservice.Passport requestPassport(String stringToken) {
        Authservice.Token token = Authservice.Token.newBuilder()
                .setToken(stringToken)
                .build();
        return authService.generatePassport(token);
    }
}
