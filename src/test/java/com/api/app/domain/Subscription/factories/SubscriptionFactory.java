package com.api.app.domain.Subscription.factories;

import java.time.LocalDate;

import com.api.app.domain.subscription.entity.PaymentMethod;
import com.api.app.domain.subscription.entity.Subscription;
import com.api.app.domain.subscription.enums.SubscriptionCategory;
import com.api.app.domain.subscription.enums.SubscriptionStatus;
import com.api.app.domain.user.entity.User;

public final class SubscriptionFactory {
  public static Subscription makeSubscription(String name, User user, SubscriptionCategory category,
      SubscriptionStatus status, Integer price, LocalDate assignedDate, String paymentDay,
      PaymentMethod paymentMethod) {
    Subscription subscription = new Subscription(name, user, category, status, price, assignedDate, paymentDay,
        paymentMethod);
    return subscription;
  }
}
