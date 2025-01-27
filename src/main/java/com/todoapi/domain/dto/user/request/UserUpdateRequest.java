package com.todoapi.domain.dto.user.request;

import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {

    @NotNull
    @Schema(description = "Username", example = "user123", required = true)
    private String username;

    @NotNull
    @Schema(description = "Email", example = "user@example.com", required = true)
    private String email;

    @Schema(description = "Password", example = "password123", required = false)
    private String password;

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

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
