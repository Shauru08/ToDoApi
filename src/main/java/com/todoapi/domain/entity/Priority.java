package com.todoapi.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Priority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id;

    @NotNull
    @Schema(description = "Priority level", example = "HIGH", required = true)
    private String level;

    @Schema(description = "Description of the priority", example = "High priority tasks", required = false)
    private String description;

    @OneToMany(mappedBy = "priority")
    @Schema(hidden = true, required = false)
    private List<Task> tasks;
}
