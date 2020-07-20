package com.ironhack.kix.users.service.controllers;

import com.ironhack.kix.users.service.controllers.Api.UserApi;
import com.ironhack.kix.users.service.models.User;
import com.ironhack.kix.users.service.models.dto.UserDTO;
import com.ironhack.kix.users.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController implements UserApi {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/users/find")
    public User getUserByEmail(@RequestParam(name = "email", required = true) String email) {
        return userService.getUserByEmail(email);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}")
    public User getUserById(@PathVariable("userId") Long userId) {
        return userService.getUserById(userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public User createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users/{userId}")
    public void updateUser(@RequestBody UserDTO userDTO, @PathVariable("userId") Long userId) {
        userService.updateUser(userDTO, userId);
    }
}
