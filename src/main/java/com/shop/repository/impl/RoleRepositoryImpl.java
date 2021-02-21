package com.shop.repository.impl;

import com.shop.content.anotation.Repository;
import com.shop.entity.Role;
import com.shop.repository.RoleRepository;

@Repository
public class RoleRepositoryImpl extends BaseRepository<Role, Long> implements RoleRepository {
}
