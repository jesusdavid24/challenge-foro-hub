package com.alura.cursos.forohub.domain.topics;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
  Optional<Topic> findByTitleAndMessage(String title, String message);
  Page<Topic> findByIsDeletedFalse(Pageable pageable);
}
