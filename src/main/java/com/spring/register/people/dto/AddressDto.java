package com.spring.register.people.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDto {

    private @NotNull String publicPlace;

    private @NotNull String cep;

    private @NotNull int number;

    private @NotNull String city;

    private @NotNull Long personId;

    private boolean mainAddress;
}
