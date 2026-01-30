package com.api.app.application.gateways;

import java.io.InputStream;

import com.api.app.application.valueobject.ExtractFile;
import com.api.app.application.valueobject.ExtractReference;

public interface IExtractStorage {
  ExtractReference saveExtract(ExtractFile file);

  InputStream readExtract(ExtractReference reference);
}
