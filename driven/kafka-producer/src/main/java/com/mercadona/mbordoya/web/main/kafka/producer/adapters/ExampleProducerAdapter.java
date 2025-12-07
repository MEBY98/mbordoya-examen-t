package com.mercadona.mbordoya.web.main.kafka.producer.adapters;

import com.mercadona.framework.cna.lib.kafka.producer.MercadonaKafkaTemplate;
import com.mercadona.mbordoya.web.main.kafka.producer.kafka_models.ExampleKafkaKey;
import com.mercadona.mbordoya.web.main.application.ports.driven.ExampleProducerPort;
import com.mercadona.mbordoya.web.main.domain.ExampleDomain;
import com.mercadona.mbordoya.web.main.kafka.producer.kafka_models.ExampleKafkaValue;
import com.mercadona.mbordoya.web.main.kafka.producer.mappers.ExampleKafkaMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutionException;

@Repository
public class ExampleProducerAdapter implements ExampleProducerPort {

  private final String topic;
  private final KafkaTemplate<ExampleKafkaKey, ExampleKafkaValue> kafkaTemplate;
  private final ExampleKafkaMapper exampleKafkaMapper;

  public ExampleProducerAdapter(@Value("${fwkcna.kafka.producer.topics.example}") final String topic,
                                final MercadonaKafkaTemplate<ExampleKafkaKey, ExampleKafkaValue> mercadonaKafkaTemplate,
                                final ExampleKafkaMapper exampleKafkaMapper) {
    this.topic = topic;
    this.kafkaTemplate = mercadonaKafkaTemplate;
    this.exampleKafkaMapper = exampleKafkaMapper;
  }

  @Override
  public void publishExample(final ExampleDomain exampleDomain) {
    try {
      this.kafkaTemplate.send(this.topic, this.exampleKafkaMapper.toKey(exampleDomain),
          this.exampleKafkaMapper.toValue(exampleDomain)).get();
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }
}
