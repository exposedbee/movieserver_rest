package com.epita.movieserver_rest.service.interfaceImp;

import com.epita.movieserver_rest.datamodel.Address;
import com.epita.movieserver_rest.repository.AddressRepository;
import com.epita.movieserver_rest.service.interfaces.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImp implements AddressService {

    AddressRepository addressRepository;

    @Autowired
    public AddressServiceImp(AddressRepository addressRepository) {
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

    @Override
    public void updateAddress(Address address,Address newAddress) {
        address.setStreet(newAddress.getStreet());
        address.setNumber(newAddress.getNumber());
        address.setArea(newAddress.getArea());
        address.setCountry(newAddress.getCountry());
        addressRepository.flush();
    }


}
