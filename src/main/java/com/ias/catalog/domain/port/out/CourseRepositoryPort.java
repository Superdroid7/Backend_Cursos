package com.ias.catalog.domain.port.out;

import com.ias.catalog.domain.model.Course;
import com.ias.catalog.domain.model.CourseCategory;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida (Driven Port).
 * El dominio define QUÉ necesita del almacenamiento, sin saber CÓMO está implementado.
 *
 * La implementación concreta vive en la capa de infraestructura.
 */
public interface CourseRepositoryPort {

    List<Course> findAll();

    List<Course> findByKeyword(String keyword);

    List<Course> findByCategory(CourseCategory category);

    Optional<Course> findById(Long id);
}
