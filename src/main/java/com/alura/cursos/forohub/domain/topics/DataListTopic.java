package com.alura.cursos.forohub.domain.topics;

import java.time.LocalDateTime;

public record DataListTopic(
  String title,
  String message,
  LocalDateTime dateCreation,
  TopicStatus status,
  Long idUser,
  Long idCourse
) {

  public DataListTopic(Topic topic) {
    this(
      topic.getTitle(),
      topic.getMessage(),
      topic.getDateCreation(),
      topic.getStatus(),
      topic.getUser().getId(),
      topic.getCourse().getId()
    );
  }
}
