package com.api.app.domain.subscription.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.api.app.domain.subscription.entity.Subscription;
import com.api.app.domain.user.entity.User;

public interface ISubscriptionRepository {
  void save(Subscription subscription);

  Optional<Subscription> findById(UUID id);

  Optional<Subscription> findByName(String name);

  List<Subscription> findByUser(User user);
}
