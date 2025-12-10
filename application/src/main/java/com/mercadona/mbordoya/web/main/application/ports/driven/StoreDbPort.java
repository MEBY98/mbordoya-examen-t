package com.mercadona.mbordoya.web.main.application.ports.driven;

import com.mercadona.mbordoya.web.main.domain.ModuleDomain;
import com.mercadona.mbordoya.web.main.domain.Store;
import com.mercadona.mbordoya.web.main.domain.StoreQuery;
import com.mercadona.mbordoya.web.main.domain.StoreStorage;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface StoreDbPort {

  Long insert(Store storeDomain);

  void insertModule(ModuleDomain moduleDomain);

  void insertStoreStorage(StoreStorage storeStorage);

  Optional<Store> findById(Long id);

  Page<Store> getAll(StoreQuery query);

  void updateStore(Store storeDB);

  boolean existsById(Long storeId);

  void deleteStoreStorageStock(Long storeStorageId);

  void deleteStoreStorageById(Long storeStorageId);

  List<ModuleDomain> getModulesByStoreId(Long storeId);

  void deleteModuleStock(Long moduleDomainId);

  void deleteModuleById(Long moduleDomainId);

  List<StoreStorage> getStoreStoragesByStoreId(Long storeId);

  void deleteById(Long storeId);

  List<ModuleDomain> getModulesWithStockByStoreId(Long id);

  List<StoreStorage> getStoreStorageWithStockByStoreId(Long id);
}
