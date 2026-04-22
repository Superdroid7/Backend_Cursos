package com.ias.catalog.infrastructure.adapter.in.web;

import com.ias.catalog.domain.model.Course;
import com.ias.catalog.domain.model.CourseCategory;
import com.ias.catalog.domain.port.in.CourseUseCase;
import com.ias.catalog.infrastructure.adapter.in.web.dto.CourseRequest;
import com.ias.catalog.infrastructure.adapter.in.web.dto.CourseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adaptador de entrada REST (Driving Adapter).
 *
 * Expone los endpoints que el frontend Angular consumirá.
 * Se comunica con el dominio SOLO a través de CourseUseCase (puerto de entrada).
 *
 * Endpoints:
 *   GET  /api/courses              → todos los cursos
 *   GET  /api/courses?search=X     → busca por keyword en título/descripción
 *   GET  /api/courses?category=X   → filtra por categoría
 *   GET  /api/courses?search=X&category=Y → búsqueda combinada
 *   GET  /api/courses/{id}         → curso por ID
 */
@RestController
@RequestMapping("/api/courses")
@CrossOrigin("*")
@RequiredArgsConstructor
@Tag(name = "Courses", description = "Endpoints for managing the course catalog")
public class CourseController {

    private final CourseUseCase courseUseCase;

    /**
     * Lista todos los cursos, con filtros opcionales de búsqueda y categoría.
     */
    @GetMapping
    @Operation(summary = "Get all courses", description = "Retrieves a list of all courses, optionally filtered by search keyword or category.")
    public ResponseEntity<List<CourseResponse>> getCourses(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String category
    ) {
        CourseCategory courseCategory = parseCategory(category);
        List<Course> courses = courseUseCase.search(search, courseCategory);

        List<CourseResponse> response = courses.stream()
                .map(CourseResponse::fromDomain)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    /**
     * Retorna un curso específico por su ID.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a course by ID", description = "Retrieves the details of a specific course using its unique ID.")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable Long id) {
        Optional<Course> course = courseUseCase.findById(id);
        return course
                .map(c -> ResponseEntity.ok(CourseResponse.fromDomain(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea un nuevo curso.
     */
    @PostMapping
    @Operation(summary = "Create a new course", description = "Creates a new course with title, themes, link, etc.")
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseRequest courseRequest) {
        Course newCourse = courseUseCase.createCourse(courseRequest.toDomain());
        return ResponseEntity.status(HttpStatus.CREATED).body(CourseResponse.fromDomain(newCourse));
    }

    // Convierte el String del query param al enum CourseCategory
    private CourseCategory parseCategory(String category) {
        if (category == null || category.isBlank()) {
            return null;
        }
        try {
            return CourseCategory.valueOf(category.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
