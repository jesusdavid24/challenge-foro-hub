package com.alura.cursos.forohub.controller;

import com.alura.cursos.forohub.domain.topics.*;
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
@RequestMapping("/topic")
@SecurityRequirement(name = "bearer-key")
public class TopicController {

  @Autowired
  private TopicService service;

  @Operation(
    summary = "Create a new topic",
    description = "This endpoint allows authorized users to create a new topic.",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = DataCreateTopic.class)
      )
    ),
    responses = {
      @ApiResponse(responseCode = "201", description = "Topic created successfully"),
      @ApiResponse(responseCode = "400", description = "Invalid input data")
    }
  )
  @PostMapping
  @Transactional
  public ResponseEntity<DataAnswerTopic> createTopic(
    @RequestBody @Valid DataCreateTopic dataCreateTopic,
    UriComponentsBuilder uriComponentsBuilder
  ) {
    DataAnswerTopic response = service.createTopic(dataCreateTopic);
    URI url = uriComponentsBuilder.path("/topic/{id}").buildAndExpand(response.id()).toUri();

    return ResponseEntity.created(url).body(response);
  }

  @Operation(
    summary = "List topics",
    description = "This endpoint allows users to list all available topics."
  )
  @GetMapping
  public ResponseEntity<Page<DataListTopic>> listTopics(
    @Parameter(description = "The page number to retrieve")
    @PageableDefault(size = 10, sort = "dateCreation")
    Pageable pageable
  ) {
    Page<DataListTopic> dataListTopicPage = service.getTopics(pageable);
    return ResponseEntity.ok(dataListTopicPage);
  }

  @Operation(
    summary = "Search for topics",
    description = "This endpoint allows users to search for topics by course name and creation date.",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = TopicSearchRequest.class)
      )
    ),
    responses = {
      @ApiResponse(responseCode = "200", description = "Topics found"),
      @ApiResponse(responseCode = "404", description = "No topics found")
    }
  )
  @GetMapping("/search")
  public ResponseEntity<Page<DataListTopic>> searchTopics(
    @RequestBody TopicSearchRequest searchRequest,
    @PageableDefault(size = 10, sort = "dateCreation") Pageable pageable
  ) {
    Page<DataListTopic> dataListTopicPage = service.getTopicsByCourseNameAndDateCreation(
      searchRequest.courseName(),
      searchRequest.dateCreation(),
      pageable
    );
    return ResponseEntity.ok(dataListTopicPage);
  }
  @Operation(
    summary = "Update a topic",
    description = "This endpoint allows authorized users to update an existing topic.",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = DataUpdateTopic.class)
      )
    ),
    responses = {
      @ApiResponse(responseCode = "200", description = "Topic updated successfully"),
      @ApiResponse(responseCode = "400", description = "Invalid input data")
    }
  )
  @PutMapping
  @Transactional
  public ResponseEntity<DataAnswerTopic> updateTopic(@RequestBody @Valid DataUpdateTopic dataUpdateTopic) {
    var topic = service.updateTopic(dataUpdateTopic);
    return ResponseEntity.ok(topic);
  }

  @Operation(
    summary = "Delete a topic",
    description = "This endpoint allows authorized users to delete an existing topic."
  )
  @DeleteMapping("/{id}")
  public ResponseEntity deletedTopic(@PathVariable Long id) {
    service.deletedTopic(id);
    return ResponseEntity.noContent().build();
  }

  @Operation(
    summary = "Get topic details",
    description = "This endpoint allows users to retrieve the details of a specific topic."
  )
  @GetMapping("/{id}")
  @Transactional
  public ResponseEntity<DataAnswerTopic> detailsTopic(@PathVariable Long id) {
    DataAnswerTopic dataAnswerTopic = service.detailsTopic(id);
    return ResponseEntity.ok(dataAnswerTopic);
  }
}
