package com.mercadona.mbordoya.web.main.driven.repositories.mappers;

import com.mercadona.mbordoya.web.main.domain.ExampleTypeDomain;
import com.mercadona.mbordoya.web.main.domain.ExampleTypeNameDomain;
import com.mercadona.mbordoya.web.main.driven.repositories.models.ExampleTypeMO;
import com.mercadona.mbordoya.web.main.driven.repositories.models.ExampleTypeNameMO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExampleTypeDbMapper {

  @Mapping(target = "exampleTypeNameDomains", source = "exampleTypeNameMOs")
  ExampleTypeDomain toExampleTypeDomain(ExampleTypeMO exampleTypeMO);

  ExampleTypeNameDomain toExampleTypeNameDomain(ExampleTypeNameMO exampleTypeNameMO);
}
