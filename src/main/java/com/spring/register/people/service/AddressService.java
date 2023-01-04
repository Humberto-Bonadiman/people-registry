package com.spring.register.people.service;

import com.spring.register.people.dto.AddressDto;
import com.spring.register.people.exception.messages.AddressNotFoundException;
import com.spring.register.people.model.Address;
import com.spring.register.people.model.Person;
import com.spring.register.people.repository.AddressRepository;
import java.util.List;
import java.util.Optional;

import com.spring.register.people.repository.PersonRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService implements AddressInterface {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PersonService personService;

    @Override
    public Address createAddress(@NotNull AddressDto addressDto) {
        Address address = new Address(
                addressDto.publicPlace(),
                addressDto.cep(),
                addressDto.number(),
                addressDto.city(),
                addressDto.mainAddress()
        );
        addressRepository.save(address);
        return null;
    }

    @Override
    public List<Address> listPersonAddresses(Long id) {
        Person person = personService.findById(id);
        List<Address> adresses = person.getAddress();
        return adresses;
    }

    @Override
    public Address findByMainAddress() {
        return findAddressByMainAddress();
    }

    private @NotNull Address findAddressByMainAddress() {
        Optional<Address> validMainAddress = addressRepository.findByMainAddress(true);
        if (validMainAddress.isEmpty()) {
            throw new AddressNotFoundException();
        }
        return validMainAddress.get();
    }
}
