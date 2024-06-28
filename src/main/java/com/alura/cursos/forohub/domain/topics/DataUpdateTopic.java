package com.alura.cursos.forohub.domain.topics;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataUpdateTopic(
  @NotNull
  Long id,
  @NotBlank
  String title,
  @NotBlank
  String message
) {
}
