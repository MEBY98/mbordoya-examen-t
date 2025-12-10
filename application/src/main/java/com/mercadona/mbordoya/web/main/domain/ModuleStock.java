package com.mercadona.mbordoya.web.main.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModuleStock {

  private Product product;
  private ModuleDomain moduleDomain;
  private Integer quantity;
}
