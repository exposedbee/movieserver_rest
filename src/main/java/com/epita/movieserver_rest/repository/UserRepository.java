package com.epita.movieserver_rest.repository;

import com.epita.movieserver_rest.datamodel.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findFirstByUsernameAndPassword(String username, String password);
}
