package com.api.app.infra.persistance.mongodb.user.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.api.app.domain.user.entity.User;
import com.api.app.domain.user.repository.IUserRepository;
import com.api.app.domain.user.valueobject.Email;
import com.api.app.infra.persistance.mongodb.user.mapper.UserMapper;

@Repository
public class UserRepository implements IUserRepository {

  private final UserMongoRepository mongoRepository;

  public UserRepository(UserMongoRepository mongoRepository) {
    this.mongoRepository = mongoRepository;
  }

  @Override
  public void save(User user) {
    this.mongoRepository.save(UserMapper.toPersistance(user));
  }

  @Override
  public Optional<User> findById(UUID id) {
    return this.mongoRepository
        .findById(id.toString())
        .map(UserMapper::toDomain);
  }

  @Override
  public Optional<User> findByEmail(Email email) {
    return this.mongoRepository.findByEmail(email.toString()).map(UserMapper::toDomain);
  }

  @Override
  public boolean existsEmail(Email email) {
    return this.mongoRepository.findByEmail(email.toString()).isPresent();
  }

}
