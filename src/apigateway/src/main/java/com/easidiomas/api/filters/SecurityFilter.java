package com.easidiomas.api.filters;

import com.easidiomas.api.clients.authenticationservice.AuthenticationServiceClient;
import com.easidiomas.api.util.MutableHttpServletRequest;
import com.easidiomas.auth.Authservice;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Auth filter triggered"); // Cambiar esto por una llamada al log service.
        HttpServletRequest req = (HttpServletRequest) request;
        MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(req);
        HttpServletResponse res = (HttpServletResponse) response;
        String stringToken = req.getHeader("token");

        // Si la petición viene directamente sin toquen..
        if(stringToken == null) {
            System.out.println("Request without token");
            res.sendError(HttpStatus.UNAUTHORIZED.value(), "token missing");
            return;
        } else {
            // Si tiene token, lo validamos y metemos el pasaporte que se genera en la cabecera de la petición
            // que se propaga a los servicios.
            // Validate the token and put the passport in the header of the request.
            Authservice.Passport passport = new AuthenticationServiceClient().requestPassport(stringToken);
            mutableRequest.putHeader("passport", new Gson().toJson(passport));
            chain.doFilter(mutableRequest, response);
        }
    }
}
