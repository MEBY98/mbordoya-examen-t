package com.mercadona.mbordoya.web.main.application.events.example;

import com.mercadona.mbordoya.web.main.application.services.ExampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExampleEventListener {

  private final ExampleService exampleService;

  @Async
  @EventListener
  public void handleCreateExampleChildEvent(CreateExampleChildrenEvent event) {
    this.exampleService.createExampleChildren(event.exampleDomainId(), event.children());
  }
}
