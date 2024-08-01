package com.todoapi.interfaces.controller;

import com.todoapi.domain.dto.task.request.TaskCreateRequest;
import com.todoapi.domain.dto.task.request.TaskUpdateRequest;
import com.todoapi.domain.dto.task.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface TaskControllerInterface {

    @Operation(summary = "Create a new task", description = "Creates a new task with the given details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task created successfully", content = @Content(schema = @Schema(implementation = TaskCreateResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content(schema = @Schema(implementation = TaskCreateResponse.class)))
    })
    @PostMapping
    ResponseEntity<TaskCreateResponse> addTask(@Valid @RequestBody(description = "Task details for creating a new task", required = true) TaskCreateRequest taskCreateRequest);

    @Operation(summary = "Get all tasks", description = "Retrieves a list of all tasks.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of tasks", content = @Content(schema = @Schema(implementation = TaskListAllResponse.class)))
    })
    @GetMapping
    ResponseEntity<TaskListAllResponse> getAllTasks();

    @Operation(summary = "Get task by ID", description = "Retrieves a task by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved task", content = @Content(schema = @Schema(implementation = TaskListByIdResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content(schema = @Schema(implementation = TaskListByIdResponse.class))),
            @ApiResponse(responseCode = "404", description = "Task not found", content = @Content(schema = @Schema(implementation = TaskListByIdResponse.class)))
    })
    @GetMapping("/{taskId}")
    ResponseEntity<TaskListByIdResponse> getTaskById(@PathVariable Long taskId);

    @Operation(summary = "Get tasks by user ID", description = "Retrieves tasks assigned to a specific user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved tasks for the user", content = @Content(schema = @Schema(implementation = TaskListByUserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content(schema = @Schema(implementation = TaskListByUserResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = TaskListByUserResponse.class))),
    })
    @GetMapping("/user/{userId}")
    ResponseEntity<TaskListByUserResponse> getUserTasks(@PathVariable Long userId);

    @Operation(summary = "Update a task", description = "Updates the details of an existing task.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated successfully", content = @Content(schema = @Schema(implementation = TaskUpdateResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content(schema = @Schema(implementation = TaskUpdateResponse.class))),
            @ApiResponse(responseCode = "404", description = "Task not found", content = @Content(schema = @Schema(implementation = TaskUpdateResponse.class)))
    })
    @PutMapping("/{taskId}")
    ResponseEntity<TaskUpdateResponse> updateTask(@Valid @RequestBody(description = "Updated task details", required = true) TaskUpdateRequest taskUpdateRequest, @PathVariable Long taskId);

    @Operation(summary = "Delete a task", description = "Deletes a task by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Task deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content(schema = @Schema(implementation = TaskDeleteResponse.class))),
            @ApiResponse(responseCode = "404", description = "Task not found", content = @Content(schema = @Schema(implementation = TaskDeleteResponse.class)))
    })
    @DeleteMapping("/{taskId}")
    ResponseEntity<TaskDeleteResponse> deleteTask(@PathVariable Long taskId);
}
