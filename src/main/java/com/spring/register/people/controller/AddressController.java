package com.spring.register.people.controller;

import com.spring.register.people.dto.AddressDto;
import com.spring.register.people.model.Address;
import com.spring.register.people.model.Person;
import com.spring.register.people.service.AddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@Tag(name = "Address")
public class AddressController implements AddressInterfaceController {

    @Autowired
    AddressService addressService;

    @Override
    public ResponseEntity<Address> createAddress(AddressDto addressDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.createAddress(addressDto));
    }

    @Override
    public ResponseEntity<List<Address>> showAllPersonAddresses(Long personId) {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.listPersonAddresses(personId));
    }

    @Override
    public ResponseEntity<Address> findByMainAddress(Long personId) {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.findByMainAddress(personId));
    }

    @Override
    public ResponseEntity<Object> alterMainAddressById(Long id) {
        addressService.alterMainAddressById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
