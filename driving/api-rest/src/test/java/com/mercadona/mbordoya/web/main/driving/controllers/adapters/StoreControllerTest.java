package com.mercadona.mbordoya.web.main.driving.controllers.adapters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadona.framework.cna.lib.error.handler.handlers.DefaultExceptionHandler;
import com.mercadona.mbordoya.web.main.application.exceptions.MessageService;
import com.mercadona.mbordoya.web.main.application.ports.driving.StoreUseCasePort;
import com.mercadona.mbordoya.web.main.domain.store.Store;
import com.mercadona.mbordoya.web.main.driving.controllers.error.ExampleExceptionController;
import com.mercadona.mbordoya.web.main.driving.controllers.http_models.store.StoreCreateRequest;
import com.mercadona.mbordoya.web.main.driving.controllers.http_models.store.StoreResponse;
import com.mercadona.mbordoya.web.main.driving.controllers.mappers.StoreControllerMapper;
import lombok.SneakyThrows;
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
    StoreController.class,
    ExampleExceptionController.class,
    WebSecurityConfigurer.class,
    MessageService.class})
class StoreControllerTest {

  private static final String STORE_ID_PATTERN = "$.id";
  private static final String STORE_NAME_PATTERN = "$.name";
  private static final String STORE_ADDRESS_PATTERN = "$.address";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private StoreUseCasePort storeUseCasePort;

  @MockBean
  private StoreControllerMapper storeControllerMapper;

  @SneakyThrows
  @Test
  void createStore() {
    final Store store = Store.builder()
        .name("NAME")
        .address("ADDRESS")
        .build();

    final var storeId = 1L;
    when(storeUseCasePort.createStore(store)).thenReturn(storeId);

    final StoreCreateRequest storeCreateRequest = StoreCreateRequest.builder()
        .name("NAME")
        .address("ADDRESS")
        .build();
    mockMvc.perform(post("/stores")
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.objectMapper.writeValueAsString(storeCreateRequest)))
        .andExpect(status().isOk());
  }

  @SneakyThrows
  @Test
  void getStoreById() {
    final Long storeId = 1L;
    final var storeName = "NAME";
    final var storeAddress = "ADDRESS";
    final Store store = Store.builder()
        .id(storeId)
        .name(storeName)
        .address(storeAddress)
        .build();

    when(storeUseCasePort.getStoreById(storeId)).thenReturn(store);
    final StoreResponse storeResponse = StoreResponse.builder()
        .id(storeId)
        .name(storeName)
        .address(storeAddress)
        .build();
    when(storeControllerMapper.toStoreResponse(store)).thenReturn(storeResponse);

    mockMvc.perform(get("/stores/{id}", storeId)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath(STORE_ID_PATTERN, is(storeId.intValue())))
        .andExpect(jsonPath(STORE_NAME_PATTERN, is(storeName)))
        .andExpect(jsonPath(STORE_ADDRESS_PATTERN, is(storeAddress)));
  }
}