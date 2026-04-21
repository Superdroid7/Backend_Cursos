package com.ias.catalog.infrastructure.adapter.out.persistence;

import com.ias.catalog.domain.model.Course;
import com.ias.catalog.domain.model.CourseCategory;
import com.ias.catalog.domain.port.out.CourseRepositoryPort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adaptador de salida — implementación en memoria del puerto de repositorio.
 *
 * Contiene los datos que el frontend muestra:
 * Learn Python, HTML/CSS, JavaScript, Docker, AWS, JUnit.
 *
 * Al estar en infraestructura, es fácil reemplazar este adaptador por
 * uno que use JPA + PostgreSQL sin tocar NADA en el dominio.
 */
public class InMemoryCourseRepository implements CourseRepositoryPort {

    private static final List<Course> COURSES = List.of(
            Course.builder()
                    .id(1L)
                    .title("Learn Python")
                    .description("Aprende programación con Python desde cero: variables, funciones, OOP y más.")
                    .category(CourseCategory.PROGRAMMING)
                    .imageUrl("https://upload.wikimedia.org/wikipedia/commons/c/c3/Python-logo-notext.svg")
                    .build(),

            Course.builder()
                    .id(2L)
                    .title("HTML/CSS")
                    .description("Domina el maquetado web con HTML5 semántico y diseño responsivo con CSS3.")
                    .category(CourseCategory.DESIGN)
                    .imageUrl("https://upload.wikimedia.org/wikipedia/commons/6/61/HTML5_logo_and_wordmark.svg")
                    .build(),

            Course.builder()
                    .id(3L)
                    .title("JavaScript")
                    .description("El lenguaje del navegador: ES6+, asincronía, DOM y fundamentos de Node.js.")
                    .category(CourseCategory.PROGRAMMING)
                    .imageUrl("https://upload.wikimedia.org/wikipedia/commons/6/6a/JavaScript-logo.png")
                    .build(),

            Course.builder()
                    .id(4L)
                    .title("Docker")
                    .description("Contenerización de aplicaciones: imágenes, contenedores, Docker Compose y buenas prácticas.")
                    .category(CourseCategory.PROGRAMMING)
                    .imageUrl("https://upload.wikimedia.org/wikipedia/commons/4/4e/Docker_%28container_engine%29_logo.svg")
                    .build(),

            Course.builder()
                    .id(5L)
                    .title("AWS")
                    .description("Amazon Web Services: EC2, S3, Lambda, RDS y arquitecturas cloud escalables.")
                    .category(CourseCategory.CLOUD)
                    .imageUrl("https://upload.wikimedia.org/wikipedia/commons/9/93/Amazon_Web_Services_Logo.svg")
                    .build(),

            Course.builder()
                    .id(6L)
                    .title("JUnit")
                    .description("Testing en Java: assertions, mocks con Mockito, TDD y cobertura de código.")
                    .category(CourseCategory.TESTING)
                    .imageUrl("https://junit.org/junit5/assets/img/junit5-logo.png")
                    .build()
    );

    @Override
    public List<Course> findAll() {
        return COURSES;
    }

    @Override
    public List<Course> findByKeyword(String keyword) {
        String lower = keyword.toLowerCase();
        return COURSES.stream()
                .filter(c -> c.getTitle().toLowerCase().contains(lower)
                        || c.getDescription().toLowerCase().contains(lower))
                .collect(Collectors.toList());
    }

    @Override
    public List<Course> findByCategory(CourseCategory category) {
        return COURSES.stream()
                .filter(c -> c.getCategory() == category)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Course> findById(Long id) {
        return COURSES.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }
}
