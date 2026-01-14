package com.api.app.infra.http.user.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.app.application.usecases.user.LoginUserUseCase;
import com.api.app.application.usecases.user.RegisterUserUseCase;
import com.api.app.infra.http.user.dto.LoginRequest;
import com.api.app.infra.http.user.dto.RegisterRequest;

@RestController
@RequestMapping("/users")
public class UserController {

  private final RegisterUserUseCase registerUserUseCase;
  private final LoginUserUseCase loginUserUseCase;

  public UserController(
      RegisterUserUseCase registerUserUseCase,
      LoginUserUseCase loginUserUseCase) {
    this.registerUserUseCase = registerUserUseCase;
    this.loginUserUseCase = loginUserUseCase;
  }

  @PostMapping("/register")
  public ResponseEntity<String> register(
      @RequestBody RegisterRequest request) {
    String response = this.registerUserUseCase.execute(request.email(), request.name(), request.password());
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(
      @RequestBody LoginRequest request) {
    String response = this.loginUserUseCase.execute(request.email(), request.password());
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
