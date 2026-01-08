package com.api.app.domain.user;

import com.api.app.application.usecases.user.dto.TokenDTO;
import com.api.app.domain.user.gateways.TokenEncrypter;

public class FakeTokenEncrypter implements TokenEncrypter {
  @Override
  public String encryptToken(TokenDTO tokenPayload) {
    return "token-encrypted-XD";
  }
}
