package com.mercadona.mbordoya.web.main.driving.controllers.http_models.store.summary_alert;

import com.mercadona.mbordoya.web.main.domain.ModuleDomain;
import com.mercadona.mbordoya.web.main.domain.ModuleStock;
import com.mercadona.mbordoya.web.main.driving.controllers.http_models.store.ProductResponse;
import com.mercadona.mbordoya.web.main.driving.controllers.http_models.store.SpecializationNameResponse;
import com.mercadona.mbordoya.web.main.driving.controllers.http_models.store.SpecializationResponse;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class StoreSummaryAlertModuleResponse {

  private Long id;
  private Integer capacity;
  private SpecializationResponse specialization;

  private List<ProductResponse> productHoles;

  public StoreSummaryAlertModuleResponse(final ModuleDomain moduleDomain) {
    this.id = moduleDomain.getId();
    this.capacity = moduleDomain.getCapacity();
    this.specialization = buildSpecialization(moduleDomain);
    this.productHoles = buildProductHoles(moduleDomain);
  }

  private static List<ProductResponse> buildProductHoles(final ModuleDomain moduleDomain) {
    return moduleDomain.getProductWithZeroStock().stream()
        .map(ModuleStock::getProduct)
        .map(ProductResponse::new)
        .toList();
  }

  private static SpecializationResponse buildSpecialization(final ModuleDomain moduleDomain) {
    final var specializationName = moduleDomain.getSpecialization().getLocalizedName();
    return SpecializationResponse.builder()
        .id(moduleDomain.getSpecialization().getId())
        .specializationName(SpecializationNameResponse.builder()
            .name(specializationName.getName())
            .localeLanguageCode(specializationName.getLocaleLanguageCode())
            .build())
        .build();
  }
}
