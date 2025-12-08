package com.mercadona.mbordoya.web.main.application.use_cases;

import com.mercadona.mbordoya.web.main.application.ports.driving.UserUseCasePort;
import com.mercadona.mbordoya.web.main.application.services.UserService;
import com.mercadona.mbordoya.web.main.application.utils.BCryptUtils;
import com.mercadona.mbordoya.web.main.domain.UserDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserUseCase implements UserUseCasePort {

  private final UserService userService;

  @Override
  public String createUser(final UserDomain userDomain) {
    return this.userService.insertUser(userDomain);
  }
}
