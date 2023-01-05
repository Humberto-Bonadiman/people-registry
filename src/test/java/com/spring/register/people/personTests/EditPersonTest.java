package com.spring.register.people.personTests;

import com.spring.register.people.dto.PersonTestDto;
import com.spring.register.people.model.Person;
import com.spring.register.people.repository.PersonRepository;
import com.spring.register.people.utils.CreatePerson;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EditPersonTest {

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
    @DisplayName("1 - Must edit a person successfully")
    void editPersonSuccessfully() throws Exception {
        Person person = CreatePerson.createTestPerson();
        personRepository.save(person);
        PersonTestDto personTestDto = CreatePerson.createPersonTestDto();
        mockMvc.perform(put("/person/" + person.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(personTestDto)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    @DisplayName("2 - Should throw a error when the id is not found")
    void idNotFoundWhenEditingPerson() throws Exception {
        PersonTestDto personTestDto = CreatePerson.createPersonTestDto();
        mockMvc.perform(put("/person/" + 10552)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(personTestDto)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(3)
    @DisplayName("3 - Must throw a error without the birth date")
    void badRequestWithoutBirthDate() throws Exception {
        Person person = CreatePerson.createTestPerson();
        personRepository.save(person);
        final Person errorPerson = new Person("Bad Request");
        mockMvc.perform(put("/person/" + person.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(errorPerson)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(4)
    @DisplayName("4 - Must throw a error without the name")
    void badRequestWithoutName() throws Exception {
        Person person = CreatePerson.createTestPerson();
        personRepository.save(person);
        final PersonTestDto errorPerson = new PersonTestDto("1994-05-31");
        mockMvc.perform(put("/person/" + person.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(errorPerson)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
