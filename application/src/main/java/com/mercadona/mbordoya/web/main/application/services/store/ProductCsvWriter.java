package com.mercadona.mbordoya.web.main.application.services.store;

import com.mercadona.mbordoya.web.main.application.exceptions.MessageService;
import com.mercadona.mbordoya.web.main.application.utils.CsvWriter;
import com.mercadona.mbordoya.web.main.domain.store.ProductCsvHeaderEnum;
import com.mercadona.mbordoya.web.main.domain.store.ProductCsvRecord;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class ProductCsvWriter extends CsvWriter<ProductCsvRecord> {
  private final MessageService messageService;

  @PostConstruct
  private void init() {
    headers = Arrays.stream(ProductCsvHeaderEnum.values())
        .map(ProductCsvHeaderEnum::getCode)
        .map(messageService::getMessage).toList();
  }
}
