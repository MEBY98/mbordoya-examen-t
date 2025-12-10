package com.mercadona.mbordoya.web.main.driving.controllers.adapters;

import com.mercadona.framework.cna.commons.rest.api.model.Pagination;
import com.mercadona.mbordoya.web.main.application.ports.driving.StoreUseCasePort;
import com.mercadona.mbordoya.web.main.domain.store.MovementRecord;
import com.mercadona.mbordoya.web.main.domain.store.MovementTypeEnum;
import com.mercadona.mbordoya.web.main.domain.store.Store;
import com.mercadona.mbordoya.web.main.domain.store.StoreQuery;
import com.mercadona.mbordoya.web.main.driving.controllers.api.StoreApi;
import com.mercadona.mbordoya.web.main.driving.controllers.http_models.store.*;
import com.mercadona.mbordoya.web.main.driving.controllers.http_models.store.summary.StoreSummaryResponse;
import com.mercadona.mbordoya.web.main.driving.controllers.http_models.store.summary_alert.StoreAlertSummaryResponse;
import com.mercadona.mbordoya.web.main.driving.controllers.mappers.StoreControllerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

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
  public ResponseEntity<String> getProductsCsv() {
    return ResponseEntity.ok(this.storeUseCasePort.getProductsCsv());
  }

  @Override
  public ResponseEntity<StoreSummaryResponse> getSummary(final Long id) {
    final var store = this.storeUseCasePort.getStore(id);
    return ResponseEntity.ok(new StoreSummaryResponse(store));
  }

  @Override
  public ResponseEntity<StoreAlertSummaryResponse> getSummaryAlerts(final Long id) {
    final var store = this.storeUseCasePort.getStore(id);
    return ResponseEntity.ok(new StoreAlertSummaryResponse(store));
  }

  @Override
  public ResponseEntity<Long> uploadMovementCsv(final Long id, final MultipartFile file) throws IOException {
    final byte[] bytes = file.getBytes();
    final String csvString = new String(bytes);
    final List<String> rows = Arrays.asList(csvString.split("\n"));

    final List<MovementRecord> movements = new LinkedList<>();
    IntStream.range(0, rows.size()).forEach(i -> {
      final String row = rows.get(i);
      if (i > 0) {
        final String[] rowSplit = row.split(";");
        final var productId = Long.valueOf(rowSplit[0]);
        final var movementType = MovementTypeEnum.valueOf(rowSplit[1]);
        final var quantity = Integer.valueOf(rowSplit[2]);
        movements.add(new MovementRecord(productId, movementType, quantity));
      }
    });
    this.storeUseCasePort.processMovements(id, movements);
    return ResponseEntity.ok(id);
  }
}
