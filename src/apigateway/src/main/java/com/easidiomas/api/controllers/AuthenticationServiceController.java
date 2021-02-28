package com.easidiomas.api.controllers;

import com.easidiomas.api.clients.authenticationservice.AuthenticationServiceClient;
<<<<<<< HEAD
=======
import com.easidiomas.api.model.LoginInfo;
>>>>>>> main
import com.easidiomas.api.util.StringResponse;
import com.easidiomas.auth.Authservice;
import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "api/auth")
public class AuthenticationServiceController {

    public final static String SERVICE_URI = "https://www.easidiomas.com/api/auth";
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceController.class);
    private static final Gson GSON = new Gson();
<<<<<<< HEAD

    @PostMapping(value = "token", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StringResponse> requestToken(@RequestHeader("username") String username, @RequestHeader("password") String password) {
        LOGGER.info(String.format("Request for token for username [%s] received", username));
        LOGGER.info(String.format("Requesting token for username [%s]to the authentication service", username));
        Authservice.Token token = null;
=======

    @PostMapping(value = "token", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity requestToken(@RequestBody LoginInfo request) {
        LOGGER.info(String.format("Request for token for username [%s] received", request.getUsername()));
        LOGGER.info(String.format("Requesting token for username [%s] to the authentication service", request.getUsername()));

        Authservice.TokenResponse token = null;
>>>>>>> main
        try {
            token = new AuthenticationServiceClient().requestToken(request.getUsername(), request.getPassword());
        } catch (Exception e) {
<<<<<<< HEAD
            LOGGER.warn(String.format("Generating token for user [%s] was not successful.", username));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new StringResponse("Username or password were not correct."));
        }
        LOGGER.info(String.format("Token for username [%s] created", username));
        return ResponseEntity.created(URI.create(SERVICE_URI+"/token/"+token.getToken())).body(new StringResponse(token.getToken()));
=======
            LOGGER.warn(String.format("Generating token for user [%s] was not successful.", request.getUsername()));
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        LOGGER.info(String.format("Token for username [%s] created", request.getUsername()));
        return ResponseEntity
                .created(URI.create(SERVICE_URI+"/token/"+token.getTokenGenerated()))
                .body(new Gson().toJson(token));
>>>>>>> main
    }
}