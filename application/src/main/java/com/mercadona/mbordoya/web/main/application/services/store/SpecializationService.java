package com.mercadona.mbordoya.web.main.application.services.store;

import com.mercadona.mbordoya.web.main.application.ports.driven.SpecializationDbPort;
import com.mercadona.mbordoya.web.main.domain.store.Specialization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class SpecializationService {

  private final SpecializationDbPort specializationDbPort;

  @Cacheable("specializations")
  public List<Specialization> getAll() {
    return this.specializationDbPort.getAll();
  }

  @Cacheable("specializationsMap")
  public Map<Long, Specialization> getMap() {
    final var specializations = this.getAll();
    return specializations.stream().collect(toMap(Specialization::getId, identity()));
  }

}
