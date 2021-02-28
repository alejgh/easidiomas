package com.easidiomas.auth.clients.usersservice;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.html.Option;
import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

public class UsersServiceClient {

    private static final String USERS_SERVICE_HOST = System.getenv("USERS_SERVICE_HOST")!=null ? System.getenv("USERS_SERVICE_HOST"): "localhost";
    private static final int USERS_SERVICE_PORT = Integer.parseInt(System.getenv("USERS_SERVICE_PORT")!=null ? System.getenv("USERS_SERVICE_PORT"): "5000");
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersServiceClient.class);

    private final Client client;
    //private final String usersServiceAddr;

    public UsersServiceClient() {
        this.client = Client.create();
        //this.usersServiceAddr = System.getenv("USERS_SERVICE_ADDR") !=null ? System.getenv("USERS_SERVICE_ADDR") : "localhost:5000";
        LOGGER.info("Connecting to client on address: " + USERS_SERVICE_HOST + ":" + USERS_SERVICE_PORT);
    }

    public Optional<User> authenticate(String username, String password) throws URISyntaxException {
        LOGGER.info("Connecting to users service on address [" + USERS_SERVICE_HOST + ":" + USERS_SERVICE_PORT + "] for user [" + username + "] and password [" + password + "].");
        URI thirdPartyApi = new URI("http", null, USERS_SERVICE_HOST, USERS_SERVICE_PORT, "/api/users", "username=" + username + "&password="+password, null);
        WebResource webResource = client.resource(thirdPartyApi);
        LOGGER.info("Web resource created " + webResource.getURI());
        LOGGER.info("Web resource created " + webResource.getURI().getScheme());
        LOGGER.info("Web resource created " + webResource.getURI().getSchemeSpecificPart());

        ClientResponse response = webResource
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);

        String stringResponse = response.getEntity(String.class);
        ResultPageWrapper results = new Gson().fromJson(stringResponse, ResultPageWrapper.class);
        if(results.getUsers() != null && results.getUsers().size() == 1) {
            return Optional.of(results.getUsers().get(0));
        }
        LOGGER.warn("No user found for user: " + username);
        return Optional.empty();
    }
}
