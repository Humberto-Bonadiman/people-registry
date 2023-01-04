package com.spring.register.people.service;

import com.spring.register.people.dto.PersonDto;
import com.spring.register.people.exception.messages.PersonNotFoundException;
import com.spring.register.people.model.Person;
import com.spring.register.people.repository.PersonRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements PersonInterface {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person createPerson(@NotNull PersonDto personDto) {
        Person person = new Person(
                personDto.getName(),
                personDto.getBirthDate()
        );
        personRepository.save(person);
        return person;
    }

    @Override
    public List<Person> showAllPeople() {
        return personRepository.findAll();
    }

    @Override
    public Person findPersonById(Long id) {
        return findById(id);
    }

    @Override
    public Person editPersonById(Long id, @NotNull PersonDto personDto) {
        Person person = findById(id);
        person.setName(personDto.getName());
        person.setBirthDate(personDto.getBirthDate());
        personRepository.save(person);
        return person;
    }

    @Override
    public void deletePersonById(Long id) {
        findById(id);
        personRepository.deleteById(id);
    }

    public @NotNull Person findById(Long id) {
        Optional<Person> validPerson = personRepository.findById(id);
        if (validPerson.isEmpty()) {
            throw new PersonNotFoundException();
        }
        return validPerson.get();
    }
}
