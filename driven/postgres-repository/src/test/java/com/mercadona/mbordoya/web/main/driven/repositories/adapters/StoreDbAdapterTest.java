package com.mercadona.mbordoya.web.main.driven.repositories.adapters;

import com.mercadona.mbordoya.web.main.driven.repositories.jpa_repositories.StoreJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StoreDbAdapterTest {

  @InjectMocks
  private StoreDbAdapter storeDbAdapter;

  @Mock
  private StoreJpaRepository storeJpaRepository;

  @Test
  void deleteById() {
    this.storeDbAdapter.deleteById(1L);
    verify(this.storeJpaRepository).deleteById(1L);
    verify(this.storeJpaRepository).flush();
  }
}