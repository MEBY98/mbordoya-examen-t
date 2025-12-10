package com.mercadona.mbordoya.web.main.domain.store;

import com.mercadona.mbordoya.web.main.application.utils.Exportable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Builder
@AllArgsConstructor
@Data
public class ProductCsvRecord implements Exportable {
  private Long productId;
  private String productName;
  private Long locationId;
  private String locationType;
  private Integer quantity;

  public ProductCsvRecord(final ModuleDomain moduleDomain, final ModuleStock moduleStock) {
    this.productId = moduleStock.getProduct().getId();
    this.productName = moduleStock.getProduct().getName();
    this.locationId = moduleDomain.getId();
    this.locationType = "M";
    this.quantity = moduleStock.getQuantity();
  }
  public ProductCsvRecord(final StoreStorage storeStorage, final StoreStorageStock storeStorageStock) {
    this.productId = storeStorageStock.getProduct().getId();
    this.productName = storeStorageStock.getProduct().getName();
    this.locationId = storeStorage.getId();
    this.locationType = "A";
    this.quantity = storeStorageStock.getQuantity();
  }

  @Override
  public List<String> getRow() {
    return List.of(
        Optional.ofNullable(productId).map(String::valueOf).map(this::formatForExcel).orElse(""),
        Optional.ofNullable(productName).map(this::formatForExcel).orElse(""),
        Optional.ofNullable(locationId).map(String::valueOf).map(this::formatForExcel).orElse(""),
        Optional.ofNullable(locationType).map(this::formatForExcel).orElse(""),
        Optional.ofNullable(quantity).map(String::valueOf).map(this::formatForExcel).orElse("")
    );
  }
}
