package com.ironhack.kix.product.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, reason = "The image service is not working currently, please, try later")
public class ImageServiceNotWorkingException extends RuntimeException{
    public ImageServiceNotWorkingException() {
        super("The image service is not working currently, please, try later");
    }
}
