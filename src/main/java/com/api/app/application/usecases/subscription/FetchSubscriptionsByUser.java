package com.api.app.application.usecases.subscription;

import java.util.List;
import java.util.UUID;

import com.api.app.application.usecases.user.exceptions.UserNotFoundException;
import com.api.app.domain.subscription.entity.Subscription;
import com.api.app.domain.subscription.repository.ISubscriptionRepository;
import com.api.app.domain.user.entity.User;
import com.api.app.domain.user.repository.IUserRepository;

public class FetchSubscriptionsByUser {
  private final ISubscriptionRepository subscriptionRepository;
  private final IUserRepository userRepository;

  public FetchSubscriptionsByUser(
      ISubscriptionRepository subscriptionRepository, IUserRepository userRepository) {
    this.subscriptionRepository = subscriptionRepository;
    this.userRepository = userRepository;
  }

  public List<Subscription> execute(UUID userId) {
    User user = this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    return this.subscriptionRepository.findByUser(user);
  }
}
