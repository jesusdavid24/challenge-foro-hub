package com.alura.cursos.forohub.domain.topics;

import java.time.LocalDateTime;

public record TopicSearchRequest(
  String courseName,
  String dateCreation
) {
}
