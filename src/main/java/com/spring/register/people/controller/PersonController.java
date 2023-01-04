package com.spring.register.people.controller;

import com.spring.register.people.dto.PersonDto;
import com.spring.register.people.model.Person;
import com.spring.register.people.service.PersonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@Tag(name = "Person")
public class PersonController implements PersonInterfaceController {

    @Autowired
    PersonService personService;

    @Override
    public ResponseEntity<Person> createPerson(PersonDto personDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.createPerson(personDto));
    }

    @Override
    public ResponseEntity<List<Person>> showAllPeople() {
        return ResponseEntity.status(HttpStatus.OK).body(personService.showAllPeople());
    }

    @Override
    public ResponseEntity<Person> findPersonById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(personService.findPersonById(id));
    }

    @Override
    public ResponseEntity<Person> updatePersonById(Long id, PersonDto personDto) {
        return ResponseEntity.status(HttpStatus.OK).body(personService.editPersonById(id, personDto));
    }

    @Override
    public ResponseEntity<Object> deletePersonById(Long id) {
        personService.deletePersonById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
