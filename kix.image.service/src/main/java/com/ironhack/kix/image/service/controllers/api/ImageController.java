package com.ironhack.kix.image.service.controllers.api;

import com.ironhack.kix.image.service.models.dto.GalleryDTO;
import com.ironhack.kix.image.service.models.dto.GalleryView;
import com.ironhack.kix.image.service.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class ImageController implements ImageApi {

    @Autowired
    ImageService imageService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/images/{imageId}")
    public HttpEntity<byte[]> getImageWithImageId(@PathVariable(name = "imageId") Long imageId, @RequestParam(value = "scale", required = false) Double scale) throws IOException {
        return imageService.getImageWithImageId(imageId, scale);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/galleries/{productId}")
    public GalleryView getGalleryById(@PathVariable(name = "productId") Long galleryId) {
        return imageService.getGalleryByProductId(galleryId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.DELETE, value = "/galleries/{productId}")
    public void deleteAllImagesByGalleryId(@PathVariable(name = "productId") Long galleryId) {
        imageService.deleteAllImagesByGalleryId(galleryId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.DELETE, value = "/images/{imageId}")
    public void deleteImageById(@PathVariable(name = "imageId") Long imageId) {
        imageService.deleteImageByImageId(imageId);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, value = "/gallery")
    public GalleryView createNewGallery(@RequestBody GalleryDTO gallery) {
        return imageService.createNewGallery(gallery);
    }
}
