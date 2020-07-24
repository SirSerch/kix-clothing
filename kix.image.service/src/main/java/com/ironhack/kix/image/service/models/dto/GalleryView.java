package com.ironhack.kix.image.service.models.dto;

import java.util.List;

public class GalleryView {
    Long galleryId;
    List<String> images;

    public Long getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(Long galleryId) {
        this.galleryId = galleryId;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public GalleryView(Long galleryId, List<String> images) {
        this.galleryId = galleryId;
        this.images = images;
    }

    public GalleryView() {
    }
}
