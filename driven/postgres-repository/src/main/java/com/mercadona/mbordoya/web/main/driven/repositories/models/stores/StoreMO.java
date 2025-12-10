package com.mercadona.mbordoya.web.main.driven.repositories.models.stores;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.util.List;

@Entity
@Table(name = "store")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class StoreMO {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "store_id_seq_generator")
  @SequenceGenerator(name = "store_id_seq_generator", sequenceName = "store_id_seq", allocationSize = 1)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "address")
  private String address;

  @OneToMany(mappedBy = "storeMO", fetch = FetchType.LAZY)
  private List<ModuleMO> moduleMOS;

  @OneToMany(mappedBy = "storeMO", fetch = FetchType.LAZY)
  private List<StoreStorageMO> storeStorageMOS;
}
