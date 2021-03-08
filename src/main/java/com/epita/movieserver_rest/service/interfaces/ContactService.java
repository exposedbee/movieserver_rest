package com.epita.movieserver_rest.service.interfaces;

import com.epita.movieserver_rest.datamodel.Address;
import com.epita.movieserver_rest.datamodel.Contact;
import com.epita.movieserver_rest.datamodel.DTO.ContactDTO;

public interface ContactService {
    void addContact(Contact contact);

    Contact getContactById(int _id);

    void updateContactAddress(Address address, int _id);

    Contact getContactByEmail(String email);

    void updateContactDetails(Contact contact);
}
