package com.todoapi.interfaces.service;

import com.todoapi.domain.dto.role.request.RoleCreateRequest;
import com.todoapi.domain.dto.role.request.RoleUpdateRequest;
import com.todoapi.domain.dto.role.response.RoleCreateResponse;
import com.todoapi.domain.dto.role.response.RoleDeleteResponse;
import com.todoapi.domain.dto.role.response.RoleListAllResponse;
import com.todoapi.domain.dto.role.response.RoleListByIdResponse;
import com.todoapi.domain.dto.role.response.RoleUpdateResponse;

public interface RoleServiceInterface {

    RoleCreateResponse addRole(RoleCreateRequest roleCreateRequest);

    RoleUpdateResponse updateRole(RoleUpdateRequest roleUpdateRequest, Long roleId);

    RoleDeleteResponse deleteRole(Long roleId);

    RoleListByIdResponse getRoleById(Long roleId);

    RoleListAllResponse getAllRoles();
}
