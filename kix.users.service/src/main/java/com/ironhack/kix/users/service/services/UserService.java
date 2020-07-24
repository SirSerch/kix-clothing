package com.ironhack.kix.users.service.services;

import com.ironhack.kix.users.service.controllers.Api.UserApi;
import com.ironhack.kix.users.service.exceptions.UserNotFoundException;
import com.ironhack.kix.users.service.models.User;
import com.ironhack.kix.users.service.models.dto.UserDTO;
import com.ironhack.kix.users.service.repositories.UserRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserApi {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(UserDTO userDTO) {
        return userRepository.save(new User(userDTO));
    }

    @Override
    public void deleteUser(Long id) {
        User user = this.getUserById(id);
        userRepository.delete(user);
    }

    @Override
    public void updateUser(UserDTO userDTO, Long id) {
        User user = this.getUserById(id);
        user.setRole(userDTO.getRole());
        user.setName(userDTO.getName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPayments(userDTO.getPayments());
        user.setAddress(userDTO.getAddress());
        userRepository.save(user);
    }
}
