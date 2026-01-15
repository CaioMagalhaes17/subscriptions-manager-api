package com.api.app.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.api.app.domain.user.gateways.PasswordHasher;
import com.api.app.infra.gateways.BCryptPasswordHasher;

@Configuration
public class Gateways {
  @Bean
  PasswordHasher passwordHasher(){
    return new BCryptPasswordHasher();
  }
}
