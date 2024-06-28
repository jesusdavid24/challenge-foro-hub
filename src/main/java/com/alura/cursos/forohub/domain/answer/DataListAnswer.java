package com.alura.cursos.forohub.domain.answer;

import java.time.LocalDateTime;

public record DataListAnswer(
  Long id,
  String message,
  Boolean solved,
  Long idTopic,
  Long idUser,
  LocalDateTime dateCreation
) {
  public  DataListAnswer(Answer answer) {
    this(
      answer.getId(),
      answer.getMessage(),
      answer.getSolved(),
      answer.getUser().getId(),
      answer.getTopics().getId(),
      answer.getDateCreation()
    );
  }
}
