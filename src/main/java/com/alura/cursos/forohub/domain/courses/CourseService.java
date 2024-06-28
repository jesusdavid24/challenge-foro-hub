package com.alura.cursos.forohub.domain.courses;

import com.alura.cursos.forohub.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {

  @Autowired
  private CourseRepository courseRepository;

  public DataAnswerCourse createCourse(DataCreateCourse dataCreateCourse) {
    Optional<Course> existingCourse = Optional.ofNullable(courseRepository.findByName(dataCreateCourse.name()));

    if (existingCourse.isPresent()) {
      throw new IntegrityValidation("Course already exists");
    }

    Course newCourse = new Course(dataCreateCourse.name(), dataCreateCourse.category());
    courseRepository.save(newCourse);
    return new DataAnswerCourse(newCourse);
  }

  public Page<DataListCourse> getCourse(Pageable pageable) {
    Page<Course> coursePage = courseRepository.findByIsDeletedFalse(pageable);
    return coursePage.map(DataListCourse::new);
  }

  public DataListCourse getCourseByName(String courseName) {
    Optional<Course> existingCourse = courseRepository.findByNameIgnoreCase(courseName);

    if (existingCourse.isEmpty()) {
      throw new IntegrityValidation("Course does not exist");
    }

    return new DataListCourse(existingCourse.get());
  }

  public DataAnswerCourse updateCourse(DataUpdateCourse dataUpdateCourse) {
    if (!courseRepository.findById(dataUpdateCourse.id()).isPresent()) {
      throw new IntegrityValidation("id course not found");
    }

    Course course = courseRepository.getReferenceById(dataUpdateCourse.id());
    course.putData(dataUpdateCourse);

    return new DataAnswerCourse(course);
  }

  public void deletedCourse(Long id) {
    Course course = courseRepository.findById(id)
      .orElseThrow(() -> new IntegrityValidation("Course not found with id"));

    course.deletedCourse();
    courseRepository.save(course);
  }
}
