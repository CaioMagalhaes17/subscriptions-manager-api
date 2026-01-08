package com.api.app.application.usecases.user;

import com.api.app.domain.user.dto.TokenDTO;
import com.api.app.domain.user.entity.User;
import com.api.app.domain.user.exceptions.EmailAlreadyInUseException;
import com.api.app.domain.user.gateways.PasswordHasher;
import com.api.app.domain.user.gateways.TokenEncrypter;
import com.api.app.domain.user.repository.IUserRepository;
import com.api.app.domain.user.valueobject.Email;
import com.api.app.domain.user.valueobject.Password;

public class RegisterUserUseCase {
  private final IUserRepository userRepository;
  private final PasswordHasher passwordHasher;
  private final TokenEncrypter tokenEncrypter;

  public RegisterUserUseCase(IUserRepository userRepository, PasswordHasher passwordHasher,
      TokenEncrypter tokenEncrypter) {
    this.userRepository = userRepository;
    this.passwordHasher = passwordHasher;
    this.tokenEncrypter = tokenEncrypter;
  }

  public String execute(String email, String name, String plainPassword) {
    Email userEmail = new Email(email);
    if (this.userRepository.existsEmail(userEmail)) {
      throw new EmailAlreadyInUseException();
    }

    User user = new User(name, userEmail, Password.fromPlainText(plainPassword, this.passwordHasher));
    this.userRepository.save(user);

    TokenDTO payload = new TokenDTO(user.getId().toString(), user.getEmail().toString(), user.getName());
    return this.tokenEncrypter.encryptToken(payload);
  }
}
