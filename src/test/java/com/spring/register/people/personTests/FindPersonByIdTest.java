package com.spring.register.people.personTests;

import com.spring.register.people.model.Person;
import com.spring.register.people.repository.PersonRepository;
import com.spring.register.people.utils.CreatePerson;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FindPersonByIdTest {

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
    @DisplayName("1 - Should return a person by id")
    void findPersonById() throws Exception {
        Person person = CreatePerson.createTestPerson();
        personRepository.save(person);
        mockMvc.perform(get("/person/" + person.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(person)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    @DisplayName("2 - Should throw a error when the id is not found")
    void idNotFoundWhenLookingPerson() throws Exception {
        mockMvc.perform(get("/person/" + 10552)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
