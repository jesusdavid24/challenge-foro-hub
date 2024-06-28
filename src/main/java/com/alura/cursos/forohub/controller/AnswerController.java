package com.alura.cursos.forohub.controller;

import com.alura.cursos.forohub.domain.answer.AnswerService;
import com.alura.cursos.forohub.domain.answer.DataAnswer;
import com.alura.cursos.forohub.domain.answer.DataCreateAnswer;
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
@RequestMapping("/answer")
public class AnswerController {

  @Autowired
  private AnswerService service;

  @PostMapping
  @Transactional
  public ResponseEntity<DataAnswer> createAnswer(
    @RequestBody @Valid DataCreateAnswer dataCreateAnswer,
    UriComponentsBuilder uriComponentsBuilder
  ) {
    DataAnswer response = service.createAnswer(dataCreateAnswer);
    URI url = uriComponentsBuilder.path("/answer/{id}").buildAndExpand(response.id()).toUri();

    return ResponseEntity.created(url).body(response);
  }
}
