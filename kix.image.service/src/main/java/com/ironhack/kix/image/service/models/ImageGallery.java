package com.ironhack.kix.image.service.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class ImageGallery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long galleryId;
    @OneToMany(mappedBy = "gallery", cascade = CascadeType.ALL)
    private List<Image> gallery;

    public ImageGallery(List<Image> gallery) {
        this.gallery = gallery;
    }

    public ImageGallery() {
    }

    public Long getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(Long galleryId) {
        this.galleryId = galleryId;
    }

    public List<Image> getGallery() {
        return gallery;
    }

    public void setGallery(List<Image> gallery) {
        this.gallery = gallery;
    }
}
