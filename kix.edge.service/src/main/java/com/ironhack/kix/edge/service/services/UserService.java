package com.ironhack.kix.edge.service.services;

import com.ironhack.kix.edge.service.models.dto.UserDTO;
import com.ironhack.kix.edge.service.models.views.UserView;
import com.ironhack.kix.edge.service.repositories.clients.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired UserClient userClient;

    public UserView createNewUser(UserDTO user) {
        return userClient.createUser(user);
    }

    public List<UserView> getAllUsers() {
        return userClient.getAllUsers();
    }

    public UserView getUserById(Long id) {
        return userClient.getUserById(id);
    }

    public void deleteUserById(Long id) {
        userClient.deleteUser(id);
    }

    protected void updateUser(UserDTO user, Long productId){
        userClient.updateUser(user, productId);
    }

}
