package com.mercadona.mbordoya.web.main.application.use_cases;

import com.mercadona.mbordoya.web.main.application.ports.driving.StoreUseCasePort;
import com.mercadona.mbordoya.web.main.application.services.store.StoreFiller;
import com.mercadona.mbordoya.web.main.application.services.store.StoreService;
import com.mercadona.mbordoya.web.main.domain.Store;
import com.mercadona.mbordoya.web.main.domain.StoreQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoreUseCase implements StoreUseCasePort {

  private final StoreService storeService;
  private final StoreFiller storeFiller;

  @Override
  public Long createStore(final Store storeDomain) {
    storeDomain.setDefaultStorageCreation();
    final Long storeId = this.storeService.insertStore(storeDomain);
    this.storeService.insertModules(storeId, storeDomain.getModules());
    this.storeService.insertStoreStorages(storeId, storeDomain.getStoreStorages());
    return storeId;
  }

  @Override
  public Store getStoreById(final Long id) {
    final var store = this.storeService.getById(id);
    this.storeFiller.fillModulesSpecialization(store.getModules());
    return store;
  }

  @Override
  public Page<Store> getStores(final StoreQuery query) {
    final var storesPage = this.storeService.getStores(query);
    storesPage.forEach(store -> this.storeFiller.fillModulesSpecialization(store.getModules()));
    return storesPage;
  }

  @Override
  public Store updateStore(final Store storeUpdated) {
    final var storeDB = this.storeService.getById(storeUpdated.getId());
    storeDB.update(storeUpdated);
    this.storeService.updateStore(storeDB);
    return storeUpdated;
  }

  @Override
  public Long deleteStore(final Long storeId) {
    if (this.storeService.existsById(storeId)) {
      this.storeService.deleteStoreStorage(storeId);
      this.storeService.deleteModules(storeId);
      this.storeService.deleteStore(storeId);
    }
    return storeId;
  }
}
