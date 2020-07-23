package com.ironhack.kix.product.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, reason = "The indexing service is not working at the moment, try again later")
public class IndexingServiceNotWorkingException extends RuntimeException{
    public IndexingServiceNotWorkingException() {
        super("The indexing service is not working at the moment, try again later");
    }
}
