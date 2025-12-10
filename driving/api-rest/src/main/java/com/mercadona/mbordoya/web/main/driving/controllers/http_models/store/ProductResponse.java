package com.mercadona.mbordoya.web.main.driving.controllers.http_models.store;

import com.mercadona.mbordoya.web.main.domain.store.Product;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductResponse {

  private Long id;
  private String name;

  public ProductResponse(final Product product) {
    this.id = product.getId();
    this.name = product.getName();
  }
}
