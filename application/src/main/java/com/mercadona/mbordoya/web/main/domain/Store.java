package com.mercadona.mbordoya.web.main.domain;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Store {

  private Long id;
  private String name;
  private String address;

  private List<ModuleDomain> modules;
  private List<StoreStorage> storeStorages;

  public void setDefaultStorageCreation() {
    this.storeStorages = new ArrayList<>();
    this.storeStorages.add(StoreStorage.builder()
            .store(this)
            .capacity(100)
            .name("Zona Almacenaje 1")
        .build());
  }

  public void update(final Store storeUpdated) {
    this.name = storeUpdated.getName();
    this.address =  storeUpdated.getAddress();
  }
}
