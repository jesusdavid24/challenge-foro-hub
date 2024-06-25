package com.alura.cursos.forohub.domain.topics;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataCreateTopic(
  @NotBlank
  String title,
  @NotBlank
  String message,
  @NotNull
  Long idUser,
  @NotNull
  Long idCourse
) {
}
