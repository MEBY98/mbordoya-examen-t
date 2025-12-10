package com.mercadona.mbordoya.web.main.domain.store;

import lombok.Builder;
import lombok.Data;

import static com.mercadona.mbordoya.web.main.application.utils.AppConstants.SPANISH_LOCALE;

@Data
@Builder
public class SpecializationName {

  private Long id;
  private String localeLanguageCode;
  private String name;

  public boolean isSpanish() {
    return SPANISH_LOCALE.equals(localeLanguageCode);
  }
}
