package com.mercadona.mbordoya.web.main.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.List;

@Getter
@Builder
public class ExampleTypeDomain {
  private Long typeId;
  private List<ExampleTypeNameDomain> exampleTypeNameDomains;

  public ExampleTypeNameDomain getLocalizedName() {
    if (this.exampleTypeNameDomains == null || this.exampleTypeNameDomains.isEmpty()) {
      return null;
    } else {
      final LocaleLanguageTagEnum languageTag =
          LocaleLanguageTagEnum.fromLanguageTag(LocaleContextHolder.getLocale().toLanguageTag());
      if (!languageTag.isSpanish()) {
        return getNameByTagOrSpanishDefault(languageTag);
      }
      return getSpanishName();
    }
  }

  private ExampleTypeNameDomain getNameByTagOrSpanishDefault(LocaleLanguageTagEnum languageTag) {
    return this.exampleTypeNameDomains.stream()
        .filter(name -> languageTag.getLanguageTag().equals(name.getLocaleLanguageCode()))
        .findFirst()
        .orElse(getSpanishName());
  }

  private ExampleTypeNameDomain getSpanishName() {
    return this.exampleTypeNameDomains.stream()
        .filter(ExampleTypeNameDomain::isSpanish)
        .findFirst()
        .orElse(null);
  }
}
