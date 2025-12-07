package com.mercadona.mbordoya.web.main.kafka.producer.adapters;

import com.mercadona.framework.cna.lib.kafka.consumer.MercadonaKafkaConsumerListener;
import com.mercadona.mbordoya.web.main.application.ports.driving.ExampleConsumerUseCase;
import com.mercadona.mbordoya.web.main.kafka.producer.kafka_models.ExampleKafkaKey;
import com.mercadona.mbordoya.web.main.kafka.producer.kafka_models.ExampleKafkaValue;
import com.mercadona.mbordoya.web.main.kafka.producer.mappers.ExampleConsumerMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ConditionalOnProperty(
    prefix = "spring.application.feature-flags",
    name = "example-consumer-enabled",
    havingValue = "true"
)
public class ExampleConsumerAdapter extends MercadonaKafkaConsumerListener<ExampleKafkaKey, ExampleKafkaValue> {

  private final ExampleConsumerUseCase exampleConsumerUseCase;
  private final ExampleConsumerMapper exampleConsumerMapper;

  protected ExampleConsumerAdapter(@Value("${fwkcna.kafka.consumer.topics.example}") final String[] topics,
                                   @Value("${fwkcna.kafka.consumer.topics.example-group-id}") final String groupId,
                                   final ExampleConsumerUseCase exampleConsumerUseCase,
                                   final ExampleConsumerMapper exampleConsumerMapper) {
    super(topics, groupId);
    this.exampleConsumerUseCase = exampleConsumerUseCase;
    this.exampleConsumerMapper = exampleConsumerMapper;
  }

  @Override
  public void consume(ConsumerRecord<ExampleKafkaKey, ExampleKafkaValue> consumerRecord) {
    if (consumerRecord.value() == null) {
      log.info("Received tombstone message for key: {}", consumerRecord.key());
    } else {
      final var exampleDomain = this.exampleConsumerMapper.toExampleDomain(consumerRecord.value());
      this.exampleConsumerUseCase.processExampleMessage(exampleDomain);
      log.info("Consumed message with key: {} and value: {}", consumerRecord.key(), consumerRecord.value());
    }
  }
}
