package com.api.app.application.valueobject;

import java.io.InputStream;

public class ExtractFile {
  private final InputStream buffer;

  public ExtractFile(InputStream buffer){
    this.buffer = buffer;
  }

  public InputStream getBuffer() {
    return buffer;
  }
}
