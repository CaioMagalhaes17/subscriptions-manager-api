package com.api.app.infra.persistance.mongodb.user.mapper;

import java.util.UUID;

import com.api.app.domain.user.entity.User;
import com.api.app.domain.user.valueobject.Email;
import com.api.app.domain.user.valueobject.Password;
import com.api.app.infra.persistance.mongodb.user.schemas.UserSchema;

public class UserMapper {
  public static User toDomain(UserSchema userSchema) {
    return new User(
        UUID.fromString(userSchema.getId()),
        userSchema.getName(),
        new Email(userSchema.getEmail()),
        userSchema.getCreatedAt(),
        userSchema.getUpdatedAt(),
        Password.fromHash(userSchema.getPassword()));
  }

  public static UserSchema toPersistance(User user) {
    UserSchema schema = new UserSchema();
    schema.setCreatedAt(user.getCreatedAt());
    schema.setEmail(user.getEmail().toString());
    schema.setId(user.getId().toString());
    schema.setName(user.getName());
    schema.setPassword(user.getPassword().toString());
    schema.setUpdatedAt(user.getUpdatedAt());
    return schema;
  }
}
