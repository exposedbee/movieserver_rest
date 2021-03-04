package com.epita.movieserver_rest.request;

import com.epita.movieserver_rest.datamodel.Contact;
import com.epita.movieserver_rest.datamodel.User;

import java.util.Date;

public class UserProfile {
    private String username;
    private String email;
    private String name;
    private Date birthday;
    private String gender;

    public UserProfile(){ }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void fromDataModel(User user){
        this.username= user.getUsername();
        Contact contact = user.getContact();
        this.email=contact.getEmail();
        this.gender=contact.getGender();
        this.birthday=contact.getBirthdate();
        this.name=contact.getName();
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                '}';
    }
}