package com.todoapi.domain.dto.task.response;

import com.google.gson.Gson;
import com.todoapi.domain.dto.base.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskCreateResponse extends BaseResponse {

    private Long taskId;

    public TaskCreateResponse setErrorResponse(Long taskId, String errorCode, String errorMessage) {
        this.setTaskId(taskId);
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
