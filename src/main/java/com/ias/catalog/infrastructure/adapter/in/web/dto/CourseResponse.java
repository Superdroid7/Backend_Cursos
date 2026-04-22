package com.ias.catalog.infrastructure.adapter.in.web.dto;

import com.ias.catalog.domain.model.Course;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Data Transfer Object for Course responses")
public class CourseResponse {

    @Schema(description = "Unique identifier of the course")
    private Long id;
    @Schema(description = "The title of the course")
    private String title;
    @Schema(description = "A brief description of the course")
    private String description;
    @Schema(description = "Category of the course")
    private String category;
    @Schema(description = "URL of the course image")
    private String imageUrl;
    @Schema(description = "Themes or programming languages covered in the course")
    private String themes;
    @Schema(description = "URL link that leads to the course")
    private String link;

    /**
     * Factory method: convierte un Course de dominio en un DTO listo para serializar.
     */
    public static CourseResponse fromDomain(Course course) {
        return CourseResponse.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .category(course.getCategory() != null ? course.getCategory().name() : null)
                .imageUrl(course.getImageUrl())
                .themes(course.getThemes())
                .link(course.getLink())
                .build();
    }
}
