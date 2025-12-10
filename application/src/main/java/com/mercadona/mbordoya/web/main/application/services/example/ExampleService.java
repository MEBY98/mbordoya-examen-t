package com.mercadona.mbordoya.web.main.application.services.example;

import com.mercadona.mbordoya.web.main.application.exceptions.ExampleNotFoundException;
import com.mercadona.mbordoya.web.main.application.ports.driven.ExampleBucketPort;
import com.mercadona.mbordoya.web.main.application.ports.driven.ExampleDbPort;
import com.mercadona.mbordoya.web.main.application.ports.driven.ExampleProducerPort;
import com.mercadona.mbordoya.web.main.domain.example.ExampleChildDomain;
import com.mercadona.mbordoya.web.main.domain.example.ExampleDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExampleService {

  private final ExampleDbPort exampleDbPort;
  private final ExampleBucketPort exampleBucketPort;
  private final ExampleProducerPort exampleProducerPort;

  public ExampleDomain getById(final Long id) {
    return this.exampleDbPort.findById(id).orElseThrow(() -> new ExampleNotFoundException(id));
  }

  public Long createExample(final ExampleDomain exampleDomain) {
    return this.exampleDbPort.insertExample(exampleDomain);
  }

  public void createExampleChildren(final Long exampleDomainId, final List<ExampleChildDomain> exampleChildDomains) {
    exampleChildDomains.forEach(exampleChildDomain -> {
      exampleChildDomain.setExampleDomain(ExampleDomain.builder().id(exampleDomainId).build());
      this.exampleDbPort.insertExampleChild(exampleChildDomain);
    });
  }

  public void saveCsvInBucket(final String fileName, final byte[] bytes) {
    this.exampleBucketPort.saveCsvInBucketA(fileName, bytes);
    this.exampleBucketPort.saveCsvInBucketB(fileName, bytes);
  }

  public void publishExample(final ExampleDomain exampleDomain) {
    this.exampleProducerPort.publishExample(exampleDomain);
  }
}
