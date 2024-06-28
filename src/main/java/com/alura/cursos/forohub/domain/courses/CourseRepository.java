package com.alura.cursos.forohub.domain.courses;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
  Course findByName(String name);
  Page<Course> findByIsDeletedFalse(Pageable pageable);
  Optional<Course> findByNameIgnoreCase(String name);
}
