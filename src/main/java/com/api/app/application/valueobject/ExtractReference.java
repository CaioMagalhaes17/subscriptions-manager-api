package com.api.app.application.valueobject;

public class ExtractReference {
  private final String path;
  private final String fileName;

  public ExtractReference(String path, String fileName){
    this.path = path;
    this.fileName = fileName;
  }

  public String path(){
    return this.path;
  }

  public String fileName(){
    return this.fileName;
  }
}
