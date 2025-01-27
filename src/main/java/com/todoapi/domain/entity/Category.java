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
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id;

    @NotNull
    @Schema(description = "Category name", example = "Work", required = true)
    private String name;

    @Schema(description = "Description of the category", example = "Tasks related to work", required = false)
    private String description;

    @ManyToMany(mappedBy = "categories")
    @Schema(hidden = true, required = false)
    private List<Task> tasks;
}