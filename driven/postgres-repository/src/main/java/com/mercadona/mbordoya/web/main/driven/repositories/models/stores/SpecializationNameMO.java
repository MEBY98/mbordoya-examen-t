package com.mercadona.mbordoya.web.main.driven.repositories.models.stores;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "specialization_name")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(SpecializationNameMOPK.class)
public class SpecializationNameMO {

  @Id
  @Column(name = "id")
  private Long id;

  @Id
  @Column(name = "localeLanguageCode")
  private String localeLanguageCode;

  @Column(name = "name")
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id", referencedColumnName = "id", updatable = false, insertable = false, foreignKey =
  @ForeignKey(name = "specialization_name_specialization_fk"))
  private SpecializationMO specializationMO;
}
