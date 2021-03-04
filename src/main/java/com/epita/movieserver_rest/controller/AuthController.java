package com.epita.movieserver_rest.controller;

import com.epita.movieserver_rest.datamodel.User;
import com.epita.movieserver_rest.request.UserLogin;
import com.epita.movieserver_rest.request.UserProfile;
import com.epita.movieserver_rest.security.JWTToken;
import com.epita.movieserver_rest.security.UserInfoFromJWT;
import com.epita.movieserver_rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;
    private JWTToken jwtToken;

    @Autowired
    public AuthController(UserService userService){
        this.userService = userService;
        this.jwtToken = new JWTToken();
    }

    @GetMapping("/")
    public String hello(@RequestBody UserLogin userLogin){
        User user=userService.getUserByUsernameAndPassword(userLogin.getUsername(),userLogin.getPassword());
//        JWTToken.createJWT(String.valueOf(user.getId()),"server",user.getUsername(),9);
        return String.format(JWTToken.createJWT(String.valueOf(user.getId()),"server",user.getUsername(),0));
    }

    @GetMapping("/profile")
    public String hello(@RequestHeader("token")String token){

        UserInfoFromJWT userInfoFromJWT=JWTToken.decodeJWT(token);
        User user= userService.verifyUser(userInfoFromJWT.getId(),userInfoFromJWT.getUsername());
        if(user!=null)
        {   UserProfile userProfile = new UserProfile();
            userProfile.fromDataModel(user);
            return  userProfile.toString();
        }
        return String.format("failed");
    }

}
