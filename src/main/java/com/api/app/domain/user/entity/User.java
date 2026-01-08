package com.api.app.domain.user.entity;

import java.time.Instant;
import java.util.UUID;

import com.api.app.domain.user.exceptions.InvalidUserNameExcetion;
import com.api.app.domain.user.valueobject.Email;
import com.api.app.domain.user.valueobject.Password;

public class User {
  private final UUID id;
  private String name;
  private final Email email;
  private final Password password;
  private final Instant createdAt;
  private Instant updatedAt;

  public User(String name, Email email, Password password) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.email = email;
    this.createdAt = Instant.now();
    this.password = password;
    this.updatedAt = null;
    this.validate();
  }

  public User(
      UUID id,
      String name,
      Email email,
      Instant createdAt,
      Instant updatedAt,
      Password password) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  private void validate() {
    if (this.name == null || this.name.isBlank()) {
      throw new InvalidUserNameExcetion();
    }
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Email getEmail() {
    return email;
  }

  public Password getPassword() {
    return password;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

}
