package com.api.app.domain.user.gateways;

public interface PasswordHasher {
  String hash(String plainText);

  boolean matches(String plainText, String hash);
}
