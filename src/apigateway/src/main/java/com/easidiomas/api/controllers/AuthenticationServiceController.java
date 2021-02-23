package com.easidiomas.api.controllers;

import com.easidiomas.api.clients.authenticationservice.AuthenticationServiceClient;
import com.easidiomas.auth.Authservice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/auth")
public class AuthenticationServiceController {

    public final static String SERVICE_URI = "https://www.easidiomas.com/api/v1/auth";

    @GetMapping(value = "token")
    public ResponseEntity<String> requestToken(@RequestHeader("username") String username, @RequestHeader("password") String password) {
        Authservice.Token token = new AuthenticationServiceClient().requestToken(username, password);
        ResponseEntity response = ResponseEntity.ok(token.getToken());
        return response;
    }
}
