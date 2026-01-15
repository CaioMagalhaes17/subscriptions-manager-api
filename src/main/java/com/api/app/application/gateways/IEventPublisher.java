package com.api.app.application.gateways;

public interface IEventPublisher<T> {
  void publish(T eventData);
}
