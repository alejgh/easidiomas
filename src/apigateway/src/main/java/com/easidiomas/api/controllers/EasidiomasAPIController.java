package com.easidiomas.api.controllers;

import com.easidiomas.auth.Authservice;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;

public class EasidiomasAPIController {

    protected Authservice.Passport getPassport(HttpServletRequest request) {
        String passportJson = request.getHeader("passport");
        Authservice.Passport passport = new Gson().fromJson(passportJson, Authservice.Passport.class);
        return passport;
    }
}
