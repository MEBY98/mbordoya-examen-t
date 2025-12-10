package com.mercadona.mbordoya.web.main.driving.controllers.http_models.store.summary;

import com.mercadona.mbordoya.web.main.domain.store.StoreStorageStock;
import com.mercadona.mbordoya.web.main.driving.controllers.http_models.store.ProductResponse;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class StoreSummaryStoreStorageProductResponse {

  private ProductResponse product;
  private Integer quantity;

  public StoreSummaryStoreStorageProductResponse(final StoreStorageStock storeStorageStock) {
    this.product = new ProductResponse(storeStorageStock.getProduct());
    this.quantity = storeStorageStock.getQuantity();
  }
}
