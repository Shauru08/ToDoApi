package com.todoapi.controller;

import com.todoapi.domain.dto.category.request.CategoryCreateRequest;
import com.todoapi.domain.dto.category.request.CategoryUpdateRequest;
import com.todoapi.domain.dto.category.response.*;
import com.todoapi.interfaces.controller.CategoryControllerInterface;
import com.todoapi.service.CategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todoapi/cate")
public class CategoryController implements CategoryControllerInterface {

    private static final Logger logger = LogManager.getLogger(CategoryController.class);

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ResponseEntity<CategoryCreateResponse> addCategory(@RequestBody CategoryCreateRequest categoryCreateRequest) {
        logger.info("Creating new category");
        CategoryCreateResponse response = categoryService.addCategory(categoryCreateRequest);

        // Processing the response and returning the correct HTTP status
        switch (response.getErrorCode()) {
            case "00":
                return ResponseEntity.ok(response); // 200 OK for success
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); // 500 Internal Server Error for any unspecified error code
        }
    }

    @Override
    public ResponseEntity<CategoryUpdateResponse> updateCategory(@RequestBody CategoryUpdateRequest categoryUpdateRequest, @PathVariable Long categoryId) {
        logger.info("Updating category with id: {}", categoryId);
        CategoryUpdateResponse response = categoryService.updateCategory(categoryUpdateRequest, categoryId);

        // Processing the response and returning the correct HTTP status
        switch (response.getErrorCode()) {
            case "00":
                return ResponseEntity.ok(response); // 200 OK for success
            case "04":
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); // 400 BAD REQUEST for category not found
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); // 500 Internal Server Error for any unspecified error code
        }
    }

    @Override
    public ResponseEntity<CategoryDeleteResponse> deleteCategory(@PathVariable Long categoryId) {
        logger.info("Deleting category with id: {}", categoryId);
        CategoryDeleteResponse response = categoryService.deleteCategory(categoryId);

        // Processing the response and returning the correct HTTP status
        switch (response.getErrorCode()) {
            case "00":
                return ResponseEntity.ok(response); // 200 OK for success
            case "04":
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); // 400 BAD REQUEST for category not found
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); // 500 Internal Server Error for any unspecified error code
        }
    }

    @Override
    public ResponseEntity<CategoryListByIdResponse> getCategoryById(@PathVariable Long categoryId) {
        logger.info("Fetching category with id: {}", categoryId);
        CategoryListByIdResponse response = categoryService.getCategoryById(categoryId);

        // Processing the response and returning the correct HTTP status
        switch (response.getErrorCode()) {
            case "00":
                return ResponseEntity.ok(response); // 200 OK for success
            case "04":
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); // 400 BAD REQUEST for category not found
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); // 500 Internal Server Error for any unspecified error code
        }
    }

    @Override
    public ResponseEntity<CategoryListAllResponse> getAllCategories() {
        logger.info("Fetching all categories");
        CategoryListAllResponse response = categoryService.getAllCategories();

        // Processing the response and returning the correct HTTP status
        switch (response.getErrorCode()) {
            case "00":
                return ResponseEntity.ok(response); // 200 OK for success
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); // 500 Internal Server Error for any unspecified error code
        }
    }
}
