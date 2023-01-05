package com.spring.register.people.addressTests;

import com.spring.register.people.dto.AddressDto;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CreateAddressTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;

    @BeforeEach
    public void setup() {
        personRepository.deleteAll();
        addressRepository.deleteAll();
    }

    @AfterEach
    public void afterEach() {
        personRepository.deleteAll();
        addressRepository.deleteAll();
    }

    @Test
    @Order(1)
    @DisplayName("1 - Must register an address successfully")
    void createAddressSuccessfully() throws Exception {
        final Person person = CreatePerson.createTestPerson();
        personRepository.save(person);
        AddressDto addressDto = CreateAddress.createTestAddressDto(person.getId());
        mockMvc.perform(post("/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(addressDto)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    @DisplayName("2 - Must throw a error without public place")
    void badRequestWithoutPublicPlace() throws Exception {
        final Person person = CreatePerson.createTestPerson();
        personRepository.save(person);
        AddressDto addressDto = new AddressDto();
        addressDto.setCep("01310930");
        addressDto.setNumber(1990);
        addressDto.setCity("São Paulo");
        addressDto.setPersonId(person.getId());
        mockMvc.perform(post("/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(addressDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(3)
    @DisplayName("3 - Must throw a error without cep")
    void badRequestWithoutCep() throws Exception {
        final Person person = CreatePerson.createTestPerson();
        personRepository.save(person);
        AddressDto addressDto = new AddressDto();
        addressDto.setPublicPlace("Rua");
        addressDto.setNumber(1990);
        addressDto.setCity("São Paulo");
        addressDto.setPersonId(person.getId());
        mockMvc.perform(post("/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(addressDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(4)
    @DisplayName("4 - Must throw a error without number")
    void badRequestWithoutNumber() throws Exception {
        final Person person = CreatePerson.createTestPerson();
        personRepository.save(person);
        AddressDto addressDto = new AddressDto();
        addressDto.setPublicPlace("Rua");
        addressDto.setCep("01310930");
        addressDto.setCity("São Paulo");
        addressDto.setPersonId(person.getId());
        mockMvc.perform(post("/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(addressDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(5)
    @DisplayName("5 - Must throw a error without city")
    void badRequestWithoutCity() throws Exception {
        final Person person = CreatePerson.createTestPerson();
        personRepository.save(person);
        AddressDto addressDto = new AddressDto();
        addressDto.setPublicPlace("Rua");
        addressDto.setCep("01310930");
        addressDto.setNumber(1990);
        addressDto.setPersonId(person.getId());
        mockMvc.perform(post("/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(addressDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(6)
    @DisplayName("6 - Must throw a error without person id")
    void badRequestWithoutPersonId() throws Exception {
        final Person person = CreatePerson.createTestPerson();
        personRepository.save(person);
        AddressDto addressDto = new AddressDto();
        addressDto.setPublicPlace("Rua");
        addressDto.setCep("01310930");
        addressDto.setNumber(1990);
        addressDto.setCity("São Paulo");
        mockMvc.perform(post("/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(addressDto)))
                .andExpect(status().isBadRequest());
    }
}
