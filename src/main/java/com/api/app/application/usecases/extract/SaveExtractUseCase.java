package com.api.app.application.usecases.extract;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.UUID;

import com.api.app.application.dto.events.ExtractSavedPayload;
import com.api.app.application.gateways.IEventPublisher;

public class SaveExtractUseCase {
  private IEventPublisher<ExtractSavedPayload> eventPublisher;

  public SaveExtractUseCase(
      IEventPublisher<ExtractSavedPayload> eventPublisher) {
    this.eventPublisher = eventPublisher;
  }

  public void execute(
      InputStream buffer, UUID userId) throws IOException {
    Path tempDir = Paths.get("target/tmp");
    String pathString = userId.toString() + "-" + LocalDate.now().toString() + ".pdf";
    Path file = tempDir.resolve(pathString);
    Files.copy(buffer, file, StandardCopyOption.REPLACE_EXISTING);
    eventPublisher.publish(new ExtractSavedPayload(userId, pathString));
  }
}
