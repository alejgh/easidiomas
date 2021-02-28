
package com.easidiomas.usersservice.clients.statisticsservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.easidiomas.usersservice.clients.statisticsservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetSystemStatistics_QNAME = new QName("http://service.statisticsservice.easidiomas.com/", "getSystemStatistics");
    private final static QName _GetSystemStatisticsResponse_QNAME = new QName("http://service.statisticsservice.easidiomas.com/", "getSystemStatisticsResponse");
    private final static QName _GetUserStatistics_QNAME = new QName("http://service.statisticsservice.easidiomas.com/", "getUserStatistics");
    private final static QName _GetUserStatisticsResponse_QNAME = new QName("http://service.statisticsservice.easidiomas.com/", "getUserStatisticsResponse");
    private final static QName _RegisterChatCreatedEvent_QNAME = new QName("http://service.statisticsservice.easidiomas.com/", "registerChatCreatedEvent");
    private final static QName _RegisterChatCreatedEventResponse_QNAME = new QName("http://service.statisticsservice.easidiomas.com/", "registerChatCreatedEventResponse");
    private final static QName _RegisterPostCreatedEvent_QNAME = new QName("http://service.statisticsservice.easidiomas.com/", "registerPostCreatedEvent");
    private final static QName _RegisterPostCreatedEventResponse_QNAME = new QName("http://service.statisticsservice.easidiomas.com/", "registerPostCreatedEventResponse");
    private final static QName _RegisterTextToSpeechCreatedEvent_QNAME = new QName("http://service.statisticsservice.easidiomas.com/", "registerTextToSpeechCreatedEvent");
    private final static QName _RegisterTextToSpeechCreatedEventResponse_QNAME = new QName("http://service.statisticsservice.easidiomas.com/", "registerTextToSpeechCreatedEventResponse");
    private final static QName _RegisterTranslationCreatedEvent_QNAME = new QName("http://service.statisticsservice.easidiomas.com/", "registerTranslationCreatedEvent");
    private final static QName _RegisterTranslationCreatedEventResponse_QNAME = new QName("http://service.statisticsservice.easidiomas.com/", "registerTranslationCreatedEventResponse");
    private final static QName _RegisterUserCreatedEvent_QNAME = new QName("http://service.statisticsservice.easidiomas.com/", "registerUserCreatedEvent");
    private final static QName _RegisterUserCreatedEventResponse_QNAME = new QName("http://service.statisticsservice.easidiomas.com/", "registerUserCreatedEventResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.easidiomas.usersservice.clients.statisticsservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UserStatistics }
     * 
     */
    public UserStatistics createUserStatistics() {
        return new UserStatistics();
    }

    /**
     * Create an instance of {@link UserStatistics.CreatedPosts }
     * 
     */
    public UserStatistics.CreatedPosts createUserStatisticsCreatedPosts() {
        return new UserStatistics.CreatedPosts();
    }

    /**
     * Create an instance of {@link RegisteredUsersData }
     * 
     */
    public RegisteredUsersData createRegisteredUsersData() {
        return new RegisteredUsersData();
    }

    /**
     * Create an instance of {@link RegisteredUsersData.Nnative }
     * 
     */
    public RegisteredUsersData.Nnative createRegisteredUsersDataNnative() {
        return new RegisteredUsersData.Nnative();
    }

    /**
     * Create an instance of {@link RegisteredUsersData.Learning }
     * 
     */
    public RegisteredUsersData.Learning createRegisteredUsersDataLearning() {
        return new RegisteredUsersData.Learning();
    }

    /**
     * Create an instance of {@link SystemStatistics }
     * 
     */
    public SystemStatistics createSystemStatistics() {
        return new SystemStatistics();
    }

    /**
     * Create an instance of {@link SystemStatistics.CreatedPosts }
     * 
     */
    public SystemStatistics.CreatedPosts createSystemStatisticsCreatedPosts() {
        return new SystemStatistics.CreatedPosts();
    }

    /**
     * Create an instance of {@link GetSystemStatistics }
     * 
     */
    public GetSystemStatistics createGetSystemStatistics() {
        return new GetSystemStatistics();
    }

    /**
     * Create an instance of {@link GetSystemStatisticsResponse }
     * 
     */
    public GetSystemStatisticsResponse createGetSystemStatisticsResponse() {
        return new GetSystemStatisticsResponse();
    }

    /**
     * Create an instance of {@link GetUserStatistics }
     * 
     */
    public GetUserStatistics createGetUserStatistics() {
        return new GetUserStatistics();
    }

    /**
     * Create an instance of {@link GetUserStatisticsResponse }
     * 
     */
    public GetUserStatisticsResponse createGetUserStatisticsResponse() {
        return new GetUserStatisticsResponse();
    }

    /**
     * Create an instance of {@link RegisterChatCreatedEvent }
     * 
     */
    public RegisterChatCreatedEvent createRegisterChatCreatedEvent() {
        return new RegisterChatCreatedEvent();
    }

    /**
     * Create an instance of {@link RegisterChatCreatedEventResponse }
     * 
     */
    public RegisterChatCreatedEventResponse createRegisterChatCreatedEventResponse() {
        return new RegisterChatCreatedEventResponse();
    }

    /**
     * Create an instance of {@link RegisterPostCreatedEvent }
     * 
     */
    public RegisterPostCreatedEvent createRegisterPostCreatedEvent() {
        return new RegisterPostCreatedEvent();
    }

    /**
     * Create an instance of {@link RegisterPostCreatedEventResponse }
     * 
     */
    public RegisterPostCreatedEventResponse createRegisterPostCreatedEventResponse() {
        return new RegisterPostCreatedEventResponse();
    }

    /**
     * Create an instance of {@link RegisterTextToSpeechCreatedEvent }
     * 
     */
    public RegisterTextToSpeechCreatedEvent createRegisterTextToSpeechCreatedEvent() {
        return new RegisterTextToSpeechCreatedEvent();
    }

    /**
     * Create an instance of {@link RegisterTextToSpeechCreatedEventResponse }
     * 
     */
    public RegisterTextToSpeechCreatedEventResponse createRegisterTextToSpeechCreatedEventResponse() {
        return new RegisterTextToSpeechCreatedEventResponse();
    }

    /**
     * Create an instance of {@link RegisterTranslationCreatedEvent }
     * 
     */
    public RegisterTranslationCreatedEvent createRegisterTranslationCreatedEvent() {
        return new RegisterTranslationCreatedEvent();
    }

    /**
     * Create an instance of {@link RegisterTranslationCreatedEventResponse }
     * 
     */
    public RegisterTranslationCreatedEventResponse createRegisterTranslationCreatedEventResponse() {
        return new RegisterTranslationCreatedEventResponse();
    }

    /**
     * Create an instance of {@link RegisterUserCreatedEvent }
     * 
     */
    public RegisterUserCreatedEvent createRegisterUserCreatedEvent() {
        return new RegisterUserCreatedEvent();
    }

    /**
     * Create an instance of {@link RegisterUserCreatedEventResponse }
     * 
     */
    public RegisterUserCreatedEventResponse createRegisterUserCreatedEventResponse() {
        return new RegisterUserCreatedEventResponse();
    }

    /**
     * Create an instance of {@link UserStatistics.CreatedPosts.Entry }
     * 
     */
    public UserStatistics.CreatedPosts.Entry createUserStatisticsCreatedPostsEntry() {
        return new UserStatistics.CreatedPosts.Entry();
    }

    /**
     * Create an instance of {@link RegisteredUsersData.Nnative.Entry }
     * 
     */
    public RegisteredUsersData.Nnative.Entry createRegisteredUsersDataNnativeEntry() {
        return new RegisteredUsersData.Nnative.Entry();
    }

    /**
     * Create an instance of {@link RegisteredUsersData.Learning.Entry }
     * 
     */
    public RegisteredUsersData.Learning.Entry createRegisteredUsersDataLearningEntry() {
        return new RegisteredUsersData.Learning.Entry();
    }

    /**
     * Create an instance of {@link SystemStatistics.CreatedPosts.Entry }
     * 
     */
    public SystemStatistics.CreatedPosts.Entry createSystemStatisticsCreatedPostsEntry() {
        return new SystemStatistics.CreatedPosts.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSystemStatistics }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetSystemStatistics }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.statisticsservice.easidiomas.com/", name = "getSystemStatistics")
    public JAXBElement<GetSystemStatistics> createGetSystemStatistics(GetSystemStatistics value) {
        return new JAXBElement<GetSystemStatistics>(_GetSystemStatistics_QNAME, GetSystemStatistics.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSystemStatisticsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetSystemStatisticsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.statisticsservice.easidiomas.com/", name = "getSystemStatisticsResponse")
    public JAXBElement<GetSystemStatisticsResponse> createGetSystemStatisticsResponse(GetSystemStatisticsResponse value) {
        return new JAXBElement<GetSystemStatisticsResponse>(_GetSystemStatisticsResponse_QNAME, GetSystemStatisticsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserStatistics }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetUserStatistics }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.statisticsservice.easidiomas.com/", name = "getUserStatistics")
    public JAXBElement<GetUserStatistics> createGetUserStatistics(GetUserStatistics value) {
        return new JAXBElement<GetUserStatistics>(_GetUserStatistics_QNAME, GetUserStatistics.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserStatisticsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetUserStatisticsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.statisticsservice.easidiomas.com/", name = "getUserStatisticsResponse")
    public JAXBElement<GetUserStatisticsResponse> createGetUserStatisticsResponse(GetUserStatisticsResponse value) {
        return new JAXBElement<GetUserStatisticsResponse>(_GetUserStatisticsResponse_QNAME, GetUserStatisticsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterChatCreatedEvent }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RegisterChatCreatedEvent }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.statisticsservice.easidiomas.com/", name = "registerChatCreatedEvent")
    public JAXBElement<RegisterChatCreatedEvent> createRegisterChatCreatedEvent(RegisterChatCreatedEvent value) {
        return new JAXBElement<RegisterChatCreatedEvent>(_RegisterChatCreatedEvent_QNAME, RegisterChatCreatedEvent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterChatCreatedEventResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RegisterChatCreatedEventResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.statisticsservice.easidiomas.com/", name = "registerChatCreatedEventResponse")
    public JAXBElement<RegisterChatCreatedEventResponse> createRegisterChatCreatedEventResponse(RegisterChatCreatedEventResponse value) {
        return new JAXBElement<RegisterChatCreatedEventResponse>(_RegisterChatCreatedEventResponse_QNAME, RegisterChatCreatedEventResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterPostCreatedEvent }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RegisterPostCreatedEvent }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.statisticsservice.easidiomas.com/", name = "registerPostCreatedEvent")
    public JAXBElement<RegisterPostCreatedEvent> createRegisterPostCreatedEvent(RegisterPostCreatedEvent value) {
        return new JAXBElement<RegisterPostCreatedEvent>(_RegisterPostCreatedEvent_QNAME, RegisterPostCreatedEvent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterPostCreatedEventResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RegisterPostCreatedEventResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.statisticsservice.easidiomas.com/", name = "registerPostCreatedEventResponse")
    public JAXBElement<RegisterPostCreatedEventResponse> createRegisterPostCreatedEventResponse(RegisterPostCreatedEventResponse value) {
        return new JAXBElement<RegisterPostCreatedEventResponse>(_RegisterPostCreatedEventResponse_QNAME, RegisterPostCreatedEventResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterTextToSpeechCreatedEvent }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RegisterTextToSpeechCreatedEvent }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.statisticsservice.easidiomas.com/", name = "registerTextToSpeechCreatedEvent")
    public JAXBElement<RegisterTextToSpeechCreatedEvent> createRegisterTextToSpeechCreatedEvent(RegisterTextToSpeechCreatedEvent value) {
        return new JAXBElement<RegisterTextToSpeechCreatedEvent>(_RegisterTextToSpeechCreatedEvent_QNAME, RegisterTextToSpeechCreatedEvent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterTextToSpeechCreatedEventResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RegisterTextToSpeechCreatedEventResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.statisticsservice.easidiomas.com/", name = "registerTextToSpeechCreatedEventResponse")
    public JAXBElement<RegisterTextToSpeechCreatedEventResponse> createRegisterTextToSpeechCreatedEventResponse(RegisterTextToSpeechCreatedEventResponse value) {
        return new JAXBElement<RegisterTextToSpeechCreatedEventResponse>(_RegisterTextToSpeechCreatedEventResponse_QNAME, RegisterTextToSpeechCreatedEventResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterTranslationCreatedEvent }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RegisterTranslationCreatedEvent }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.statisticsservice.easidiomas.com/", name = "registerTranslationCreatedEvent")
    public JAXBElement<RegisterTranslationCreatedEvent> createRegisterTranslationCreatedEvent(RegisterTranslationCreatedEvent value) {
        return new JAXBElement<RegisterTranslationCreatedEvent>(_RegisterTranslationCreatedEvent_QNAME, RegisterTranslationCreatedEvent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterTranslationCreatedEventResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RegisterTranslationCreatedEventResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.statisticsservice.easidiomas.com/", name = "registerTranslationCreatedEventResponse")
    public JAXBElement<RegisterTranslationCreatedEventResponse> createRegisterTranslationCreatedEventResponse(RegisterTranslationCreatedEventResponse value) {
        return new JAXBElement<RegisterTranslationCreatedEventResponse>(_RegisterTranslationCreatedEventResponse_QNAME, RegisterTranslationCreatedEventResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterUserCreatedEvent }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RegisterUserCreatedEvent }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.statisticsservice.easidiomas.com/", name = "registerUserCreatedEvent")
    public JAXBElement<RegisterUserCreatedEvent> createRegisterUserCreatedEvent(RegisterUserCreatedEvent value) {
        return new JAXBElement<RegisterUserCreatedEvent>(_RegisterUserCreatedEvent_QNAME, RegisterUserCreatedEvent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterUserCreatedEventResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RegisterUserCreatedEventResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.statisticsservice.easidiomas.com/", name = "registerUserCreatedEventResponse")
    public JAXBElement<RegisterUserCreatedEventResponse> createRegisterUserCreatedEventResponse(RegisterUserCreatedEventResponse value) {
        return new JAXBElement<RegisterUserCreatedEventResponse>(_RegisterUserCreatedEventResponse_QNAME, RegisterUserCreatedEventResponse.class, null, value);
    }

}
