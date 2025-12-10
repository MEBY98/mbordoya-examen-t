package com.mercadona.mbordoya.web.main.kafka.producer.mappers;

import com.mercadona.mbordoya.web.main.domain.example.ExampleDomain;
import com.mercadona.mbordoya.web.main.kafka.producer.kafka_models.ExampleKafkaKey;
import com.mercadona.mbordoya.web.main.kafka.producer.kafka_models.ExampleKafkaValue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExampleKafkaMapper {

  ExampleKafkaKey toKey(ExampleDomain exampleDomain);

  @Mapping(target = "typeId", source = "exampleTypeDomain.typeId")
  ExampleKafkaValue toValue(ExampleDomain exampleDomain);
}
