package com.api.app.infra.persistance.mongodb.user.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.api.app.infra.persistance.mongodb.user.schemas.UserSchema;

public interface UserMongoRepository
                extends MongoRepository<UserSchema, String> {

        Optional<UserSchema> findByEmail(String email);
}