package com.ironhack.kix.users.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User not found by id or email")
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("User not found by id or email");
    }
}
