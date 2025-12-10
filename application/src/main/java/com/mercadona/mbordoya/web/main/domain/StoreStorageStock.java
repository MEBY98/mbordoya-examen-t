package com.mercadona.mbordoya.web.main.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreStorageStock {

  private Product product;
  private StoreStorage storeStorage;
  private Integer quantity;
}
