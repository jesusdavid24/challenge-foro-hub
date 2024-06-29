package com.alura.cursos.forohub.domain.users;

public record DataListUser(
  Long id,
  String name,
  String email
) {

  public DataListUser(User user) {
    this(
      user.getId(),
      user.getName(),
      user.getEmail()
    );
  }
}
