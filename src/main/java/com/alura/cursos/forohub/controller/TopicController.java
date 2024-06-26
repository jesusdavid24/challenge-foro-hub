package com.alura.cursos.forohub.controller;

import com.alura.cursos.forohub.domain.topics.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topic")
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
  public ResponseEntity<Page<DataListTopic>> listTopics(Pageable pageable) {
    Page<DataListTopic> dataListTopicPage = service.getTopics(pageable);
    return ResponseEntity.ok(dataListTopicPage);
  }
}
