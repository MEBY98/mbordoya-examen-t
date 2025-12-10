package com.mercadona.mbordoya.web.main.driving.controllers.http_models.store;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class StoreResponse {
  private Long id;
  private String name;
  private String address;

  private List<StoreModuleResponse> modules;
}
