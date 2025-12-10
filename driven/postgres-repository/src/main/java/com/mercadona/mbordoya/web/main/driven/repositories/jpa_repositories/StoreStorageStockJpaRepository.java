package com.mercadona.mbordoya.web.main.driven.repositories.jpa_repositories;

import com.mercadona.mbordoya.web.main.driven.repositories.models.stores.ModuleStockMOPK;
import com.mercadona.mbordoya.web.main.driven.repositories.models.stores.StoreStorageStockMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface StoreStorageStockJpaRepository extends JpaRepository<StoreStorageStockMO, ModuleStockMOPK> {

  @Modifying
  @Query("""
      DELETE FROM StoreStorageStockMO ss
      WHERE ss.storeStorageId = :storeStorageId
      """)
  void deleteAllByStoreStorageId(Long storeStorageId);
}
