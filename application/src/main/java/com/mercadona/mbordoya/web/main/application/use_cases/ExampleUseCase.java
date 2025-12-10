package com.mercadona.mbordoya.web.main.application.use_cases;

import com.mercadona.mbordoya.web.main.application.events.example.CreateExampleChildrenEvent;
import com.mercadona.mbordoya.web.main.application.ports.driving.ExampleUseCasePort;
import com.mercadona.mbordoya.web.main.application.services.example.ExampleDomainFiller;
import com.mercadona.mbordoya.web.main.application.services.example.ExampleService;
import com.mercadona.mbordoya.web.main.domain.example.ExampleChildDomain;
import com.mercadona.mbordoya.web.main.domain.example.ExampleDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
@RequiredArgsConstructor
public class ExampleUseCase implements ExampleUseCasePort {

  private final ExampleService exampleService;
  private final ExampleDomainFiller exampleDomainFiller;
  private final ApplicationEventPublisher applicationEventPublisher;

  @Override
  public Long createExample(final ExampleDomain exampleDomain) {
    final var exampleDomainId = this.exampleService.createExample(exampleDomain);
    this.applicationEventPublisher.publishEvent(new CreateExampleChildrenEvent(exampleDomainId,
        exampleDomain.getExampleChildMOs()));
    exampleDomain.setId(exampleDomainId);
    this.exampleService.publishExample(exampleDomain);
    return exampleDomainId;
  }

  @Override
  public ExampleDomain getExampleById(final Long exampleDomainId) {
    final var exampleDomain = this.exampleService.getById(exampleDomainId);
    exampleDomainFiller.fillExampleType(exampleDomain);
    return exampleDomain;
  }

  @Override
  public void createExampleChildrenFromCsv(final Long id, final LinkedList<String> childrenDescriptions) {
    final var children =
        childrenDescriptions.stream().map(description -> ExampleChildDomain.builder().childDescription(description).build()).toList();
    this.applicationEventPublisher.publishEvent(new CreateExampleChildrenEvent(id, children));
  }

  @Override
  public void saveCsvInBucket(final String fileName, final byte[] bytes) {
    this.exampleService.saveCsvInBucket(fileName, bytes);
  }
}
