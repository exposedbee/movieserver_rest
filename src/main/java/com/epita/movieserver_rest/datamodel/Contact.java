package com.epita.movieserver_rest.datamodel;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.xml.crypto.Data;
import java.sql.Date;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    @Column(name = "name", nullable = false)
    private String name;
    @NotEmpty
    private java.sql.Date birthdate;
    @NotNull
    private String gender;
    @NotNull
    private String email;

    @OneToOne
    private Address address;

    public Contact(){}

    public Contact(String name, Date birthdate, String gender, String email) {
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthdate=" + birthdate +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                '}';
    }

}
