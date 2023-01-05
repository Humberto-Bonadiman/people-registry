package com.spring.register.people.service;

import com.spring.register.people.dto.AddressDto;
import com.spring.register.people.exception.messages.AddressNotFoundException;
import com.spring.register.people.exception.messages.MainAddressNotExistException;
import com.spring.register.people.exception.messages.NumberLessThanZeroException;
import com.spring.register.people.exception.messages.PersonNotFoundException;
import com.spring.register.people.model.Address;
import com.spring.register.people.model.Person;
import com.spring.register.people.repository.AddressRepository;
import com.spring.register.people.repository.PersonRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService implements AddressInterface {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @Override
    public Address createAddress(@NotNull AddressDto addressDto) {
        testGetNumber(addressDto.getNumber());
        Address address = new Address(
                addressDto.getPublicPlace(),
                addressDto.getCep(),
                addressDto.getNumber(),
                addressDto.getCity(),
                addressDto.isMainAddress()
        );
        Person person = personService.findById(addressDto.getPersonId());
        person.addAddress(address);
        Person savedPerson = personRepository.save(person);
        return savedPerson.getAddress().get(savedPerson.getAddress().size() - 1);
    }

    @Override
    public List<Address> listPersonAddresses(Long id) {
        Person person = personService.findById(id);
        return person.getAddress();
    }

    @Override
    public Address findByMainAddress(Long personId) throws MainAddressNotExistException {
        Person person = personService.findById(personId);
        List<Address> addresses = person.getAddress();
        if (addresses.size() == 0) {
            throw new MainAddressNotExistException();
        }
        Address mainAddress = addresses.stream()
                .filter(address -> Objects.equals(
                        address.isMainAddress(),
                        true
                ))
                .findFirst()
                .orElseThrow(MainAddressNotExistException::new);
        return mainAddress;
    }

    @Override
    public void alterMainAddressById(Long id) {
        Address address = findById(id);
        if (address.isMainAddress() == true) {
            address.setMainAddress(false);
            addressRepository.save(address);
        }
        if (address.isMainAddress() == false) {
            address.setMainAddress(true);
            addressRepository.save(address);
        }
    }

    private @NotNull Address findById(Long id) {
        Optional<Address> validAddress = addressRepository.findById(id);
        if (validAddress.isEmpty()) {
            throw new AddressNotFoundException();
        }
        return validAddress.get();
    }

    private void testGetNumber(int number) {
        if (number < 1) {
            throw new NumberLessThanZeroException();
        }
    }
}
