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

    // Many tasks may have one status
    @ManyToOne
    @JoinColumn(name = "task_status_id") // Correct column name
    private TaskStatus taskStatus;

    // Many tasks may belong to one user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Many tasks may belong to many categories
    @ManyToMany
    @JoinTable(
            name = "task_category",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    // Many tasks may have one priority
    @ManyToOne
    @JoinColumn(name = "priority_id")
    private Priority priority;

    //Methods
    public Task() {
    }

    public Task(TaskCreateRequest taskCreateRequest, TaskStatus defaultStatus, Priority priority, User user, List<Category> categories) {
        this.title = taskCreateRequest.getTitle();
        this.description = taskCreateRequest.getDescription();
        this.startDate = taskCreateRequest.getStartDate();
        this.endDate = taskCreateRequest.getEndDate();
        this.taskStatus = defaultStatus;
        this.priority = priority;
        this.user = user;
        this.categories = categories;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
