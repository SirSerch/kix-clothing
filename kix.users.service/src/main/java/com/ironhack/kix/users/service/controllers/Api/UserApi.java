package com.ironhack.kix.users.service.controllers.Api;

import com.ironhack.kix.users.service.models.User;

public interface UserApi {
    User getUserByEmail(String email);
}
