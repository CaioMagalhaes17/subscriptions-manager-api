package com.api.app.application.usecases.subscription;

import java.util.UUID;

import com.api.app.application.usecases.subscription.exceptions.SubscribeActionNotAllowed;
import com.api.app.application.usecases.subscription.exceptions.SubscriptionNotFoundException;
import com.api.app.domain.subscription.entity.Subscription;
import com.api.app.domain.subscription.repository.ISubscriptionRepository;

public class DeleteSubscription {
  private final ISubscriptionRepository subscriptionRepository;

  public DeleteSubscription(
      ISubscriptionRepository subscriptionRepository) {
    this.subscriptionRepository = subscriptionRepository;
  }

  public void execute(UUID id, UUID userId) {
    Subscription subscription = this.subscriptionRepository.findById(id)
        .orElseThrow(SubscriptionNotFoundException::new);

    if (!userId.equals(subscription.getUser().getId())) {
      throw new SubscribeActionNotAllowed();
    }

    this.subscriptionRepository.delete(subscription.getId());
  }
}
