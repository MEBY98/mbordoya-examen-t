package com.mercadona.mbordoya.web.main.driving.controllers.http_models.store.summary_alert;

import com.mercadona.mbordoya.web.main.driving.controllers.http_models.store.ProductResponse;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class StoreSummaryAlertStoreStorageResponse {
  private Long id;
  private String name;
  private Integer capacity;

  private List<ProductResponse> productsUnexposed;
}
