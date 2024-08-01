package com.todoapi.controller;

import com.todoapi.domain.dto.user.request.UserCreateRequest;
import com.todoapi.domain.dto.user.request.UserUpdateRequest;
import com.todoapi.domain.dto.user.response.*;
import com.todoapi.domain.entity.User;
import com.todoapi.interfaces.controller.UserControllerInterface;
import com.todoapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/todoapi/user")
public class UserController implements UserControllerInterface {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserCreateResponse> addUser(UserCreateRequest userCreateRequest) {
        return ResponseEntity.ok().body(userService.addUser(userCreateRequest));
    }

    @Override
    public ResponseEntity<UserListAllResponse> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Override
    public ResponseEntity<UserListByIdResponse> getUserById(Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Override
    public ResponseEntity<UserUpdateResponse> updateUser(UserUpdateRequest userUpdateRequest) {
        return ResponseEntity.ok(userService.updateUser(userUpdateRequest));
    }

    @Override
    public ResponseEntity<UserDeleteResponse> deleteUser(Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

}
