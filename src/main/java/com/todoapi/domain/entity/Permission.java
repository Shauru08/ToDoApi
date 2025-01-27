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
    @Schema(hidden = true)  // No se mostrará en la documentación de Swagger
    private Long id;

    @NotNull
    @Schema(description = "Permission name", example = "READ_TASKS", required = true)
    private String name;

    @Schema(description = "Description of the permission", example = "Allows reading tasks", required = false)
    private String description;

    @ManyToMany(mappedBy = "permissions")
    @Schema(hidden = true)  // Las relaciones ManyToMany suelen estar ocultas en la API pública
    private List<Role> roles;

    // Constructor vacío
    public Permission() {
    }

    // Constructor para facilitar la creación de permisos desde el DTO (opcional)
    public Permission(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
