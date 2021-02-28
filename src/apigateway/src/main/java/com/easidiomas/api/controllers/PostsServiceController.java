package com.easidiomas.api.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.easidiomas.auth.Authservice;
import com.google.gson.Gson;

@RestController
@RequestMapping(value = "api/posts")
public class PostsServiceController extends EasidiomasAPIController {

    public final static String SERVICE_HOST = "postsservice";
    public final static int SERVICE_PORT = 80;
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersServiceController.class);
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> index(HttpServletRequest request) throws IOException, URISyntaxException {
    	return this.doRedirect(request, "/api/posts");
    }
    
    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> get(@PathVariable long id, HttpServletRequest request) throws IOException, URISyntaxException {
    	return this.doRedirect(request, "/api/posts/" + id);
    }
 
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(HttpServletRequest request) throws IOException, URISyntaxException {
    	return this.doRedirect(request, "/api/posts");
    } 
 
    @PutMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@PathVariable long id, HttpServletRequest request) throws IOException, URISyntaxException {
    	return this.doRedirect(request, "/api/posts/" + id);
    }

    @DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable long id, HttpServletRequest request) throws IOException, URISyntaxException {
    	return this.doRedirect(request, "/api/posts/" + id);
    }
    
    private ResponseEntity<String> doRedirect(HttpServletRequest request, String path) throws URISyntaxException, IOException {
        // obtener uri a la que enviar la nueva peticion
    	URI thirdPartyApi = new URI("http", null, SERVICE_HOST, SERVICE_PORT, path, request.getQueryString(), null);
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
