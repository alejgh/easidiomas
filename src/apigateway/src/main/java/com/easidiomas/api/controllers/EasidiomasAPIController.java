package com.easidiomas.api.controllers;

import com.easidiomas.auth.Authservice;
import com.google.gson.Gson;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class EasidiomasAPIController {

    protected Authservice.Passport getPassport(HttpServletRequest request) {
        String passportJson = request.getHeader("passport");
        Authservice.Passport passport = new Gson().fromJson(passportJson, Authservice.Passport.class);
        return passport;
    }

    protected void logRequest(HttpServletRequest request, Logger logger) {
        logger.info(String.format("Request to path [%s] received.", request.getMethod() + ":" + request.getRequestURL()));
    }

    protected void logRedirect(Logger logger, String host, int port) {
        logger.info(String.format("Redirecting request to [%s:%s].", host, port));
    }
}
