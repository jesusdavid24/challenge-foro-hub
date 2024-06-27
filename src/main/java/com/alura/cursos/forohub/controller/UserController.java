package com.alura.cursos.forohub.controller;

import com.alura.cursos.forohub.domain.users.DataAnswerUser;
import com.alura.cursos.forohub.domain.users.DataRecordsUsers;
import com.alura.cursos.forohub.domain.users.UserService;
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
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService service;

  @PostMapping
  @Transactional
  public ResponseEntity<DataAnswerUser> createUser(
    @RequestBody @Valid DataRecordsUsers dataRecordsUsers,
    UriComponentsBuilder uriComponentsBuilder
    ){
    DataAnswerUser response = service.createUser(dataRecordsUsers);
    URI url = uriComponentsBuilder.path("/user/{id}").buildAndExpand(response.id()).toUri();

    return ResponseEntity.created(url).body(response);
  }
}
