package com.epita.movieserver_rest.service.interfaces;

import com.epita.movieserver_rest.datamodel.Role;

public interface RoleService {
    void addRole(Role role);

    Role getRoleByName(String name);
}
