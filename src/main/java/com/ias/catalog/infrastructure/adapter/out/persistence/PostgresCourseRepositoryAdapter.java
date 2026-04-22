package com.ias.catalog.infrastructure.adapter.out.persistence;

import com.ias.catalog.domain.model.Course;
import com.ias.catalog.domain.model.CourseCategory;
import com.ias.catalog.domain.port.out.CourseRepositoryPort;
import com.ias.catalog.infrastructure.adapter.out.persistence.entity.CourseEntity;
import com.ias.catalog.infrastructure.adapter.out.persistence.repository.CourseJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adaptador de salida para PostgreSQL (JPA).
 * Sustituye a InMemoryCourseRepository para usar la base de datos real.
 */
@RequiredArgsConstructor
public class PostgresCourseRepositoryAdapter implements CourseRepositoryPort {

    private final CourseJpaRepository courseJpaRepository;

    @Override
    public List<Course> findAll() {
        return courseJpaRepository.findAll().stream()
                .map(this::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Course> findByKeyword(String keyword) {
        return courseJpaRepository.findByKeyword(keyword).stream()
                .map(this::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Course> findByCategory(CourseCategory category) {
        return courseJpaRepository.findByCategory(category.name()).stream()
                .map(this::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Course> findById(Long id) {
        return courseJpaRepository.findById(id).map(this::toDomainModel);
    }

    @Override
    public Course save(Course course) {
        CourseEntity entity = toEntity(course);
        CourseEntity savedEntity = courseJpaRepository.save(entity);
        return toDomainModel(savedEntity);
    }

    // Mapper manual (Entity -> Domain)
    private Course toDomainModel(CourseEntity entity) {
        return Course.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .category(entity.getCategory() != null ? CourseCategory.valueOf(entity.getCategory()) : null)
                .imageUrl(entity.getImageUrl())
                .themes(entity.getThemes())
                .link(entity.getLink())
                .build();
    }

    // Mapper manual (Domain -> Entity)
    private CourseEntity toEntity(Course course) {
        return CourseEntity.builder()
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
