package com.mercadona.mbordoya.web.main.driving.controllers.mappers;

import com.mercadona.mbordoya.web.main.domain.UserDomain;
import com.mercadona.mbordoya.web.main.driving.controllers.http_models.UserCreateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserControllerMapper {

  UserDomain toUserDomain(UserCreateRequest userRequest);
}
