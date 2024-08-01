package com.todoapi.domain.entity;

import com.google.gson.Gson;
import com.todoapi.domain.dto.task.request.TaskCreateRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Date startDate;

    private Date endDate;

    private int status;

    // Relación con User (Muchos a Uno)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Relación con Category (Muchos a Muchos)
    @ManyToMany
    @JoinTable(
            name = "task_category",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    // Relación con Priority (Muchos a Uno)
    @ManyToOne
    @JoinColumn(name = "priority_id")
    private Priority priority;

    // Otros campos y métodos (si es necesario)...

    public Task() {
    }

    public Task(TaskCreateRequest taskCreateRequest) {
        this.title = taskCreateRequest.getTitle();
        this.description = taskCreateRequest.getDescription();
        this.startDate = taskCreateRequest.getStartDate();
        this.endDate = taskCreateRequest.getEndDate();
        this.status = taskCreateRequest.getStatus();
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
