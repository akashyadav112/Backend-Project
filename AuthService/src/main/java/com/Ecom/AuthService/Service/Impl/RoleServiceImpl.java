package com.Ecom.AuthService.Service.Impl;

import com.Ecom.AuthService.Models.Role;
import com.Ecom.AuthService.Repository.RoleRepository;
import com.Ecom.AuthService.Service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role createRole(String name) {
        Role role = new Role();
        role.setRoleName(name);
        return roleRepository.save(role);
    }
}
