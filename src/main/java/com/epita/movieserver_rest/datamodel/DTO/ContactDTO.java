package com.epita.movieserver_rest.datamodel.DTO;

import com.epita.movieserver_rest.datamodel.Contact;

import java.sql.Date;

public class ContactDTO {
    private String name;
    private Date birthDate;
    private String gender;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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

    public Contact toDataModel(){
        Contact contact = new Contact();
        contact.setEmail(this.email);
        contact.setGender(this.gender);
        contact.setBirthdate((java.sql.Date) this.birthDate);
        contact.setName(this.name);
        return contact;
    }
}
