package com.shop.repository.impl;

import com.shop.content.anotation.Repository;
import com.shop.listerner.ApplicationListener;
import com.shop.repository.PermissionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PermissionRepositoryImpl implements PermissionRepository {
    @Override
    public void save(List<Long> roleIds, Integer userId) {
        String sql = "INSERT INTO permission(user_id, role_id) VALUES %s";
        StringBuilder condition = new StringBuilder();
        for (int i=0; i<roleIds.size();i++){
            condition.append("(").append(userId).append(",").append(roleIds.get(i)).append("),");
        }

        sql = String.format(sql, condition.deleteCharAt(condition.length()-1));
        try (Connection connection = ApplicationListener.connectionManagement.connection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
