package com.api.app.domain.user.gateways;

import com.api.app.domain.user.dto.TokenDTO;

public interface TokenEncrypter {
  String encryptToken(TokenDTO tokenPayload);
}
