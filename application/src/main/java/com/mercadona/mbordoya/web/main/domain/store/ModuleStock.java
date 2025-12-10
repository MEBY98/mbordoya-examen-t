package com.mercadona.mbordoya.web.main.domain.store;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModuleStock {

  private Product product;
  private ModuleDomain moduleDomain;
  private Integer quantity;

  public boolean isZeroStock() {
    return 0 == quantity;
  }
}
