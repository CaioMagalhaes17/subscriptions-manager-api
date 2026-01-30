package com.api.app.domain.subscription.valueobject;

import java.util.UUID;

public class UserId {

  private final UUID value;

  private UserId(UUID value) {
    if (value == null) throw new IllegalArgumentException("UserId cannot be null");
    this.value = value;
  }

  public static UserId of(UUID value) {
    return new UserId(value);
  }

  public UUID value() {
    return value;
  }
}
