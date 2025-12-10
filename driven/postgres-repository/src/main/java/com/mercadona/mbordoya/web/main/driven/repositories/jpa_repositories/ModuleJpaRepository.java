package com.mercadona.mbordoya.web.main.driven.repositories.jpa_repositories;

import com.mercadona.mbordoya.web.main.driven.repositories.models.stores.ModuleMO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleJpaRepository extends JpaRepository<ModuleMO, Long> {

  List<ModuleMO> findAllByStoreId(Long storeId);
}
