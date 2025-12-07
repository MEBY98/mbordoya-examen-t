package com.mercadona.mbordoya.web.main.driven.repositories.jpa_repositories;

import com.mercadona.mbordoya.web.main.driven.repositories.models.ExampleTypeMO;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExampleTypeJpaRepository extends JpaRepository<ExampleTypeMO, Long> {

  @EntityGraph(attributePaths = {"exampleTypeNameMOs"})
  List<ExampleTypeMO> findAll();
}
