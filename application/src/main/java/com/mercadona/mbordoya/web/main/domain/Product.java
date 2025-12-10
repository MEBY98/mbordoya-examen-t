package com.mercadona.mbordoya.web.main.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {

  private String id;
  private String name;
  private Double sellingPrice;
  private Specialization specialization;
}
