package com.api.app.application.usecases.subscription.dto;

import java.time.LocalDate;

import com.api.app.domain.subscription.enums.SubscriptionCategory;
import com.api.app.domain.subscription.enums.SubscriptionStatus;

public final class EditSubscriptionDTO {
  private final String name;
  private final SubscriptionCategory category;
  private final SubscriptionStatus status;
  private final Integer price;
  private final LocalDate assignedDate;
  private final String paymentDay;

  public EditSubscriptionDTO(
      String name, SubscriptionCategory category, SubscriptionStatus status, Integer price,
      LocalDate assignedDate, String paymentDay) {
    this.assignedDate = assignedDate;
    this.category = category;
    this.name = name;
    this.paymentDay = paymentDay;
    this.price = price;
    this.status = status;
  }

  public String getName() {
    return name;
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
