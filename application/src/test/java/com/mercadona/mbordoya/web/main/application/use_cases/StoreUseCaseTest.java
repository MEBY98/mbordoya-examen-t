package com.mercadona.mbordoya.web.main.application.use_cases;

import com.mercadona.mbordoya.web.main.application.services.store.StoreService;
import com.mercadona.mbordoya.web.main.domain.store.Store;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StoreUseCaseTest {

  @InjectMocks
  private StoreUseCase storeUseCase;

  @Mock
  private StoreService storeService;

  @Test
  void updateStore() {
    final String newName = "new name";
    final String newAddress = "new address";
    final Store store = Store.builder().id(1L).name(newName).address(newAddress).build();
    final Store storeDB = Store.builder().id(1L).build();

    when(this.storeService.getById(store.getId())).thenReturn(storeDB);

    final var result = this.storeUseCase.updateStore(store);

    final ArgumentCaptor<Store> storeAc = ArgumentCaptor.forClass(Store.class);
    verify(this.storeService).updateStore(storeAc.capture());
    assertEquals(newName, storeAc.getValue().getName());
    assertEquals(newAddress, storeAc.getValue().getAddress());
    verify(this.storeService).getById(1L);
    assertEquals(storeDB, result);

  }
}