package com.alura.cursos.forohub.domain.courses;

public record DataAnswerCourse(
  Long id,
  String name,
  Category category,
  Boolean isDeleted

) {

  public DataAnswerCourse(Course course) {
    this(
      course.getId(),
      course.getName(),
      course.getCategory(),
      course.getIsDeleted()
    );
  }
}
