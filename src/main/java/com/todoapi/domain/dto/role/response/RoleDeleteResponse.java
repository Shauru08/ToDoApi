package com.todoapi.domain.dto.role.response;

import com.google.gson.Gson;
import com.todoapi.domain.dto.base.BaseResponse;
import com.todoapi.domain.dto.task.response.TaskDeleteResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDeleteResponse extends BaseResponse {

    private Long roleId;

    public RoleDeleteResponse setErrorResponse(Long roleId, String errorCode, String errorMessage) {
        this.setRoleId(roleId);
        this.setErrorCode(errorCode);
        this.setErrorMessage(errorMessage);
        return this;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
