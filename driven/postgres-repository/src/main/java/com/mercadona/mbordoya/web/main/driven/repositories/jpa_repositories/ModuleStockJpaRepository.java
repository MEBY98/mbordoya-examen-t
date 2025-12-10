package com.mercadona.mbordoya.web.main.driven.repositories.jpa_repositories;

import com.mercadona.mbordoya.web.main.driven.repositories.models.stores.ModuleStockMO;
import com.mercadona.mbordoya.web.main.driven.repositories.models.stores.ModuleStockMOPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ModuleStockJpaRepository extends JpaRepository<ModuleStockMO, ModuleStockMOPK> {

  @Modifying
  @Query("""
      DELETE FROM ModuleStockMO ms
      WHERE ms.moduleId = :moduleId
      """)
  void deleteAllByModuleId(Long moduleId);
}
