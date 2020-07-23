package com.ironhack.kix.product.service.clients.fallbacks;

import com.ironhack.kix.product.service.clients.ImageClient;
import com.ironhack.kix.product.service.exceptions.ImageServiceNotWorkingException;
import com.ironhack.kix.product.service.models.dto.GalleryDTO;
import com.ironhack.kix.product.service.models.dto.GalleryView;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Component
public class ImageClientFallback implements ImageClient {
    @Override
    public HttpEntity<byte[]> getImageWithImageId(Long imageId, Double scale) throws IOException {
        return null;
    }

    @Override
    public GalleryView getGalleryById(Long galleryId) {
        GalleryView gallery = new GalleryView();
        List<String> url = new LinkedList<>();
        url.add("https://storage.googleapis.com/kix-products-storage/placeholder.png");
        gallery.setImages(url);
        return gallery;
    }

    @Override
    public void deleteAllImagesByGalleryId(Long galleryId) {
        throw new ImageServiceNotWorkingException();
    }

    @Override
    public void deleteImageById(Long imageId) {
         throw new ImageServiceNotWorkingException();
    }

    @Override
    public GalleryView createNewGallery(GalleryDTO gallery) {
         throw new ImageServiceNotWorkingException();
    }
}

