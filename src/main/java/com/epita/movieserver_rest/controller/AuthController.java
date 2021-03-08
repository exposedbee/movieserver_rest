package com.epita.movieserver_rest.controller;

import com.epita.movieserver_rest.errorHandling.ApiError;
import com.epita.movieserver_rest.request.UserLogin;
import com.epita.movieserver_rest.request.UserProfile;
import com.epita.movieserver_rest.security.JWTToken;
import com.epita.movieserver_rest.security.UserInfoFromJWT;
import com.epita.movieserver_rest.service.UserAuthServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserAuthServices userAuthServices;
    private static final Logger logger = LogManager.getLogger(AuthController.class);


    @Autowired
    public AuthController(UserAuthServices userAuthServices) {
        this.userAuthServices = userAuthServices;
    }

    @PostMapping("/hello")
    public String helloWorld(@RequestBody String userLogin){
        logger.debug(userLogin);

        return String.format("Hello World");
    }

    @PostMapping("/")
    public ResponseEntity hello(@RequestBody UserLogin userLogin) {
        logger.info("token request");

        try {
            //try to generate a token
            return new ResponseEntity(userAuthServices.validateUser(userLogin), HttpStatus.OK);
        } catch (Exception e) {
            //if token generation fails
            return new ResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "username and password invalid", e), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/profile")
    public ResponseEntity hello(@RequestHeader("token") String token) {
        logger.info("User Profile");
        UserInfoFromJWT userInfoFromJWT = JWTToken.decodeJWT(token);
        //Get User Details
        if(userInfoFromJWT!=null) {
            UserProfile userProfile = userAuthServices.getUserProfile(userInfoFromJWT.getId());
            if (userProfile != null)
                return new ResponseEntity(userProfile, HttpStatus.OK);
        }
        //let the user know that the token is invalid
        return new ResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "Token not valid"), HttpStatus.BAD_REQUEST);
        //the control should not even reach this stage..
    }

}
