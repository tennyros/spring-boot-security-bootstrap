package com.github.tennyros.bootstrap.repositories;

import com.github.tennyros.bootstrap.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
    @Query("SELECT r FROM Role r")
    Set<Role> getAllRoles();
}
