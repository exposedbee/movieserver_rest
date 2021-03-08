package com.epita.movieserver_rest.controller;

import com.epita.movieserver_rest.datamodel.Address;
import com.epita.movieserver_rest.datamodel.DTO.AddressDTO;
import com.epita.movieserver_rest.datamodel.DTO.ContactDTO;
import com.epita.movieserver_rest.datamodel.DTO.UserDTO;
import com.epita.movieserver_rest.datamodel.Role;
import com.epita.movieserver_rest.datamodel.User;
import com.epita.movieserver_rest.errorHandling.ApiError;
import com.epita.movieserver_rest.request.PasswordChange;
import com.epita.movieserver_rest.request.UserLogin;
import com.epita.movieserver_rest.security.JWTToken;
import com.epita.movieserver_rest.security.UserInfoFromJWT;
import com.epita.movieserver_rest.service.interfaces.AddressService;
import com.epita.movieserver_rest.service.interfaces.ContactService;
import com.epita.movieserver_rest.service.interfaces.RoleService;
import com.epita.movieserver_rest.service.interfaces.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("user")
public class UserController {

    private UserService userService;
    private RoleService roleService;
    private ContactService contactService;
    private AddressService addressService;
    private static final Logger logger = LogManager.getLogger(AuthController.class);


    @Autowired
    public UserController(UserService userService, RoleService roleService, ContactService contactService, AddressService addressService) {
        this.userService = userService;
        this.roleService = roleService;
        this.addressService = addressService;
        this.contactService = contactService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity handleNewUserRegister(@RequestBody UserDTO userDTO) {
        User user = userDTO.toDataModel();
        if (userService.getUserByUsername(user.getUsername()) != null)
            return new ResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "Username is taken"), HttpStatus.BAD_REQUEST);

        if (contactService.getContactByEmail(userDTO.getEmail()) != null)
            return new ResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "Account with email already exists"), HttpStatus.BAD_REQUEST);


        Set<Role> userRoles = new HashSet<>();
        try {
            for (String name : userDTO.getRoles()
            ) {
                Role role = roleService.getRoleByName(name.toUpperCase());
                userRoles.add(role);
            }
        } catch (Exception e) {
            return new ResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "Roles not valid", e), HttpStatus.BAD_REQUEST);
        }

        try {
            user.setRoles(userRoles);
            contactService.addContact(user.getContact());
            userService.addUser(user);
            return new ResponseEntity("User Added", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "Incorrect data received", e), HttpStatus.BAD_REQUEST);

        }
    }

    @RequestMapping(value = "/update/username", method = RequestMethod.POST)
    public ResponseEntity handleUsernameChange(@RequestHeader("token") String token, @RequestBody UserLogin userLogin) {

        UserInfoFromJWT userInfoFromJWT = JWTToken.decodeJWT(token);
        if (userInfoFromJWT == null)
            return new ResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "invalid token"), HttpStatus.BAD_REQUEST);

        userService.updateUsername(userInfoFromJWT.getId(), userLogin.getUsername());

        return new ResponseEntity("Success!", HttpStatus.OK);
    }

    @RequestMapping(value = "/update/password", method = RequestMethod.POST)
    public ResponseEntity handlePasswordChange(@RequestHeader("token") String token, @RequestBody PasswordChange passwordChange) {
        logger.info("updatePasswordChange");
        UserInfoFromJWT userInfoFromJWT = JWTToken.decodeJWT(token);
        if (userInfoFromJWT == null)
            return new ResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "invalid token"), HttpStatus.BAD_REQUEST);

        if (userService.updatePassword(userInfoFromJWT.getId(), passwordChange.getOldPassword(), passwordChange.getNewPassword()))
            return new ResponseEntity("Success!", HttpStatus.OK);

        return new ResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "Old Password is wrong"), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/update/contact", method = RequestMethod.POST)
    public ResponseEntity handleContactChange(@RequestHeader("token") String token, @RequestBody ContactDTO contactDTO) {
        logger.info("ContactUpdate");
        UserInfoFromJWT userInfoFromJWT = JWTToken.decodeJWT(token);
        if (userInfoFromJWT == null)
            return new ResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "invalid token"), HttpStatus.BAD_REQUEST);

        if (userService.updateContact(userInfoFromJWT.getId(),contactDTO))
            return new ResponseEntity("Success!", HttpStatus.OK);

        return new ResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "Unexpected Error"), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/update/address", method = RequestMethod.POST)
    public ResponseEntity handleAddressChange(@RequestHeader("token") String token, @RequestBody AddressDTO addressDTO){
        logger.info("AddressUpdate");
        UserInfoFromJWT userInfoFromJWT = JWTToken.decodeJWT(token);
        if (userInfoFromJWT == null)
            return new ResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "invalid token"), HttpStatus.BAD_REQUEST);
        User user=userService.getUserById(userInfoFromJWT.getId());
        Address newAddress = addressDTO.toDataModel();
        //check if address already exists
        if(user.getContact().getAddress()==null) {
            //create a new address
            addressService.addAddress(newAddress);
            user.getContact().setAddress(newAddress);
            contactService.updateContactDetails(user.getContact());
        }
        else{
            //update the existing address
            Address address=user.getContact().getAddress();
            addressService.updateAddress(address,newAddress);
        }
        return new ResponseEntity("UPDATED", HttpStatus.OK);

    }

}
