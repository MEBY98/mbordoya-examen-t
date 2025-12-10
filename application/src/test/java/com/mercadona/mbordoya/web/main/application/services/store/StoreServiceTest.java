package com.mercadona.mbordoya.web.main.application.services.store;

import com.mercadona.mbordoya.web.main.application.exceptions.StoreNotFoundException;
import com.mercadona.mbordoya.web.main.application.ports.driven.StoreDbPort;
import com.mercadona.mbordoya.web.main.domain.store.Store;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StoreServiceTest {

  @InjectMocks
  private StoreService storeService;

  @Mock
  private StoreDbPort storeDbPort;

  @Test
  void whenStoreEmpty_whenGetById_thenThrowException() {
    when(this.storeDbPort.findById(1L)).thenReturn(Optional.empty());

    final var storeNotFoundException = assertThrows(StoreNotFoundException.class, () -> this.storeService.getById(1L));

    verify(this.storeDbPort).findById(1L);

    assertEquals(404, storeNotFoundException.getHttpStatus());
    assertEquals("STORE_NOT_FOUND_ERROR_CODE", storeNotFoundException.getErrorCode());
    assertEquals("STORE_NOT_FOUND_ERROR_CODE_DETAIL", storeNotFoundException.getErrorDetailCode());
    assertEquals(1L, storeNotFoundException.getArgs()[0]);
  }

  @Test
  void whenStoreFounded_whenGetById_thenReturnStore() {
    final Store storeDB = Store.builder().build();
    when(this.storeDbPort.findById(1L)).thenReturn(Optional.of(storeDB));

    final var store = this.storeService.getById(1L);

    verify(this.storeDbPort).findById(1L);
    assertEquals(storeDB, store);
  }
}