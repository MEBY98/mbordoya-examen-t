package com.mercadona.mbordoya.web.main.driving.controllers.adapters;

import com.mercadona.mbordoya.web.main.application.ports.driving.AuthorizationUseCasePort;
import com.mercadona.mbordoya.web.main.driving.controllers.api.LoginApi;
import com.mercadona.mbordoya.web.main.driving.controllers.http_models.auth.LoginRequest;
import com.mercadona.mbordoya.web.main.driving.controllers.http_models.auth.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController implements LoginApi {
  private final AuthorizationUseCasePort authorizationUseCasePort;

  @Override
  public ResponseEntity<TokenResponse> getAccessToken(final LoginRequest loginRequest) {
    return ResponseEntity.ok(TokenResponse.builder()
        .username(loginRequest.getUsername())
        .accessToken(this.authorizationUseCasePort.login(loginRequest.getUsername(), loginRequest.getPassword()))
        .build());
  }
}
