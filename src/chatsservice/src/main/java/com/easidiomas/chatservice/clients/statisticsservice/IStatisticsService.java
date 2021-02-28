
package com.easidiomas.chatservice.clients.statisticsservice;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.0
 * Generated source version: 2.2
 * 
 */
@WebService(name = "IStatisticsService", targetNamespace = "http://service.statisticsservice.easidiomas.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface IStatisticsService {


    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "registerTranslationCreatedEvent", targetNamespace = "http://service.statisticsservice.easidiomas.com/", className = "com.easidiomas.chatsservice.clients.statisticsservice.RegisterTranslationCreatedEvent")
    @ResponseWrapper(localName = "registerTranslationCreatedEventResponse", targetNamespace = "http://service.statisticsservice.easidiomas.com/", className = "com.easidiomas.chatsservice.clients.statisticsservice.RegisterTranslationCreatedEventResponse")
    public void registerTranslationCreatedEvent(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "registerUserCreatedEvent", targetNamespace = "http://service.statisticsservice.easidiomas.com/", className = "com.easidiomas.chatsservice.clients.statisticsservice.RegisterUserCreatedEvent")
    @ResponseWrapper(localName = "registerUserCreatedEventResponse", targetNamespace = "http://service.statisticsservice.easidiomas.com/", className = "com.easidiomas.chatsservice.clients.statisticsservice.RegisterUserCreatedEventResponse")
    public void registerUserCreatedEvent(
        @WebParam(name = "arg0", targetNamespace = "")
        List<String> arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "registerTextToSpeechCreatedEvent", targetNamespace = "http://service.statisticsservice.easidiomas.com/", className = "com.easidiomas.chatsservice.clients.statisticsservice.RegisterTextToSpeechCreatedEvent")
    @ResponseWrapper(localName = "registerTextToSpeechCreatedEventResponse", targetNamespace = "http://service.statisticsservice.easidiomas.com/", className = "com.easidiomas.chatsservice.clients.statisticsservice.RegisterTextToSpeechCreatedEventResponse")
    public void registerTextToSpeechCreatedEvent(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "registerPostCreatedEvent", targetNamespace = "http://service.statisticsservice.easidiomas.com/", className = "com.easidiomas.chatsservice.clients.statisticsservice.RegisterPostCreatedEvent")
    @ResponseWrapper(localName = "registerPostCreatedEventResponse", targetNamespace = "http://service.statisticsservice.easidiomas.com/", className = "com.easidiomas.chatsservice.clients.statisticsservice.RegisterPostCreatedEventResponse")
    public void registerPostCreatedEvent(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @return
     *     returns com.easidiomas.chatsservice.clients.statisticsservice.SystemStatistics
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getSystemStatistics", targetNamespace = "http://service.statisticsservice.easidiomas.com/", className = "com.easidiomas.chatsservice.clients.statisticsservice.GetSystemStatistics")
    @ResponseWrapper(localName = "getSystemStatisticsResponse", targetNamespace = "http://service.statisticsservice.easidiomas.com/", className = "com.easidiomas.chatsservice.clients.statisticsservice.GetSystemStatisticsResponse")
    public SystemStatistics getSystemStatistics();

    /**
     * 
     */
    @WebMethod
    @RequestWrapper(localName = "registerChatCreatedEvent", targetNamespace = "http://service.statisticsservice.easidiomas.com/", className = "com.easidiomas.chatsservice.clients.statisticsservice.RegisterChatCreatedEvent")
    @ResponseWrapper(localName = "registerChatCreatedEventResponse", targetNamespace = "http://service.statisticsservice.easidiomas.com/", className = "com.easidiomas.chatsservice.clients.statisticsservice.RegisterChatCreatedEventResponse")
    public void registerChatCreatedEvent();

    /**
     * 
     * @param arg0
     * @return
     *     returns com.easidiomas.chatsservice.clients.statisticsservice.UserStatistics
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getUserStatistics", targetNamespace = "http://service.statisticsservice.easidiomas.com/", className = "com.easidiomas.chatsservice.clients.statisticsservice.GetUserStatistics")
    @ResponseWrapper(localName = "getUserStatisticsResponse", targetNamespace = "http://service.statisticsservice.easidiomas.com/", className = "com.easidiomas.chatsservice.clients.statisticsservice.GetUserStatisticsResponse")
    public UserStatistics getUserStatistics(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}
