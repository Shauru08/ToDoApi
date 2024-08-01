package com.todoapi.domain.dto.task.response;

import com.google.gson.Gson;
import com.todoapi.domain.dto.base.BaseResponse;
import com.todoapi.domain.entity.Task;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TaskListAllResponse extends BaseResponse {

    private List<Task> taskList;

    public TaskListAllResponse setErrorResponse(List<Task> taskList, String errorCode, String errorMessage) {
        this.taskList = taskList;
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
