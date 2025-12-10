package com.mercadona.mbordoya.web.main.driving.controllers.http_models.store.summary;

import com.mercadona.mbordoya.web.main.domain.store.StoreStorage;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class StoreSummaryStoreStorageResponse {
  private Long id;
  private String name;
  private Integer capacity;

  private List<StoreSummaryStoreStorageProductResponse> products;

  public StoreSummaryStoreStorageResponse(final StoreStorage storeStorage) {
    this.id = storeStorage.getId();
    this.name = storeStorage.getName();
    this.capacity = storeStorage.getCapacity();
    this.products = buildProducts(storeStorage);
  }

  private static List<StoreSummaryStoreStorageProductResponse> buildProducts(final StoreStorage storeStorage) {
    return storeStorage.getStoreStorageStocks().stream().map(StoreSummaryStoreStorageProductResponse::new).toList();
  }
}
