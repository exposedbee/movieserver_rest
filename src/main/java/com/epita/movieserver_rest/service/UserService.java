package com.epita.movieserver_rest.service;

import com.epita.movieserver_rest.datamodel.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserService {
    void addUser(User user);
    User getUserById(int _id);
    User getUserByUsernameAndPassword(String username,String password);
    User verifyUser(int _id, String username);
}
