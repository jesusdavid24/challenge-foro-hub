package com.alura.cursos.forohub.controller;

import com.alura.cursos.forohub.domain.answer.*;
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
@SecurityRequirement(name = "bearer-key")
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

  @GetMapping
  public ResponseEntity<Page<DataListAnswer>> listAnswer(
    @PageableDefault(size = 10, sort = "dateCreation")
    Pageable pageable
  ) {
    Page<DataListAnswer> dataListAnswers = service.getAnswer(pageable);
    return ResponseEntity.ok(dataListAnswers);
  }
  @PutMapping
  @Transactional
  public ResponseEntity<DataAnswer> udpateAnswer(@RequestBody @Valid DataUpdateAnswer dataUpdateAnswer) {
    var answer = service.updateAnswer(dataUpdateAnswer);
    return ResponseEntity.ok(answer);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deletedAnswer(@PathVariable Long id) {
    service.deletedAnswer(id);
    return ResponseEntity.noContent().build();
  }
  @GetMapping("/{id}")
  @Transactional
  public ResponseEntity<DataAnswer> detailsAnswer(@PathVariable Long id) {
    DataAnswer dataAnswer = service.detailsAnswer(id);
    return ResponseEntity.ok(dataAnswer);
  }

}
