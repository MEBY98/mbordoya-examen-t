package com.mercadona.mbordoya.web.main.application.ports.driven;

import com.mercadona.mbordoya.web.main.domain.ExampleDomain;

public interface ExampleProducerPort {

  void publishExample(ExampleDomain exampleDomain);
}
