package com.ironhack.kix.image.service.models.dto;

import java.util.List;

public class GalleryDTO {
    private List<String> base64Images;

    public List<String> getBase64Images() {
        return base64Images;
    }

    public void setBase64Images(List<String> base64Images) {
        this.base64Images = base64Images;
    }
}
