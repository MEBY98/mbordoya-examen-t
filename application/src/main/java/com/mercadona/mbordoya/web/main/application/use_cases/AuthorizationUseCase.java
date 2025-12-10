package com.mercadona.mbordoya.web.main.application.use_cases;

import com.mercadona.mbordoya.web.main.application.exceptions.InvalidPasswordException;
import com.mercadona.mbordoya.web.main.application.exceptions.UserNotExistsException;
import com.mercadona.mbordoya.web.main.application.ports.driving.AuthorizationUseCasePort;
import com.mercadona.mbordoya.web.main.application.services.security.AuthorizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorizationUseCase implements AuthorizationUseCasePort {

  private final AuthorizationService authorizationService;

  @Override
  public String login(final String username, final String password) {
    if (this.authorizationService.userExists(username)) {
      if (this.authorizationService.isPasswordValid(username, password)) {
        return this.authorizationService.generateToken(username);
      } else {
        throw new InvalidPasswordException();
      }
    } else {
      throw new UserNotExistsException();
    }
  }
}
