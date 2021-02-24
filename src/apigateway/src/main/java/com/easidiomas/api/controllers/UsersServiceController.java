package com.easidiomas.api.controllers;

import com.easidiomas.api.clients.usersservice.UserInfo;
import com.easidiomas.api.filters.SecurityFilter;
import com.easidiomas.auth.Authservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequestMapping(value = "api/users")
public class UsersServiceController extends EasidiomasAPIController {

    public final static String SERVICE_URI = "https://www.easidiomas.com/api/users";
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersServiceController.class);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity index(HttpServletRequest request, @Nullable @RequestParam Integer limit,
                                @Nullable @RequestParam Integer offset, @Nullable @RequestParam Integer minAge,
                                @Nullable @RequestParam Integer maxAge, @Nullable @RequestParam String[] speaks,
                                @Nullable @RequestParam String wantsToLearn) {
        LOGGER.info(String.format("Request to path [%s] received.", request.getRequestURL()));
        LOGGER.info(String.format("Getting the passport from the header for request [%s].", request.getRequestURL()));
        Authservice.Passport passport = super.getPassport(request);

        LOGGER.info(String.format("Requesting to the users service the list of users paginated [%s].", request.getRequestURL()));

        // Propagar al servicio de clientes.
        return ResponseEntity.ok().body("{\n" +
                "\t“links”: {\n" +
                "\t\t“self”: “url_actual”,\n" +
                "\t\t“first”: “url_primeros_elementos”,\n" +
                "\t\t“prev”: “url_pagina_anterior”,\n" +
                "\t\t“next”: “url_pagina_siguiente”,\n" +
                "\t\t“last”: “url_ultima_pagina”\n" +
                "\t},\n" +
                "\t“hits”: 10,\n" +
                "\t“total”: 500,\n" +
                "\t“users”: [\n" +
                "\t{\n" +
                "\t\t\"id\": 1,\n" +
                "                \"name\": \"Pepito\",\n" +
                "                \"surname\": \"Sanchez Perez\"\n" +
                "\t\t\"learning\": [“en”, \"ru\"],\n" +
                "                \"speaks\": \"es\",\n" +
                "                \"birthDate\": \"<long_time_since_epoch>\",\n" +
                "                \"avatar\": \"<url_del_avatar>\",\n" +
                "                \"followers\": [\"<url_get_carmen>\", \"<url_get_paco>\"],\n" +
                "                \"following\": [\"<url_get_fulanito>\", \"<url_get_menganita>\"]\n" +
                "\t},\n" +
                "\t…\n" +
                "\t{\n" +
                "\t\t\"id\": 10,\n" +
                "\t\t…\n" +
                "\t}\n" +
                "\t]\n" +
                "}");
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(HttpServletRequest request, @RequestBody UserInfo user) {
        LOGGER.info(String.format("Request to path [%s] received.", request.getRequestURL()));
        // Crear el usuario en el servicio de usuarios y actualizar el objeto de retorno de la response
        // Para que sea el objeto creado. Es decir, con el ID correcto.
        return ResponseEntity.created(URI.create(SERVICE_URI+"/1")).body(user);
    }
}
