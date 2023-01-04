package com.spring.register.people.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class PersonDto {
    private final @NotNull String name;

    private final @NotNull LocalDate birthDate;
}
