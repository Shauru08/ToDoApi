package com.todoapi.domain.dto.category.response;

import com.google.gson.Gson;
import com.todoapi.domain.dto.base.BaseResponse;
import com.todoapi.domain.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryListByIdResponse extends BaseResponse {

    @Schema(description = "ID of the category", example = "1")
    private Long categoryId;

    @Schema(description = "Name of the category", example = "Work")
    private String name;

    @Schema(description = "Description of the category", example = "Tasks related to work")
    private String description;

    public CategoryListByIdResponse setErrorResponse(Category category, String errorCode, String errorMessage) {
        if (category != null) {
            this.categoryId = category.getId();
            this.name = category.getName();
            this.description = category.getDescription();
        }
        this.setErrorCode(errorCode);
        this.setErrorMessage(errorMessage);
        return this;
    }
    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
