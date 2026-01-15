package com.api.app.application.valueobject;

import java.util.List;
import java.util.UUID;

public class ExtractInterpretation {
  private final UUID userId;
  private final List<ExtractSubscription> extractSubscriptions;

  public ExtractInterpretation(
      UUID userId,
      List<ExtractSubscription> extractSubscriptions) {
    this.userId = userId;
    this.extractSubscriptions = extractSubscriptions;
  }

  public UUID getUserId() {
    return userId;
  }

  public List<ExtractSubscription> getExtractSubscriptions() {
    return extractSubscriptions;
  }

}
