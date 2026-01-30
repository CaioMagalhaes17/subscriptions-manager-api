package com.api.app.domain.subscription.entity;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.api.app.domain.subscription.enums.SubscriptionCategory;
import com.api.app.domain.subscription.enums.SubscriptionStatus;
import com.api.app.domain.subscription.exceptions.InvalidSubscriptionValues;
import com.api.app.domain.subscription.valueobject.Aggregate;
import com.api.app.domain.subscription.valueobject.UserId;

public class Subscription {
  private final UUID id;
  private final UserId userId;
  private String name;
  private SubscriptionCategory category;
  private SubscriptionStatus status;
  private Integer price;
  private LocalDate assignedDate;
  private String paymentDay;
  private List<Aggregate> aggregates = new ArrayList<>();
  private PaymentMethod paymentMethod;
  private final Instant createdAt;
  private Instant updatedAt;

  public Subscription(String name, UserId userId, SubscriptionCategory category, SubscriptionStatus status, Integer price,
      LocalDate assignedDate, String paymentDay, PaymentMethod paymentMethod) {
    this.id = UUID.randomUUID();
    this.userId = userId;
    this.name = name;
    this.category = category;
    this.status = status;
    this.price = price;
    this.assignedDate = assignedDate;
    this.paymentDay = paymentDay;
    this.createdAt = Instant.now();
    this.updatedAt = null;
    this.aggregates = new ArrayList<>();
    this.paymentMethod = paymentMethod;
    this.validate();
  }

  public Subscription(UUID id, UserId userId, String name, SubscriptionCategory category, SubscriptionStatus status,
      Integer price,
      LocalDate assignedDate, String paymentDay, List<Aggregate> aggregates, Instant createdAt, Instant updatedAt,
      PaymentMethod paymentMethod) {
    this.id = id;
    this.name = name;
    this.userId = userId;
    this.category = category;
    this.status = status;
    this.price = price;
    this.assignedDate = assignedDate;
    this.paymentDay = paymentDay;
    this.aggregates = aggregates;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.paymentMethod = paymentMethod;
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

  public void addAggregate(Aggregate aggregate) {
    this.aggregates.add(aggregate);
    this.touch();
  }

  public void updateAggregate(Aggregate aggregate) {
    Aggregate existing = this.aggregates.stream().filter(a -> a.getName().equals(aggregate.getName())).findFirst()
        .orElseThrow();
    this.aggregates.remove(existing);
    this.aggregates.add(aggregate);
    this.touch();
  }

  public void removeAggregate(Aggregate aggregate) {
    this.aggregates.remove(aggregate);
    this.touch();
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

  public PaymentMethod getPaymentMethod() {
    return this.paymentMethod;
  }

  public List<Aggregate> getAggregates() {
    return this.aggregates;
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

  public UserId getUserId() {
    return userId;
  }
}
