package com.api.app.domain.Subscription.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.api.app.domain.subscription.entity.Subscription;
import com.api.app.domain.subscription.repository.ISubscriptionRepository;
import com.api.app.domain.user.entity.User;

public class InMemorySubscriptionRepository implements ISubscriptionRepository {
  private List<Subscription> subscriptions = new ArrayList<>();

  @Override
  public Optional<Subscription> findById(UUID id) {
    return this.subscriptions.stream().filter(sub -> sub.getId().equals(id)).findFirst();
  }

  @Override
  public Optional<Subscription> findByName(String name) {
    return this.subscriptions.stream().filter(subscription -> subscription.getName().equals(name)).findFirst();
  }

  @Override
  public List<Subscription> findByUser(User user) {
    return this.subscriptions.stream().filter(subscription -> subscription.getUser().equals(user)).toList();
  }

  @Override
  public void save(Subscription subscription) {
    this.subscriptions.add(subscription);
  }
}
