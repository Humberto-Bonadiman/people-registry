package com.spring.register.people.addressTests;

import com.spring.register.people.dto.PersonTestDto;
import com.spring.register.people.model.Address;
import com.spring.register.people.model.Person;
import com.spring.register.people.repository.AddressRepository;
import com.spring.register.people.repository.PersonRepository;
import com.spring.register.people.utils.CreateAddress;
import com.spring.register.people.utils.CreatePerson;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AlterMainAddressByIdTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;

    @BeforeEach
    public void setup() {
        personRepository.deleteAll();
    }

    @AfterEach
    public void afterEach() {
        personRepository.deleteAll();
    }

    @Test
    @Order(1)
    @DisplayName("1 - Must edit a person successfully")
    void editPersonSuccessfully() throws Exception {
        Person person = CreatePerson.createTestPerson();
        personRepository.save(person);
        Address address = CreateAddress.createTestAddress();
        person.addAddress(address);
        personRepository.save(person);
        addressRepository.save(address);
        mockMvc.perform(patch("/address/" + address.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(2)
    @DisplayName("2 - Should throw a error when the id is not found")
    void idNotFoundWhenEditingPerson() throws Exception {
        mockMvc.perform(patch("/address/" + 10552))
                .andExpect(status().isNotFound());
    }
}
