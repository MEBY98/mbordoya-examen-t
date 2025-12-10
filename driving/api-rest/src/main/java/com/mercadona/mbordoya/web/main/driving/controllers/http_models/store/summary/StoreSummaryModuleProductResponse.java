package com.mercadona.mbordoya.web.main.driving.controllers.http_models.store.summary;

import com.mercadona.mbordoya.web.main.domain.store.ModuleStock;
import com.mercadona.mbordoya.web.main.driving.controllers.http_models.store.ProductResponse;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class StoreSummaryModuleProductResponse {
  private ProductResponse product;
  private Integer quantity;

  public StoreSummaryModuleProductResponse(final ModuleStock moduleStock) {
    this.product = new ProductResponse(moduleStock.getProduct());
    this.quantity = moduleStock.getQuantity();
  }
}
