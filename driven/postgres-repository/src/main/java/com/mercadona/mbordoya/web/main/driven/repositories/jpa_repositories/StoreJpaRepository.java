package com.mercadona.mbordoya.web.main.driven.repositories.jpa_repositories;

import com.mercadona.mbordoya.web.main.driven.repositories.models.stores.StoreMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface StoreJpaRepository extends JpaRepository<StoreMO, Long>, JpaSpecificationExecutor<StoreMO> {

  @Modifying
  @Query("""
      UPDATE StoreMO s
      SET s.name = :name, s.address = :address
      WHERE s.id = :id
      """)
  void updateStore(Long id, String name, String address);
}
