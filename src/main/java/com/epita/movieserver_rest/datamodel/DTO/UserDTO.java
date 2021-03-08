package com.epita.movieserver_rest.datamodel.DTO;

import com.epita.movieserver_rest.datamodel.Contact;
import com.epita.movieserver_rest.datamodel.User;

import java.sql.Date;
import java.util.List;

public class UserDTO {

    private String username;
    private String password;
    private List<String> roles;
    private String name;
    private Date date;
    private String gender;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }


    public User toDataModel(){
        User result = new User();
        result.setUsername(this.username);
        result.setPassword(this.password);
        result.setContact(new Contact(this.name,this.date,this.gender,this.email));
        return result;
    }
}
