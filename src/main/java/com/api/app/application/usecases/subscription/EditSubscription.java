package com.api.app.application.usecases.subscription;

import java.util.UUID;

import com.api.app.application.usecases.subscription.dto.EditSubscriptionDTO;
import com.api.app.application.usecases.subscription.exceptions.SubscribeActionNotAllowed;
import com.api.app.application.usecases.subscription.exceptions.SubscriptionNotFoundException;
import com.api.app.domain.subscription.entity.Subscription;
import com.api.app.domain.subscription.repository.ISubscriptionRepository;

public class EditSubscription {
  private final ISubscriptionRepository subscriptionRepository;

  public EditSubscription(
      ISubscriptionRepository subscriptionRepository) {
    this.subscriptionRepository = subscriptionRepository;
  }

  public void execute(EditSubscriptionDTO payload, UUID id, UUID userId) {
    Subscription subscription = this.subscriptionRepository.findById(id)
        .orElseThrow(SubscriptionNotFoundException::new);

    if (!userId.equals(subscription.getUser().getId())) {
      throw new SubscribeActionNotAllowed();
    }
    if (payload.getAssignedDate() != null) {
      subscription.changeAssignedDate(payload.getAssignedDate());
    }

    if (payload.getCategory() != null) {
      subscription.changeCategory(payload.getCategory());
    }

    if (payload.getName() != null) {
      subscription.changeName(payload.getName());
    }

    if (payload.getPaymentDay() != null) {
      subscription.changePaymentDay(payload.getPaymentDay());
    }

    if (payload.getStatus() != null) {
      subscription.changeStatus(payload.getStatus());
    }

    if (payload.getPrice() != null) {
      subscription.changePrice(payload.getPrice());
    }

    this.subscriptionRepository.save(subscription);
  }

}
