package com.api.app.domain.Subscription.usecases;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.api.app.application.usecases.subscription.DeleteSubscription;
import com.api.app.domain.Subscription.factories.SubscriptionFactory;
import com.api.app.domain.Subscription.repository.InMemorySubscriptionRepository;
import com.api.app.domain.subscription.entity.PaymentMethod;
import com.api.app.domain.subscription.entity.Subscription;
import com.api.app.domain.subscription.enums.SubscriptionCategory;
import com.api.app.domain.subscription.enums.SubscriptionStatus;
import com.api.app.domain.user.UserFactory;
import com.api.app.domain.user.entity.User;

public class DeleteSubscriptionTest {
  private InMemorySubscriptionRepository subscriptionRepository;
  private DeleteSubscription useCase;

  @BeforeEach
  void setUp() {
    this.subscriptionRepository = new InMemorySubscriptionRepository();
    this.useCase = new DeleteSubscription(subscriptionRepository);
  }

  @Test
  void shouldDeleteSubscription() {
    User user = UserFactory.makeUser("user", "user@gmail.com");

    Subscription subscription = SubscriptionFactory.makeSubscription("Netflix", user, SubscriptionCategory.STREAMING,
        SubscriptionStatus.ACTIVE, 10, LocalDate.now(), "25", PaymentMethod.CARD);
    this.subscriptionRepository.save((subscription));

    this.useCase.execute(subscription.getId(), user.getId());
    assertThrows(NoSuchElementException.class,
        () -> this.subscriptionRepository.findById(subscription.getId()).orElseThrow());
  }
}
