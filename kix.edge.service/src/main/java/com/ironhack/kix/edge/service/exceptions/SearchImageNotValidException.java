package com.ironhack.kix.edge.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The image in the search petition is not in base64 format")
public class SearchImageNotValidException extends RuntimeException {
    public SearchImageNotValidException() {
        super("The image in the search petition is not in base64 format");
    }
}
