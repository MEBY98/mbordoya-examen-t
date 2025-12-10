package com.mercadona.mbordoya.web.main.driven.repositories.adapters;

import com.mercadona.mbordoya.web.main.application.ports.driven.SpecializationDbPort;
import com.mercadona.mbordoya.web.main.domain.Specialization;
import com.mercadona.mbordoya.web.main.driven.repositories.jpa_repositories.SpecializationJpaRepository;
import com.mercadona.mbordoya.web.main.driven.repositories.mappers.SpecializationDBMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class SpecializationDbAdapter implements SpecializationDbPort {

  private final SpecializationJpaRepository specializationJpaRepository;
  private final SpecializationDBMapper specializationDBMapper;

  @Override
  public List<Specialization> getAll() {
    return this.specializationJpaRepository.findAll().stream().map(this.specializationDBMapper::toSpecialization).toList();
  }
}
