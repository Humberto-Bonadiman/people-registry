package com.spring.register.people.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Getter
public class AddressDto {

    private final @NotNull String publicPlace;

    private final @NotNull Long cep;

    private final @NotNull int number;

    private final @NotNull String city;

    private final @NotNull Long personId;

    private final boolean mainAddress;
}
