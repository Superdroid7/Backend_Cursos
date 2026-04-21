package com.ias.catalog.infrastructure.adapter.out.persistence.repository;

import com.ias.catalog.infrastructure.adapter.out.persistence.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseJpaRepository extends JpaRepository<CourseEntity, Long> {

    List<CourseEntity> findByCategory(String category);

    @Query("SELECT c FROM CourseEntity c WHERE LOWER(c.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(c.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<CourseEntity> findByKeyword(@Param("keyword") String keyword);
}
