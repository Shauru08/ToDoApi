package com.todoapi.domain.dto.category.response;

import com.google.gson.Gson;
import com.todoapi.domain.dto.base.BaseResponse;
import com.todoapi.domain.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryListAllResponse extends BaseResponse {

    @Schema(description = "List of categories")
    private List<Category> categories;

    public CategoryListAllResponse setErrorResponse(List<Category> categories, String errorCode, String errorMessage) {
        this.categories = categories;
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
