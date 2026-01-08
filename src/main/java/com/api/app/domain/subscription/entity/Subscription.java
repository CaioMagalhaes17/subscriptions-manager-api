package com.api.app.domain.subscription.entity;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import com.api.app.domain.subscription.enums.SubscriptionCategory;
import com.api.app.domain.subscription.enums.SubscriptionStatus;
import com.api.app.domain.subscription.exceptions.InvalidSubscriptionValues;
import com.api.app.domain.user.entity.User;

public class Subscription {
  private final UUID id;
  private final User user;
  private String name;
  private SubscriptionCategory category;
  private SubscriptionStatus status;
  private Integer price;
  private LocalDate assignedDate;
  private String paymentDay;
  private final Instant createdAt;
  private Instant updatedAt;

  public Subscription(String name, User user, SubscriptionCategory category, SubscriptionStatus status, Integer price,
      LocalDate assignedDate, String paymentDay) {
    this.id = UUID.randomUUID();
    this.user = user;
    this.name = name;
    this.category = category;
    this.status = status;
    this.price = price;
    this.assignedDate = assignedDate;
    this.paymentDay = paymentDay;
    this.createdAt = Instant.now();
    this.updatedAt = null;
    this.validate();
  }

  public Subscription(UUID id, User user, String name, SubscriptionCategory category, SubscriptionStatus status,
      Integer price,
      LocalDate assignedDate, String paymentDay, Instant createdAt, Instant updatedAt) {
    this.id = id;
    this.name = name;
    this.user = user;
    this.category = category;
    this.status = status;
    this.price = price;
    this.assignedDate = assignedDate;
    this.paymentDay = paymentDay;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  private void validate() {
    if (this.name == null || this.name.isBlank()) {
      throw new InvalidSubscriptionValues("Invalid Subscription name");
    }

    if (this.status == null) {
      throw new InvalidSubscriptionValues("Invalid Subscription status");
    }

    if (this.price == null || this.price == 0) {
      throw new InvalidSubscriptionValues("Invalid Subscription price");
    }

    if (this.category == null) {
      throw new InvalidSubscriptionValues("Invalid Subscription category");
    }

    if (this.assignedDate == null) {
      throw new InvalidSubscriptionValues("Invalid Subscription assignedDate");
    }

    if (this.paymentDay == null) {
      throw new InvalidSubscriptionValues("Invalid Subscription paymentDay");
    }
  }

  public void changeName(String name) {
    this.name = name;
    this.touch();
  }

  public void changePaymentDay(String paymentDay) {
    this.paymentDay = paymentDay;
    this.touch();
  }

  public void changeAssignedDate(LocalDate assignedDate) {
    this.assignedDate = assignedDate;
    this.touch();
  }

  public void changePrice(Integer price) {
    this.price = price;
    this.touch();
  }

  public void changeCategory(SubscriptionCategory category) {
    this.category = category;
    this.touch();
  }

  public void changeStatus(SubscriptionStatus status) {
    this.status = status;
    this.touch();
  }

  private void touch() {
    this.updatedAt = Instant.now();
  }

  public UUID getId() {
    return id;
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

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public User getUser() {
    return user;
  }
}
