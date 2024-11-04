package com.todoapi.repository;

import com.todoapi.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Puedes agregar métodos personalizados aquí si es necesario
}
