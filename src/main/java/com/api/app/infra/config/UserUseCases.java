package com.api.app.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.api.app.application.usecases.user.LoginUserUseCase;
import com.api.app.application.usecases.user.RegisterUserUseCase;
import com.api.app.domain.user.gateways.PasswordHasher;
import com.api.app.domain.user.gateways.TokenEncrypter;
import com.api.app.domain.user.repository.IUserRepository;

@Configuration
public class UserUseCases {

  @Bean
  RegisterUserUseCase registerUserUseCase(
      IUserRepository userRepository,
      PasswordHasher passwordHasher,
      TokenEncrypter tokenEncrypter) {
    return new RegisterUserUseCase(userRepository, passwordHasher, tokenEncrypter);
  }

  @Bean
  LoginUserUseCase loginUserUseCase(
      IUserRepository userRepository,
      PasswordHasher passwordHasher,
      TokenEncrypter tokenEncrypter) {
    return new LoginUserUseCase(userRepository, passwordHasher, tokenEncrypter);
  }
}
