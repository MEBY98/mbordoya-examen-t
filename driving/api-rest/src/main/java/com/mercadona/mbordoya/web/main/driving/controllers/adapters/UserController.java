package com.mercadona.mbordoya.web.main.driving.controllers.adapters;

import com.mercadona.mbordoya.web.main.application.ports.driving.UserUseCasePort;
import com.mercadona.mbordoya.web.main.domain.UserDomain;
import com.mercadona.mbordoya.web.main.driving.controllers.api.UserApi;
import com.mercadona.mbordoya.web.main.driving.controllers.http_models.UserCreateRequest;
import com.mercadona.mbordoya.web.main.driving.controllers.mappers.UserControllerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController implements UserApi {

  private final UserUseCasePort userUseCasePort;
  private final UserControllerMapper userControllerMapper;

  @Override
  public ResponseEntity<String> createUser(final UserCreateRequest userRequest) {
    final var userDomain = this.userControllerMapper.toUserDomain(userRequest);
    return ResponseEntity.ok(this.userUseCasePort.createUser(userDomain));
  }
}
