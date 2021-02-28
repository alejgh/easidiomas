package com.easidiomas.statisticsservice.service;

import com.easidiomas.statisticsservice.model.SystemStatistics;
import com.easidiomas.statisticsservice.model.UserStatistics;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface IStatisticsService {

    @WebMethod
    void registerUserCreatedEvent(String[] learning, String speaks);

    @WebMethod
    void registerPostCreatedEvent(String userId, String language);

    @WebMethod
    void registerChatCreatedEvent();

    @WebMethod
    void registerTranslationCreatedEvent(String userId);

    @WebMethod
    void registerTextToSpeechCreatedEvent(String userId);

    @WebMethod
    SystemStatistics getSystemStatistics();

    @WebMethod
    UserStatistics getUserStatistics(String userId);
}
