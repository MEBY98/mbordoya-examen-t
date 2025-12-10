package com.mercadona.mbordoya.web.main.driven.repositories.models.stores;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "store_storage_stock")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(StoreStorageStockMOPK.class)
public class StoreStorageStockMO {
  @Id
  @Column(name = "product_id")
  private Long productId;

  @Id
  @Column(name = "store_storage_id")
  private Long storeStorageId;

  @Column(name = "quantity")
  private Integer quantity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", referencedColumnName = "id", updatable = false, insertable = false, foreignKey =
  @ForeignKey(name = "store_storage_stock_product_fk"))
  private ProductMO productMO;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_storage_id", referencedColumnName = "id", updatable = false, insertable = false, foreignKey =
  @ForeignKey(name = "store_storage_stock_store_storage_fk"))
  private StoreStorageMO storeStorageMO;
}
