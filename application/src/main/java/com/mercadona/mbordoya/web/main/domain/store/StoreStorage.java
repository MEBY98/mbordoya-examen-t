package com.mercadona.mbordoya.web.main.domain.store;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StoreStorage {

  private Long id;
  private String name;
  private Integer capacity;
  private Store store;

  private List<StoreStorageStock> storeStorageStocks;
}
