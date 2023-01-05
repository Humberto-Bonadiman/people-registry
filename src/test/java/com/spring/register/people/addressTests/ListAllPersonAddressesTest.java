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
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ListAllPersonAddressesTest {
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
    @DisplayName("1 - Should return a list of all person addresses")
    void listAllPersonAddresses() throws Exception {
        Person person = CreatePerson.createTestPerson();
        Address firstAddress = CreateAddress.createTestAddress();
        person.addAddress(firstAddress);
        Address secondAddress = CreateAddress.createSecondTestAddress();
        person.addAddress(secondAddress);
        personRepository.save(person);
        mockMvc.perform(get("/address")
                        .param("personId", person.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].publicPlace").value(firstAddress.getPublicPlace()))
                .andExpect(jsonPath("$[0].cep").value(firstAddress.getCep()))
                .andExpect(jsonPath("$[0].number").value(firstAddress.getNumber()))
                .andExpect(jsonPath("$[0].city").value(firstAddress.getCity()))
                .andExpect(jsonPath("$[0].mainAddress").value(firstAddress.isMainAddress()))
                .andExpect(jsonPath("$[1].publicPlace").value(secondAddress.getPublicPlace()))
                .andExpect(jsonPath("$[1].cep").value(secondAddress.getCep()))
                .andExpect(jsonPath("$[1].number").value(secondAddress.getNumber()))
                .andExpect(jsonPath("$[1].city").value(secondAddress.getCity()))
                .andExpect(jsonPath("$[1].mainAddress").value(secondAddress.isMainAddress()));
    }

    @Test
    @Order(2)
    @DisplayName("2 - Should throw an error if person id does not exist")
    void throwErrorIfPersonIdNotExist() throws Exception {
        mockMvc.perform(get("/address")
                        .param("personId", "1500"))
                .andExpect(status().isNotFound());
    }
}
