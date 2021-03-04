package com.epita.movieserver_rest.service;

import com.epita.movieserver_rest.datamodel.User;
import com.epita.movieserver_rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserById(int _id) {
        return userRepository.getOne(_id);
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        return userRepository.findFirstByUsernameAndPassword(username,password);
    }

    @Override
    public User verifyUser(int _id, String username) {
        try {
            User user = userRepository.getOne(_id);
            if(user.getUsername().equals(username)) {
                System.out.println(user.toString());

                return user;
            }
        }
        catch (Exception e)
        {}
        return null;
    }
}
