package com.mercadona.mbordoya.web.main.application.ports.driving;

import com.mercadona.mbordoya.web.main.domain.example.ExampleDomain;

import java.util.LinkedList;

public interface ExampleUseCasePort {

  Long createExample(ExampleDomain exampleRequest);

  ExampleDomain getExampleById(Long exampleDomainId);

  void createExampleChildrenFromCsv(Long id, LinkedList<String> childrenDescriptions);

  void saveCsvInBucket(String s, byte[] bytes);
}
