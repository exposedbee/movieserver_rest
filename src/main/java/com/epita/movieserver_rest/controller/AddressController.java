package com.epita.movieserver_rest.controller;

import com.epita.movieserver_rest.datamodel.Address;
import com.epita.movieserver_rest.service.interfaces.AddressService;
import com.epita.movieserver_rest.service.interfaces.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/address")
public class AddressController {

    AddressService addressService;
    ContactService contactService;

    @Autowired
    public AddressController(AddressService addressService, ContactService contactService){
        this.addressService = addressService;
        this.contactService = contactService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String handleContactAdd(@RequestBody Address address)throws SQLException {
        addressService.addAddress(address);
        contactService.updateContactAddress(address,4);
        return String.format("In add contacts");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getContactById(@RequestParam(name = "id")int id)throws SQLException {

        return String.format(String.valueOf(addressService.getAddressById(id).toString()));
    }
}
