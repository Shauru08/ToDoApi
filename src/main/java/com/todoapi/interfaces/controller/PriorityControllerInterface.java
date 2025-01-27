package com.todoapi.interfaces.controller;

import com.todoapi.domain.dto.priority.request.PriorityCreateRequest;
import com.todoapi.domain.dto.priority.request.PriorityUpdateRequest;
import com.todoapi.domain.dto.priority.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface PriorityControllerInterface {

    @Operation(summary = "Create a new priority", description = "Creates a new priority level for tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Priority created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    ResponseEntity<PriorityCreateResponse> addPriority(@RequestBody PriorityCreateRequest priorityCreateRequest);

    @Operation(summary = "Update a priority", description = "Updates an existing priority level")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Priority updated successfully"),
            @ApiResponse(responseCode = "404", description = "Priority not found")
    })
    @PutMapping("/{priorityId}")
    ResponseEntity<PriorityUpdateResponse> updatePriority(@RequestBody PriorityUpdateRequest priorityUpdateRequest, @PathVariable Long priorityId);

    @Operation(summary = "Delete a priority", description = "Deletes a priority by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Priority deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Priority not found")
    })
    @DeleteMapping("/{priorityId}")
    ResponseEntity<PriorityDeleteResponse> deletePriority(@PathVariable Long priorityId);

    @Operation(summary = "Get a priority by ID", description = "Retrieves a priority by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Priority retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Priority not found")
    })
    @GetMapping("/{priorityId}")
    ResponseEntity<PriorityListByIdResponse> getPriorityById(@PathVariable Long priorityId);

    @Operation(summary = "Get all priorities", description = "Retrieves all priorities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of priorities")
    })
    @GetMapping
    ResponseEntity<PriorityListAllResponse> getAllPriorities();
}
