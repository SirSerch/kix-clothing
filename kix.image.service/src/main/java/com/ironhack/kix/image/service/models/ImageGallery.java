package com.ironhack.kix.image.service.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class ImageGallery {
    @Id
    private String productId;
    @OneToMany(mappedBy = "gallery", cascade = CascadeType.ALL)
    private List<Image> gallery;

    public ImageGallery(String productId, List<Image> gallery) {
        this.productId = productId;
        this.gallery = gallery;
    }

    public ImageGallery(String productId) {
        this.productId = productId;
    }

    public ImageGallery() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public List<Image> getGallery() {
        return gallery;
    }

    public void setGallery(List<Image> gallery) {
        this.gallery = gallery;
    }
}
