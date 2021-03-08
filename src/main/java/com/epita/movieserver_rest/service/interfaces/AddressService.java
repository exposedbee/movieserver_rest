package com.epita.movieserver_rest.service.interfaces;

import com.epita.movieserver_rest.datamodel.Address;

public interface AddressService {
    void addAddress(Address address);

    Address getAddressById(int _id);

    void updateAddress(Address address,Address newAddress);


}
