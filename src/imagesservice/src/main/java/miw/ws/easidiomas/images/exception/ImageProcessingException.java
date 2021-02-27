package miw.ws.easidiomas.images.exception;

public class ImageProcessingException extends Exception {

	private static final long serialVersionUID = 1L;
	private String faultCode;
	private String faultString;
	
	public ImageProcessingException() {
		super("ImageProcessingException - There was an error processing the image");
		this.faultString = getMessage();
		this.faultCode = "500 Internal Server Error";
	}

	public String getFaultCode() {
		return faultCode;
	}

	public String getFaultString() {
		return faultString;
	}

}
