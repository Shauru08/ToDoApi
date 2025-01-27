package com.todoapi.interfaces.controller;

import com.todoapi.domain.dto.role.request.RoleCreateRequest;
import com.todoapi.domain.dto.role.request.RoleUpdateRequest;
import com.todoapi.domain.dto.role.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface RoleControllerInterface {

    @Operation(summary = "Create a new role", description = "Creates a new role with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role created successfully", content = @Content(schema = @Schema(implementation = RoleCreateResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content(schema = @Schema(implementation = RoleCreateResponse.class)))
    })
    @PostMapping
    ResponseEntity<RoleCreateResponse> addRole(@Valid @RequestBody RoleCreateRequest roleCreateRequest);

    @Operation(summary = "Update an existing role", description = "Updates the details of an existing role.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role updated successfully", content = @Content(schema = @Schema(implementation = RoleUpdateResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content(schema = @Schema(implementation = RoleUpdateResponse.class))),
            @ApiResponse(responseCode = "404", description = "Role not found", content = @Content(schema = @Schema(implementation = RoleUpdateResponse.class)))
    })
    @PutMapping("/{roleId}")
    ResponseEntity<RoleUpdateResponse> updateRole(@Valid @RequestBody RoleUpdateRequest roleUpdateRequest, @PathVariable Long roleId);

    @Operation(summary = "Delete a role", description = "Deletes a role by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Role deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content(schema = @Schema(implementation = RoleDeleteResponse.class))),
            @ApiResponse(responseCode = "404", description = "Role not found", content = @Content(schema = @Schema(implementation = RoleDeleteResponse.class)))
    })
    @DeleteMapping("/{roleId}")
    ResponseEntity<RoleDeleteResponse> deleteRole(@PathVariable Long roleId);

    @Operation(summary = "Get role by ID", description = "Retrieves a role by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved role", content = @Content(schema = @Schema(implementation = RoleListByIdResponse.class))),
            @ApiResponse(responseCode = "404", description = "Role not found", content = @Content(schema = @Schema(implementation = RoleListByIdResponse.class)))
    })
    @GetMapping("/{roleId}")
    ResponseEntity<RoleListByIdResponse> getRoleById(@PathVariable Long roleId);

    @Operation(summary = "Get all roles", description = "Retrieves a list of all roles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of roles", content = @Content(schema = @Schema(implementation = RoleListAllResponse.class)))
    })
    @GetMapping
    ResponseEntity<RoleListAllResponse> getAllRoles();
}
