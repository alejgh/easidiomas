package com.easidiomas.api.filters;

import com.easidiomas.api.EasidiomasAPIGateway;
import com.easidiomas.api.clients.authenticationservice.AuthenticationServiceClient;
import com.easidiomas.api.util.MutableHttpServletRequest;
import com.easidiomas.auth.Authservice;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        LOGGER.info(String.format("Security filter dispatched for request on path [%s]", req.getRequestURL()));

        MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(req);
        HttpServletResponse res = (HttpServletResponse) response;
        String stringToken = req.getHeader("token");

        // Si la petición viene directamente sin toquen..
        if(stringToken == null) {
            LOGGER.info(String.format("Request to path [%s] does not contain a token", req.getRequestURL()));
            res.sendError(HttpStatus.UNAUTHORIZED.value(), "No token was given. Please add you 'token' to the headers.");
            return;
        } else {
            // Si tiene token, lo validamos y metemos el pasaporte que se genera en la cabecera de la petición
            // que se propaga a los servicios.
            // Validate the token and put the passport in the header of the request.
            Authservice.Passport passport = null;
            try {
                passport = new AuthenticationServiceClient().requestPassport(stringToken);
            } catch (Exception e) {
                LOGGER.info(String.format("Request to path [%s] contains a token [%s] but is not valid", req.getRequestURL(), stringToken));
                res.sendError(HttpStatus.UNAUTHORIZED.value(), "The provided token is no longer valid.");
                return;
            }
            LOGGER.info(String.format("Request to path [%s] contains a token [%s] and it is valid, injecting passport in the header.", req.getRequestURL(), stringToken));
            mutableRequest.putHeader("passport", new Gson().toJson(passport));

            LOGGER.info(String.format("Passing request to path [%s] to the specific controller", req.getRequestURL()));
            chain.doFilter(mutableRequest, res);
        }
    }
}
