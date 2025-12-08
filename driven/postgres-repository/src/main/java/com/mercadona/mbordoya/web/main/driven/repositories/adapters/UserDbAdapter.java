package com.mercadona.mbordoya.web.main.driven.repositories.adapters;

import com.mercadona.mbordoya.web.main.application.ports.driven.UserDbPort;
import com.mercadona.mbordoya.web.main.domain.UserDomain;
import com.mercadona.mbordoya.web.main.driven.repositories.jpa_repositories.UserJpaRepository;
import com.mercadona.mbordoya.web.main.driven.repositories.mappers.UserDbMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
@Slf4j
public class UserDbAdapter implements UserDbPort {

  private final UserJpaRepository userJpaRepository;
  private final UserDbMapper userDbMapper;

  @Override
  @Transactional(readOnly = true)
  public boolean existsByUsername(final String username) {
    return this.userJpaRepository.existsById(username);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<UserDomain> getByUsername(final String username) {
    return this.userJpaRepository.findById(username).map(this.userDbMapper::toUserDomain);
  }

  @Override
  public String insertUser(final UserDomain userDomain) {
    return this.userJpaRepository.save(this.userDbMapper.toUserMO(userDomain)).getUsername();
  }
}
