package com.alura.cursos.forohub.controller;

import com.alura.cursos.forohub.domain.topics.DataAnswerTopic;
import com.alura.cursos.forohub.domain.topics.DataCreateTopic;
import com.alura.cursos.forohub.domain.topics.TopicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
