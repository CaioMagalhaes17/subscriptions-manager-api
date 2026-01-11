package com.api.app.domain.subscription.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.api.app.domain.subscription.entity.Subscription;
import com.api.app.domain.user.entity.User;

public interface ISubscriptionRepository {
  void save(Subscription subscription);

  void delete(UUID id);

  Optional<Subscription> findById(UUID id);

  List<Subscription> findByName(String name);

  List<Subscription> findByUser(User user);
}
