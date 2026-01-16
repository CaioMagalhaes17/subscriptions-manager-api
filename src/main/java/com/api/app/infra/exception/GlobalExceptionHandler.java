package com.api.app.infra.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.api.app.core.ErrorResponse;
import com.api.app.domain.user.exceptions.EmailAlreadyInUseException;
import com.api.app.domain.user.exceptions.InvalidCredentialsException;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(EmailAlreadyInUseException.class)
  public ResponseEntity<ErrorResponse> handleEmailAlreadyInUse(
      EmailAlreadyInUseException ex
  ) {
    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(new ErrorResponse(
            HttpStatus.CONFLICT.value(),
            "EMAIL_ALREADY_IN_USE",
            ex.getMessage(),
            Instant.now()
        ));
  }

  @ExceptionHandler(InvalidCredentialsException.class)
  public ResponseEntity<ErrorResponse> handleInvalidCredentials(
    InvalidCredentialsException ex
  ) {
    return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .body(new ErrorResponse(
          HttpStatus.UNAUTHORIZED.value(),
          "INVALID_CREDENTIALS",
          ex.getMessage(),
          Instant.now()
      ));
  }
}
