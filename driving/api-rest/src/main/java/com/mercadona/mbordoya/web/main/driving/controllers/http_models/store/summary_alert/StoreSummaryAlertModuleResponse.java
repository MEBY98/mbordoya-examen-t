package com.mercadona.mbordoya.web.main.driving.controllers.http_models.store.summary_alert;

import com.mercadona.mbordoya.web.main.driving.controllers.http_models.store.ProductResponse;
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
}
