package com.mercadona.mbordoya.web.main.application.services.store;

import com.mercadona.mbordoya.web.main.application.exceptions.StoreNotFoundException;
import com.mercadona.mbordoya.web.main.application.exceptions.StoreStorageNotHasSpaceException;
import com.mercadona.mbordoya.web.main.application.ports.driven.StoreBucketPort;
import com.mercadona.mbordoya.web.main.application.ports.driven.StoreDbPort;
import com.mercadona.mbordoya.web.main.domain.store.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Comparator.comparing;

@Service
@RequiredArgsConstructor
public class StoreService {

  private final StoreDbPort storeDbPort;
  private final StoreBucketPort storeBucketPort;

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
    return this.storeDbPort.findById(id).orElseThrow(() -> new StoreNotFoundException(id));
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

  public List<ModuleDomain> getModulesWithStockByStoreId(final Long id) {
    return this.storeDbPort.getModulesWithStockByStoreId(id);
  }

  public List<StoreStorage> getStoreStoragesWithStockByStoreId(final Long id) {
    return this.storeDbPort.getStoreStorageWithStockByStoreId(id);
  }

  public List<ModuleDomain> getAllModulesWithStock() {
    return this.storeDbPort.getAllModulesWithStock();
  }

  public List<StoreStorage> getAllStoreStoragesWithStock() {
    return this.storeDbPort.getAllStoreStoragesWithStock();
  }

  public String uploadProductCsv(final byte[] bytes) {
    return this.storeBucketPort.uploadProductCsv(bytes).toString();
  }

  public void processMovementTypeVEAndRO(final List<ModuleStock> moduleStocks, final List<MovementRecord> movements) {
    final var movementsVEAndRO = movements.stream().filter(MovementRecord::isVEOrRO).toList();

    movementsVEAndRO.forEach(movementRecordVE -> {
      final var moduleProductStocks = moduleStocks.stream()
          .filter(moduleStock -> moduleStock.getProduct().getId().equals(movementRecordVE.productId()))
          .sorted(comparing(ModuleStock::getQuantity))
          .toList();

      boolean movementApplied = false;
      int i = 0;
      while (!movementApplied && i < moduleProductStocks.size()) {
        final var moduleStock = moduleProductStocks.get(i);
        if (moduleStock.getQuantity() >= movementRecordVE.quantity()) {
          moduleStock.setQuantity(moduleStock.getQuantity() - movementRecordVE.quantity());
          movementApplied = true;
        } else {
          i++;
        }
      }

      if (!movementApplied) {
        moduleProductStocks.getFirst().setQuantity(0);
      }
    });
  }

  public void processMovementTypeEA(final List<StoreStorage> storeStorages, final List<MovementRecord> movements) {
    final var movementsEA = movements.stream().filter(MovementRecord::isEA).toList();

    movementsEA.forEach(movementRecordEA -> {
      boolean movementApplied = false;
      int i = 0;

      while (!movementApplied && i < storeStorages.size()) {
        final var storeStorage = storeStorages.get(i);
        if (storeStorage.getRemainSpace() >= movementRecordEA.quantity()) {
          if (storeStorage.containProductInStock(movementRecordEA.productId())) {
            storeStorage.getStoreStorageStockByProductId(movementRecordEA.productId())
                .ifPresent(storeStorageStock -> storeStorageStock.setQuantity(storeStorageStock.getQuantity() + movementRecordEA.quantity()));
          } else {
            storeStorage.getStoreStorageStocks().add(StoreStorageStock.builder()
                .product(Product.builder().id(movementRecordEA.productId()).build())
                .quantity(movementRecordEA.quantity())
                .build());
          }
          movementApplied = true;
        } else {
          i++;
        }
      }

      if (!movementApplied) {
        throw new StoreStorageNotHasSpaceException(movementRecordEA.productId(), movementRecordEA.quantity());
      }
    });
  }

  public void updateStoreStoragesStock(final List<StoreStorageStock> list) {
    //TODO actualizar cada stock del almacen en BBDD
  }

  public void updateModulesStock(final List<ModuleStock> moduleStocks) {
    //TODO actualizar cada stock del modulo en BBDD
  }
}
