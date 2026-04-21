package com.ias.catalog.domain.service;

import com.ias.catalog.domain.model.Course;
import com.ias.catalog.domain.model.CourseCategory;
import com.ias.catalog.domain.port.in.CourseUseCase;
import com.ias.catalog.domain.port.out.CourseRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servicio de dominio — implementa los casos de uso de CourseUseCase.
 *
 * NO usa @Service de Spring aquí. La inyección de dependencias
 * se declara explícitamente en BeanConfiguration (infraestructura),
 * manteniendo el dominio libre de frameworks.
 */
@RequiredArgsConstructor
public class CourseService implements CourseUseCase {

    private final CourseRepositoryPort courseRepositoryPort;

    @Override
    public List<Course> findAll() {
        return courseRepositoryPort.findAll();
    }

    @Override
    public List<Course> searchByKeyword(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return courseRepositoryPort.findAll();
        }
        return courseRepositoryPort.findByKeyword(keyword.trim());
    }

    @Override
    public List<Course> filterByCategory(CourseCategory category) {
        if (category == null) {
            return courseRepositoryPort.findAll();
        }
        return courseRepositoryPort.findByCategory(category);
    }

    @Override
    public List<Course> search(String keyword, CourseCategory category) {
        boolean hasKeyword = keyword != null && !keyword.isBlank();
        boolean hasCategory = category != null;

        if (!hasKeyword && !hasCategory) {
            return courseRepositoryPort.findAll();
        }

        List<Course> byKeyword = hasKeyword
                ? courseRepositoryPort.findByKeyword(keyword.trim())
                : courseRepositoryPort.findAll();

        if (!hasCategory) {
            return byKeyword;
        }

        // Aplica filtro de categoría sobre el resultado previo
        return byKeyword.stream()
                .filter(course -> course.getCategory() == category)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Course> findById(Long id) {
        return courseRepositoryPort.findById(id);
    }
}
