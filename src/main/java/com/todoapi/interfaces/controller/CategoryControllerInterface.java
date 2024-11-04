package com.todoapi.interfaces.controller;

import com.todoapi.domain.dto.category.request.CategoryCreateRequest;
import com.todoapi.domain.dto.category.request.CategoryUpdateRequest;
import com.todoapi.domain.dto.category.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CategoryControllerInterface {

    @Operation(summary = "Create a new category", description = "Creates a new category with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category created successfully", content = @Content(schema = @Schema(implementation = CategoryCreateResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content(schema = @Schema(implementation = CategoryCreateResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = CategoryCreateResponse.class)))
    })
    ResponseEntity<CategoryCreateResponse> addCategory(
            @Valid @RequestBody @Parameter(description = "Details for creating a new category", required = true) CategoryCreateRequest categoryCreateRequest
    );

    @Operation(summary = "Update an existing category", description = "Updates the details of an existing category.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category updated successfully", content = @Content(schema = @Schema(implementation = CategoryUpdateResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content(schema = @Schema(implementation = CategoryUpdateResponse.class))),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content(schema = @Schema(implementation = CategoryUpdateResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = CategoryUpdateResponse.class)))
    })
    ResponseEntity<CategoryUpdateResponse> updateCategory(
            @Valid @RequestBody @Parameter(description = "Updated details for the category", required = true) CategoryUpdateRequest categoryUpdateRequest,
            @PathVariable @Parameter(description = "ID of the category to update", required = true) Long categoryId
    );

    @Operation(summary = "Delete a category", description = "Deletes a category by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category deleted successfully", content = @Content(schema = @Schema(implementation = CategoryDeleteResponse.class))),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content(schema = @Schema(implementation = CategoryDeleteResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = CategoryDeleteResponse.class)))
    })
    ResponseEntity<CategoryDeleteResponse> deleteCategory(
            @PathVariable @Parameter(description = "ID of the category to delete", required = true) Long categoryId
    );

    @Operation(summary = "Get category by ID", description = "Retrieves a category by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category retrieved successfully", content = @Content(schema = @Schema(implementation = CategoryListByIdResponse.class))),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content(schema = @Schema(implementation = CategoryListByIdResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = CategoryListByIdResponse.class)))
    })
    ResponseEntity<CategoryListByIdResponse> getCategoryById(
            @PathVariable @Parameter(description = "ID of the category to retrieve", required = true) Long categoryId
    );

    @Operation(summary = "Get all categories", description = "Retrieves a list of all categories.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categories retrieved successfully", content = @Content(schema = @Schema(implementation = CategoryListAllResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = CategoryListAllResponse.class)))
    })
    ResponseEntity<CategoryListAllResponse> getAllCategories();
}
