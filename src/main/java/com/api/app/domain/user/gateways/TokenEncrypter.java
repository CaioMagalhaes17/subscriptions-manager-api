package com.api.app.domain.user.gateways;

import com.api.app.application.usecases.user.dto.TokenDTO;

public interface TokenEncrypter {
  String encryptToken(TokenDTO tokenPayload);
}
