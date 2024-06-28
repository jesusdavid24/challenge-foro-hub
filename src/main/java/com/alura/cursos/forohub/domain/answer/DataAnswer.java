package com.alura.cursos.forohub.domain.answer;

public record DataAnswer(
  Long id,
  String message,
  Boolean solved,
  Long idTopic,
  Long idUser
) {
  public DataAnswer(Answer answer) {
    this(
      answer.getId(),
      answer.getMessage(),
      answer.getSolved(),
      answer.getUser().getId(),
      answer.getTopics().getId()
    );
  }
}
