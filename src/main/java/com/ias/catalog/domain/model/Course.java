package com.ias.catalog.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Entidad central del dominio.
 * Representa un curso con título, categoría, imagen y descripción.
 *
 * Inmutable mediante Lombok — no hay setters.
 * Se construye con el patrón Builder para mantener limpieza en la creación.
 */
@Getter
@Builder
@AllArgsConstructor
public class Course {

    private final Long id;
    private final String title;
    private final String description;
    private final CourseCategory category;
    private final String imageUrl;
    private final String themes;
    private final String link;
}
