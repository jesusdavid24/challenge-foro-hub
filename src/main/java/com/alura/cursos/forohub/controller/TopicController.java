package com.alura.cursos.forohub.controller;

import com.alura.cursos.forohub.domain.topics.*;
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

  @GetMapping
  public ResponseEntity<Page<DataListTopic>> listTopics(
    @PageableDefault(size = 10, sort = "dateCreation" )
    Pageable pageable
  ) {
    Page<DataListTopic> dataListTopicPage = service.getTopics(pageable);
    return ResponseEntity.ok(dataListTopicPage);
  }

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

  @PutMapping
  @Transactional
  public ResponseEntity<DataAnswerTopic> updateTopic(@RequestBody @Valid DataUpdateTopic dataUpdateTopic) {
    var topic = service.updateTopic(dataUpdateTopic);
    return ResponseEntity.ok(topic);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deletedTopic(@PathVariable Long id) {
    service.deletedTopic(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}")
  @Transactional
  public ResponseEntity<DataAnswerTopic> detailsTopic(@PathVariable Long id) {
    DataAnswerTopic dataAnswerTopic = service.detailsTopic(id);
    return ResponseEntity.ok(dataAnswerTopic);
  }
}
