package com.ironhack.kix.image.service.controllers.api;

import com.ironhack.kix.image.service.models.dto.GalleryDTO;
import com.ironhack.kix.image.service.models.dto.GalleryView;
import org.springframework.http.HttpEntity;

import java.io.IOException;
import java.util.List;

public interface ImageApi {
    GalleryView createNewGallery(GalleryDTO base64Gallery);
    GalleryView getGalleryByProductId(String productId);
    HttpEntity<byte[]> getImageWithImageId(Long imageId, Double scale) throws IOException;
    void deleteAllImagesByProductId(String productId);
    void deleteImageByImageId(Long imageId);
}
