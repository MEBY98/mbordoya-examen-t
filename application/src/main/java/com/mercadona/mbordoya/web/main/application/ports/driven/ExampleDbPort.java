package com.mercadona.mbordoya.web.main.application.ports.driven;

import com.mercadona.mbordoya.web.main.domain.ExampleChildDomain;
import com.mercadona.mbordoya.web.main.domain.ExampleDomain;

import java.util.Optional;

public interface ExampleDbPort {

  Long insertExample(ExampleDomain exampleDomain);

  void insertExampleChild(ExampleChildDomain exampleChildDomain);

  Optional<ExampleDomain> findById(Long id);
}
