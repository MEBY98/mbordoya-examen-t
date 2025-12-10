package com.mercadona.mbordoya.web.main.driving.controllers.adapters;

import com.mercadona.framework.cna.commons.rest.api.model.Pagination;
import com.mercadona.mbordoya.web.main.application.ports.driving.StoreUseCasePort;
import com.mercadona.mbordoya.web.main.domain.Store;
import com.mercadona.mbordoya.web.main.domain.StoreQuery;
import com.mercadona.mbordoya.web.main.driving.controllers.api.StoreApi;
import com.mercadona.mbordoya.web.main.driving.controllers.http_models.store.*;
import com.mercadona.mbordoya.web.main.driving.controllers.http_models.store.summary.StoreSummaryResponse;
import com.mercadona.mbordoya.web.main.driving.controllers.http_models.store.summary_alert.StoreAlertSummaryResponse;
import com.mercadona.mbordoya.web.main.driving.controllers.mappers.StoreControllerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoreController implements StoreApi {

  private final StoreUseCasePort storeUseCasePort;
  private final StoreControllerMapper storeControllerMapper;

  @Override
  public ResponseEntity<Long> createStore(final StoreCreateRequest storeRequest) {
    return ResponseEntity.ok(this.storeUseCasePort.createStore(this.storeControllerMapper.toStoreDomain(storeRequest)));
  }

  @Override
  public ResponseEntity<StoreResponse> getStoreById(final Long id) {
    return ResponseEntity.ok(this.storeControllerMapper.toStoreResponse(this.storeUseCasePort.getStoreById(id)));
  }

  @Override
  public ResponseEntity<StorePaginatedResponse> getStores(final int page, final int pageSize, final String sort,
                                                          final String name) {
    final var query = StoreQuery.builder()
        .page(page)
        .pageSize(pageSize)
        .sort(sort)
        .name(name)
        .build();
    final Page<Store> stores = this.storeUseCasePort.getStores(query);
    return ResponseEntity.ok(StorePaginatedResponse.builder()
            .data(stores.map(this.storeControllerMapper::toStoreResponse).getContent())
            .pagination(Pagination.builder()
                .retrievedResults(stores.getContent().size())
                .totalResults(stores.getTotalElements())
                .build())
        .build());
  }

  @Override
  public ResponseEntity<StoreUpdatedResponse> updateStore(final Long id, final StoreUpdateRequest storeRequest) {
    final Store storeUpdated = this.storeControllerMapper.toStoreDomain(id, storeRequest);
    return ResponseEntity.ok(this.storeControllerMapper.toStoreUpdatedResponse(this.storeUseCasePort.updateStore(storeUpdated)));
  }

  @Override
  public ResponseEntity<Long> deleteStore(final Long id) {
    return ResponseEntity.ok(this.storeUseCasePort.deleteStore(id));
  }

  @Override
  public ResponseEntity<StoreSummaryResponse> getSummary(final Long id) {
    final var store = this.storeUseCasePort.getStore(id);
    return ResponseEntity.ok(new StoreSummaryResponse(store));
  }

  @Override
  public ResponseEntity<StoreAlertSummaryResponse> getSummaryAlerts(final Long id) {
    return null;
  }
}
