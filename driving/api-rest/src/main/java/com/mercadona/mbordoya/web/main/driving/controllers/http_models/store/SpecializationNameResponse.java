package com.mercadona.mbordoya.web.main.driving.controllers.http_models.store;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SpecializationNameResponse {
  private String localeLanguageCode;
  private String name;
}
