package com.mercadona.mbordoya.web.main.driving.controllers.api;

import com.mercadona.mbordoya.web.main.driving.controllers.http_models.store.*;
import com.mercadona.mbordoya.web.main.driving.controllers.http_models.store.summary.StoreSummaryResponse;
import com.mercadona.mbordoya.web.main.driving.controllers.http_models.store.summary_alert.StoreAlertSummaryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/stores")
@Tag(name = "Store API", description = "API for Store operations")
public interface StoreApi {

  @Operation(summary = "Create store", security = @SecurityRequirement(name = "adfs"))
  @PostMapping
  ResponseEntity<Long> createStore(@RequestBody StoreCreateRequest storeRequest);

  @Operation(summary = "Get store by Id", security = @SecurityRequirement(name = "adfs"))
  @GetMapping("/{id}")
  ResponseEntity<StoreResponse> getStoreById(@PathVariable Long id);

  @Operation(summary = "Get stores", security = @SecurityRequirement(name = "adfs"))
  @GetMapping
  ResponseEntity<StorePaginatedResponse> getStores(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                                   @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,
                                                   @RequestParam(name = "sort", required = false, defaultValue = "-id") String sort,
                                                   @RequestParam(name = "name", required = false) String name);

  @Operation(summary = "Update Store", security = @SecurityRequirement(name = "adfs"))
  @PutMapping("/{id}")
  ResponseEntity<StoreUpdatedResponse> updateStore(@PathVariable(name = "id") Long id, @RequestBody StoreUpdateRequest storeRequest);

  @Operation(summary = "Delete Store", security = @SecurityRequirement(name = "adfs"))
  @DeleteMapping("/{id}")
  ResponseEntity<Long> deleteStore(@PathVariable(name = "id") Long id);

  @Operation(summary = "Summary Store", security = @SecurityRequirement(name = "adfs"))
  @GetMapping("/{id}/summary")
  ResponseEntity<StoreSummaryResponse> getSummary(@PathVariable Long id);

  @Operation(summary = "Summary Alert Store", security = @SecurityRequirement(name = "adfs"))
  @GetMapping("/{id}/summary-alerts")
  ResponseEntity<StoreAlertSummaryResponse> getSummaryAlerts(@PathVariable Long id);
}
