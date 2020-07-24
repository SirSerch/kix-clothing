package com.ironhack.kix.image.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "One of the base64 images you have provided is not valid, please correct it")
public class InvalidBase64ImageException extends RuntimeException {
    public InvalidBase64ImageException() {
        super("One of the base64 images you have provided is not valid, please correct it");
    }
}
