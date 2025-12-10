package com.mercadona.mbordoya.web.main.domain.store;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {

  private Long id;
  private String name;
  private Double sellingPrice;
  private Specialization specialization;
}
