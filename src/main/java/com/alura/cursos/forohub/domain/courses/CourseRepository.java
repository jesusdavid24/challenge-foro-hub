package com.alura.cursos.forohub.domain.courses;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
  Course findByName(String name);
}
