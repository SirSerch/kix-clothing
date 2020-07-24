package com.ironhack.kix.users.service.controllers;

import com.ironhack.kix.users.service.controllers.Api.UserApi;
import com.ironhack.kix.users.service.models.User;
import com.ironhack.kix.users.service.models.dto.UserDTO;
import com.ironhack.kix.users.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController implements UserApi {

    @Autowired
    UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/users/find")
    public User getUserByEmail(@RequestParam(name = "email", required = true) String email) {
        return userService.getUserByEmail(email);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}")
    public User getUserById(@PathVariable("userId") Long userId) {
        return userService.getUserById(userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public User createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.PUT, value = "/users/{userId}")
    public void updateUser(@RequestBody UserDTO userDTO, @PathVariable("userId") Long userId) {
        userService.updateUser(userDTO, userId);
    }
}
