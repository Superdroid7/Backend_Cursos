package com.ias.catalog.infrastructure.adapter.in.web.dto;

import com.ias.catalog.domain.model.Course;
import com.ias.catalog.domain.model.CourseCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Data Transfer Object for creating a new Course")
public class CourseRequest {
    @Schema(description = "The title of the course", example = "Cursos para principiantes de Java", required = true)
    private String title;
    @Schema(description = "A brief description of the course", example = "Aprende Java desde cero")
    private String description;
    @Schema(description = "Category of the course (PROGRAMMING, DESIGN, CLOUD, TESTING)", example = "PROGRAMMING")
    private String category;
    @Schema(description = "URL of the course image to display in cards", example = "https://example.com/java.png")
    private String imageUrl;
    @Schema(description = "Themes or programming languages covered in the course", example = "Java, Spring Boot")
    private String themes;
    @Schema(description = "URL link that leads to the course", example = "https://example.com/course/java")
    private String link;

    public Course toDomain() {
        CourseCategory courseCategory = null;
        if (category != null && !category.isBlank()) {
            try {
                courseCategory = CourseCategory.valueOf(category.toUpperCase());
            } catch (IllegalArgumentException e) {
                // Ignore or handle invalid category
            }
        }

        return Course.builder()
                .title(title)
                .description(description)
                .category(courseCategory)
                .imageUrl(imageUrl)
                .themes(themes)
                .link(link)
                .build();
    }
}
