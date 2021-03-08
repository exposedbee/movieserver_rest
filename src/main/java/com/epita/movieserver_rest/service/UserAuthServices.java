package com.epita.movieserver_rest.service;

import com.epita.movieserver_rest.datamodel.User;
import com.epita.movieserver_rest.errorHandling.ApiError;
import com.epita.movieserver_rest.request.UserLogin;
import com.epita.movieserver_rest.request.UserProfile;
import com.epita.movieserver_rest.security.JWTToken;
import com.epita.movieserver_rest.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserAuthServices {

    private UserService userService;

    @Autowired
    public UserAuthServices(UserService userService){
        this.userService = userService;
    }

    public String validateUser(UserLogin userLogin)throws Exception{
        User user = userService.getUserByUsernameAndPassword(userLogin.getUsername(), userLogin.getPassword());
        try {
            return JWTToken.createJWT(String.valueOf(user.getId()), "server", user.getContact().getName(), 0);
        } catch (Exception e) {
            throw new Exception("Invalid Input");
        }
    }


    public UserProfile getUserProfile(int _id){
        try {
            //get user information to witch the token belongs to
            User user = userService.getUserById(_id);
            //get user profile if user is found and validated
            UserProfile userProfile = new UserProfile();
            userProfile.fromDataModel(user);
            return userProfile;
        } catch (Exception e) {
            //let the user know that the token is invalid
            return null;
        }
    }
}
