package com.shop.repository.impl;

import com.shop.content.anotation.Repository;
import com.shop.entity.User;
import com.shop.listerner.ApplicationListener;
import com.shop.repository.UserRepository;
import com.shop.utils.ReflectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class UserRepositoryImpl  extends BaseRepository<User , Long> implements UserRepository {

    @Override
    public Optional<User> findUserByUserNameAndPassword(String userName, String password) {
        String sql = "SELECT * FROM user WHERE user_name = ? AND password = ?";
        try(Connection connection = ApplicationListener.connectionManagement.connection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            User user = null;
            while (rs.next()){
                user = ReflectionUtils.convertToEntity(rs, User.class);
            }
            return Optional.of(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
