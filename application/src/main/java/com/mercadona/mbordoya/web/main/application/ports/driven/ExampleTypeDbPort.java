package com.mercadona.mbordoya.web.main.application.ports.driven;

import com.mercadona.mbordoya.web.main.domain.ExampleTypeDomain;

import java.util.List;

public interface ExampleTypeDbPort {

  List<ExampleTypeDomain> getAll();
}
