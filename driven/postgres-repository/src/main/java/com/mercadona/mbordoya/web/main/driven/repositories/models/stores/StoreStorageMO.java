package com.mercadona.mbordoya.web.main.driven.repositories.models.stores;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "store_storage")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreStorageMO {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "store_storage_id_seq_generator")
  @SequenceGenerator(name = "store_storage_id_seq_generator", sequenceName = "store_storage_id_seq", allocationSize = 1)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "capacity")
  private Integer capacity;

  @Column(name = "store_id")
  private Long storeId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id", referencedColumnName = "id", updatable = false, insertable = false, foreignKey =
  @ForeignKey(name = "store_storage_store_fk"))
  private StoreMO storeMO;

  @OneToMany(mappedBy = "storeStorageMO", fetch = FetchType.LAZY)
  private List<StoreStorageStockMO> storeStorageStockMOS;
}
