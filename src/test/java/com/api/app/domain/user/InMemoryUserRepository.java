package com.api.app.domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.api.app.domain.user.entity.User;
import com.api.app.domain.user.repository.IUserRepository;
import com.api.app.domain.user.valueobject.Email;

public class InMemoryUserRepository implements IUserRepository {
  private List<User> users = new ArrayList<>();

  @Override
  public void save(User user) {
    this.users.add(user);
  }

  @Override
  public boolean existsEmail(Email email) {
    return this.users.stream().anyMatch(user -> user.getEmail().equals(email));
  }

  @Override
  public Optional<User> findByEmail(Email email) {
    Optional<User> response = users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
    return response;
  }

  @Override
  public Optional<User> findById(UUID id) {
    return this.users.stream().filter(u -> u.getId().equals(id)).findFirst();
  }
}
