package com.alura.cursos.forohub.domain.topics;

import com.alura.cursos.forohub.domain.courses.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
  Optional<Topic> findByTitleAndMessage(String title, String message);
  Page<Topic> findByIsDeletedFalse(Pageable pageable);
  Page<Topic> findByCourseAndDateCreationAfter(Course course, LocalDateTime dateCreation, Pageable pageable);
}

