package com.mercadona.mbordoya.web.main.application.services;

import com.mercadona.mbordoya.web.main.application.exceptions.UserNotExistsException;
import com.mercadona.mbordoya.web.main.application.ports.driven.UserDbPort;
import com.mercadona.mbordoya.web.main.application.utils.BCryptUtils;
import com.mercadona.mbordoya.web.main.domain.UserDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

  private final String pepper;
  private final UserDbPort userDbPort;

  public UserService(@Value("${spring.application.security.pepper}") final String pepper,
                     final UserDbPort userDbPort) {
    this.pepper = pepper;
    this.userDbPort = userDbPort;
  }

  public boolean existsByUsername(final String username) {
    return this.userDbPort.existsByUsername(username);
  }

  public UserDomain getByUsername(final String username) {
    return this.userDbPort.getByUsername(username).orElseThrow(UserNotExistsException::new);
  }

  public String insertUser(final UserDomain userDomain) {
    userDomain.setPassword(BCryptUtils.hashPassword(userDomain.getPassword() + this.pepper));
    return this.userDbPort.insertUser(userDomain);
  }
}
