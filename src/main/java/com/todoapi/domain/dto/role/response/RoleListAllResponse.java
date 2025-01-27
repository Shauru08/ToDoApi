package com.todoapi.domain.dto.role.response;

import com.google.gson.Gson;
import com.todoapi.domain.dto.base.BaseResponse;
import com.todoapi.domain.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleListAllResponse extends BaseResponse {

    private List<Role> roleList;

    public RoleListAllResponse setErrorResponse(List<Role> roleList, String errorCode, String errorMessage) {
        this.roleList = roleList;
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
