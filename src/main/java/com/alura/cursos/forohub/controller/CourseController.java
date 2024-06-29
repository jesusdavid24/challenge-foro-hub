package com.alura.cursos.forohub.controller;

import com.alura.cursos.forohub.domain.courses.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/course")
@SecurityRequirement(name = "bearer-key")
public class CourseController {

  @Autowired
  private CourseService service;

  @PostMapping
  @Transactional
  public ResponseEntity<DataAnswerCourse> createCourse(
    @RequestBody @Valid DataCreateCourse dataCreateCourse,
    UriComponentsBuilder uriComponentsBuilder
    ) {
    DataAnswerCourse response = service.createCourse(dataCreateCourse);
    URI url = uriComponentsBuilder.path("/course/{id}").buildAndExpand(response.id()).toUri();

    return ResponseEntity.created(url).body(response);
  }

  @GetMapping
  public ResponseEntity<Page<DataListCourse>> listCourse(
    @PageableDefault(size = 10, sort = "name")
    Pageable pageable
  ) {
    Page<DataListCourse> dataListCourses = service.getCourse(pageable);
    return ResponseEntity.ok(dataListCourses);
  }

  @GetMapping("/search")
  public ResponseEntity<DataListCourse> searchCourse(@RequestBody @Valid CourseSearchReques courseSearchReques) {
    DataListCourse dataListCourse = service.getCourseByName(courseSearchReques.name());
    return ResponseEntity.ok(dataListCourse);
  }

  @PutMapping
  @Transactional
  public ResponseEntity<DataAnswerCourse> updateCourse(@RequestBody @Valid DataUpdateCourse dataUpdateCourse) {
    var course = service.updateCourse(dataUpdateCourse);
    return ResponseEntity.ok(course);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deletedCourse(@PathVariable Long id) {
    service.deletedCourse(id);
    return ResponseEntity.noContent().build();
  }
}
