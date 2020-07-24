package com.ironhack.kix.product.service.clients;

import com.ironhack.kix.product.service.clients.fallbacks.ImageClientFallback;
import com.ironhack.kix.product.service.models.dto.GalleryDTO;
import com.ironhack.kix.product.service.models.dto.GalleryView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@FeignClient(value = "kix-image-service")
public interface ImageClient {

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/images/{imageId}")
    HttpEntity<byte[]> getImageWithImageId(@PathVariable(name = "imageId") Long imageId, @RequestParam(value = "scale", required = false) Double scale) throws IOException;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/galleries/{productId}")
    GalleryView getGalleryById(@PathVariable(name = "productId") Long galleryId);

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.DELETE, value = "/galleries/{productId}")
    void deleteAllImagesByGalleryId(@PathVariable(name = "productId") Long galleryId);

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.DELETE, value = "/images/{imageId}")
    void deleteImageById(@PathVariable(name = "imageId") Long imageId);

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, value = "/gallery")
    GalleryView createNewGallery(@RequestBody GalleryDTO gallery);

}
