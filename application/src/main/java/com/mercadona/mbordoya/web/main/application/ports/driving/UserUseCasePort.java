package com.mercadona.mbordoya.web.main.application.ports.driving;

import com.mercadona.mbordoya.web.main.domain.UserDomain;

public interface UserUseCasePort {

  String createUser(UserDomain userDomain);
}
