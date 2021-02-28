package com.easidiomas.api.controllers;

import com.easidiomas.auth.Authservice;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/chats")
public class ChatsServiceController  extends EasidiomasAPIController {

        private static final String CHATS_SERVICE_HOST = System.getenv("CHATS_SERVICE_HOST")!=null ? System.getenv("CHATS_SERVICE_HOST"): "localhost";
        private static final int CHATS_SERVICE_PORT = Integer.parseInt(System.getenv("CHATS_SERVICE_PORT")!=null ? System.getenv("CHATS_SERVICE_PORT"): "5000");

        public final static String SERVICE_URI = "https://www.easidiomas.com/api/users";
        private static final Logger LOGGER = LoggerFactory.getLogger(com.easidiomas.api.controllers.UsersServiceController.class);

        @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity index(HttpServletRequest request) throws IOException, URISyntaxException {
            LOGGER.info(String.format("Request to path [%s] received.", request.getRequestURL()));
            LOGGER.info(String.format("Requesting to the chats service the list of chats"));
            LOGGER.info(String.format("Redirecting request to [%s:%s].", CHATS_SERVICE_HOST, CHATS_SERVICE_PORT));
            return this.doRedirect(request, "/api/chats");
        }

        @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity create(HttpServletRequest request) throws IOException, URISyntaxException {
            LOGGER.info(String.format("Request to path [%s] received.", request.getRequestURL()));
            LOGGER.info(String.format("Requesting to the chats service the creation of a new chat"));
            LOGGER.info(String.format("Redirecting request to [%s:%s].", CHATS_SERVICE_HOST, CHATS_SERVICE_PORT));
            return this.doRedirect(request, "/api/chats");
        }

        @GetMapping(value = "/{id}/messages", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity index(HttpServletRequest request, @PathVariable String id) throws IOException, URISyntaxException {
            LOGGER.info(String.format("Request to path [%s] received.", request.getRequestURL()));
            LOGGER.info(String.format("Requesting to the chats service the messages of a specific chat."));
            LOGGER.info(String.format("Redirecting request to [%s:%s].", CHATS_SERVICE_HOST, CHATS_SERVICE_PORT));
            return this.doRedirect(request, "/api/chats/" + id+"/messages");
        }

        @PostMapping(value = "/{id}/messages",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity create(HttpServletRequest request,@PathVariable String id) throws IOException, URISyntaxException {
            LOGGER.info(String.format("Request to path [%s] received.", request.getRequestURL()));
            LOGGER.info(String.format("Requesting to the chats service the creation of a new message in a specific chat."));
            LOGGER.info(String.format("Redirecting request to [%s:%s].", CHATS_SERVICE_HOST, CHATS_SERVICE_PORT));
            return this.doRedirect(request, "/api/chats/" + id+"/messages");
        }


        private ResponseEntity<String> doRedirect(HttpServletRequest request, String path) throws URISyntaxException, IOException {
            // obtener uri a la que enviar la nueva peticion
            URI thirdPartyApi = new URI("http", null, CHATS_SERVICE_HOST, CHATS_SERVICE_PORT, path, request.getQueryString(), null);
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
