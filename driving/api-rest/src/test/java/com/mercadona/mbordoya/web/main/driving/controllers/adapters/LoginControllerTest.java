package com.mercadona.mbordoya.web.main.driving.controllers.adapters;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadona.framework.cna.lib.error.handler.handlers.DefaultExceptionHandler;
import com.mercadona.mbordoya.web.main.application.exceptions.MessageService;
import com.mercadona.mbordoya.web.main.application.ports.driving.AuthorizationUseCasePort;
import com.mercadona.mbordoya.web.main.driving.controllers.error.ExampleExceptionController;
import com.mercadona.mbordoya.web.main.driving.controllers.http_models.auth.TokenResponse;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@WithMockUser
@Import(DefaultExceptionHandler.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = {
    LoginController.class,
    ExampleExceptionController.class,
    WebSecurityConfigurer.class,
    MessageService.class})
class LoginControllerTest {

  private static final String TOKEN_TYPE_PATTERN = "$.tokenType";
  private static final String USERNAME_PATTERN = "$.username";
  private static final String ACCESS_TOKEN_PATTERN = "$.accessToken";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private AuthorizationUseCasePort authorizationUseCasePort;

  @Test
  @SneakyThrows
  void getAccessToken() {
    final TokenResponse tokenResponse = TokenResponse.builder()
        .username("testuser")
        .accessToken("testtoken")
        .build();

    when(authorizationUseCasePort.login("testuser", "testpass")).thenReturn("testtoken");

    mockMvc.perform(post("/auth/token")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"username\":\"testuser\",\"password\":\"testpass\"}"))
        .andExpect(status().isOk())
        .andExpect(jsonPath(USERNAME_PATTERN, is(tokenResponse.getUsername())))
        .andExpect(jsonPath(ACCESS_TOKEN_PATTERN, is(tokenResponse.getAccessToken())))
        .andExpect(jsonPath(TOKEN_TYPE_PATTERN, is(tokenResponse.getTokenType())));
  }
}