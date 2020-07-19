package com.ironhack.kix.product.service.models.dto;

import java.util.List;

public class GalleryDTO {
    private String productId;
    private List<String> base64Images;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public List<String> getBase64Images() {
        return base64Images;
    }

    public void setBase64Images(List<String> base64Images) {
        this.base64Images = base64Images;
    }
}
