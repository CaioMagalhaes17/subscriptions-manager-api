package com.api.app.domain.user;

import org.junit.jupiter.api.Test;

import com.api.app.application.usecases.user.LoginUserUseCase;
import com.api.app.domain.user.entity.User;
import com.api.app.domain.user.exceptions.InvalidCredentialsException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class LoginTest {

  private InMemoryUserRepository userRepository;
  private FakePasswordHasher passwordHasher;
  private LoginUserUseCase useCase;
  private FakeTokenEncrypter tokenEncrypter;

  @BeforeEach
  void setUp() {
    userRepository = new InMemoryUserRepository();
    passwordHasher = new FakePasswordHasher();
    tokenEncrypter = new FakeTokenEncrypter();
    useCase = new LoginUserUseCase(userRepository, passwordHasher, tokenEncrypter);
  }

  @Test
  void shouldLoginAndReturnToken() {
    User user = UserFactory.makeUser("Caio", "user@gmail.com");
    this.userRepository.save(user);

    String token = this.useCase.execute("user@gmail.com", "12345");
    assertNotNull(token);
  }

  @Test
  void shouldNotLogin() {
    User user = UserFactory.makeUser("Caio", "user@gmail.com");
    this.userRepository.save(user);

    assertThrows(InvalidCredentialsException.class, () -> this.useCase.execute("user@gmail.com", "wrong-password"));
  }
}
