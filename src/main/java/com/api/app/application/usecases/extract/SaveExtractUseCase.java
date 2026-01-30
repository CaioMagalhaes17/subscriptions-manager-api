package com.api.app.application.usecases.extract;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.UUID;

import com.api.app.application.dto.events.BankExtractSavedEvent;

import com.api.app.application.gateways.IEventPublisher;
import com.api.app.application.gateways.IExtractStorage;
import com.api.app.application.valueobject.ExtractFile;
import com.api.app.application.valueobject.ExtractReference;

public class SaveExtractUseCase {
  private IEventPublisher<BankExtractSavedEvent> eventPublisher;
  private IExtractStorage extractStorage;

  public SaveExtractUseCase(IEventPublisher<BankExtractSavedEvent> eventPublisher, IExtractStorage extractStorage) {
    this.eventPublisher = eventPublisher;
    this.extractStorage = extractStorage;
  }

  public void execute(InputStream buffer, UUID userId) {
    ExtractReference extractReference = this.extractStorage.saveExtract(new ExtractFile(buffer));
    eventPublisher.publish(new BankExtractSavedEvent(userId, extractReference));
  }
}
