package com.epita.movieserver_rest.repository;

import com.epita.movieserver_rest.datamodel.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findByName(String name);
}
