package com.spring.register.people.service;

import com.spring.register.people.dto.AddressDto;
import com.spring.register.people.model.Address;

import java.util.List;

public interface AddressInterface {
    Address createAddress(AddressDto addressDto);

    List<Address> listPersonAddresses(Long id);

    Address findByMainAddress(Long personId);
}
