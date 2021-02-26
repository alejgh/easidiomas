package miw.ws.easidiomas.images.service;

import java.awt.Image;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.bind.annotation.XmlMimeType;

import miw.ws.easidiomas.images.exception.ImageProcessingException;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface IImageManager {

	String uploadImage(@XmlMimeType("image/jpeg") Image data) throws ImageProcessingException;

}
