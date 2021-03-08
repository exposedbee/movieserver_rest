package com.epita.movieserver_rest.datamodel.DTO;

import com.epita.movieserver_rest.datamodel.Address;

public class AddressDTO {

    private String country;
    private String area;
    private String street;
    private String number;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Address toDataModel(){
        Address address= new Address();
        address.setArea(this.area);
        address.setCountry(this.country);
        address.setNumber(this.number);
        address.setStreet(this.street);
        return address;
    }

    public void fromDataModel(Address address) {
        this.number=address.getNumber();
        this.country=address.getCountry();
        this.area=address.getArea();
        this.street=address.getStreet();
    }
}
