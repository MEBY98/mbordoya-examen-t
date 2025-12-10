package com.mercadona.mbordoya.web.main.driven.repositories.models.stores;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductMO {

  @Id
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "selling_price")
  private Double sellingPrice;

  @Column(name = "specialization_id")
  private Long specializationId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "specialization_id", referencedColumnName = "id", updatable = false, insertable = false, foreignKey =
  @ForeignKey(name = "product_specialization_fk"))
  private SpecializationMO specializationMO;

  @OneToMany(mappedBy = "productMO", fetch = FetchType.LAZY)
  private List<ModuleStockMO> moduleStockMOS;

  @OneToMany(mappedBy = "productMO", fetch = FetchType.LAZY)
  private List<StoreStorageStockMO> storeStorageStockMOS;
}
