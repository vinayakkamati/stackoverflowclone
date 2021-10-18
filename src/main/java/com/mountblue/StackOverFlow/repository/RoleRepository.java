package com.mountblue.StackOverFlow.repository;

import com.mountblue.StackOverFlow.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("SELECT r FROM Role r WHERE r.roleName LIKE %?1%")
    Role findRoleByName(String roleName);
}
