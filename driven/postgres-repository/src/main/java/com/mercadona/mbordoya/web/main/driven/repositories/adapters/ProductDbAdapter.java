package com.mercadona.mbordoya.web.main.driven.repositories.adapters;

import com.mercadona.mbordoya.web.main.application.ports.driven.ProductDbPort;
import com.mercadona.mbordoya.web.main.domain.store.Product;
import com.mercadona.mbordoya.web.main.driven.repositories.jpa_repositories.ProductJpaRepository;
import com.mercadona.mbordoya.web.main.driven.repositories.mappers.StoreDBMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
@Slf4j
public class ProductDbAdapter implements ProductDbPort {

  private final ProductJpaRepository productJpaRepository;
  private final StoreDBMapper storeDBMapper;

  @Override
  public List<Product> getAll() {
    return this.productJpaRepository.findAll().stream().map(this.storeDBMapper::toProduct).toList();
  }
}
