package com.ironhack.kix.product.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No product with the specified ID was found")
public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException() {
        super("No product with the specified ID was found");
    }
}
