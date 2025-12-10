package com.mercadona.mbordoya.web.main.driven.repositories.adapters;

import com.mercadona.mbordoya.web.main.application.ports.driven.ExampleTypeDbPort;
import com.mercadona.mbordoya.web.main.domain.example.ExampleTypeDomain;
import com.mercadona.mbordoya.web.main.driven.repositories.jpa_repositories.ExampleTypeJpaRepository;
import com.mercadona.mbordoya.web.main.driven.repositories.mappers.ExampleTypeDbMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ExampleTypeDbAdapter implements ExampleTypeDbPort {

  private final ExampleTypeJpaRepository exampleTypeJpaRepository;
  private final ExampleTypeDbMapper exampleTypeDbMapper;

  @Override
  @Transactional(readOnly = true)
  public List<ExampleTypeDomain> getAll() {
    return this.exampleTypeJpaRepository.findAll().stream().map(this.exampleTypeDbMapper::toExampleTypeDomain).toList();
  }
}
