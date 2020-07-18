package com.ironhack.kix.users.service.services;

import com.ironhack.kix.users.service.controllers.Api.UserApi;
import com.ironhack.kix.users.service.exceptions.UserNotFoundException;
import com.ironhack.kix.users.service.models.User;
import com.ironhack.kix.users.service.repositories.UserRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserApi {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }
}
