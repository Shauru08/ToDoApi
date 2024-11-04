package com.todoapi.domain.dto.priority.response;

import com.google.gson.Gson;
import com.todoapi.domain.dto.base.BaseResponse;
import com.todoapi.domain.entity.Priority;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PriorityListAllResponse extends BaseResponse {

    @Schema(description = "List of all priorities")
    private List<Priority> priorities;

    // Método para establecer la respuesta de éxito o error
    public PriorityListAllResponse setErrorResponse(List<Priority> priorities, String errorCode, String errorMessage) {
        this.priorities = priorities;
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
