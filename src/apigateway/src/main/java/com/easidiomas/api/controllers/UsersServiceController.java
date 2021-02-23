package com.easidiomas.api.controllers;

import com.easidiomas.api.clients.usersservice.UserInfo;
import com.easidiomas.auth.Authservice;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequestMapping(value = "api/v1/users")
public class UsersServiceController extends EasidiomasAPIController {

    public final static String SERVICE_URI = "https://www.easidiomas.com/api/v1/users";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity index(HttpServletRequest request, @Nullable @RequestParam String name,
                                @Nullable @RequestParam String surname, @Nullable @RequestParam String age,
                                @Nullable @RequestParam String speaks, @Nullable @RequestParam String wantsToLearn) {
        Authservice.Passport passport = super.getPassport(request);

        // Propagar al servicio de clientes.
        return ResponseEntity.ok().body("");
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(HttpServletRequest request, @RequestBody UserInfo user) {
        // Crear el usuario en el servicio de usuarios y actualizar el objeto de retorno de la response
        // Para que sea el objeto creado. Es decir, con el ID correcto.
        return ResponseEntity.created(URI.create(SERVICE_URI+"/1")).body(user);
    }
}
