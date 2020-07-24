package com.ironhack.kix.image.service.models;

import javax.persistence.*;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long imageId;
    private String image;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private ImageGallery gallery;

    public Image() {
    }

    public Image(String url) {
        this.image = url;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String imageUrl) {
        this.image = imageUrl;
    }

    public ImageGallery getGallery() {
        return gallery;
    }

    public void setGallery(ImageGallery gallery) {
        this.gallery = gallery;
    }

}
