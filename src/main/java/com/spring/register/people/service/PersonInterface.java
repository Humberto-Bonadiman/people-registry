package com.spring.register.people.service;

import com.spring.register.people.dto.PersonDto;
import com.spring.register.people.model.Person;

import java.util.List;

public interface PersonInterface {
    Person createPerson(PersonDto personDto);

    List<Person> showAllPeople();

    Person findPersonById(Long id);

    Person editPersonById(Long id, PersonDto personDto);

    void deletePersonById(Long id);
}
