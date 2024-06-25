package com.alura.cursos.forohub.domain.topics;

import java.time.LocalDateTime;

public record DataAnswerTopic(
  Long id,
  String title,
  String message,
  LocalDateTime dateCreation,
  TopicStatus status,
  Long idUser,
  Long idCourse,
  Boolean isDeleted
) {

  public DataAnswerTopic(Topic topic) {
    this(
      topic.getId(),
      topic.getTitle(),
      topic.getMessage(),
      topic.getDateCreation(),
      topic.getStatus(),
      topic.getUser().getId(),
      topic.getCourse().getId(),
      topic.getIsDeleted()
    );

  }
}
