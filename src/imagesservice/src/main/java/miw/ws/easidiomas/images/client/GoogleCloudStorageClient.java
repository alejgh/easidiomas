package miw.ws.easidiomas.images.client;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class GoogleCloudStorageClient {
	
	private Storage storageClient;

	public GoogleCloudStorageClient() {
		this.storageClient = StorageOptions.getDefaultInstance().getService();
	}
	
	public String uploadToStorage(byte[] data, String bucketName, String fileName) {
	    BlobId blobId = BlobId.of(bucketName, fileName);
	    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/jpeg").build();
	    storageClient.create(blobInfo, data);
	    return "https://" + bucketName + ".storage.googleapis.com/" + fileName;
	}
	
}
