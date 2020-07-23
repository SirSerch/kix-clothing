package com.ironhack.kix.product.service.models.dto;

import com.ironhack.kix.product.service.models.Product;

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

    public GalleryView(Product product){
        this.galleryId = product.getProductGallery();
    }

    @Override
    public String toString() {
        return "GalleryView{" +
                "galleryId=" + galleryId +
                ", images=" + images.toString() +
                '}';
    }
}
