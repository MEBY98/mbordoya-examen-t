package com.mercadona.mbordoya.web.main.domain.store;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProductCsvHeaderEnum {
  PRODUCT_ID("PRODUCT_ID"),
  PRODUCT_NAME("PRODUCT_NAME"),
  LOCATION_ID("LOCATION_ID"),
  LOCATION_TYPE("LOCATION_TYPE"),
  QUANTITY("QUANTITY");

  private final String code;
}
