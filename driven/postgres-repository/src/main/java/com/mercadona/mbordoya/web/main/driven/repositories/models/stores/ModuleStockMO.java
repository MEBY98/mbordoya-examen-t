package com.mercadona.mbordoya.web.main.driven.repositories.models.stores;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "module_stock")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ModuleStockMOPK.class)
public class ModuleStockMO {
  @Id
  @Column(name = "product_id")
  private Long productId;

  @Id
  @Column(name = "module_id")
  private Long moduleId;

  @Column
  private Integer quantity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", referencedColumnName = "id", updatable = false, insertable = false, foreignKey =
  @ForeignKey(name = "module_stock_product_fk"))
  private ProductMO productMO;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "module_id", referencedColumnName = "id", updatable = false, insertable = false, foreignKey =
  @ForeignKey(name = "module_stock_module_fk"))
  private ModuleMO moduleMO;
}
