package com.spring.register.people.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @JsonIgnore
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Address> address;

    public Person(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.address = new ArrayList<>();
    }

    public Person() {
        super();
        this.address = new ArrayList<Address>();
    }

    public Person(String name) {
        this.name = name;
    }

    public void addAddress(@NotNull Address address) {
        address.setPerson(this);
        this.address.add(address);
    }
}
