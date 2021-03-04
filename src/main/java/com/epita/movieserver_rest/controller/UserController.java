package com.epita.movieserver_rest.controller;

import com.epita.movieserver_rest.datamodel.DTO.UserDTO;
import com.epita.movieserver_rest.datamodel.Role;
import com.epita.movieserver_rest.datamodel.User;
import com.epita.movieserver_rest.service.ContactService;
import com.epita.movieserver_rest.service.RoleService;
import com.epita.movieserver_rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
@RequestMapping("user")
public class UserController {

    private UserService userService;
    private RoleService roleService;
    private ContactService contactService;

    @Autowired
    public UserController(UserService userService, RoleService roleService, ContactService contactService){
        this.userService = userService;
        this.roleService = roleService;
        this.contactService = contactService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String handleContactAdd(@RequestBody UserDTO userDTO){
        User user=userDTO.toDataModel();
        Set<Role> userRoles = new HashSet<>();
        for (String name:userDTO.getRoles()
             ) {
            Role role=roleService.getRoleByName(name.toUpperCase());
            userRoles.add(role);
        }

        user.setRoles(userRoles);
        contactService.addContact(user.getContact());
        userService.addUser(user);
        return String.format("User Added");
    }
}
