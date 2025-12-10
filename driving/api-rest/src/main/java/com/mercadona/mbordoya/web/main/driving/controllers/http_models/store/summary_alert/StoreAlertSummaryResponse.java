package com.mercadona.mbordoya.web.main.driving.controllers.http_models.store.summary_alert;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class StoreAlertSummaryResponse {

  private Long id;
  private String name;
  private String address;

  private List<StoreSummaryAlertModuleResponse> modules;
  private List<StoreSummaryAlertStoreStorageResponse> storeStorages;
}
