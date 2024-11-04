package com.todoapi.repository;

import com.todoapi.domain.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    // No es necesario implementar nada adicional por ahora,
    // JpaRepository proporciona los métodos CRUD básicos.
}
