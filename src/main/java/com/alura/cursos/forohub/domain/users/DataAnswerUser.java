package com.alura.cursos.forohub.domain.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DataAnswerUser(
  Long id,
  String name,
  String email,
  String password
) {
  public DataAnswerUser(User user) {
    this(
      user.getId(),
      user.getName(),
      user.getEmail(),
      user.getPassword()
    );
  }
}
