package com.mercadona.mbordoya.web.main.driven.repositories.mappers;

import com.mercadona.mbordoya.web.main.domain.example.ExampleChildDomain;
import com.mercadona.mbordoya.web.main.domain.example.ExampleDomain;
import com.mercadona.mbordoya.web.main.driven.repositories.models.ExampleChildMO;
import com.mercadona.mbordoya.web.main.driven.repositories.models.ExampleMO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExampleDbMapper {

  @Mapping(target = "typeId", source = "exampleTypeDomain.typeId")
  @Mapping(target = "exampleTypeMO", ignore = true)
  ExampleMO toExampleMO(ExampleDomain exampleDomain);

  @Mapping(target = "exampleId", source = "exampleDomain.id")
  @Mapping(target = "exampleMO", ignore = true)
  ExampleChildMO toExampleChildMO(ExampleChildDomain exampleChildDomain);

  @Mapping(target = "exampleTypeDomain.typeId", source = "typeId")
  ExampleDomain toExampleDomain(ExampleMO exampleMO);
}
