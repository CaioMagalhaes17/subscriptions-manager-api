package com.api.app.domain.Subscription.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.api.app.application.usecases.subscription.exceptions.SubscriptionNotFoundException;
import com.api.app.domain.subscription.entity.Subscription;
import com.api.app.domain.subscription.repository.ISubscriptionRepository;
import com.api.app.domain.subscription.valueobject.UserId;

public class InMemorySubscriptionRepository implements ISubscriptionRepository {
  private List<Subscription> subscriptions = new ArrayList<>();

  @Override
  public Optional<Subscription> findById(UUID id) {
    return this.subscriptions.stream().filter(sub -> sub.getId().equals(id)).findFirst();
  }

  @Override
  public List<Subscription> findByName(String name) {
    return this.subscriptions.stream().filter(subscription -> subscription.getName().equals(name)).toList();
  }

  @Override
  public List<Subscription> findByUserId(UserId userId) {
    return this.subscriptions.stream().filter(subscription -> subscription.getUserId().equals(userId)).toList();
  }

  @Override
  public void save(Subscription subscription) {
    boolean isEmpty = this.subscriptions.stream().filter(sub -> sub.getId().equals(subscription.getId()))
        .findFirst().isEmpty();
    if (isEmpty) {
      this.subscriptions.add(subscription);
      return;
    }
    Subscription existing = this.subscriptions.stream().filter(sub -> sub.getId().equals(subscription.getId()))
        .findFirst().orElseThrow();
    this.subscriptions.remove(existing);
    this.subscriptions.add(subscription);
  }

  @Override
  public void delete(UUID id) {
    Subscription subscription = this.findById(id).orElseThrow(SubscriptionNotFoundException::new);
    this.subscriptions.remove(subscription);
  }
}
