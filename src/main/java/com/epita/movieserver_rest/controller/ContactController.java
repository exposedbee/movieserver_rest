package com.epita.movieserver_rest.controller;

import com.epita.movieserver_rest.datamodel.Contact;
import com.epita.movieserver_rest.service.interfaces.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/contact")
public class ContactController {

    ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService){
        this.contactService = contactService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String handleContactAdd(@RequestBody Contact contact)throws SQLException {
        contactService.addContact(contact);
        return String.format("In add contacts");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getContactById(@RequestParam(name = "id")int id)throws SQLException {

        return String.format(String.valueOf(contactService.getContactById(id).toString()));
    }
}
