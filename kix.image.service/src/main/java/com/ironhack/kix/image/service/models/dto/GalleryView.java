package com.ironhack.kix.image.service.models.dto;

import java.util.List;

public class GalleryView {
    String productId;
    List<String> images;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public GalleryView(String productId, List<String> images) {
        this.productId = productId;
        this.images = images;
    }

    public GalleryView() {
    }
}
