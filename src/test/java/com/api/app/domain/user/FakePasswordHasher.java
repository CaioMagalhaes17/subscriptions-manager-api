package com.api.app.domain.user;

import com.api.app.domain.user.gateways.PasswordHasher;

public class FakePasswordHasher implements PasswordHasher {
  @Override
  public String hash(String plain) {
    return "hashed-" + plain;
  }

  @Override
  public boolean matches(String plain, String hash) {
    return hash.equals("hashed-" + plain);
  }
}
