package com.api.app.application.usecases.subscription;

import com.api.app.application.usecases.subscription.dto.AddSubscriptionDTO;
import com.api.app.application.usecases.user.exceptions.UserNotFoundException;
import com.api.app.domain.subscription.entity.Subscription;
import com.api.app.domain.subscription.repository.ISubscriptionRepository;
import com.api.app.domain.user.entity.User;
import com.api.app.domain.user.repository.IUserRepository;

public class AddSubscription {
  private final ISubscriptionRepository subscriptionRepository;
  private final IUserRepository userRepository;

  public AddSubscription(
      ISubscriptionRepository subscriptionRepository, IUserRepository userRepository) {
    this.subscriptionRepository = subscriptionRepository;
    this.userRepository = userRepository;
  }

  public void execute(AddSubscriptionDTO payload) {
    User user = this.userRepository.findById(payload.getUserId()).orElseThrow(UserNotFoundException::new);
    Subscription subscription = new Subscription(payload.getName(), user, payload.getCategory(), payload.getStatus(),
        payload.getPrice(), payload.getAssignedDate(), payload.getPaymentDay());
    this.subscriptionRepository.save(subscription);
  }
}
