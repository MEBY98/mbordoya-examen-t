package com.mercadona.mbordoya.web.main.driving.controllers.http_models.store.summary;

import com.mercadona.mbordoya.web.main.domain.store.ModuleDomain;
import com.mercadona.mbordoya.web.main.driving.controllers.http_models.store.SpecializationNameResponse;
import com.mercadona.mbordoya.web.main.driving.controllers.http_models.store.SpecializationResponse;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class StoreSummaryModuleResponse {
  private Long id;
  private Integer capacity;
  private SpecializationResponse specialization;

  private List<StoreSummaryModuleProductResponse> products;

  public StoreSummaryModuleResponse(final ModuleDomain moduleDomain) {
    this.id = moduleDomain.getId();
    this.capacity = moduleDomain.getCapacity();
    this.specialization = buildSpecialization(moduleDomain);
    this.products = buildProducts(moduleDomain);
  }

  private static List<StoreSummaryModuleProductResponse> buildProducts(final ModuleDomain moduleDomain) {
    return moduleDomain.getModuleStocks().stream().map(StoreSummaryModuleProductResponse::new).toList();
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
