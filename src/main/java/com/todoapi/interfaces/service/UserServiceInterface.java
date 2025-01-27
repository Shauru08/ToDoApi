package com.todoapi.interfaces.service;

import com.todoapi.domain.dto.user.request.UserCreateRequest;
import com.todoapi.domain.dto.user.request.UserUpdateRequest;
import com.todoapi.domain.dto.user.response.*;
import com.todoapi.domain.entity.User;

public interface UserServiceInterface {

    /**
     * Create a new user.
     *
     * @param userCreateRequest the request containing user creation details.
     * @return a response indicating the result of the user creation.
     */
    UserCreateResponse addUser(UserCreateRequest userCreateRequest);

    /**
     * Get all users.
     *
     * @return a response containing the list of all users.
     */
    UserListAllResponse getAllUsers();

    /**
     * Get a user by their ID.
     *
     * @param id the ID of the user to retrieve.
     * @return a response containing the user's details.
     */
    UserListByIdResponse getUserById(Long id);

    /**
     * Update a user's details.
     *
     * @param userUpdateRequest the request containing updated user details.
     * @param id the ID of the user to update.
     * @return a response indicating the result of the update operation.
     */
    UserUpdateResponse updateUser(UserUpdateRequest userUpdateRequest, Long id);

    /**
     * Delete a user by their ID.
     *
     * @param id the ID of the user to delete.
     * @return a response indicating the result of the delete operation.
     */
    UserDeleteResponse deleteUser(Long id);
}

