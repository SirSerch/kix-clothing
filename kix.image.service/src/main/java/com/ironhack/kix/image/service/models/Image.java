package com.ironhack.kix.image.service.models;

import com.ironhack.kix.image.service.exceptions.InvalidBase64ImageException;
import com.sun.jersey.core.util.Base64;

import javax.persistence.*;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long imageId;
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private ImageGallery gallery;

    public Image() {
    }

    public Image(String base64Image) {
        if(Base64.isBase64(base64Image) && ! base64Image.isEmpty())
            this.image = Base64.decode(base64Image);
        else throw new InvalidBase64ImageException();
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public ImageGallery getGallery() {
        return gallery;
    }

    public void setGallery(ImageGallery gallery) {
        this.gallery = gallery;
    }

    public String getBase64Image(){
        return new String(Base64.encode(this.image));
    }
}
