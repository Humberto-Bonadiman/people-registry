package com.spring.register.people.utils;

import com.spring.register.people.dto.PersonTestDto;
import com.spring.register.people.model.Person;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.jetbrains.annotations.NotNull;

public class CreatePerson {
    public static @NotNull Person createTestPerson() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String date = "16/08/1998";
        LocalDate localDate = LocalDate.parse(date, formatter);
        Person person = new Person("Name Test", localDate);
        return person;
    }

    public static @NotNull Person createSecondTestPerson() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String date = "20/05/1998";
        LocalDate localDate = LocalDate.parse(date, formatter);
        Person person = new Person("Second Name Test", localDate);
        return person;
    }

    public static @NotNull PersonTestDto createPersonTestDto() {
        PersonTestDto personTestDto = new PersonTestDto(
                "Name Test",
                "1998-08-16"
        );
        return personTestDto;
    }
}
