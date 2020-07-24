package com.ironhack.kix.image.service.services;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.ironhack.kix.image.service.models.Image;
import com.ironhack.kix.image.service.models.utils.Utils;
import com.sun.jersey.core.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GoogleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleService.class);

    @Value("${PROJECT_ID}")
    String projectId;

    @Value("${LOCATION_ID}")
    String locationId;

    @Value("${STORAGE_NAME}")
    String storageName;

    @Value("${PRODUCT_SET}")
    String productSet;

    @Value("${PRODUCT_CATEGORY}")
    String productCategory;

    public Image uploadProductImageToProductStorage(String imageBase64) {
        byte[] productImage = Base64.decode(imageBase64);
        LOGGER.info(String.format("Project ID: %s, Location ID: %s, Bucket ID: %s", this.projectId, this.locationId, this.storageName));
        String imageName = Utils.bytesToHash256(productImage).concat(".jpg");
        try {
            LOGGER.info(String.format("Uploading file: %s", imageName));
            Storage storage = StorageOptions.newBuilder().setProjectId(this.projectId).build().getService();
            BlobId blobId = BlobId.of(this.storageName, imageName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
            storage.create(blobInfo, productImage);
            LOGGER.info("Uploaded!");
        } catch (Exception e) {
            LOGGER.error(String.format("Error uploading file: %s, Error: %s", imageName, e.getMessage()));
        }
        return new Image(String.format("https://storage.googleapis.com/%s/%s", storageName, imageName));
    }

    public void deleteProductImageFromProductStorage(String imageId){
        String referenceImageId = imageId.replaceAll("gs://[a-z-]+/", "");
        Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
        LOGGER.info("Deleting image: " + referenceImageId);
        storage.delete(this.storageName, referenceImageId);
        LOGGER.info("Deleted!");
    }
}
