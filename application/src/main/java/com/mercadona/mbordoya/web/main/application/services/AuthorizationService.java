package com.mercadona.mbordoya.web.main.application.services;

import com.mercadona.mbordoya.web.main.application.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorizationService {

  private final UserService userService;

  public boolean userExists(final String username) {
    return true;
  }

  public boolean isPasswordValid(final String username, final String password) {
    return true;
  }

  public String generateToken(final String username) {
    return JwtUtil.generateToken(username);
  }
}
