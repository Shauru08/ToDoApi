package com.todoapi.domain.dto.category.request;

import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryCreateRequest {

    @NotBlank(message = "Category name is required")
    @Schema(description = "Name of the category", example = "Work", required = true)
    private String name;

    @Schema(description = "Description of the category", example = "Tasks related to work")
    private String description;


    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
