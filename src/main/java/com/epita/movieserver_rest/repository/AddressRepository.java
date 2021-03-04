package com.epita.movieserver_rest.repository;

import com.epita.movieserver_rest.datamodel.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findByOrderByIdDesc();
}
