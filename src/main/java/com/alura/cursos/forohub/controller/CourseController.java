package com.alura.cursos.forohub.controller;

import com.alura.cursos.forohub.domain.courses.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

  @Operation(
    summary = "Create a new course",
    description = "This endpoint allows authorized users to create a new course.",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = DataCreateCourse.class)
      )
    ),
    responses = {
      @ApiResponse(responseCode = "201", description = "Course created successfully"),
      @ApiResponse(responseCode = "400", description = "Invalid input data")
    }
  )
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

  @Operation(
    summary = "List courses",
    description = "This endpoint allows users to list all available courses."
  )
  @GetMapping
  public ResponseEntity<Page<DataListCourse>> listCourse(
    @Parameter(description = "The page number to retrieve")
    @PageableDefault(size = 10, sort = "name")
    Pageable pageable
  ) {
    Page<DataListCourse> dataListCourses = service.getCourse(pageable);
    return ResponseEntity.ok(dataListCourses);
  }

  @Operation(
    summary = "Search for a course",
    description = "This endpoint allows users to search for a course by name.",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = CourseSearchReques.class)
      )
    ),
    responses = {
      @ApiResponse(responseCode = "200", description = "Course found"),
      @ApiResponse(responseCode = "404", description = "Course not found")
    }
  )
  @GetMapping("/search")
  public ResponseEntity<DataListCourse> searchCourse(@RequestBody @Valid CourseSearchReques courseSearchReques) {
    DataListCourse dataListCourse = service.getCourseByName(courseSearchReques.name());
    return ResponseEntity.ok(dataListCourse);
  }

  @Operation(
    summary = "Update a course",
    description = "This endpoint allows authorized users to update an existing course.",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = DataUpdateCourse.class)
      )
    ),
    responses = {
      @ApiResponse(responseCode = "200", description = "Course updated successfully"),
      @ApiResponse(responseCode = "400", description = "Invalid input data")
    }
  )
  @PutMapping
  @Transactional
  public ResponseEntity<DataAnswerCourse> updateCourse(@RequestBody @Valid DataUpdateCourse dataUpdateCourse) {
    var course = service.updateCourse(dataUpdateCourse);
    return ResponseEntity.ok(course);
  }

  @Operation(
    summary = "Delete a course",
    description = "This endpoint allows authorized users to delete an existing course."
  )
  @DeleteMapping("/{id}")
  public ResponseEntity deletedCourse(@PathVariable Long id) {
    service.deletedCourse(id);
    return ResponseEntity.noContent().build();
  }
}
