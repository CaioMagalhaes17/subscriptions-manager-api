package com.api.app.domain.user.exceptions;

public class InvalidUserNameExcetion extends RuntimeException {
  public InvalidUserNameExcetion() {
    super("Invalid user name");
  }

  public InvalidUserNameExcetion(String message) {
    super(message);
  }
}
