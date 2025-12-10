package com.mercadona.mbordoya.web.main.driven.repositories.models.stores;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.util.List;

@Entity
@Table(name = "module")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class ModuleMO {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "module_id_seq_generator")
  @SequenceGenerator(name = "module_id_seq_generator", sequenceName = "module_id_seq", allocationSize = 1)
  private Long id;

  @Column(name = "capacity")
  private Integer capacity;

  @Column(name = "store_id")
  private Long storeId;

  @Column(name = "specialization_id")
  private Long specializationId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id", referencedColumnName = "id", updatable = false, insertable = false, foreignKey =
  @ForeignKey(name = "module_store_fk"))
  private StoreMO storeMO;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "specialization_id", referencedColumnName = "id", updatable = false, insertable = false, foreignKey =
  @ForeignKey(name = "module_specialization_fk"))
  @NotAudited
  private SpecializationMO specializationMO;

  @OneToMany(mappedBy = "moduleMO", fetch = FetchType.LAZY)
  @NotAudited
  private List<ModuleStockMO> moduleStockMOS;
}
