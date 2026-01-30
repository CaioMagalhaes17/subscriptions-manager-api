package com.api.app.infra.persistance.mongodb.subscription.schemas;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "subscription")
public class SubscriptionSchema {
  @Id
  private String id;
}
