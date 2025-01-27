package com.todoapi.controller;

import com.todoapi.domain.dto.permission.request.PermissionCreateRequest;
import com.todoapi.domain.dto.permission.request.PermissionUpdateRequest;
import com.todoapi.domain.dto.permission.response.*;
import com.todoapi.interfaces.controller.PermissionControllerInterface;
import com.todoapi.service.PermissionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/todoapi/permission")
public class PermissionController implements PermissionControllerInterface {

    private static final Logger logger = LogManager.getLogger("AppLogger");

    private final PermissionService permissionService;

    @Autowired
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    public ResponseEntity<PermissionCreateResponse> addPermission(@Valid @RequestBody PermissionCreateRequest permissionCreateRequest) {
        logger.info("Creating new permission");
        PermissionCreateResponse permissionCreateResponse = permissionService.addPermission(permissionCreateRequest);

        switch (permissionCreateResponse.getErrorCode()) {
            case "00":
                return ResponseEntity.ok(permissionCreateResponse);  // 200 OK
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(permissionCreateResponse);  // 500 Internal Server Error
        }
    }

    @Override
    public ResponseEntity<PermissionUpdateResponse> updatePermission(@Valid @RequestBody PermissionUpdateRequest permissionUpdateRequest, @PathVariable Long permissionId) {
        logger.info("Updating permission with id: " + permissionId);
        PermissionUpdateResponse permissionUpdateResponse = permissionService.updatePermission(permissionUpdateRequest, permissionId);

        switch (permissionUpdateResponse.getErrorCode()) {
            case "00":
                return ResponseEntity.ok(permissionUpdateResponse);  // 200 OK
            case "404":
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(permissionUpdateResponse);  // 404 Not Found
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(permissionUpdateResponse);  // 500 Internal Server Error
        }
    }

    @Override
    public ResponseEntity<PermissionDeleteResponse> deletePermission(@PathVariable Long permissionId) {
        logger.info("Deleting permission with id: " + permissionId);
        PermissionDeleteResponse permissionDeleteResponse = permissionService.deletePermission(permissionId);

        switch (permissionDeleteResponse.getErrorCode()) {
            case "00":
                return ResponseEntity.ok(permissionDeleteResponse);  // 200 OK
            case "404":
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(permissionDeleteResponse);  // 404 Not Found
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(permissionDeleteResponse);  // 500 Internal Server Error
        }
    }

    @Override
    public ResponseEntity<PermissionListByIdResponse> getPermissionById(@PathVariable Long permissionId) {
        logger.info("Fetching permission with id: " + permissionId);
        PermissionListByIdResponse permissionListByIdResponse = permissionService.getPermissionById(permissionId);

        switch (permissionListByIdResponse.getErrorCode()) {
            case "00":
                return ResponseEntity.ok(permissionListByIdResponse);  // 200 OK
            case "404":
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(permissionListByIdResponse);  // 404 Not Found
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(permissionListByIdResponse);  // 500 Internal Server Error
        }
    }

    @Override
    public ResponseEntity<PermissionListAllResponse> getAllPermissions() {
        logger.info("Fetching all permissions");
        PermissionListAllResponse permissionListAllResponse = permissionService.getAllPermissions();

        switch (permissionListAllResponse.getErrorCode()) {
            case "00":
                return ResponseEntity.ok(permissionListAllResponse);  // 200 OK
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(permissionListAllResponse);  // 500 Internal Server Error
        }
    }
}
