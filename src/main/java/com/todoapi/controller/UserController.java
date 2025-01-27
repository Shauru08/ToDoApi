package com.todoapi.controller;

import com.todoapi.domain.dto.user.request.UserCreateRequest;
import com.todoapi.domain.dto.user.request.UserUpdateRequest;
import com.todoapi.domain.dto.user.response.*;
import com.todoapi.interfaces.controller.UserControllerInterface;
import com.todoapi.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todoapi/user")
public class UserController implements UserControllerInterface {

    private static final Logger logger = LogManager.getLogger("AppLogger");

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserCreateResponse> addUser(@RequestBody UserCreateRequest userCreateRequest) {
        logger.info("Creating new user");

        UserCreateResponse userCreateResponse = userService.addUser(userCreateRequest);

        switch (userCreateResponse.getErrorCode()) {
            case "00":
                return ResponseEntity.ok(userCreateResponse); // 200 OK
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(userCreateResponse); // 500 Internal Server Error
        }
    }

    @Override
    public ResponseEntity<UserListAllResponse> getAllUsers() {
        logger.info("Fetching all users");

        UserListAllResponse userListAllResponse = userService.getAllUsers();

        switch (userListAllResponse.getErrorCode()) {
            case "00":
                return ResponseEntity.ok(userListAllResponse); // 200 OK
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(userListAllResponse); // 500 Internal Server Error
        }
    }

    @Override
    public ResponseEntity<UserListByIdResponse> getUserById(@PathVariable Long id) {
        logger.info("Fetching user with id: {}", id);

        UserListByIdResponse userListByIdResponse = userService.getUserById(id);

        switch (userListByIdResponse.getErrorCode()) {
            case "00":
                return ResponseEntity.ok(userListByIdResponse); // 200 OK
            case "04":
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userListByIdResponse); // 400 Bad Request
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(userListByIdResponse); // 500 Internal Server Error
        }
    }

    @Override
    public ResponseEntity<UserUpdateResponse> updateUser(@RequestBody UserUpdateRequest userUpdateRequest, @PathVariable Long id) {
        logger.info("Updating user with id: {}", id);

        UserUpdateResponse userUpdateResponse = userService.updateUser(userUpdateRequest, id);

        switch (userUpdateResponse.getErrorCode()) {
            case "00":
                return ResponseEntity.ok(userUpdateResponse); // 200 OK
            case "04":
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userUpdateResponse); // 400 Bad Request
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(userUpdateResponse); // 500 Internal Server Error
        }
    }

    @Override
    public ResponseEntity<UserDeleteResponse> deleteUser(@PathVariable Long id) {
        logger.info("Deleting user with id: {}", id);

        UserDeleteResponse userDeleteResponse = userService.deleteUser(id);

        switch (userDeleteResponse.getErrorCode()) {
            case "00":
                return ResponseEntity.ok(userDeleteResponse); // 200 OK
            case "04":
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userDeleteResponse); // 400 Bad Request
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(userDeleteResponse); // 500 Internal Server Error
        }
    }
}
