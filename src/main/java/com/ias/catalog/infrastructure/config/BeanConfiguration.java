package com.ias.catalog.infrastructure.config;

import com.ias.catalog.domain.port.in.CourseUseCase;
import com.ias.catalog.domain.port.out.CourseRepositoryPort;
import com.ias.catalog.domain.service.CourseService;
import com.ias.catalog.infrastructure.adapter.out.persistence.PostgresCourseRepositoryAdapter;
import com.ias.catalog.infrastructure.adapter.out.persistence.repository.CourseJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CourseRepositoryPort courseRepositoryPort(CourseJpaRepository courseJpaRepository) {
        return new PostgresCourseRepositoryAdapter(courseJpaRepository);
    }

    @Bean
    public CourseUseCase courseUseCase(CourseRepositoryPort courseRepositoryPort) {
        return new CourseService(courseRepositoryPort);
    }
}
