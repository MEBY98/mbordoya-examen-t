package com.mercadona.mbordoya.web.main.driving.controllers.http_models.store.summary;

import com.mercadona.mbordoya.web.main.domain.Store;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class StoreSummaryResponse {
  private Long id;
  private String name;
  private String address;

  private List<StoreSummaryModuleResponse> modules;
  private List<StoreSummaryStoreStorageResponse> storeStorages;

  public StoreSummaryResponse(final Store store) {
    this.id = store.getId();
    this.name = store.getName();
    this.address = store.getAddress();

    this.modules = store.getModules().stream().map(StoreSummaryModuleResponse::new).toList();
    this.storeStorages = store.getStoreStorages().stream().map(StoreSummaryStoreStorageResponse::new).toList();
  }
}
