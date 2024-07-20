package com.todoapi.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User {

    // Mandatory
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id;

    @NotNull
    @Schema(description = "Username", example = "user123", required = true)
    private String username;

    @NotNull
    @Schema(description = "Password", example = "password", required = true)
    private String password;

    @NotNull
    @Schema(description = "Email", example = "user@example.com", required = true)
    private String email;

    // Non-mandatory
    @Schema(description = "Name", example = "John", required = false)
    private String firstName;

    @Schema(description = "Lastname", example = "Doe", required = false)
    private String lastName;

    @Schema(description = "Mobile Phone", example = "+123456789", required = false)
    private String phone;

    @Schema(description = "Residency Address", example = "1234 Elm Street", required = false)
    private String address;

    @Schema(description = "Residency City", example = "Springfield", required = false)
    private String city;

    @Schema(description = "Residency State", example = "IL", required = false)
    private String state;

    @Schema(description = "Zip Code", example = "62704", required = false)
    private String zip;

    @Schema(description = "Country", example = "USA", required = false)
    private String country;

    @OneToMany(mappedBy = "user")
    @Schema(hidden = true, required = false)
    private List<Task> tasks;
}
