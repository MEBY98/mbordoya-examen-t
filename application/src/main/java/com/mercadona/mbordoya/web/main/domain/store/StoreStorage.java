package com.mercadona.mbordoya.web.main.domain.store;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
@Builder
public class StoreStorage {

  private Long id;
  private String name;
  private Integer capacity;
  private Store store;

  private List<StoreStorageStock> storeStorageStocks;

  public Integer getRemainSpace() {
    return this.capacity - storeStorageStocks.stream().mapToInt(StoreStorageStock::getQuantity).sum();
  }

  public boolean containProductInStock(final Long productId) {
    return storeStorageStocks.stream().anyMatch(storeStorageStock -> productId.equals(storeStorageStock.getProduct().getId()));
  }

  public Optional<StoreStorageStock> getStoreStorageStockByProductId(final Long productId) {
    return storeStorageStocks.stream()
        .filter(storeStorageStock -> productId.equals(storeStorageStock.getProduct().getId()))
        .findFirst();
  }
}
