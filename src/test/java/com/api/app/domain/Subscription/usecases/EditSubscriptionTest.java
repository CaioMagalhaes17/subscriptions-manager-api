package com.api.app.domain.Subscription.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.api.app.application.usecases.subscription.EditSubscription;
import com.api.app.application.usecases.subscription.dto.EditSubscriptionDTO;
import com.api.app.application.usecases.subscription.exceptions.SubscribeActionNotAllowed;
import com.api.app.domain.Subscription.factories.SubscriptionFactory;
import com.api.app.domain.Subscription.repository.InMemorySubscriptionRepository;
import com.api.app.domain.subscription.entity.PaymentMethod;
import com.api.app.domain.subscription.entity.Subscription;
import com.api.app.domain.subscription.enums.SubscriptionCategory;
import com.api.app.domain.subscription.enums.SubscriptionStatus;
import com.api.app.domain.user.UserFactory;
import com.api.app.domain.user.entity.User;

public class EditSubscriptionTest {
  private InMemorySubscriptionRepository subscriptionRepository;
  private EditSubscription useCase;

  @BeforeEach
  void setUp() {
    this.subscriptionRepository = new InMemorySubscriptionRepository();
    this.useCase = new EditSubscription(subscriptionRepository);
  }

  @Test
  void shouldEditSubscription() {
    User user = UserFactory.makeUser("user", "user@gmail.com");

    Subscription subscription = SubscriptionFactory.makeSubscription("Netflix", user, SubscriptionCategory.STREAMING,
        SubscriptionStatus.ACTIVE, 10, LocalDate.now(), "25", PaymentMethod.CARD);
    this.subscriptionRepository.save((subscription));
    EditSubscriptionDTO payload = new EditSubscriptionDTO(null, null, SubscriptionStatus.CANCELED, null, null, null);
    this.useCase.execute(payload, subscription.getId(), user.getId());

    assertEquals(this.subscriptionRepository.findById(subscription.getId()).orElseThrow().getStatus(),
        SubscriptionStatus.CANCELED);

  }

  @Test
  void shouldNotBeAllowedToDeleteSubscription() {
    User user = UserFactory.makeUser("user", "user@gmail.com");
    User user2 = UserFactory.makeUser("user2", "user2@gmail.com");
    Subscription subscription = SubscriptionFactory.makeSubscription("Netflix", user, SubscriptionCategory.STREAMING,
        SubscriptionStatus.ACTIVE, 10, LocalDate.now(), "25", PaymentMethod.CARD);
    this.subscriptionRepository.save((subscription));
    EditSubscriptionDTO payload = new EditSubscriptionDTO(null, null, SubscriptionStatus.CANCELED, null, null, null);
    assertThrows(SubscribeActionNotAllowed.class,
        () -> this.useCase.execute(payload, subscription.getId(), user2.getId()));
  }
}
