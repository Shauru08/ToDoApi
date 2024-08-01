package com.todoapi.domain.dto.task.request;

import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TaskCreateRequest {

    @NotBlank(message = "Title cannot be blank")
    @Schema(description = "Title of the task", example = "My Task")
    private String title;

    @Schema(description = "Description of the task", example = "Details about the task")
    private String description;

    @NotNull(message = "Start date cannot be null")
    @FutureOrPresent(message = "Start date must be in the present or future")
    @Schema(description = "Start date of the task", example = "2024-08-01")
    private Date startDate;

    @NotNull(message = "End date cannot be null")
    @FutureOrPresent(message = "End date must be in the present or future")
    @Schema(description = "End date of the task", example = "2024-08-10")
    private Date endDate;

    @PositiveOrZero(message = "Status must be zero or positive")
    @Schema(description = "Status of the task", example = "1")
    private int status;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
