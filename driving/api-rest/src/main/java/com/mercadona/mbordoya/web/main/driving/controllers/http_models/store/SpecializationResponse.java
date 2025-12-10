package com.mercadona.mbordoya.web.main.driving.controllers.http_models.store;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SpecializationResponse {
  private Long id;
  private SpecializationNameResponse specializationName;

}
