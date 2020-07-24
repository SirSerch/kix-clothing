package com.ironhack.kix.image.service.controllers.api;

import com.ironhack.kix.image.service.models.dto.GalleryDTO;
import com.ironhack.kix.image.service.models.dto.GalleryView;
import org.springframework.http.HttpEntity;

import java.io.IOException;

public interface ImageApi {
    GalleryView createNewGallery(GalleryDTO base64Gallery);
    GalleryView getGalleryById(Long galleryId);
    HttpEntity<byte[]> getImageWithImageId(Long imageId, Double scale) throws IOException;
    void deleteAllImagesByGalleryId(Long galleryId);
    void deleteImageById(Long imageId);
}
