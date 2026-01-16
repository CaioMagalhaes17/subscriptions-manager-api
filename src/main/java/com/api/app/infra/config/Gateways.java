package com.api.app.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.api.app.domain.user.gateways.PasswordHasher;
import com.api.app.domain.user.gateways.TokenEncrypter;
import com.api.app.infra.gateways.BCryptPasswordHasher;
import com.api.app.infra.gateways.JwtToken;

@Configuration
public class Gateways {
  @Bean
  PasswordHasher passwordHasher() {
    return new BCryptPasswordHasher();
  }

  @Bean
  TokenEncrypter tokenEncrypter(){
    return new JwtToken();
  }
}
