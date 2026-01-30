package com.api.app.domain.Subscription.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.api.app.application.usecases.subscription.AddAggregate;
import com.api.app.application.usecases.subscription.EditAggregate;
import com.api.app.domain.Subscription.factories.SubscriptionFactory;
import com.api.app.domain.Subscription.repository.InMemorySubscriptionRepository;
import com.api.app.domain.subscription.entity.PaymentMethod;
import com.api.app.domain.subscription.entity.Subscription;
import com.api.app.domain.subscription.enums.SubscriptionCategory;
import com.api.app.domain.subscription.enums.SubscriptionStatus;
import com.api.app.domain.subscription.valueobject.Aggregate;
import com.api.app.domain.subscription.valueobject.UserId;
import com.api.app.domain.user.UserFactory;
import com.api.app.domain.user.entity.User;

public class EditAggregateTest {
  private InMemorySubscriptionRepository subscriptionRepository;
  private EditAggregate useCase;
  private AddAggregate addAggregateUseCase;

  @BeforeEach
  void setUp() {
    this.subscriptionRepository = new InMemorySubscriptionRepository();
    this.addAggregateUseCase = new AddAggregate(subscriptionRepository);
    this.useCase = new EditAggregate(subscriptionRepository);
  }

  @Test
  void shouldEditAggregates() {
    User user = UserFactory.makeUser("user", "user@gmail.com");

    Subscription subscription = SubscriptionFactory.makeSubscription("Netflix", UserId.of(user.getId()), SubscriptionCategory.STREAMING,
        SubscriptionStatus.ACTIVE, 10, LocalDate.now(), "25", PaymentMethod.CARD);
    this.subscriptionRepository.save((subscription));
    this.addAggregateUseCase.execute(subscription.getId(), new Aggregate("Joao", 0.33, LocalDate.now()));
    assertNotNull(subscription.getAggregates());
    assertEquals("Joao", subscription.getAggregates().get(0).getName());
    this.useCase.execute(subscription.getId(), new Aggregate("Joao", 0.55, LocalDate.now()));
    assertEquals(0.55, subscription.getAggregates().get(0).getPaymentPercentage());

  }
}
