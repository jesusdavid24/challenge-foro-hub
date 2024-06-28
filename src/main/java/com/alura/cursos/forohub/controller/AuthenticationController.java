package com.alura.cursos.forohub.controller;

import com.alura.cursos.forohub.domain.users.User;
import com.alura.cursos.forohub.domain.users.UserAuthenticationData;
import com.alura.cursos.forohub.infra.security.DataJWTtoken;
import com.alura.cursos.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenService tokenService;

  @PostMapping
  public ResponseEntity authenticateUser(@RequestBody @Valid UserAuthenticationData userAuthenticationData) {
    Authentication authToken = new UsernamePasswordAuthenticationToken(
      userAuthenticationData.email(),
      userAuthenticationData.password()
    );
    var userAuthenticated = authenticationManager.authenticate(authToken);
    var JWTtoken = tokenService.generToken((User) userAuthenticated.getPrincipal());
    return ResponseEntity.ok(new DataJWTtoken(JWTtoken));
  }
}
