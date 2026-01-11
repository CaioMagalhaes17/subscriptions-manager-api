package com.api.app.application.usecases.subscription;

import java.util.UUID;

import com.api.app.application.usecases.subscription.exceptions.SubscriptionNotFoundException;
import com.api.app.domain.subscription.entity.Subscription;
import com.api.app.domain.subscription.repository.ISubscriptionRepository;

public class FetchSubscriptionById {
  private final ISubscriptionRepository subscriptionRepository;

  public FetchSubscriptionById(
      ISubscriptionRepository subscriptionRepository) {
    this.subscriptionRepository = subscriptionRepository;
  }

  public Subscription execute(UUID id) {
    return this.subscriptionRepository.findById(id).orElseThrow(SubscriptionNotFoundException::new);
  }
}
