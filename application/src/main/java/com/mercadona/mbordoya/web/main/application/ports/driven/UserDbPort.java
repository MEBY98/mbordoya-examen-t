package com.mercadona.mbordoya.web.main.application.ports.driven;

import com.mercadona.mbordoya.web.main.domain.UserDomain;

import java.util.Optional;

public interface UserDbPort {

  boolean existsByUsername(String username);

  Optional<UserDomain> getByUsername(String username);

  String insertUser(UserDomain userDomain);
}
