package com.ias.catalog.infrastructure.adapter.in.web.dto;

import com.ias.catalog.domain.model.Course;
import lombok.Builder;
import lombok.Getter;

/**
 * DTO de respuesta REST para un Course.
 *
 * Separa el contrato HTTP del modelo de dominio:
 * si cambia el dominio, el JSON que ve el frontend NO cambia (y viceversa).
 */
@Getter
@Builder
public class CourseResponse {

    private Long id;
    private String title;
    private String description;
    private String category;
    private String imageUrl;

    /**
     * Factory method: convierte un Course de dominio en un DTO listo para serializar.
     */
    public static CourseResponse fromDomain(Course course) {
        return CourseResponse.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .category(course.getCategory().name())
                .imageUrl(course.getImageUrl())
                .build();
    }
}
