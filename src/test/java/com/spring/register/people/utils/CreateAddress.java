package com.spring.register.people.utils;

import com.spring.register.people.dto.AddressDto;
import com.spring.register.people.model.Address;
import com.spring.register.people.model.Person;
import com.spring.register.people.repository.PersonRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateAddress {

    @Autowired
    static PersonRepository personRepository;

    public static @NotNull Address createTestAddress() {
        Address address = new Address(
                "Rua",
                "01310930",
                1990,
                "São Paulo",
                true
        );
        return address;
    }

    public static @NotNull Address createSecondTestAddress() {
        Address address = new Address(
                "Rua",
                "01310930",
                2000,
                "São Paulo",
                false
        );
        return address;
    }

    public static @NotNull AddressDto createTestAddressDto(Long personId) {
        AddressDto addressDto = new AddressDto(
                "Rua",
                "01310930",
                1990,
                "São Paulo",
                personId,
                true
        );
        return addressDto;
    }
}
