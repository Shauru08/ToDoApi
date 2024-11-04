package com.todoapi.interfaces.controller;

import com.todoapi.domain.dto.permission.request.PermissionCreateRequest;
import com.todoapi.domain.dto.permission.request.PermissionUpdateRequest;
import com.todoapi.domain.dto.permission.response.PermissionCreateResponse;
import com.todoapi.domain.dto.permission.response.PermissionDeleteResponse;
import com.todoapi.domain.dto.permission.response.PermissionListAllResponse;
import com.todoapi.domain.dto.permission.response.PermissionListByIdResponse;
import com.todoapi.domain.dto.permission.response.PermissionUpdateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

public interface PermissionControllerInterface {

    @Operation(summary = "Create a new permission", description = "Creates a new permission in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permission created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    ResponseEntity<PermissionCreateResponse> addPermission(@Valid @RequestBody PermissionCreateRequest permissionCreateRequest);

    @Operation(summary = "Update an existing permission", description = "Updates the details of an existing permission.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permission updated successfully"),
            @ApiResponse(responseCode = "404", description = "Permission not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PutMapping("/{permissionId}")
    ResponseEntity<PermissionUpdateResponse> updatePermission(@Valid @RequestBody PermissionUpdateRequest permissionUpdateRequest, @PathVariable Long permissionId);

    @Operation(summary = "Delete a permission", description = "Deletes a permission by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permission deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Permission not found")
    })
    @DeleteMapping("/{permissionId}")
    ResponseEntity<PermissionDeleteResponse> deletePermission(@PathVariable Long permissionId);

    @Operation(summary = "Get permission by ID", description = "Fetches a permission by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permission found successfully"),
            @ApiResponse(responseCode = "404", description = "Permission not found")
    })
    @GetMapping("/{permissionId}")
    ResponseEntity<PermissionListByIdResponse> getPermissionById(@PathVariable Long permissionId);

    @Operation(summary = "Get all permissions", description = "Retrieves a list of all permissions.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permissions retrieved successfully")
    })
    @GetMapping
    ResponseEntity<PermissionListAllResponse> getAllPermissions();
}
