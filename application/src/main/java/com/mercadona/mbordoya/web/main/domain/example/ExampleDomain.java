package com.mercadona.mbordoya.web.main.domain.example;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ExampleDomain {

  private Long id;
  private String name;
  private ExampleTypeDomain exampleTypeDomain;
  private List<ExampleChildDomain> exampleChildMOs;
}
