package miw.ws.easidiomas.images.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.jws.WebService;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.MTOM;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import miw.ws.easidiomas.images.client.GoogleCloudStorageClient;
import miw.ws.easidiomas.images.exception.ImageProcessingException;
import miw.ws.easidiomas.images.util.ImageUtils;

@MTOM
@WebService(endpointInterface = "miw.ws.easidiomas.images.service.IImageManager", serviceName = "imageManager")
public class ImageManagerImpl implements IImageManager {
	
    private static final Logger logger = LoggerFactory.getLogger(ImageManagerImpl.class);
	
	private static final int RESIZE_WIDTH = Integer.parseInt(System.getenv("RESIZE_WIDTH") !=null ? System.getenv("RESIZE_WIDTH"): "512");
	
	private static final int RESIZE_HEIGHT = Integer.parseInt(System.getenv("RESIZE_HEIGHT") !=null ? System.getenv("RESIZE_HEIGHT"): "512");
	
	private static final String BUCKET_NAME = System.getenv("BUCKET_NAME") != null ? System.getenv("BUCKET_NAME") : "miw_ws";
	
	private GoogleCloudStorageClient gcloudClient;
	
	public ImageManagerImpl() {
		gcloudClient = new GoogleCloudStorageClient();
	}

	@Override
	public String uploadImage(Image data) throws ImageProcessingException {
		logger.debug("Upload image was called");
		if(data == null) throw new WebServiceException("The image was not received");
		
		try {
           	BufferedImage resizedImage = ImageUtils.resizeImage(data, RESIZE_WIDTH, RESIZE_HEIGHT);
           	
           	ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(resizedImage, "jpg", baos);
           	byte[] imageData = baos.toByteArray();
           	
           	String url = gcloudClient.uploadToStorage(imageData, BUCKET_NAME, UUID.randomUUID().toString() + ".jpg");
            return url;
		} catch (IOException e) {
			e.printStackTrace();
		    throw new ImageProcessingException();
		}
    }

}
