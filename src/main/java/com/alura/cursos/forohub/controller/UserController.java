package com.alura.cursos.forohub.controller;

import com.alura.cursos.forohub.domain.users.*;
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
import java.util.List;

@RestController
@SecurityRequirement(name = "bearer-key")
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

  @GetMapping
  public ResponseEntity<List<DataListUser>> listUser() {
    List<DataListUser> dataListUsers = service.getUsers();
    return ResponseEntity.ok(dataListUsers);
  }

  @PutMapping
  @Transactional
  public ResponseEntity<DataAnswerUser> updateUser(@RequestBody @Valid DataUpdateUser dataUpdateUser) {
    var user = service.updateUser(dataUpdateUser);
    return ResponseEntity.ok(user);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deletedUser(@PathVariable Long id) {
    service.deletedUser(id);
    return ResponseEntity.noContent().build();
  }
}
