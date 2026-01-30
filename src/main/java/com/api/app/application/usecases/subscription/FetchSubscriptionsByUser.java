package com.api.app.application.usecases.subscription;

import java.util.List;
import java.util.UUID;

import com.api.app.domain.subscription.entity.Subscription;
import com.api.app.domain.subscription.repository.ISubscriptionRepository;
import com.api.app.domain.subscription.valueobject.UserId;

public class FetchSubscriptionsByUser {
  private final ISubscriptionRepository subscriptionRepository;

  public FetchSubscriptionsByUser(
      ISubscriptionRepository subscriptionRepository) {
    this.subscriptionRepository = subscriptionRepository;
  }

  public List<Subscription> execute(UUID userId) {
    return this.subscriptionRepository.findByUserId(UserId.of(userId));
  }
}
