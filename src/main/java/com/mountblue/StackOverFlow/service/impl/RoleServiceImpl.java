package com.mountblue.StackOverFlow.service.impl;

import com.mountblue.StackOverFlow.model.Role;
import com.mountblue.StackOverFlow.repository.RoleRepository;
import com.mountblue.StackOverFlow.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleByName(String roleName) {
        return  roleRepository.findRoleByName(roleName);
    }
}
