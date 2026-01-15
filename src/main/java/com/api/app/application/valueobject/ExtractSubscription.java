package com.api.app.application.valueobject;

public class ExtractSubscription {
  private final String name;
  private final String category;
  private final Integer price;
  private final String paymentDay;

  public ExtractSubscription(
      String name,
      String category,
      Integer price,
      String paymentDay) {

    this.category = category;
    this.name = name;
    this.price = price;
    this.paymentDay = paymentDay;
  }

  public String getName() {
    return name;
  }

  public String getCategory() {
    return category;
  }

  public Integer getPrice() {
    return price;
  }

  public String getPaymentDay() {
    return paymentDay;
  }

}
