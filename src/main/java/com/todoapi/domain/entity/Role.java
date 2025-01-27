package com.todoapi.domain.entity;

import com.todoapi.domain.dto.role.request.RoleCreateRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id;

    @NotNull
    @Schema(description = "Role name", example = "USER", required = true)
    private String name;

    @Schema(description = "Description of the role", example = "Regular user with basic privileges", required = false)
    private String description;

    @ManyToMany(mappedBy = "roles")
    @Schema(hidden = true, required = false)
    private List<User> users;

    @ManyToMany
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    @Schema(hidden = true, required = false)
    private List<Permission> permissions;

    public Role(RoleCreateRequest roleCreateRequest) {
        this.name = roleCreateRequest.getName();
        this.description = roleCreateRequest.getDescription();
    }

    public Role() {
    }
}
