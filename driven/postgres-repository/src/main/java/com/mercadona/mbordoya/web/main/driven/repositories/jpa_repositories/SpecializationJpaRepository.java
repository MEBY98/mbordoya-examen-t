package com.mercadona.mbordoya.web.main.driven.repositories.jpa_repositories;

import com.mercadona.mbordoya.web.main.driven.repositories.models.stores.SpecializationMO;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecializationJpaRepository extends JpaRepository<SpecializationMO, Long> {

  @EntityGraph(attributePaths = {"specializationNameMOList"})
  List<SpecializationMO> findAll();
}
