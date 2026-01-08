package com.api.app.application.usecases.user;

import com.api.app.application.usecases.user.dto.TokenDTO;
import com.api.app.domain.user.entity.User;
import com.api.app.domain.user.exceptions.InvalidCredentialsException;
import com.api.app.domain.user.gateways.PasswordHasher;
import com.api.app.domain.user.gateways.TokenEncrypter;
import com.api.app.domain.user.repository.IUserRepository;
import com.api.app.domain.user.valueobject.Email;

public class LoginUserUseCase {
  private final IUserRepository userRepository;
  private final PasswordHasher passwordHasher;
  private final TokenEncrypter tokenEncrypter;

  public LoginUserUseCase(IUserRepository userRepository, PasswordHasher passwordHasher,
      TokenEncrypter tokenEncrypter) {
    this.userRepository = userRepository;
    this.passwordHasher = passwordHasher;
    this.tokenEncrypter = tokenEncrypter;
  }

  public String execute(String email, String plainPassword) {
    User user = this.userRepository.findByEmail(new Email(email)).orElseThrow(InvalidCredentialsException::new);

    if (!user.getPassword().matches(plainPassword, passwordHasher)) {
      throw new InvalidCredentialsException();
    }
    TokenDTO payload = new TokenDTO(user.getId().toString(), user.getEmail().toString(), user.getName());
    return this.tokenEncrypter.encryptToken(payload);
  }
}
