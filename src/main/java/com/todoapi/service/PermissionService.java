package com.todoapi.service;

import com.todoapi.domain.dto.permission.request.PermissionCreateRequest;
import com.todoapi.domain.dto.permission.request.PermissionUpdateRequest;
import com.todoapi.domain.dto.permission.response.*;
import com.todoapi.domain.entity.Permission;
import com.todoapi.domain.enums.ErrorCodes;
import com.todoapi.interfaces.service.PermissionServiceInterface;
import com.todoapi.repository.PermissionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService implements PermissionServiceInterface {

    private static final Logger logger = LogManager.getLogger(PermissionService.class);

    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public PermissionCreateResponse addPermission(PermissionCreateRequest permissionCreateRequest) {
        PermissionCreateResponse response = new PermissionCreateResponse();
        try {
            logger.info("addPermission [Init]");
            Permission newPermission = new Permission();
            newPermission.setName(permissionCreateRequest.getName());
            newPermission.setDescription(permissionCreateRequest.getDescription());
            permissionRepository.save(newPermission);

            response.setErrorResponse(newPermission.getId(), ErrorCodes.SUCCESS.getCode(), "Permission created successfully");
        } catch (Exception ex) {
            logger.error("An error occurred while creating the permission. Exception: {}", ex.getMessage(), ex);
            response.setErrorResponse(null, ErrorCodes.EXCEPTION.getCode(), "An error occurred while creating the permission");
        } finally {
            logger.info("addPermission [Fin]");
        }
        return response;
    }

    @Override
    public PermissionUpdateResponse updatePermission(PermissionUpdateRequest permissionUpdateRequest, Long permissionId) {
        PermissionUpdateResponse response = new PermissionUpdateResponse();
        try {
            logger.info("updatePermission [Init] - PermissionId: {}", permissionId);
            Optional<Permission> permissionOptional = permissionRepository.findById(permissionId);
            if (permissionOptional.isPresent()) {
                Permission permissionToUpdate = permissionOptional.get();
                permissionToUpdate.setName(permissionUpdateRequest.getName());
                permissionToUpdate.setDescription(permissionUpdateRequest.getDescription());
                permissionRepository.save(permissionToUpdate);

                response.setErrorResponse(permissionToUpdate, ErrorCodes.SUCCESS.getCode(), "Permission updated successfully");
            } else {
                response.setErrorResponse(null, ErrorCodes.NOT_FOUND.getCode(), "Permission not found");
            }
        } catch (Exception ex) {
            logger.error("An error occurred while updating the permission. Exception: {}", ex.getMessage(), ex);
            response.setErrorResponse(null, ErrorCodes.EXCEPTION.getCode(), "An error occurred while updating the permission");
        } finally {
            logger.info("updatePermission [Fin]");
        }
        return response;
    }

    @Override
    public PermissionDeleteResponse deletePermission(Long permissionId) {
        PermissionDeleteResponse response = new PermissionDeleteResponse();
        try {
            logger.info("deletePermission [Init] - PermissionId: {}", permissionId);
            if (permissionRepository.existsById(permissionId)) {
                permissionRepository.deleteById(permissionId);
                response.setErrorResponse(permissionId, ErrorCodes.SUCCESS.getCode(), "Permission deleted successfully");
            } else {
                response.setErrorResponse(null, ErrorCodes.NOT_FOUND.getCode(), "Permission not found");
            }
        } catch (Exception ex) {
            logger.error("An error occurred while deleting the permission. Exception: {}", ex.getMessage(), ex);
            response.setErrorResponse(null, ErrorCodes.EXCEPTION.getCode(), "An error occurred while deleting the permission");
        } finally {
            logger.info("deletePermission [Fin]");
        }
        return response;
    }

    @Override
    public PermissionListByIdResponse getPermissionById(Long permissionId) {
        PermissionListByIdResponse response = new PermissionListByIdResponse();
        try {
            logger.info("getPermissionById [Init] - PermissionId: {}", permissionId);
            Optional<Permission> permissionOptional = permissionRepository.findById(permissionId);
            if (permissionOptional.isPresent()) {
                response.setErrorResponse(permissionOptional.get(), ErrorCodes.SUCCESS.getCode(), "Permission found successfully");
            } else {
                response.setErrorResponse(null, ErrorCodes.NOT_FOUND.getCode(), "Permission not found");
            }
        } catch (Exception ex) {
            logger.error("An error occurred while fetching the permission. Exception: {}", ex.getMessage(), ex);
            response.setErrorResponse(null, ErrorCodes.EXCEPTION.getCode(), "An error occurred while fetching the permission");
        } finally {
            logger.info("getPermissionById [Fin]");
        }
        return response;
    }

    @Override
    public PermissionListAllResponse getAllPermissions() {
        PermissionListAllResponse response = new PermissionListAllResponse();
        try {
            logger.info("getAllPermissions [Init]");
            List<Permission> permissions = permissionRepository.findAll();
            if (!permissions.isEmpty()) {
                response.setErrorResponse(permissions, ErrorCodes.SUCCESS.getCode(), "Permissions retrieved successfully");
            } else {
                response.setErrorResponse(null, ErrorCodes.NOT_FOUND.getCode(), "No permissions found");
            }
        } catch (Exception ex) {
            logger.error("An error occurred while fetching all permissions. Exception: {}", ex.getMessage(), ex);
            response.setErrorResponse(null, ErrorCodes.EXCEPTION.getCode(), "An error occurred while fetching all permissions");
        } finally {
            logger.info("getAllPermissions [Fin]");
        }
        return response;
    }
}
