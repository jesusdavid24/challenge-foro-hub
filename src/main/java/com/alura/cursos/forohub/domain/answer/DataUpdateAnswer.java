package com.alura.cursos.forohub.domain.answer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataUpdateAnswer(
  @NotNull
  Long id,
  @NotBlank
  String message,
  @NotNull
  Long idTopic,
  @NotNull
  Long idUser

) {
}
