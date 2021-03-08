package com.epita.movieserver_rest.repository;

import com.epita.movieserver_rest.datamodel.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    Contact findFirstByEmail(String email);
}
