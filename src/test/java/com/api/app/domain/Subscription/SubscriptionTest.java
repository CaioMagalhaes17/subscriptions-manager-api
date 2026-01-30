package com.api.app.domain.Subscription;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.api.app.domain.Subscription.factories.SubscriptionFactory;
import com.api.app.domain.subscription.entity.PaymentMethod;
import com.api.app.domain.subscription.entity.Subscription;
import com.api.app.domain.subscription.enums.SubscriptionCategory;
import com.api.app.domain.subscription.enums.SubscriptionStatus;
import com.api.app.domain.subscription.valueobject.UserId;
import com.api.app.domain.user.UserFactory;
import com.api.app.domain.user.entity.User;

public class SubscriptionTest {
  @Test
  void shouldCreateSubscriptionSuccessfully() {
    User user = UserFactory.makeUser("Caio", "caio@gmail.com");
    Subscription subscription = SubscriptionFactory.makeSubscription("Netflix", UserId.of(user.getId()), SubscriptionCategory.STREAMING,
        SubscriptionStatus.ACTIVE, 10, LocalDate.now(), "25", PaymentMethod.CARD);
    assertNotNull(subscription);
    assertNotNull(subscription.getId());
  }
}
