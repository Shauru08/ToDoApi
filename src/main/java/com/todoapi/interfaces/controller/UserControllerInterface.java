package com.todoapi.interfaces.controller;

import com.todoapi.domain.dto.user.request.UserCreateRequest;
import com.todoapi.domain.dto.user.request.UserUpdateRequest;
import com.todoapi.domain.dto.user.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserControllerInterface {

    @Operation(summary = "Create a new user", description = "Creates a new user with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully", content = @Content(schema = @Schema(implementation = UserCreateResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content(schema = @Schema(implementation = UserCreateResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = UserCreateResponse.class)))
    })
    @PostMapping
    ResponseEntity<UserCreateResponse> addUser(@RequestBody(description = "Details of the user to create", required = true) UserCreateRequest userCreateRequest);

    @Operation(summary = "Get all users", description = "Retrieves a list of all users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of users", content = @Content(schema = @Schema(implementation = UserListAllResponse.class)))
    })
    @GetMapping
    ResponseEntity<UserListAllResponse> getAllUsers();

    @Operation(summary = "Get user by ID", description = "Retrieves a user by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user", content = @Content(schema = @Schema(implementation = UserListByIdResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content(schema = @Schema(implementation = UserListByIdResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = UserListByIdResponse.class)))
    })
    @GetMapping("/{id}")
    ResponseEntity<UserListByIdResponse> getUserById(@PathVariable Long id);

    @Operation(summary = "Update a user", description = "Updates the details of an existing user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully", content = @Content(schema = @Schema(implementation = UserUpdateResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content(schema = @Schema(implementation = UserUpdateResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = UserUpdateResponse.class)))
    })
    @PutMapping("/{id}")
    ResponseEntity<UserUpdateResponse> updateUser(@RequestBody(description = "Updated details of the user", required = true) UserUpdateRequest userUpdateRequest);

    @Operation(summary = "Delete a user", description = "Deletes a user by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully", content = @Content(schema = @Schema(implementation = UserDeleteResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content(schema = @Schema(implementation = UserDeleteResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = UserDeleteResponse.class)))
    })
    @DeleteMapping("/{id}")
    ResponseEntity<UserDeleteResponse> deleteUser(@PathVariable Long id);
}
