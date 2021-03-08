package com.epita.movieserver_rest.service.interfaces;

import com.epita.movieserver_rest.datamodel.DTO.ContactDTO;
import com.epita.movieserver_rest.datamodel.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserService {
    void addUser(User user);

    User getUserById(int _id);

    User getUserByUsernameAndPassword(String username, String password);

    User getUserByUsername(String username);

    boolean updateUsername(int _id, String username);

    boolean updatePassword(int _id, String oldPassword, String newPassword);

    boolean updateContact(int _id, ContactDTO contactDTO);
}
