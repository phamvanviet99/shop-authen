package com.shop.repository;

import com.shop.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User , Long >{
    Optional<User> findUserByUserNameAndPassword(String userName, String password);

}
