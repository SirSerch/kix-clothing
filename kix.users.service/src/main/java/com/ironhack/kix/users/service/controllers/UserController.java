package com.ironhack.kix.users.service.controllers;

import com.ironhack.kix.users.service.controllers.Api.UserApi;
import com.ironhack.kix.users.service.models.User;
import com.ironhack.kix.users.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserApi {

    @Autowired
    UserService userService;

    @Override
    public User getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }
}
