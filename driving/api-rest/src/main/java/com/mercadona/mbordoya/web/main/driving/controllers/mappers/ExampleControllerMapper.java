package com.mercadona.mbordoya.web.main.driving.controllers.mappers;

import com.mercadona.mbordoya.web.main.domain.example.ExampleChildDomain;
import com.mercadona.mbordoya.web.main.domain.example.ExampleDomain;
import com.mercadona.mbordoya.web.main.driving.controllers.http_models.ExampleChildCreateRequest;
import com.mercadona.mbordoya.web.main.driving.controllers.http_models.ExampleCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExampleControllerMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "exampleTypeDomain.typeId", source = "typeId")
  @Mapping(target = "exampleChildMOs", source = "exampleChildCreateRequestList")
  ExampleDomain toExample(ExampleCreateRequest exampleRequest);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "exampleDomain", ignore = true)
  ExampleChildDomain toExampleChildDomain(ExampleChildCreateRequest exampleChildCreateRequest);
}
