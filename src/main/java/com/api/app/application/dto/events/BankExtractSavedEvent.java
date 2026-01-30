package com.api.app.application.dto.events;

import java.util.UUID;

import com.api.app.application.valueobject.ExtractReference;

public class BankExtractSavedEvent  {
  private final UUID userId;
  private final ExtractReference extractReference;

  public BankExtractSavedEvent (
      UUID userId,
      ExtractReference extractReference) {
    this.userId = userId;
    this.extractReference = extractReference;
  }

  public UUID getUserId() {
    return userId;
  }

  public ExtractReference getExtractReference() {
    return extractReference;
  }

}
