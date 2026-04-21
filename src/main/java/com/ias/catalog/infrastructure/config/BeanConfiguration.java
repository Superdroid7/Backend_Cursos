package com.ias.catalog.infrastructure.config;

import com.ias.catalog.domain.port.in.CourseUseCase;
import com.ias.catalog.domain.port.out.CourseRepositoryPort;
import com.ias.catalog.domain.service.CourseService;
import com.ias.catalog.infrastructure.adapter.out.persistence.InMemoryCourseRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de Beans — el "pegamento" de la arquitectura hexagonal.
 *
 * Aquí se ensamblan las piezas: se crea el repositorio, se inyecta en el servicio,
 * y el servicio queda disponible como bean de Spring (CourseUseCase).
 *
 * El dominio NO sabe de Spring — esta clase es la única que conoce todo.
 */
@Configuration
public class BeanConfiguration {

    @Bean
    public CourseRepositoryPort courseRepositoryPort() {
        return new InMemoryCourseRepository();
    }

    @Bean
    public CourseUseCase courseUseCase(CourseRepositoryPort courseRepositoryPort) {
        return new CourseService(courseRepositoryPort);
    }
}
