package com.mercadona.mbordoya.web.main.driving.controllers.api;


import com.mercadona.mbordoya.web.main.driving.controllers.http_models.auth.LoginRequest;
import com.mercadona.mbordoya.web.main.driving.controllers.http_models.auth.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@Tag(name = "Auth API", description = "Login Api")
public interface LoginApi {

  @Operation(summary = "Login user")
  @PostMapping("/token")
  ResponseEntity<TokenResponse> getAccessToken(@RequestBody LoginRequest loginRequest);

}
