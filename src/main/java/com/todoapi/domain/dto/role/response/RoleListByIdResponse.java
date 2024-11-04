package com.todoapi.domain.dto.role.response;

import com.google.gson.Gson;
import com.todoapi.domain.dto.base.BaseResponse;
import com.todoapi.domain.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleListByIdResponse extends BaseResponse {

    private Role role;

    // Constructor vacío
    public RoleListByIdResponse() {
    }

    // Método para establecer la respuesta de error o éxito
    public void setErrorResponse(Role role, String errorCode, String errorMessage) {
        this.role = role;
        this.setErrorCode(errorCode);
        this.setErrorMessage(errorMessage);
    }

    // Método toString para convertir a JSON
    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
