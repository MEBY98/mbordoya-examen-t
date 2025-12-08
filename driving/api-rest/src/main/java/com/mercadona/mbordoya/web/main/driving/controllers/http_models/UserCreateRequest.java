package com.mercadona.mbordoya.web.main.driving.controllers.http_models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserCreateRequest {

  private String username;
  private String password;
}
