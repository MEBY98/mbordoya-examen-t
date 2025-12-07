package com.mercadona.mbordoya.web.main.kafka.producer.error;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;

@Slf4j
public class ExampleConsumerErrorHandler implements CommonErrorHandler {

  @Override
  public boolean handleOne(Exception thrownException, ConsumerRecord<?, ?> record,
                           Consumer<?, ?> consumer, MessageListenerContainer container) {
    log.error("ERROR HANDLE [{}][{}] - {}",
        (record.key() == null ? "N/A" : record.key().toString()),
        (record.value() == null ? "N/A" : record.value().toString()),
        thrownException.getMessage());
    return true;
  }
}
