package com.mercadona.mbordoya.web.main.driving.controllers.http_models;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ExampleCreateRequest {
  private String name;
  private Long typeId;
  private List<ExampleChildCreateRequest> exampleChildCreateRequestList;
}
