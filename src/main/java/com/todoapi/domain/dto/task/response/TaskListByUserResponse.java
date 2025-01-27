package com.todoapi.domain.dto.task.response;

import com.google.gson.Gson;
import com.todoapi.domain.dto.base.BaseResponse;
import com.todoapi.domain.entity.Task;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class TaskListByUserResponse extends BaseResponse {
    private List<Task> userTasks;

    public TaskListByUserResponse setErrorResponse(List<Task> userTasks, String errorCode, String errorMessage) {
        this.userTasks = userTasks;
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
