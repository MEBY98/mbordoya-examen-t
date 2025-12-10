package com.mercadona.mbordoya.web.main.driving.controllers.http_models.store;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class StoreCreateModuleItemRequest {

  private Integer capacity;
  private Long specializationId;

}
