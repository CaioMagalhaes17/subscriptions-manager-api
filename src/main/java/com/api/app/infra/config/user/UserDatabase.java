package com.api.app.infra.config.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.api.app.domain.user.repository.IUserRepository;
import com.api.app.infra.persistance.mongodb.user.repository.UserMongoRepository;
import com.api.app.infra.persistance.mongodb.user.repository.UserRepository;

@Configuration
public class UserDatabase {
  @Bean
  IUserRepository userRepository(
    UserMongoRepository userMongoRepository
  ){
    return new UserRepository(userMongoRepository);
  }
}
