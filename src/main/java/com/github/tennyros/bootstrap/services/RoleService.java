package com.github.tennyros.bootstrap.services;

import com.github.tennyros.bootstrap.models.Role;

import java.util.Set;

public interface RoleService {
    void addRole(Role role);
    Role getRoleByName(String roleName);
    Set<Role> getAllRoles();
}
