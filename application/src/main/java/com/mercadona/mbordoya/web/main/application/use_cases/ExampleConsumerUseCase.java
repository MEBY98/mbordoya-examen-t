package com.mercadona.mbordoya.web.main.application.use_cases;

import com.mercadona.mbordoya.web.main.application.services.example.ExampleService;
import com.mercadona.mbordoya.web.main.domain.example.ExampleDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExampleConsumerUseCase implements com.mercadona.mbordoya.web.main.application.ports.driving.ExampleConsumerUseCase {

  private final ExampleService exampleService;

  @Override
  public void processExampleMessage(final ExampleDomain exampleDomain) {
    final var exampleDomainDB = this.exampleService.getById(exampleDomain.getId());
    log.info("Example Domain in DB: {}", exampleDomainDB);
  }
}
