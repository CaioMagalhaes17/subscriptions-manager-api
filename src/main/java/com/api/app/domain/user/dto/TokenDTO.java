package com.api.app.domain.user.dto;

public final class TokenDTO {
  private final String id;
  private final String name;
  private final String email;

  public TokenDTO(String id, String name, String email) {
    this.id = id;
    this.email = email;
    this.name = name;
  }
}
