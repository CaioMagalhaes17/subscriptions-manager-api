package com.api.app.application.usecases.subscription.exceptions;

public class SubscriptionNotFoundException extends RuntimeException {
  public SubscriptionNotFoundException() {
    super("Subscription not found");
  }

  public SubscriptionNotFoundException(String message) {
    super(message);
  }
}
