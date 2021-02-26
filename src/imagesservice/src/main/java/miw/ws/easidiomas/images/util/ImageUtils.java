package miw.ws.easidiomas.images.util;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class ImageUtils {
	
	public static BufferedImage resizeImage(Image src, int finalWidth, int finalHeight) {
	    Image resultingImage = src.getScaledInstance(finalWidth, finalHeight, Image.SCALE_DEFAULT);
	    BufferedImage outputImage = new BufferedImage(finalWidth, finalHeight, BufferedImage.TYPE_INT_RGB);
	    outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
	    return outputImage;
	}

}
