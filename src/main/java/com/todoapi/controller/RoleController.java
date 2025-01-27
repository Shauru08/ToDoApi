package com.todoapi.controller;

import com.todoapi.domain.dto.role.request.RoleCreateRequest;
import com.todoapi.domain.dto.role.request.RoleUpdateRequest;
import com.todoapi.domain.dto.role.response.*;
import com.todoapi.interfaces.controller.RoleControllerInterface;
import com.todoapi.service.RoleService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todoapi/role")
public class RoleController implements RoleControllerInterface {

    private static final Logger logger = LogManager.getLogger("AppLogger");

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public ResponseEntity<RoleCreateResponse> addRole(@Valid @RequestBody RoleCreateRequest roleCreateRequest) {
        logger.info("Creating new role");
        // Call for the service to execute the logic of the method
        RoleCreateResponse roleCreateResponse = roleService.addRole(roleCreateRequest);

        // Process the response and return the correct HTTP status.
        switch (roleCreateResponse.getErrorCode()) {
            case "00":
                return ResponseEntity.ok(roleCreateResponse); // 200 OK for success

            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(roleCreateResponse); // 500 Internal Server Error for any unspecified error code
        }
    }

    @Override
    public ResponseEntity<RoleUpdateResponse> updateRole(@Valid @RequestBody RoleUpdateRequest roleUpdateRequest, @PathVariable Long roleId) {
        logger.info("Updating role with id: " + roleId);
        // Call for the service to execute the logic of the method
        RoleUpdateResponse roleUpdateResponse = roleService.updateRole(roleUpdateRequest, roleId);

        // Process the response and return the correct HTTP status.
        switch (roleUpdateResponse.getErrorCode()) {
            case "00":
                return ResponseEntity.ok(roleUpdateResponse); // 200 OK for success

            case "04":
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(roleUpdateResponse); // 400 BAD REQUEST for role not found

            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(roleUpdateResponse); // 500 Internal Server Error for any unspecified error code
        }
    }

    @Override
    public ResponseEntity<RoleDeleteResponse> deleteRole(@PathVariable Long roleId) {
        logger.info("Deleting role with id: " + roleId);
        // Call for the service to execute the logic of the method
        RoleDeleteResponse roleDeleteResponse = roleService.deleteRole(roleId);

        // Process the response and return the correct HTTP status.
        switch (roleDeleteResponse.getErrorCode()) {
            case "00":
                return ResponseEntity.ok(roleDeleteResponse); // 200 OK for success

            case "04":
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(roleDeleteResponse); // 400 BAD REQUEST for role not found

            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(roleDeleteResponse); // 500 Internal Server Error for any unspecified error code
        }
    }

    @Override
    public ResponseEntity<RoleListByIdResponse> getRoleById(@PathVariable Long roleId) {
        logger.info("Fetching role with id: " + roleId);
        // Call for the service to execute the logic of the method
        RoleListByIdResponse roleListByIdResponse = roleService.getRoleById(roleId);

        // Process the response and return the correct HTTP status.
        switch (roleListByIdResponse.getErrorCode()) {
            case "00":
                return ResponseEntity.ok(roleListByIdResponse); // 200 OK for success

            case "04":
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(roleListByIdResponse); // 400 BAD REQUEST for role not found

            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(roleListByIdResponse); // 500 Internal Server Error for any unspecified error code
        }
    }

    @Override
    public ResponseEntity<RoleListAllResponse> getAllRoles() {
        logger.info("Fetching all roles");

        // Call for the service to execute the logic of the method
        RoleListAllResponse roleListResponse = roleService.getAllRoles();

        // Proccess the response and return the correct HTTP status.
        switch (roleListResponse.getErrorCode()) {
            case "00":
                return ResponseEntity.ok(roleListResponse); // 200 OK for success
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(roleListResponse); // 500 Internal Server Error for any unspecified error code
        }
    }

}
