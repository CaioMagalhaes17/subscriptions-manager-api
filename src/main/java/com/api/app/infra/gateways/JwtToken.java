package com.api.app.infra.gateways;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

import com.api.app.application.usecases.user.dto.TokenDTO;
import com.api.app.domain.user.gateways.TokenEncrypter;

import java.time.Instant;
import java.util.Date;

public class JwtToken implements TokenEncrypter {

  private final SecretKey secretKey;
  private final long expirationInMillis;

  public JwtToken() {
    String secret = "todoDSAPDMASDMSAIDSAMIODSADMDSAIO";
    this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    this.expirationInMillis = 5000000;
  }

  @Override
  public String encryptToken(TokenDTO tokenPayload) {
    Instant now = Instant.now();

    return Jwts.builder()
        .setSubject(tokenPayload.getId())
        .claim("email", tokenPayload.getEmail())
        .setIssuedAt(Date.from(now))
        .setExpiration(Date.from(now.plusMillis(expirationInMillis)))
        .signWith(secretKey, SignatureAlgorithm.HS256)
        .compact();
  }
}