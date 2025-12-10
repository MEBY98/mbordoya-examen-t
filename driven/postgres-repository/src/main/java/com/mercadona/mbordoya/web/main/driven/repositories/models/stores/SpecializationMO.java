package com.mercadona.mbordoya.web.main.driven.repositories.models.stores;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Table(name = "specialization")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecializationMO {
  @Id
  @Column(name = "id")
  private Long id;

  @OneToMany(mappedBy = "specializationMO", fetch = FetchType.LAZY)
  private List<SpecializationNameMO> specializationNameMOList;
}
