package com.api.app.domain.user.exceptions;

public class EmailAlreadyInUseException extends RuntimeException {
  public EmailAlreadyInUseException() {
    super("Email is already in use");
  }

  public EmailAlreadyInUseException(String message) {
    super(message);
  }
}
