package com.mercadona.mbordoya.web.main.driving.controllers.http_models.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenResponse {

  private String username;
  private String accessToken;
  private final String tokenType = "Bearer";

}
