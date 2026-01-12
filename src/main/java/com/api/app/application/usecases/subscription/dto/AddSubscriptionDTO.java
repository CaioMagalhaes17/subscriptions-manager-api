package com.api.app.application.usecases.subscription.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.api.app.domain.subscription.entity.PaymentMethod;
import com.api.app.domain.subscription.enums.SubscriptionCategory;
import com.api.app.domain.subscription.enums.SubscriptionStatus;

public final class AddSubscriptionDTO {
  private final String name;
  private final UUID userId;
  private final SubscriptionCategory category;
  private final SubscriptionStatus status;
  private final Integer price;
  private final LocalDate assignedDate;
  private final String paymentDay;
  private final PaymentMethod paymentMethod;

  public AddSubscriptionDTO(
      String name, UUID userId, SubscriptionCategory category, SubscriptionStatus status, Integer price,
      LocalDate assignedDate, String paymentDay, PaymentMethod paymentMethod) {
    this.assignedDate = assignedDate;
    this.category = category;
    this.name = name;
    this.paymentDay = paymentDay;
    this.price = price;
    this.status = status;
    this.userId = userId;
    this.paymentMethod = paymentMethod;
  }

  public String getName() {
    return name;
  }

  public UUID getUserId() {
    return userId;
  }

  public PaymentMethod getpaPaymentMethod() {
    return this.paymentMethod;
  }

  public SubscriptionCategory getCategory() {
    return category;
  }

  public SubscriptionStatus getStatus() {
    return status;
  }

  public Integer getPrice() {
    return price;
  }

  public LocalDate getAssignedDate() {
    return assignedDate;
  }

  public String getPaymentDay() {
    return paymentDay;
  }
}
