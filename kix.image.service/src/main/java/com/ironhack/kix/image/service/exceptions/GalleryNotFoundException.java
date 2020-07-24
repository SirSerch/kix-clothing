package com.ironhack.kix.image.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Could not find any Image Gallery with the Product ID provided")
public class GalleryNotFoundException extends RuntimeException{
    public GalleryNotFoundException() {
        super("Could not find any Image Gallery with the Product ID provided");
    }
}
