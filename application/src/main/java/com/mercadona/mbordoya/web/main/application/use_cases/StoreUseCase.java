package com.mercadona.mbordoya.web.main.application.use_cases;

import com.mercadona.mbordoya.web.main.application.ports.driving.StoreUseCasePort;
import com.mercadona.mbordoya.web.main.application.services.store.ProductCsvWriter;
import com.mercadona.mbordoya.web.main.application.services.store.StoreFiller;
import com.mercadona.mbordoya.web.main.application.services.store.StoreService;
import com.mercadona.mbordoya.web.main.domain.store.MovementRecord;
import com.mercadona.mbordoya.web.main.domain.store.ProductCsvRecord;
import com.mercadona.mbordoya.web.main.domain.store.Store;
import com.mercadona.mbordoya.web.main.domain.store.StoreQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoreUseCase implements StoreUseCasePort {

  private final StoreService storeService;
  private final StoreFiller storeFiller;
  private final ProductCsvWriter productCsvWriter;

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

  @Override
  public Store getStore(final Long id) {
    final var store = this.storeService.getById(id);
    final var modules = this.storeService.getModulesWithStockByStoreId(id);
    this.storeFiller.fillModulesSpecialization(modules);
    final var storeStorages = this.storeService.getStoreStoragesWithStockByStoreId(id);
    store.setModules(modules);
    store.setStoreStorages(storeStorages);
    return store;
  }

  @Override
  public String getProductsCsv() {
    final var modules = this.storeService.getAllModulesWithStock();
    final var storeStorages = this.storeService.getAllStoreStoragesWithStock();
    final List<ProductCsvRecord> productRecords = new LinkedList<>();

    modules.forEach(moduleDomain -> moduleDomain.getModuleStocks()
            .forEach(moduleStock -> productRecords.add(new ProductCsvRecord(moduleDomain, moduleStock))));
    storeStorages.forEach(storeStorage -> storeStorage.getStoreStorageStocks()
        .forEach(storeStorageStock -> productRecords.add(new ProductCsvRecord(storeStorage, storeStorageStock))));

    final var bytes = this.productCsvWriter.parseToBytesWithHeaders(productRecords);

    return this.storeService.uploadProductCsv(bytes);
  }

  @Override
  public void processMovements(final Long storeId, final List<MovementRecord> movements) {
    final var modules = this.storeService.getModulesWithStockByStoreId(storeId);
    final var moduleStocks = modules.stream().flatMap(moduleDomain -> moduleDomain.getModuleStocks().stream()).toList();

    final var storeStorages = this.storeService.getStoreStoragesWithStockByStoreId(storeId);

    this.storeService.processMovementTypeVEAndRO(moduleStocks, movements);
    this.storeService.processMovementTypeEA(storeStorages, movements);
    //TODO hacer el process de los tipos de movimientos que falta: RP y RA

    this.storeService.updateStoreStoragesStock(storeStorages.stream().flatMap(storeStorage -> storeStorage.getStoreStorageStocks().stream()).toList());
    this.storeService.updateModulesStock(moduleStocks);
  }
}
