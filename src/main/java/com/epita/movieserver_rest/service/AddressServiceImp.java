package com.epita.movieserver_rest.service;

import com.epita.movieserver_rest.datamodel.Address;
import com.epita.movieserver_rest.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImp implements AddressService {

    AddressRepository addressRepository;


    @Autowired
    public AddressServiceImp(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    @Override
    public void addAddress(Address address) {
        addressRepository.save(address);
    }

    @Override
    public Address getAddressById(int _id) {
//        System.out.println(addressRepository.findByOrderByIdDesc());
        return addressRepository.getOne(_id);
    }

}
