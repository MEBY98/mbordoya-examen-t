package com.mercadona.mbordoya.web.main.driving.controllers.api;

import com.mercadona.mbordoya.web.main.driving.controllers.http_models.ExampleCreateRequest;
import com.mercadona.mbordoya.web.main.driving.controllers.http_models.UserCreateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Tag(name = "User API", description = "User Api")
public interface UserApi {

  @Operation(summary = "Create user", security = @SecurityRequirement(name = "adfs"))
  @PostMapping
  ResponseEntity<String> createUser(@RequestBody UserCreateRequest userRequest);

}
