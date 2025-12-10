package com.mercadona.mbordoya.web.main.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.List;

@Data
@Builder
public class Specialization {
  private Long id;
  private List<SpecializationName> specializationNameList;

  public SpecializationName getLocalizedName() {
    if (this.specializationNameList == null || this.specializationNameList.isEmpty()) {
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

  private SpecializationName getNameByTagOrSpanishDefault(LocaleLanguageTagEnum languageTag) {
    return this.specializationNameList.stream()
        .filter(name -> languageTag.getLanguageTag().equals(name.getLocaleLanguageCode()))
        .findFirst()
        .orElse(getSpanishName());
  }

  private SpecializationName getSpanishName() {
    return this.specializationNameList.stream()
        .filter(SpecializationName::isSpanish)
        .findFirst()
        .orElse(null);
  }
}
