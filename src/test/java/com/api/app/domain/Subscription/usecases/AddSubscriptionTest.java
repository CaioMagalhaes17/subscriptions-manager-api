package com.api.app.domain.Subscription.usecases;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.api.app.application.usecases.subscription.AddSubscription;
import com.api.app.application.usecases.subscription.dto.AddSubscriptionDTO;
import com.api.app.domain.Subscription.repository.InMemorySubscriptionRepository;
import com.api.app.domain.subscription.entity.PaymentMethod;
import com.api.app.domain.subscription.enums.SubscriptionCategory;
import com.api.app.domain.subscription.enums.SubscriptionStatus;
import com.api.app.domain.user.InMemoryUserRepository;
import com.api.app.domain.user.UserFactory;
import com.api.app.domain.user.entity.User;

public class AddSubscriptionTest {
  private InMemoryUserRepository userRepository;
  private InMemorySubscriptionRepository subscriptionRepository;
  private AddSubscription useCase;

  @BeforeEach
  void setUp() {
    this.subscriptionRepository = new InMemorySubscriptionRepository();
    this.userRepository = new InMemoryUserRepository();
    this.useCase = new AddSubscription(subscriptionRepository, userRepository);
  }

  @Test
  void shouldAddSubscription() {
    User user = UserFactory.makeUser("user", "user@gmail.com");
    this.userRepository.save(user);
    this.useCase.execute(new AddSubscriptionDTO("Netflix", user.getId(),
        SubscriptionCategory.STREAMING,
        SubscriptionStatus.ACTIVE, 10, LocalDate.now(), "25", PaymentMethod.CARD));

    assertNotNull(this.subscriptionRepository.findByUser(user).stream().filter(sub -> sub.getName().equals("Netflix"))
        .findFirst());
  }
}
