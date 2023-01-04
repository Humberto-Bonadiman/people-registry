package com.spring.register.people.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "public_place", nullable = false)
    private String publicPlace;

    @Column(name = "cep", nullable = false)
    private Long cep;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "main_address")
    private boolean mainAddress;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Address(
            String publicPlace,
            Long cep,
            int number,
            String city,
            boolean mainAddress) {
        this.publicPlace = publicPlace;
        this.cep = cep;
        this.number = number;
        this.city = city;
        this.mainAddress = mainAddress;
    }
}
