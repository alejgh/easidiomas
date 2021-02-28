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


    private static final String POSTS_SERVICE_HOST = System.getenv("POSTS_SERVICE_HOST")!=null ? System.getenv("POSTS_SERVICE_HOST"): "localhost";
    private static final int POSTS_SERVICE_PORT = Integer.parseInt(System.getenv("POSTS_SERVICE_PORT")!=null ? System.getenv("POSTS_SERVICE_PORT"): "5000");

    private static final Logger LOGGER = LoggerFactory.getLogger(PostsServiceController.class);
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> index(HttpServletRequest request) throws IOException, URISyntaxException {
        LOGGER.info(String.format("Request to path [%s] received.", request.getRequestURL()));
        LOGGER.info(String.format("Requesting to the posts service the list of posts paginated [%s].", request.getRequestURL()));
        LOGGER.info(String.format("Redirecting request to [%s:%s].", POSTS_SERVICE_HOST, POSTS_SERVICE_PORT));
    	return this.doRedirect(request, "/api/posts");
    }
    
    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> get(@PathVariable long id, HttpServletRequest request) throws IOException, URISyntaxException {
        LOGGER.info(String.format("Request to path [%s] received.", request.getRequestURL()));
        LOGGER.info(String.format("Requesting to the posts service a post [%s].", request.getRequestURL()));
        LOGGER.info(String.format("Redirecting request to [%s:%s].", POSTS_SERVICE_HOST, POSTS_SERVICE_PORT));
    	return this.doRedirect(request, "/api/posts/" + id);
    }
 
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(HttpServletRequest request) throws IOException, URISyntaxException {
        LOGGER.info(String.format("Request to path [%s] received.", request.getRequestURL()));
        LOGGER.info(String.format("Requesting to the posts service to create a new post [%s].", request.getRequestURL()));
        LOGGER.info(String.format("Redirecting request to [%s:%s].", POSTS_SERVICE_HOST, POSTS_SERVICE_PORT));
    	return this.doRedirect(request, "/api/posts");
    } 
 
    @PutMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@PathVariable long id, HttpServletRequest request) throws IOException, URISyntaxException {
        LOGGER.info(String.format("Request to path [%s] received.", request.getRequestURL()));
        LOGGER.info(String.format("Requesting to the posts service to update a post [%s].", request.getRequestURL()));
        LOGGER.info(String.format("Redirecting request to [%s:%s].", POSTS_SERVICE_HOST, POSTS_SERVICE_PORT));
    	return this.doRedirect(request, "/api/posts/" + id);
    }

    @DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable long id, HttpServletRequest request) throws IOException, URISyntaxException {
        LOGGER.info(String.format("Request to path [%s] received.", request.getRequestURL()));
        LOGGER.info(String.format("Requesting to the posts service to delete a post [%s].", request.getRequestURL()));
        LOGGER.info(String.format("Redirecting request to [%s:%s].", POSTS_SERVICE_HOST, POSTS_SERVICE_PORT));
    	return this.doRedirect(request, "/api/posts/" + id);
    }
    
    private ResponseEntity<String> doRedirect(HttpServletRequest request, String path) throws URISyntaxException, IOException {
        // obtener uri a la que enviar la nueva peticion
    	URI thirdPartyApi = new URI("http", null, POSTS_SERVICE_HOST, POSTS_SERVICE_PORT, path, request.getQueryString(), null);

        // copiar headers a la nueva peticion
        HttpHeaders headers = new HttpHeaders();
        for (Enumeration<?> e = request.getHeaderNames(); e.hasMoreElements();) {
            String nextHeaderName = (String) e.nextElement();
            String headerValue = request.getHeader(nextHeaderName);
            headers.add(nextHeaderName, headerValue);
        }
        
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
