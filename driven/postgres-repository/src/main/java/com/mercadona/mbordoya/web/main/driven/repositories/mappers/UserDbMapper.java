package com.mercadona.mbordoya.web.main.driven.repositories.mappers;

import com.mercadona.mbordoya.web.main.domain.UserDomain;
import com.mercadona.mbordoya.web.main.driven.repositories.models.UserMO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDbMapper {

  UserDomain toUserDomain(UserMO userMO);

  UserMO toUserMO(UserDomain userDomain);
}
