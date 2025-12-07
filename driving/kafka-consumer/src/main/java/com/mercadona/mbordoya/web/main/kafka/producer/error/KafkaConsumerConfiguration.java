package com.mercadona.mbordoya.web.main.kafka.producer.error;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.CommonErrorHandler;

@Configuration
public class KafkaConsumerConfiguration {

  @Bean("customKafkaErrorHandler")
  public CommonErrorHandler exampleConsumerErrorHandler() {
    return new ExampleConsumerErrorHandler();
  }
}
