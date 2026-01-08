package com.api.app.domain.user.exceptions;

public class InvalidEmailException extends RuntimeException {
  public InvalidEmailException() {
    super("Invalid Email");
  }

  public InvalidEmailException(String message) {
    super(message);
  }
}
