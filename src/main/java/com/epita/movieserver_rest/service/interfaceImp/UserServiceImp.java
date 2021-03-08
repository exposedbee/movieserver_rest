package com.epita.movieserver_rest.service.interfaceImp;

import com.epita.movieserver_rest.datamodel.Contact;
import com.epita.movieserver_rest.datamodel.DTO.ContactDTO;
import com.epita.movieserver_rest.datamodel.User;
import com.epita.movieserver_rest.repository.UserRepository;
import com.epita.movieserver_rest.service.interfaces.ContactService;
import com.epita.movieserver_rest.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;
    private ContactService contactService;

    @Autowired
    public UserServiceImp(UserRepository userRepository, ContactService contactService) {
        this.userRepository = userRepository;
        this.contactService = contactService;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserById(int _id) {
        return userRepository.getOne(_id);
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        return userRepository.findFirstByUsernameAndPassword(username, password);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findFirstByUsername(username);
    }

    @Override
    public boolean updateUsername(int _id, String username) {
        User result = userRepository.getOne(_id);
        result.setUsername(username);
        userRepository.flush();
        return true;
    }

    @Override
    public boolean updatePassword(int _id, String oldPassword, String newPassword) {
        User user = userRepository.getOne(_id);
        if (user.getPassword().equals(oldPassword)) {
            user.setPassword(newPassword);
            userRepository.flush();
            return true;
        }
        return false;
    }

    @Override
    public boolean updateContact(int _id, ContactDTO contactDTO) {
        User user = userRepository.getOne(_id);
        Contact contact = user.getContact();
        contact.setName(contactDTO.getName());
        contact.setBirthdate(contactDTO.getBirthDate());
        contact.setGender(contactDTO.getGender());
        contact.setEmail(contactDTO.getEmail());
        contactService.updateContactDetails(contact);
        return true;
    }

}
