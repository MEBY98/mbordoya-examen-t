package com.mercadona.mbordoya.web.main.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;

@Getter
@Slf4j
@RequiredArgsConstructor
public enum LocaleLanguageTagEnum {
  ES("es", "ES"),
  PT("pt", "PT");

  private final String language;
  private final String country;

  public String getLanguageTag() {
    return this.language + "-" + this.country;
  }

  public static LocaleLanguageTagEnum fromLanguageTag(final String languageTag) {
    for (LocaleLanguageTagEnum localeLanguageTagEnum : values()) {
      if (localeLanguageTagEnum.getLanguageTag().equals(languageTag)) {
        return localeLanguageTagEnum;
      }
    }
    log.warn("LANGUAGE TAG: {} NOT FOUND IN LOCALE_LANGUAGE_TAG_ENUM USING DEFAULT es-ES", LocaleContextHolder.getLocale().toLanguageTag());
    return ES;
  }

  public boolean isSpanish() {
    return this == ES;
  }
}
