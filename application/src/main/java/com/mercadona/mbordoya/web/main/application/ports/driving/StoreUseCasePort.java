package com.mercadona.mbordoya.web.main.application.ports.driving;

import com.mercadona.mbordoya.web.main.domain.Store;
import com.mercadona.mbordoya.web.main.domain.StoreQuery;
import org.springframework.data.domain.Page;

public interface StoreUseCasePort {

  Long createStore(Store storeDomain);

  Store getStoreById(Long id);

  Page<Store> getStores(StoreQuery query);

  Store updateStore(Store storeUpdated);

  Long deleteStore(Long id);

  Store getStore(Long id);
}
