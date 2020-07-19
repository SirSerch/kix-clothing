package com.ironhack.kix.image.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Could not find any image with the ID provided")
public class ImageNotFoundException extends RuntimeException{
    public ImageNotFoundException() {
        super("Could not find any image with the ID provided");
    }
}
