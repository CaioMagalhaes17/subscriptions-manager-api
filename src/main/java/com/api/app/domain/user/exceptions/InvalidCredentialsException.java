package com.api.app.domain.user.exceptions;

public class InvalidCredentialsException extends RuntimeException {
  public InvalidCredentialsException() {
    super("Incorrect Email/Password");
  }

  public InvalidCredentialsException(String message) {
    super(message);
  }
}
