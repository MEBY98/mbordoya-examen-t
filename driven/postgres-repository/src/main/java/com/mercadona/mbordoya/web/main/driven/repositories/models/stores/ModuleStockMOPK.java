package com.mercadona.mbordoya.web.main.driven.repositories.models.stores;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ModuleStockMOPK implements Serializable {
  private Long productId;
  private Long moduleId;
}
