package com.shop.repository;

import java.util.List;

public interface PermissionRepository {
    void save(List<Long> roleIds, Integer userId);

}
