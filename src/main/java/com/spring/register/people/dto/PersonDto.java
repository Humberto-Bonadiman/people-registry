package com.spring.register.people.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@RequiredArgsConstructor
@Accessors(fluent = true)
@Getter
public class PersonDto {
    private final @NotNull String name;

    private final @NotNull LocalDate birthDate;
}
