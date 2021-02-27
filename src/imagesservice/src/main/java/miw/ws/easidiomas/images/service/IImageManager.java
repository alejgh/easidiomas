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

	/**
	 * Uploads an image to be processed and stored for future use.
	 * 
	 * The processing involves resizing the image to a standard size.
	 * An URL to access the image will be returned.
	 * 
	 * @param data Binary image data.
	 * @return URL to access the processed image.
	 * @throws ImageProcessingException If there was an error resizing the image.
	 */
	String uploadImage(@XmlMimeType("image/jpeg") Image data) throws ImageProcessingException;

}
