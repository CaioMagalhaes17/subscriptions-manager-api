package com.api.app.application.usecases.extract;

import java.util.List;
import java.util.stream.Collectors;

import com.api.app.application.dto.events.BankExtractSavedEvent;
import com.api.app.application.gateways.IExtractInterpreter;
import com.api.app.application.valueobject.ExtractInterpretation;
import com.api.app.domain.subscription.entity.PaymentMethod;
import com.api.app.domain.subscription.entity.Subscription;
import com.api.app.domain.subscription.enums.SubscriptionCategory;
import com.api.app.domain.subscription.enums.SubscriptionStatus;
import com.api.app.domain.subscription.repository.ISubscriptionRepository;
import com.api.app.domain.subscription.valueobject.UserId;


public class ReadExtractUseCase {
  private IExtractInterpreter extractInterpreter;
  private ISubscriptionRepository subscriptionRepository;

  public ReadExtractUseCase(
      IExtractInterpreter extractInterpreter,
      ISubscriptionRepository subscriptionRepository) {
    this.extractInterpreter = extractInterpreter;
    this.subscriptionRepository = subscriptionRepository;
  }

  void execute(BankExtractSavedEvent extractEvent) {
    ExtractInterpretation interpretation = this.extractInterpreter.interpretExtract(extractEvent.getExtractReference().path());
    List<Subscription> subscriptionsSaved = interpretation.getExtractSubscriptions().stream().map(
      subscription -> {
        Subscription subscriptionEntity = new Subscription(
          subscription.getName(),
          UserId.of(interpretation.getUserId()),
          SubscriptionCategory.valueOf(subscription.getCategory().toUpperCase()),
          SubscriptionStatus.ACTIVE,
          subscription.getPrice(),
          null,
          subscription.getPaymentDay(),
          PaymentMethod.CARD 
        );
        this.subscriptionRepository.save(subscriptionEntity);
        return subscriptionEntity;
      }
    ).collect(Collectors.toList());
  }
}
