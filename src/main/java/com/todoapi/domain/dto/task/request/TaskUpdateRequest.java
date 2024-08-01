package com.todoapi.domain.dto.task.request;

import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TaskUpdateRequest {

    @Schema(description = "Title of the task", example = "Updated Task Title")
    private String title;

    @Schema(description = "Description of the task", example = "Updated Task Description")
    private String description;

    @FutureOrPresent(message = "Start date must be in the present or future")
    @Schema(description = "Start date of the task", example = "2024-08-01T00:00:00")
    private Date startDate;

    @FutureOrPresent(message = "End date must be in the present or future")
    @Schema(description = "End date of the task", example = "2024-08-10T00:00:00")
    private Date endDate;

    @PositiveOrZero(message = "Status must be zero or positive")
    @Schema(description = "Status of the task", example = "1")
    private Integer status;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
