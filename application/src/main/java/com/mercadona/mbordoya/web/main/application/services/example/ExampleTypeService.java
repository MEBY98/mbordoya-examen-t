package com.mercadona.mbordoya.web.main.application.services.example;

import com.mercadona.mbordoya.web.main.application.ports.driven.ExampleTypeDbPort;
import com.mercadona.mbordoya.web.main.domain.example.ExampleTypeDomain;
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
public class ExampleTypeService {

  private final ExampleTypeDbPort exampleTypeDbPort;

  @Cacheable("exampleTypes")
  public List<ExampleTypeDomain> getAll() {
    return this.exampleTypeDbPort.getAll();
  }

  @Cacheable("exampleTypesMap")
  public Map<Long, ExampleTypeDomain> getMap() {
    final var exampleTypeDomains = this.getAll();
    return exampleTypeDomains.stream().collect(toMap(ExampleTypeDomain::getTypeId, identity()));
  }
}
