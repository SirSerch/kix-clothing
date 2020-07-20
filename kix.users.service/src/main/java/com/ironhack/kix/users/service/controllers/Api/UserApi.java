package com.ironhack.kix.users.service.controllers.Api;

import com.ironhack.kix.users.service.models.User;
import com.ironhack.kix.users.service.models.dto.UserDTO;

import java.util.List;

public interface UserApi {
    User getUserByEmail(String email);
    User getUserById(Long id);
    List<User> getAllUsers();
    User createUser(UserDTO userDTO);
    void deleteUser(Long id);
    void updateUser(UserDTO userDTO, Long id);
}
