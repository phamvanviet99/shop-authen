package com.shop.service.impl;

import com.shop.content.anotation.Autowire;
import com.shop.content.anotation.Service;
import com.shop.entity.User;
import com.shop.mapper.UserMapper;
import com.shop.model.request.user.AuthRequest;
import com.shop.model.request.user.UserSaveRequest;
import com.shop.repository.PermissionRepository;
import com.shop.repository.RoleRepository;
import com.shop.repository.UserRepository;
import com.shop.service.UserService;
import com.shop.utils.PasswordHasher;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowire
    private final UserRepository userRepository;
    @Autowire
    private final PermissionRepository permissionRepository;

    public UserServiceImpl(UserRepository userRepository, PermissionRepository permissionRepository) {
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
    }

//    private volatile static UserServiceImpl userService;
//
//    public static UserServiceImpl getInstance(UserRepository userRepository, PermissionRepository permissionRepository){
//         if (userService == null){
//             synchronized (UserServiceImpl.class){
//                 if (userService == null){
//                    userService = new UserServiceImpl(userRepository, permissionRepository);
//                 }
//             }
//         }
//         return userService;
//    }

    @Override
    public void save(UserSaveRequest userSaveRequest) {
        User user = UserMapper.mapToEntity(userSaveRequest);
        userRepository.save(user);
        permissionRepository.save(userSaveRequest.getIds(), user.getId());
    }

    @Override
    public Optional<User> findByUserNameAndPassword(AuthRequest authRequest) {
        try {
            String password = PasswordHasher.hash(authRequest.getPassword());
            Optional<User> user = userRepository.findUserByUserNameAndPassword(authRequest.getUserName(), password);

            user.orElseThrow(()->new RuntimeException("User not found"));
            return user;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
