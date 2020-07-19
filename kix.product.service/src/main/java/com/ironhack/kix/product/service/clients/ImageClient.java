package com.ironhack.kix.product.service.clients;

import com.ironhack.kix.product.service.models.dto.GalleryDTO;
import com.ironhack.kix.product.service.models.dto.GalleryView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@FeignClient("kix-image-service")
public interface ImageClient {

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/images/{imageId}")
    public HttpEntity<byte[]> getImageWithImageId(@PathVariable(name = "imageId") Long imageId, @RequestParam(value = "scale", required = false) Double scale) throws IOException;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/galleries/{productId}")
    public GalleryView getGalleryByProductId(@PathVariable(name = "productId") String productId);

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.DELETE, value = "/galleries/{productId}")
    public void deleteAllImagesByProductId(@PathVariable(name = "productId") String productId);

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.DELETE, value = "/images/{imageId}")
    public void deleteImageByImageId(@PathVariable(name = "imageId") Long imageId);

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, value = "/gallery")
    public GalleryView createNewGallery(@RequestBody GalleryDTO gallery);

}
