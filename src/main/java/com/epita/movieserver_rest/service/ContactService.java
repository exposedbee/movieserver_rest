package com.epita.movieserver_rest.service;

import com.epita.movieserver_rest.datamodel.Address;
import com.epita.movieserver_rest.datamodel.Contact;

import java.awt.*;

public interface ContactService {
    void addContact(Contact contact);
    Contact getContactById(int _id);
    void updateContactAddress(Address address, int _id);
}
