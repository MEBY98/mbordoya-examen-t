package com.mercadona.mbordoya.web.main.domain.example;

import lombok.Builder;
import lombok.Data;

import static com.mercadona.mbordoya.web.main.application.utils.AppConstants.SPANISH_LOCALE;

@Data
@Builder
public class ExampleTypeNameDomain {
  private String description;
  private String localeLanguageCode;

  public boolean isSpanish() {
    return SPANISH_LOCALE.equals(localeLanguageCode);
  }
}
