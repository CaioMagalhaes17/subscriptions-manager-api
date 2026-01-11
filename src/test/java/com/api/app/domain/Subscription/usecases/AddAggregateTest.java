package com.api.app.domain.Subscription.usecases;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.api.app.application.usecases.subscription.AddAggregate;
import com.api.app.domain.Subscription.factories.SubscriptionFactory;
import com.api.app.domain.Subscription.repository.InMemorySubscriptionRepository;
import com.api.app.domain.subscription.entity.Subscription;
import com.api.app.domain.subscription.enums.SubscriptionCategory;
import com.api.app.domain.subscription.enums.SubscriptionStatus;
import com.api.app.domain.subscription.valueobject.Aggregate;
import com.api.app.domain.user.UserFactory;
import com.api.app.domain.user.entity.User;

public class AddAggregateTest {
  private InMemorySubscriptionRepository subscriptionRepository;
  private AddAggregate useCase;

  @BeforeEach
  void setUp() {
    this.subscriptionRepository = new InMemorySubscriptionRepository();
    this.useCase = new AddAggregate(subscriptionRepository);
  }

  @Test
  void shouldAddAggregate() {
    User user = UserFactory.makeUser("user", "user@gmail.com");

    Subscription subscription = SubscriptionFactory.makeSubscription("Netflix", user, SubscriptionCategory.STREAMING,
        SubscriptionStatus.ACTIVE, 10, LocalDate.now(), "25");
    this.subscriptionRepository.save((subscription));

    assertTrue(subscription.getAggregates().isEmpty());
    this.useCase.execute(subscription.getId(), new Aggregate("Joao", 0.33, LocalDate.now()));
    assertNotNull(subscription.getAggregates());
    assertEquals("Joao", subscription.getAggregates().get(0).getName());
  }
}
