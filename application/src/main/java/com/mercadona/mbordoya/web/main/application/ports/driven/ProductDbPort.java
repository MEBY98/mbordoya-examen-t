package com.mercadona.mbordoya.web.main.application.ports.driven;

import com.mercadona.mbordoya.web.main.domain.store.Product;

import java.util.List;

public interface ProductDbPort {

  List<Product> getAll();

}
