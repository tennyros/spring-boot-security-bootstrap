package com.github.tennyros.bootstrap.services.impl;

import com.github.tennyros.bootstrap.models.Role;
import com.github.tennyros.bootstrap.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.tennyros.bootstrap.repositories.RoleRepository;

import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleByName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Role> getAllRoles() {
        return roleRepository.getAllRoles();
    }
}
