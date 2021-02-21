package com.shop.mapper;

import com.shop.entity.User;
import com.shop.model.request.user.UserSaveRequest;
import com.shop.utils.ReflectionUtils;

public class UserMapper {
    public static User mapToEntity(UserSaveRequest userSaveRequest){
        User user = new User();
        try {
            ReflectionUtils.copy(userSaveRequest, user);
            return user;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

    }
}
