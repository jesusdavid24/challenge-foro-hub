package com.alura.cursos.forohub.domain.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataUpdateUser(
  @NotNull
  Long id,
  @NotBlank
  String name,
  @NotBlank
  String email,
  @NotBlank
  String password
) {
}
