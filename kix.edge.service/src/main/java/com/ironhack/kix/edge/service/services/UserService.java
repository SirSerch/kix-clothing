package com.ironhack.kix.edge.service.services;

import com.ironhack.kix.edge.service.models.dto.UserDTO;
import com.ironhack.kix.edge.service.models.views.UserView;
import com.ironhack.kix.edge.service.repositories.clients.UserClient;
import com.ironhack.kix.edge.service.security.SecurityUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired UserClient userClient;

    public UserView createNewUser(UserDTO user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userClient.updateUser(user, productId);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LOGGER.info("Login by: " + email);
        UserView user = userClient.getUserByEmail(email);

        if (user == null)
            throw new UsernameNotFoundException("Invalid username/password combination.");

        return new SecurityUser(user);
    }
}
