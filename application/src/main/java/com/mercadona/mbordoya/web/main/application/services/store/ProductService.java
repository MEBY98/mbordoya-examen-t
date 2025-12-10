package com.mercadona.mbordoya.web.main.application.services.store;

import com.mercadona.mbordoya.web.main.application.ports.driven.ProductDbPort;
import com.mercadona.mbordoya.web.main.domain.store.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

  private final ProductDbPort productDbPort;

  @Cacheable("products")
  public List<Product> getAll() {
    return this.productDbPort.getAll();
  }

  @Cacheable("productsMap")
  public Map<Long, Product> getMap() {
    final var products = this.getAll();
    return products.stream().collect(toMap(Product::getId, identity()));
  }

}
