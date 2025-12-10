package com.mercadona.mbordoya.web.main.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ModuleDomain {

  private Long id;
  private Integer capacity;
  private Store store;
  private Specialization specialization;

  private List<ModuleStock> moduleStocks;

  public boolean hasSpecialization() {
    return specialization != null && specialization.getId() != null;
  }

  public boolean hasProductWithZeroStock() {
    return moduleStocks.stream().anyMatch(ModuleStock::isZeroStock);
  }

  public List<ModuleStock> getProductWithZeroStock() {
    return moduleStocks.stream().filter(ModuleStock::isZeroStock).toList();
  }
}
