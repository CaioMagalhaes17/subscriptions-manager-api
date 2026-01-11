package com.api.app.domain.subscription.valueobject;

import java.time.LocalDate;

public class Aggregate {
  private final String name;
  private final Double paymentPercentage;
  private final LocalDate addedDate;

  public Aggregate(String name, Double paymentPercentage, LocalDate addedDate) {
    this.name = name;
    this.paymentPercentage = paymentPercentage;
    this.addedDate = addedDate;
  }

  public String getName() {
    return name;
  }

  public Double getPaymentPercentage() {
    return paymentPercentage;
  }

  public LocalDate getAddedDate() {
    return addedDate;
  }

}
