package com.easidiomas.api.controllers;

import com.easidiomas.api.clients.usersservice.UserInfo;
import com.easidiomas.api.filters.SecurityFilter;
import com.easidiomas.auth.Authservice;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/users")
public class UsersServiceController extends EasidiomasAPIController {

    private static final String USERS_SERVICE_HOST = System.getenv("USERS_SERVICE_HOST")!=null ? System.getenv("USERS_SERVICE_HOST"): "localhost";
    private static final int USERS_SERVICE_PORT = Integer.parseInt(System.getenv("USERS_SERVICE_PORT")!=null ? System.getenv("USERS_SERVICE_PORT"): "5000");

    public final static String SERVICE_URI = "https://www.easidiomas.com/api/users";
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersServiceController.class);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity index(HttpServletRequest request) throws IOException, URISyntaxException {
        LOGGER.info(String.format("Request to path [%s] received.", request.getRequestURL()));
        LOGGER.info(String.format("Getting the passport from the header for request [%s].", request.getRequestURL()));
        Authservice.Passport passport = super.getPassport(request);

        LOGGER.info(String.format("Requesting to the users service the list of users paginated [%s].", request.getRequestURL()));
        LOGGER.info(String.format("Redirecting request to [%s:%s].", USERS_SERVICE_HOST, USERS_SERVICE_PORT));
        return this.doRedirect(request, "/api/users");
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity index(HttpServletRequest request, @PathVariable String id) throws IOException, URISyntaxException {
        LOGGER.info(String.format("Request to path [%s] received.", request.getRequestURL()));
        LOGGER.info(String.format("Getting the passport from the header for request [%s].", request.getRequestURL()));
        Authservice.Passport passport = super.getPassport(request);

        LOGGER.info(String.format("Requesting to the users service the list of users paginated [%s].", request.getRequestURL()));
        LOGGER.info(String.format("Redirecting request to [%s:%s].", USERS_SERVICE_HOST, USERS_SERVICE_PORT));
        return this.doRedirect(request, "/api/users/" + id);
    }

    @GetMapping(value = ":generateData", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity generateData(HttpServletRequest request) throws IOException, URISyntaxException {
        LOGGER.info(String.format("Request to path [%s] received.", request.getRequestURL()));
        LOGGER.info(String.format("Getting the passport from the header for request [%s].", request.getRequestURL()));
        Authservice.Passport passport = super.getPassport(request);

        LOGGER.info(String.format("Requesting to the users service the list of users paginated [%s].", request.getRequestURL()));
        LOGGER.info(String.format("Redirecting request to [%s:%s].", USERS_SERVICE_HOST, USERS_SERVICE_PORT));
        return this.doRedirect(request, "/api/users:generateData");
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(HttpServletRequest request) throws IOException, URISyntaxException {
        LOGGER.info(String.format("Request to path [%s] received.", request.getRequestURL()));
        // Crear el usuario en el servicio de usuarios y actualizar el objeto de retorno de la response
        // Para que sea el objeto creado. Es decir, con el ID correcto.
        //return ResponseEntity.created(URI.create(SERVICE_URI+"/1")).body(user);
        LOGGER.info(String.format("Redirecting request to [%s:%s].", USERS_SERVICE_HOST, USERS_SERVICE_PORT));
        return this.doRedirect(request, "/api/users");
    }

    private ResponseEntity<String> doRedirect(HttpServletRequest request, String path) throws URISyntaxException, IOException {
        // obtener uri a la que enviar la nueva peticion
        URI thirdPartyApi = new URI("http", null, USERS_SERVICE_HOST, USERS_SERVICE_PORT, path, request.getQueryString(), null);
        Authservice.Passport passport = super.getPassport(request);

        // copiar headers a la nueva peticion
        HttpHeaders headers = new HttpHeaders();
        for (Enumeration<?> e = request.getHeaderNames(); e.hasMoreElements();) {
            String nextHeaderName = (String) e.nextElement();
            String headerValue = request.getHeader(nextHeaderName);
            headers.add(nextHeaderName, headerValue);
        }

        // esto no se muy bien como va. creo que ya debería estar el passport aqui asi que seguramente se pueda borrar
        if (passport != null) headers.add("passport", new Gson().toJson(passport));

        // obtener el body del post
        String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        // enviamos la peticion a la nueva uri
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<String>(body, headers);

        ResponseEntity<String> resp;
        try {
            resp =
                    restTemplate.exchange(thirdPartyApi, HttpMethod.resolve(request.getMethod()), entity, String.class);
        } catch (HttpClientErrorException e) {
            resp =
                    ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
        return resp;
    }
}
