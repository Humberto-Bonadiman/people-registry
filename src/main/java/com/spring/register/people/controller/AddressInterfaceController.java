package com.spring.register.people.controller;

import com.spring.register.people.dto.AddressDto;
import com.spring.register.people.dto.PersonDto;
import com.spring.register.people.model.Address;
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

@RequestMapping("/address")
public interface AddressInterfaceController {

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create address",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Address.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content)})
    @Operation(summary = "Create address")
    ResponseEntity<Address> createAddress(@RequestBody AddressDto addressDto);

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Show all person addresses",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Address.class)))})
    })
    @Operation(summary = "Show all person addresses")
    ResponseEntity<List<Address>> showAllPersonAddresses(@RequestParam Long personId);

    @GetMapping("/main_address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find the main address",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Address.class)) }),
            @ApiResponse(responseCode = "404", description = "Address not found",
                    content = @Content)})
    @Operation(summary = "Find the main address")
    ResponseEntity<Address> findByMainAddress(@RequestParam Long personId);

    @PatchMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Address.class)) }),
            @ApiResponse(responseCode = "404", description = "Address not found",
                    content = @Content)})
    @Operation(summary = "Alter Main Address By Id")
    ResponseEntity<Object> alterMainAddressById(@PathVariable Long id);
}
