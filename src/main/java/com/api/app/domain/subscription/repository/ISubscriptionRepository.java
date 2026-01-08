package com.api.app.domain.subscription.repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Flow.Subscription;

public interface ISubscriptionRepository {
  void save(Subscription subscription);

  Optional<Subscription> findById();

  Optional<Subscription> findByName();

  List<Subscription> findByUser();
}
