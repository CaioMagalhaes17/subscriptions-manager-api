package com.api.app.domain.subscription.exceptions;

public class InvalidSubscriptionValues extends RuntimeException {
  public InvalidSubscriptionValues() {
    super("Invalid subscription inputs");
  }

  public InvalidSubscriptionValues(String message) {
    super(message);
  }
}
