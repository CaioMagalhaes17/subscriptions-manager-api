package com.api.app.application.usecases.subscription.exceptions;

public class SubscribeActionNotAllowed extends RuntimeException {
  public SubscribeActionNotAllowed() {
    super("Action not allowed to subscription");
  }

  public SubscribeActionNotAllowed(String message) {
    super(message);
  }
}
