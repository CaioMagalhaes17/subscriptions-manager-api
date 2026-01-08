package com.api.app.domain.user.repository;

import java.util.Optional;
import java.util.UUID;

import com.api.app.domain.user.entity.User;
import com.api.app.domain.user.valueobject.Email;

public interface IUserRepository {
  void save(User user);

  Optional<User> findById(UUID id);

  Optional<User> findByEmail(Email email);

  boolean existsEmail(Email email);
}
