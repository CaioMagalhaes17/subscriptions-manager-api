package com.api.app.application.usecases.subscription;

import java.time.LocalDate;
import java.util.UUID;

import com.api.app.application.usecases.subscription.exceptions.SubscriptionNotFoundException;
import com.api.app.domain.subscription.entity.Subscription;
import com.api.app.domain.subscription.repository.ISubscriptionRepository;
import com.api.app.domain.subscription.valueobject.Aggregate;

public class AddAggregate {
  private ISubscriptionRepository subscriptionRepository;

  public AddAggregate(
      ISubscriptionRepository subscriptionRepository) {
    this.subscriptionRepository = subscriptionRepository;
  }

  public void execute(
      UUID id, Aggregate aggregate) {
    Subscription subscription = this.subscriptionRepository.findById(id)
        .orElseThrow(SubscriptionNotFoundException::new);
    subscription.addAggregate(aggregate);
    this.subscriptionRepository.save(subscription);
  }
}
