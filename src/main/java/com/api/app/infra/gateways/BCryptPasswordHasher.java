package com.api.app.infra.gateways;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.api.app.domain.user.gateways.PasswordHasher;

public class BCryptPasswordHasher implements PasswordHasher {

  private final BCryptPasswordEncoder encoder;

  public BCryptPasswordHasher() {
    this.encoder = new BCryptPasswordEncoder();
  }

  @Override
  public String hash(String plainPassword) {
    return encoder.encode(plainPassword);
  }

  @Override
  public boolean matches(String plainPassword, String hashedPassword) {
    return encoder.matches(plainPassword, hashedPassword);
  }
}