
package com.easidiomas.usersservice.clients.imagesservice;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.0
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "IImageManagerService", targetNamespace = "http://service.images.easidiomas.ws.miw/", wsdlLocation = "http://localhost:5004/soapws/images?wsdl")
public class IImageManagerService
    extends Service
{

    private final static URL IIMAGEMANAGERSERVICE_WSDL_LOCATION;
    private final static WebServiceException IIMAGEMANAGERSERVICE_EXCEPTION;
    private final static QName IIMAGEMANAGERSERVICE_QNAME = new QName("http://service.images.easidiomas.ws.miw/", "IImageManagerService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:5004/soapws/images?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        IIMAGEMANAGERSERVICE_WSDL_LOCATION = url;
        IIMAGEMANAGERSERVICE_EXCEPTION = e;
    }

    public IImageManagerService() {
        super(__getWsdlLocation(), IIMAGEMANAGERSERVICE_QNAME);
    }

    public IImageManagerService(WebServiceFeature... features) {
        super(__getWsdlLocation(), IIMAGEMANAGERSERVICE_QNAME, features);
    }

    public IImageManagerService(URL wsdlLocation) {
        super(wsdlLocation, IIMAGEMANAGERSERVICE_QNAME);
    }

    public IImageManagerService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, IIMAGEMANAGERSERVICE_QNAME, features);
    }

    public IImageManagerService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public IImageManagerService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns IImageManager
     */
    @WebEndpoint(name = "IImageManagerPort")
    public IImageManager getIImageManagerPort() {
        return super.getPort(new QName("http://service.images.easidiomas.ws.miw/", "IImageManagerPort"), IImageManager.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IImageManager
     */
    @WebEndpoint(name = "IImageManagerPort")
    public IImageManager getIImageManagerPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.images.easidiomas.ws.miw/", "IImageManagerPort"), IImageManager.class, features);
    }

    private static URL __getWsdlLocation() {
        if (IIMAGEMANAGERSERVICE_EXCEPTION!= null) {
            throw IIMAGEMANAGERSERVICE_EXCEPTION;
        }
        return IIMAGEMANAGERSERVICE_WSDL_LOCATION;
    }

}
