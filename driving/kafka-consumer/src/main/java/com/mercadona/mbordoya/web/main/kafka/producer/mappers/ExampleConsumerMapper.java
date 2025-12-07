package com.mercadona.mbordoya.web.main.kafka.producer.mappers;

import com.mercadona.mbordoya.web.main.domain.ExampleDomain;
import com.mercadona.mbordoya.web.main.kafka.producer.kafka_models.ExampleKafkaValue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExampleConsumerMapper {

  @Mapping(target = "exampleTypeDomain.typeId", source = "typeId")
  @Mapping(target = "exampleChildMOs", ignore = true)
  ExampleDomain toExampleDomain(ExampleKafkaValue value);
}
