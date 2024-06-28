package com.alura.cursos.forohub.domain.courses;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataCreateCourse(
  @NotBlank
  String name,
  @NotNull
  Category category
) {
}
