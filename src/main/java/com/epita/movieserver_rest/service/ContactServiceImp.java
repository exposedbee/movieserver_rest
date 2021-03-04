package com.epita.movieserver_rest.service;

import com.epita.movieserver_rest.datamodel.Address;
import com.epita.movieserver_rest.datamodel.Contact;
import com.epita.movieserver_rest.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImp implements ContactService{

    ContactRepository contactRepository;

    @Autowired
    public ContactServiceImp(ContactRepository contactRepository){
        this.contactRepository= contactRepository;
    }

    @Override
    public void addContact(Contact contact) {
        contactRepository.save(contact);
    }

    @Override
    public Contact getContactById(int _id) {
        return contactRepository.getOne(_id);
    }

    @Override
    public void updateContactAddress(Address address, int _id) {
        Contact contact= getContactById(_id);
        contact.setAddress(address);
        contactRepository.flush();
    }
}
