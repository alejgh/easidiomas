package miw.ws.easidiomas.images.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import miw.ws.easidiomas.images.service.ImageManagerImpl;

/**
 *
 * Implementation of a simple google cloud storage client.
 * 
 * This client makes use of the Google Cloud Storage Java library
 * (see https://googleapis.dev/java/google-cloud-storage/latest/index.html)
 * to connect to a bucket in Google Cloud Storage and upload files to it.
 * 
 * These files can then be accessed by clients of easidiomas.
 *
 */
public class GoogleCloudStorageClient {
	
	private Storage storageClient;
	
    private static final Logger logger = LoggerFactory.getLogger(ImageManagerImpl.class);

	public GoogleCloudStorageClient() {
		logger.info("Initializing Google Cloud Storage client...");
		this.storageClient = StorageOptions.getDefaultInstance().getService();
		logger.info("Google Cloud Storage client initialized successfully!");
	}
	
	/**
	 * Uploads the given binary data to Google Cloud Storage.
	 * 
	 * @param data Bytes array with the data to be uploaded.
	 * @param bucketName Name of the bucket where the image will be stored. The user must
	 * 	have write permissions on the bucket for the operation to be performed.
	 * @param fileName Name of the file that will be stored in the bucket.
	 * @return String with the URL to access the file publicly.
	 */
	public String uploadToStorage(byte[] data, String bucketName, String fileName, String contentType) {
		logger.info("Uploading image '" + fileName + "' to bucket '" + bucketName + "'");
		logger.trace("Byte data received: " + data);
	    BlobId blobId = BlobId.of(bucketName, fileName);
	    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(contentType).build();
	    storageClient.create(blobInfo, data);
	    logger.info("Image has been uploaded successfully!");
	    return "https://" + bucketName + ".storage.googleapis.com/" + fileName;
	}
	
}
