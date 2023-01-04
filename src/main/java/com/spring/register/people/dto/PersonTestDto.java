package com.spring.register.people.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PersonTestDto {
    private @NotNull String name;

    private @NotNull String birthDate;

    public PersonTestDto(String birthDate) {
        this.birthDate = birthDate;
    }
}
