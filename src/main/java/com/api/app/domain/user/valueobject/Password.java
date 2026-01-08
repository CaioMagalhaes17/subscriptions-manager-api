package com.api.app.domain.user.valueobject;

import com.api.app.domain.user.gateways.PasswordHasher;

public final class Password {

  private final String hash;

  private Password(String hash) {
    this.hash = hash;
  }

  public static Password fromPlainText(
      String plainPassword,
      PasswordHasher hasher) {
    String hash = hasher.hash(plainPassword);
    return new Password(hash);
  }

  public boolean matches(
      String plainPassword,
      PasswordHasher hasher) {
    return hasher.matches(plainPassword, hash);
  }

  @Override
  public String toString() {
    return "******"; // nunca exponha
  }
}
