package com.api.app.application.usecases.user;

import com.api.app.domain.user.entity.User;
import com.api.app.domain.user.exceptions.InvalidCredentialsException;
import com.api.app.domain.user.gateways.PasswordHasher;
import com.api.app.domain.user.repository.IUserRepository;
import com.api.app.domain.user.valueobject.Email;

public class LoginUserUseCase {
  private final IUserRepository userRepository;
  private final PasswordHasher passwordHasher;

  public LoginUserUseCase(IUserRepository userRepository, PasswordHasher passwordHasher) {
    this.userRepository = userRepository;
    this.passwordHasher = passwordHasher;
  }

  public void execute(String email, String name, String plainPassword) {
    User user = this.userRepository.findByEmail(new Email(email)).orElseThrow(InvalidCredentialsException::new);

    if (!user.getPassword().matches(plainPassword, passwordHasher)) {
      throw new InvalidCredentialsException();
    }
  }
}
