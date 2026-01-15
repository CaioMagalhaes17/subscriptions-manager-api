package com.api.app.application.usecases.extract;

import java.util.List;
import java.util.stream.Collectors;

import com.api.app.application.dto.events.ExtractSavedPayload;
import com.api.app.application.gateways.IExtractInterpreter;
import com.api.app.application.usecases.user.exceptions.UserNotFoundException;
import com.api.app.application.valueobject.ExtractInterpretation;
import com.api.app.domain.subscription.entity.PaymentMethod;
import com.api.app.domain.subscription.entity.Subscription;
import com.api.app.domain.subscription.enums.SubscriptionCategory;
import com.api.app.domain.subscription.enums.SubscriptionStatus;
import com.api.app.domain.subscription.repository.ISubscriptionRepository;
import com.api.app.domain.user.entity.User;
import com.api.app.domain.user.repository.IUserRepository;

public class ReadExtractUseCase {
  private IExtractInterpreter extractInterpreter;
  private IUserRepository userRepository;
  private ISubscriptionRepository subscriptionRepository;

  public ReadExtractUseCase(
      IExtractInterpreter extractInterpreter, IUserRepository userRepository,
      ISubscriptionRepository subscriptionRepository) {
    this.extractInterpreter = extractInterpreter;
    this.userRepository = userRepository;
    this.subscriptionRepository = subscriptionRepository;
  }

  void execute(
      ExtractSavedPayload extractSavedPayload) {
    ExtractInterpretation interpretation = this.extractInterpreter.interpretExtract(extractSavedPayload.getPathName());
    User user = this.userRepository.findById(extractSavedPayload.getUserId()).orElseThrow(UserNotFoundException::new);
    List<Subscription> subscriptionsSaved = interpretation.getExtractSubscriptions().stream().map(
      subscription -> {
        Subscription subscriptionEntity = new Subscription(
          subscription.getName(),
          user,
          SubscriptionCategory.valueOf(subscription.getCategory().toUpperCase()),
          SubscriptionStatus.ACTIVE,
          subscription.getPrice(),
          null,
          subscription.getPaymentDay(),
          PaymentMethod.CARD 
        );
        this.subscriptionRepository.save(subscriptionEntity);
        return subscriptionEntity
      }
    ).collect(Collectors.toList());
  }
}
