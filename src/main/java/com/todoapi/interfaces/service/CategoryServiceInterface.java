package com.todoapi.interfaces.service;

import com.todoapi.domain.dto.category.request.CategoryCreateRequest;
import com.todoapi.domain.dto.category.request.CategoryUpdateRequest;
import com.todoapi.domain.dto.category.response.*;

public interface CategoryServiceInterface {

    CategoryCreateResponse addCategory(CategoryCreateRequest categoryCreateRequest);

    CategoryUpdateResponse updateCategory(CategoryUpdateRequest categoryUpdateRequest, Long categoryId);

    CategoryDeleteResponse deleteCategory(Long categoryId);

    CategoryListByIdResponse getCategoryById(Long categoryId);

    CategoryListAllResponse getAllCategories();
}
