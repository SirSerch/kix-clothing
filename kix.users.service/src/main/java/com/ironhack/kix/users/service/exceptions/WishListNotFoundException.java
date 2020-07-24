package com.ironhack.kix.users.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.OK, reason = "Wish list not found!")
public class WishListNotFoundException extends RuntimeException{
    public WishListNotFoundException() {
        super("Wish list not found!");
    }
}
