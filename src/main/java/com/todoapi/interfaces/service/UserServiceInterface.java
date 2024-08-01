package com.todoapi.interfaces.service;

import com.todoapi.domain.dto.user.request.UserCreateRequest;
import com.todoapi.domain.dto.user.request.UserUpdateRequest;
import com.todoapi.domain.dto.user.response.*;

import java.util.List;

public interface UserServiceInterface {
    UserCreateResponse addUser(UserCreateRequest userCreateRequest);

    UserListAllResponse getAllUsers();

    UserListByIdResponse getUserById(Long id);

    UserUpdateResponse updateUser(UserUpdateRequest userUpdateRequest);

    UserDeleteResponse deleteUser(Long id);
}
