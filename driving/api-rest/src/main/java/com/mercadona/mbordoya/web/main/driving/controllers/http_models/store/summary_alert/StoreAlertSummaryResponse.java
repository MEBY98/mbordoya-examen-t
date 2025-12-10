package com.mercadona.mbordoya.web.main.driving.controllers.http_models.store.summary_alert;

import com.mercadona.mbordoya.web.main.domain.store.Product;
import com.mercadona.mbordoya.web.main.domain.store.Store;
import lombok.*;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class StoreAlertSummaryResponse {

  private Long id;
  private String name;
  private String address;

  private List<StoreSummaryAlertModuleResponse> modules;
  private List<StoreSummaryAlertStoreStorageResponse> storeStorages;

  public StoreAlertSummaryResponse(final Store store) {
    this.id = store.getId();
    this.name = store.getName();
    this.address = store.getAddress();
    this.modules = buildModules(store);
    this.storeStorages = buildStoreStorages(store);
  }

  private static List<StoreSummaryAlertStoreStorageResponse> buildStoreStorages(final Store store) {
    final List<StoreSummaryAlertStoreStorageResponse> result = new LinkedList<>();
    store.getStoreStorages().forEach(storeStorage -> {
      final List<Product> productsUnexposed = new LinkedList<>();
      storeStorage.getStoreStorageStocks().forEach(storeStorageStock -> {
        if (storeStorageStock.getQuantity() > 0 && store.isProductUnexposed(storeStorageStock.getProduct())) {
          productsUnexposed.add(storeStorageStock.getProduct());
        }
      });
      if (!productsUnexposed.isEmpty()) {
        result.add(new StoreSummaryAlertStoreStorageResponse(storeStorage, productsUnexposed));
      }
    });
    return result;
  }

  private static List<StoreSummaryAlertModuleResponse> buildModules(final Store store) {
    final var modulesWithProductWithZeroStock = store.getModulesWithProductsWithZeroStock();
    return modulesWithProductWithZeroStock.stream().map(StoreSummaryAlertModuleResponse::new).toList();
  }
}
