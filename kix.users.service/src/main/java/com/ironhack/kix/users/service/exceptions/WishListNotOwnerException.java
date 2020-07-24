package com.ironhack.kix.users.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "You are not the owner of this WishList!")
public class WishListNotOwnerException extends RuntimeException{
    public WishListNotOwnerException() {
        super("You are not the owner of this WishList!");
    }
}
