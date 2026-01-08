package com.api.app.domain.user;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.api.app.application.usecases.user.RegisterUserUseCase;
import com.api.app.domain.user.entity.User;
import com.api.app.domain.user.exceptions.EmailAlreadyInUseException;
import com.api.app.domain.user.valueobject.Email;

public class RegisterTest {
  private InMemoryUserRepository userRepository;
  private FakePasswordHasher passwordHasher;
  private RegisterUserUseCase useCase;
  private FakeTokenEncrypter tokenEncrypter;

  @BeforeEach
  void setUp() {
    userRepository = new InMemoryUserRepository();
    passwordHasher = new FakePasswordHasher();
    tokenEncrypter = new FakeTokenEncrypter();
    useCase = new RegisterUserUseCase(userRepository, passwordHasher, tokenEncrypter);
  }

  @Test
  void shouldRegisterAndReturnToken() {
    String tokenResponse = this.useCase.execute("user@gmail.com", "user", "12345");
    User user = this.userRepository.findByEmail(new Email("user@gmail.com")).orElseThrow();

    assertNotNull(tokenResponse);
    assertNotNull(user);
  }

  @Test
  void shouldNotRegister() {
    User user = UserFactory.makeUser("user", "user@gmail.com");
    this.userRepository.save(user);
    assertThrows(EmailAlreadyInUseException.class, () -> this.useCase.execute("user@gmail.com", "user", "12345"));
  }
}
