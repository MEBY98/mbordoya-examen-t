package com.mercadona.mbordoya.web.main.domain.store;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StoreQuery {
  private String name;
  private int page;
  private int pageSize;
  private String sort;
}
