package miw.ws.easidiomas.images.util;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class ImageUtils {
	
    /**
     * Resizes an image to the given dimensions.
     * 
     * @param src java.awt.Image object with the original image to be resized.
     * @param finalWidth Target width that the final image will have.
     * @param finalHeight Target height that the final image will have.
     * @return BufferedImage object with the resized image data.
     */
	public static BufferedImage resizeImage(Image src, int finalWidth, int finalHeight) {
	    Image resultingImage = src.getScaledInstance(finalWidth, finalHeight, Image.SCALE_DEFAULT);
	    BufferedImage outputImage = new BufferedImage(finalWidth, finalHeight, BufferedImage.TYPE_INT_RGB);
	    outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
	    return outputImage;
	}

}
