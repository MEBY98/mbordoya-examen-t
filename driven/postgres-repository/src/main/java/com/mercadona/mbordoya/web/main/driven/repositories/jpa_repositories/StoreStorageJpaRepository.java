package com.mercadona.mbordoya.web.main.driven.repositories.jpa_repositories;

import com.mercadona.mbordoya.web.main.driven.repositories.models.stores.StoreStorageMO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreStorageJpaRepository extends JpaRepository<StoreStorageMO, Long> {

  List<StoreStorageMO> findAllByStoreId(Long storeId);
}
