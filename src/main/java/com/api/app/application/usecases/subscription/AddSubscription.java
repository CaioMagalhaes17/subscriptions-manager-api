package com.api.app.application.usecases.subscription;

import com.api.app.application.usecases.subscription.dto.AddSubscriptionDTO;
import com.api.app.domain.subscription.entity.Subscription;
import com.api.app.domain.subscription.repository.ISubscriptionRepository;
import com.api.app.domain.subscription.valueobject.UserId;
import com.api.app.domain.user.entity.User;

public class AddSubscription {
  private final ISubscriptionRepository subscriptionRepository;
  

  public AddSubscription(ISubscriptionRepository subscriptionRepository) {
    this.subscriptionRepository = subscriptionRepository;
  }

  public void execute(AddSubscriptionDTO payload) {
    Subscription subscription = new Subscription(payload.getName(), UserId.of(payload.getUserId()), payload.getCategory(), payload.getStatus(),
        payload.getPrice(), payload.getAssignedDate(), payload.getPaymentDay(), payload.getpaPaymentMethod());
    this.subscriptionRepository.save(subscription);
  }
}
