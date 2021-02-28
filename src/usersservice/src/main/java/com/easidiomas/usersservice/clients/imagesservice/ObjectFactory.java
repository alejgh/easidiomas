
package com.easidiomas.usersservice.clients.imagesservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.easidiomas.usersservice.clients.imagesservice package. 
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

    private final static QName _UploadImage_QNAME = new QName("http://service.images.easidiomas.ws.miw/", "uploadImage");
    private final static QName _UploadImageResponse_QNAME = new QName("http://service.images.easidiomas.ws.miw/", "uploadImageResponse");
    private final static QName _ImageProcessingException_QNAME = new QName("http://service.images.easidiomas.ws.miw/", "ImageProcessingException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.easidiomas.usersservice.clients.imagesservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UploadImage }
     * 
     */
    public UploadImage createUploadImage() {
        return new UploadImage();
    }

    /**
     * Create an instance of {@link UploadImageResponse }
     * 
     */
    public UploadImageResponse createUploadImageResponse() {
        return new UploadImageResponse();
    }

    /**
     * Create an instance of {@link ImageProcessingException }
     * 
     */
    public ImageProcessingException createImageProcessingException() {
        return new ImageProcessingException();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UploadImage }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UploadImage }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.images.easidiomas.ws.miw/", name = "uploadImage")
    public JAXBElement<UploadImage> createUploadImage(UploadImage value) {
        return new JAXBElement<UploadImage>(_UploadImage_QNAME, UploadImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UploadImageResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UploadImageResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.images.easidiomas.ws.miw/", name = "uploadImageResponse")
    public JAXBElement<UploadImageResponse> createUploadImageResponse(UploadImageResponse value) {
        return new JAXBElement<UploadImageResponse>(_UploadImageResponse_QNAME, UploadImageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImageProcessingException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ImageProcessingException }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.images.easidiomas.ws.miw/", name = "ImageProcessingException")
    public JAXBElement<ImageProcessingException> createImageProcessingException(ImageProcessingException value) {
        return new JAXBElement<ImageProcessingException>(_ImageProcessingException_QNAME, ImageProcessingException.class, null, value);
    }

}
