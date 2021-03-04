package com.epita.movieserver_rest.service;

import com.epita.movieserver_rest.datamodel.Role;
import com.epita.movieserver_rest.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService{

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImp(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role getRoleByName(String name) {
        List<Role> res= roleRepository.findByName(name);
        return res.get(0);
    }
}
