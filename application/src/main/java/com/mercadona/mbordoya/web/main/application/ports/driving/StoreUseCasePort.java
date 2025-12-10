package com.mercadona.mbordoya.web.main.application.ports.driving;

import com.mercadona.mbordoya.web.main.domain.store.MovementRecord;
import com.mercadona.mbordoya.web.main.domain.store.Store;
import com.mercadona.mbordoya.web.main.domain.store.StoreQuery;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StoreUseCasePort {

  Long createStore(Store storeDomain);

  Store getStoreById(Long id);

  Page<Store> getStores(StoreQuery query);

  Store updateStore(Store storeUpdated);

  Long deleteStore(Long id);

  Store getStore(Long id);

  String getProductsCsv();

  void processMovements(Long storeId, List<MovementRecord> movements);
}
