package com.easidiomas.api.controllers;

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
@RequestMapping(value = "api/TextsToSpeechs")
public class TextToSpeechService extends EasidiomasAPIController {

    private static final String TTS_SERVICE_HOST = System.getenv("TTS_SERVICE_HOST")!=null ? System.getenv("TTS_SERVICE_HOST"): "localhost";
    private static final int TTS_SERVICE_PORT = Integer.parseInt(System.getenv("TTS_SERVICE_PORT")!=null ? System.getenv("TTS_SERVICE_PORT"): "5000");

    private static final Logger LOGGER = LoggerFactory.getLogger(TextToSpeechService.class);

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(HttpServletRequest request) throws IOException, URISyntaxException {
        LOGGER.info(String.format("Request to path [%s] received.", request.getRequestURL()));
        LOGGER.info(String.format("Redirecting request to [%s:%s].", TTS_SERVICE_HOST, TTS_SERVICE_PORT));
        return this.doRedirect(request, "/api/TextsToSpeechs");
    }

    private ResponseEntity<String> doRedirect(HttpServletRequest request, String path) throws URISyntaxException, IOException {
        // obtener uri a la que enviar la nueva peticion
        URI thirdPartyApi = new URI("http", null, TTS_SERVICE_HOST, TTS_SERVICE_PORT, path, request.getQueryString(), null);

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
