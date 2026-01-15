package com.api.app.application.gateways;

import com.api.app.application.valueobject.ExtractInterpretation;

public interface IExtractInterpreter {
  ExtractInterpretation interpretExtract(String pathString);
}
