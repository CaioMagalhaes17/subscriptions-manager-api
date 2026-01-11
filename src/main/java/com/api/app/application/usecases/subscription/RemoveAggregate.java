package com.api.app.application.usecases.subscription;

import java.util.UUID;

import com.api.app.application.usecases.subscription.exceptions.SubscriptionNotFoundException;
import com.api.app.domain.subscription.entity.Subscription;
import com.api.app.domain.subscription.repository.ISubscriptionRepository;
import com.api.app.domain.subscription.valueobject.Aggregate;

public class RemoveAggregate {
  private ISubscriptionRepository subscriptionRepository;

  public RemoveAggregate(
      ISubscriptionRepository subscriptionRepository) {
    this.subscriptionRepository = subscriptionRepository;
  }

  public void execute(
      UUID id, Aggregate aggregate) {
    Subscription subscription = this.subscriptionRepository.findById(id)
        .orElseThrow(SubscriptionNotFoundException::new);
    subscription.removeAggregate(aggregate);
    this.subscriptionRepository.save(subscription);
  }
}
