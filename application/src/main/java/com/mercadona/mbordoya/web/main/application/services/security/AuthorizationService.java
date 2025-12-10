package com.mercadona.mbordoya.web.main.application.services.security;

import com.mercadona.mbordoya.web.main.application.utils.BCryptUtils;
import com.mercadona.mbordoya.web.main.application.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthorizationService {

  private final String pepper;
  private final UserService userService;

  public AuthorizationService(@Value("${spring.application.security.pepper}") final String pepper,
                              final UserService userService) {
    this.pepper = pepper;
    this.userService = userService;
  }

  public boolean userExists(final String username) {
    return this.userService.existsByUsername(username);
  }

  public boolean isPasswordValid(final String username, final String password) {
    final var user = this.userService.getByUsername(username);
    return BCryptUtils.verifyPassword(password + this.pepper, user.getPassword());
  }

  public String generateToken(final String username) {
    return JwtUtil.generateToken(username);
  }
}
