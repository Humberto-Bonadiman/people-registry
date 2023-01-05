package com.spring.register.people.addressTests;

import com.spring.register.people.model.Address;
import com.spring.register.people.model.Person;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FindByMainAddressTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonRepository personRepository;

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
    @DisplayName("1 - Should find the main address")
    void findMainAddressSuccessfully() throws Exception {
        Person person = CreatePerson.createTestPerson();
        Address address = CreateAddress.createTestAddress();
        person.addAddress(address);
        personRepository.save(person);
        mockMvc.perform(get("/address/main_address")
                        .param("personId", person.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(address)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    @DisplayName("2 - Should throw a error when the id is not found")
    void idNotFoundWhenLookingMainAddress() throws Exception {
        mockMvc.perform(get("/address/main_address")
                        .param("personId", "10552")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
