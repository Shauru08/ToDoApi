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
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id;

    @NotNull
    @Schema(description = "Permission name", example = "READ_TASKS", required = true)
    private String name;

    @Schema(description = "Description of the permission", example = "Allows reading tasks", required = false)
    private String description;

    @ManyToMany(mappedBy = "permissions")
    @Schema(hidden = true, required = false)
    private List<Role> roles;
}
