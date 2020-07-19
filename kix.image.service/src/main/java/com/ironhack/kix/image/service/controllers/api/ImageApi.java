package com.ironhack.kix.image.service.controllers.api;

import com.ironhack.kix.image.service.models.dto.GalleryDTO;
import org.springframework.http.HttpEntity;

import java.io.IOException;
import java.util.List;

public interface ImageApi {
    List<String> addNewImageWithProductId(GalleryDTO base64Gallery);
    HttpEntity<byte[]> getImageWithImageId(Long imageId, Double scale) throws IOException;
    List<String> getAllUriImagesWithProductId(String productId);
    void deleteAllImagesByProductId(String productId);
    void deleteImageByImageId(Long imageId);
}
