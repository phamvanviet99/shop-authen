package com.shop.service;

import com.shop.entity.User;
import com.shop.model.request.user.AuthRequest;
import com.shop.model.request.user.UserSaveRequest;

import java.util.Optional;

public interface UserService {
    void save(UserSaveRequest userSaveRequest);

    Optional<User> findByUserNameAndPassword(AuthRequest authRequest);
}
