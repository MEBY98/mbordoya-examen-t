package com.mercadona.mbordoya.web.main.application.services.example;

import com.mercadona.mbordoya.web.main.domain.example.ExampleDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ExampleDomainFiller {

  private final ExampleTypeService exampleTypeService;

  public void fillExampleType(final ExampleDomain exampleDomain) {
    final var exampleTypeDomain = this.exampleTypeService.getMap().get(exampleDomain.getExampleTypeDomain().getTypeId());
    exampleDomain.setExampleTypeDomain(exampleTypeDomain);
  }
}
