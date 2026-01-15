package com.api.app.application.dto.events;

import java.util.UUID;

public class ExtractSavedPayload {
  private UUID userId;
  private String pathName;

  public ExtractSavedPayload(
      UUID userId,
      String pathString) {
    this.userId = userId;
    this.pathName = pathString;
  }

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  public String getPathName() {
    return pathName;
  }

  public void setPathName(String pathName) {
    this.pathName = pathName;
  }

}
