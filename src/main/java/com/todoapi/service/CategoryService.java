package com.todoapi.service;

import com.todoapi.domain.dto.category.request.CategoryCreateRequest;
import com.todoapi.domain.dto.category.request.CategoryUpdateRequest;
import com.todoapi.domain.dto.category.response.*;
import com.todoapi.domain.entity.Category;
import com.todoapi.domain.enums.ErrorCodes;
import com.todoapi.interfaces.service.CategoryServiceInterface;
import com.todoapi.repository.CategoryRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements CategoryServiceInterface {

    private static final Logger logger = LogManager.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryCreateResponse addCategory(CategoryCreateRequest request) {
        CategoryCreateResponse response = new CategoryCreateResponse();
        try {
            Category newCategory = new Category();
            newCategory.setName(request.getName());
            newCategory.setDescription(request.getDescription());
            categoryRepository.save(newCategory);
            response.setErrorResponse(newCategory.getId(), ErrorCodes.SUCCESS.getCode(), "Category created successfully");
        } catch (Exception e) {
            response.setErrorResponse(null, ErrorCodes.EXCEPTION.getCode(), "Error occurred while creating the category");
        }
        return response;
    }

    @Override
    public CategoryUpdateResponse updateCategory(CategoryUpdateRequest request, Long categoryId) {
        CategoryUpdateResponse response = new CategoryUpdateResponse();
        try {
            Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
            if (categoryOptional.isPresent()) {
                Category categoryToUpdate = categoryOptional.get();
                categoryToUpdate.setName(request.getName());
                categoryToUpdate.setDescription(request.getDescription());
                categoryRepository.save(categoryToUpdate);
                response.setErrorResponse(categoryToUpdate.getId(), ErrorCodes.SUCCESS.getCode(), "Category updated successfully");
            } else {
                response.setErrorResponse(null, ErrorCodes.NOT_FOUND.getCode(), "Category not found");
            }
        } catch (Exception e) {
            response.setErrorResponse(null, ErrorCodes.EXCEPTION.getCode(), "Error occurred while updating the category");
        }
        return response;
    }

    @Override
    public CategoryDeleteResponse deleteCategory(Long categoryId) {
        CategoryDeleteResponse response = new CategoryDeleteResponse();
        try {
            if (categoryRepository.existsById(categoryId)) {
                categoryRepository.deleteById(categoryId);
                response.setErrorResponse(categoryId, ErrorCodes.SUCCESS.getCode(), "Category deleted successfully");
            } else {
                response.setErrorResponse(null, ErrorCodes.NOT_FOUND.getCode(), "Category not found");
            }
        } catch (Exception e) {
            response.setErrorResponse(null, ErrorCodes.EXCEPTION.getCode(), "Error occurred while deleting the category");
        }
        return response;
    }

    @Override
    public CategoryListByIdResponse getCategoryById(Long categoryId) {
        CategoryListByIdResponse response = new CategoryListByIdResponse();
        try {
            Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
            if (categoryOptional.isPresent()) {
                response.setErrorResponse(categoryOptional.get(), ErrorCodes.SUCCESS.getCode(), "Category found successfully");
            } else {
                response.setErrorResponse(null, ErrorCodes.NOT_FOUND.getCode(), "Category not found");
            }
        } catch (Exception e) {
            response.setErrorResponse(null, ErrorCodes.EXCEPTION.getCode(), "Error occurred while fetching the category");
        }
        return response;
    }

    @Override
    public CategoryListAllResponse getAllCategories() {
        CategoryListAllResponse response = new CategoryListAllResponse();
        try {
            logger.info("getAllCategories [Init]");

            // Obtener todas las categorías del repositorio
            List<Category> categories = categoryRepository.findAll();

            // Verificar si se encontraron categorías
            if (!categories.isEmpty()) {
                response.setErrorResponse(categories, ErrorCodes.SUCCESS.getCode(), "Categories retrieved successfully");
            } else {
                response.setErrorResponse(null, ErrorCodes.NOT_FOUND.getCode(), "No categories found");
            }
        } catch (Exception ex) {
            logger.error("An error occurred while fetching categories. Exception: {}", ex.getMessage(), ex);
            response.setErrorResponse(null, ErrorCodes.EXCEPTION.getCode(), "An error occurred while fetching categories");
        } finally {
            logger.info("getAllCategories [Fin]");
        }
        return response;
    }
}