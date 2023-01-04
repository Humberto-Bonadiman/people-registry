package com.spring.register.people.controller;

import com.spring.register.people.dto.PersonDto;
import com.spring.register.people.model.Person;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/person")
public interface PersonInterfaceController {

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create person",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Person.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content)})
    @Operation(summary = "Create person")
    ResponseEntity<Person> createPerson(@RequestBody PersonDto personDto);

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Show all people",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Person.class)))})
    })
    @Operation(summary = "Show all people")
    ResponseEntity<List<Person>> showAllPeople();

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find a person by id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Person.class)) }),
            @ApiResponse(responseCode = "404", description = "Person not found",
                    content = @Content)})
    @Operation(summary = "Find a person by id")
    ResponseEntity<Person> findPersonById(@PathVariable Long id);

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Person.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Person not found",
                    content = @Content)})
    @Operation(summary = "Update person by id")
    ResponseEntity<Person> updatePersonById(
            @PathVariable Long id,
            @RequestBody PersonDto personDto
    );

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Person not found",
                    content = @Content)})
    @Operation(summary = "Delete person by id")
    ResponseEntity<Object> deletePersonById(@PathVariable Long id);
}
