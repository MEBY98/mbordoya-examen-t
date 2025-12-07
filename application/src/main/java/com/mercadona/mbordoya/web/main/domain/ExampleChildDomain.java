package com.mercadona.mbordoya.web.main.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExampleChildDomain {
  private Long id;
  private String childDescription;
  private ExampleDomain exampleDomain;
}
