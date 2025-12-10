package com.mercadona.mbordoya.web.main.application.services.store;

import com.mercadona.mbordoya.web.main.application.exceptions.StoreNotFoundException;
import com.mercadona.mbordoya.web.main.application.ports.driven.StoreDbPort;
import com.mercadona.mbordoya.web.main.domain.ModuleDomain;
import com.mercadona.mbordoya.web.main.domain.Store;
import com.mercadona.mbordoya.web.main.domain.StoreQuery;
import com.mercadona.mbordoya.web.main.domain.StoreStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

  private final StoreDbPort storeDbPort;

  public Long insertStore(final Store storeDomain) {
    return this.storeDbPort.insert(storeDomain);
  }

  public void insertModules(final Long storeId, final List<ModuleDomain> modules) {
    modules.forEach(moduleDomain -> {
      moduleDomain.setStore(Store.builder().id(storeId).build());
      this.storeDbPort.insertModule(moduleDomain);
    });
  }

  public void insertStoreStorages(final Long storeId, final List<StoreStorage> storeStorages) {
    storeStorages.forEach(storeStorage -> {
      storeStorage.setStore(Store.builder().id(storeId).build());
      this.storeDbPort.insertStoreStorage(storeStorage);
    });
  }

  public Store getById(final Long id) {
    return this.storeDbPort.findById(id).orElseThrow(StoreNotFoundException::new);
  }

  public Page<Store> getStores(final StoreQuery query) {
    return this.storeDbPort.getAll(query);
  }

  public void updateStore(final Store storeUpdated) {
    this.storeDbPort.updateStore(storeUpdated);
  }

  public boolean existsById(final Long storeId) {
    return this.storeDbPort.existsById(storeId);
  }

  public void deleteStoreStorage(final Long storeId) {
    final var storeStorages = this.storeDbPort.getStoreStoragesByStoreId(storeId);

    storeStorages.forEach(storeStorage -> {
      final var storeStorageId = storeStorage.getId();
      this.storeDbPort.deleteStoreStorageStock(storeStorageId);
      this.storeDbPort.deleteStoreStorageById(storeStorageId);
    });
  }

  public void deleteModules(final Long storeId) {
    final var modules = this.storeDbPort.getModulesByStoreId(storeId);

    modules.forEach(moduleDomain -> {
      final var moduleDomainId = moduleDomain.getId();
      this.storeDbPort.deleteModuleStock(moduleDomainId);
      this.storeDbPort.deleteModuleById(moduleDomainId);
    });
  }

  public void deleteStore(final Long storeId) {
    this.storeDbPort.deleteById(storeId);
  }
}
