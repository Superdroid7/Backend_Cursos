package com.ias.catalog.domain.port.in;

import com.ias.catalog.domain.model.Course;
import com.ias.catalog.domain.model.CourseCategory;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de entrada (Driving Port).
 * Define los casos de uso disponibles para el dominio.
 *
 * Cualquier actor externo (REST controller, CLI, scheduler, etc.)
 * interactúa con el dominio ÚNICAMENTE a través de esta interfaz.
 */
public interface CourseUseCase {

    /**
     * Retorna todos los cursos disponibles.
     */
    List<Course> findAll();

    /**
     * Busca cursos cuyo título contiene la palabra clave dada.
     * La búsqueda es insensible a mayúsculas/minúsculas.
     */
    List<Course> searchByKeyword(String keyword);

    /**
     * Filtra cursos por categoría.
     */
    List<Course> filterByCategory(CourseCategory category);

    /**
     * Búsqueda combinada: keyword Y/O categoría.
     * Si ambos son null/vacíos, retorna todos.
     */
    List<Course> search(String keyword, CourseCategory category);

    /**
     * Retorna un curso por su ID único.
     */
    Optional<Course> findById(Long id);
}
