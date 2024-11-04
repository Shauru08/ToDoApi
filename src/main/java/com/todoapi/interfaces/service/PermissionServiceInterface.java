package com.todoapi.interfaces.service;

import com.todoapi.domain.dto.permission.request.PermissionCreateRequest;
import com.todoapi.domain.dto.permission.request.PermissionUpdateRequest;
import com.todoapi.domain.dto.permission.response.*;

public interface PermissionServiceInterface {

    PermissionCreateResponse addPermission(PermissionCreateRequest permissionCreateRequest);

    PermissionUpdateResponse updatePermission(PermissionUpdateRequest permissionUpdateRequest, Long permissionId);

    PermissionDeleteResponse deletePermission(Long permissionId);

    PermissionListByIdResponse getPermissionById(Long permissionId);

    PermissionListAllResponse getAllPermissions();
}
