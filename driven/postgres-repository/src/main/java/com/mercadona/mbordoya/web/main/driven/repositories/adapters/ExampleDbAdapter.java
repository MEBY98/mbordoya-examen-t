package com.mercadona.mbordoya.web.main.driven.repositories.adapters;

import com.mercadona.mbordoya.web.main.application.ports.driven.ExampleDbPort;
import com.mercadona.mbordoya.web.main.domain.example.ExampleChildDomain;
import com.mercadona.mbordoya.web.main.domain.example.ExampleDomain;
import com.mercadona.mbordoya.web.main.driven.repositories.jpa_repositories.ExampleChildJpaRepository;
import com.mercadona.mbordoya.web.main.driven.repositories.jpa_repositories.ExampleJpaRepository;
import com.mercadona.mbordoya.web.main.driven.repositories.mappers.ExampleDbMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ExampleDbAdapter implements ExampleDbPort {

  private final ExampleJpaRepository exampleMOJpaRepository;
  private final ExampleChildJpaRepository exampleChildMOJpaRepository;
  private final ExampleDbMapper exampleDbMapper;

  @Override
  @Transactional
  public Long insertExample(final ExampleDomain exampleDomain) {
    return this.exampleMOJpaRepository.save(this.exampleDbMapper.toExampleMO(exampleDomain)).getId();
  }

  @Override
  @Transactional
  public void insertExampleChild(final ExampleChildDomain exampleChildDomain) {
    this.exampleChildMOJpaRepository.save(this.exampleDbMapper.toExampleChildMO(exampleChildDomain));
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<ExampleDomain> findById(final Long id) {
    return this.exampleMOJpaRepository.findById(id)
        .map(this.exampleDbMapper::toExampleDomain);
  }
}
