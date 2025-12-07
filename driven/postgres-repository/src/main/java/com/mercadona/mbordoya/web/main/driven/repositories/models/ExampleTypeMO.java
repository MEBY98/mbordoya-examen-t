package com.mercadona.mbordoya.web.main.driven.repositories.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "example_type_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExampleTypeMO {

  @Id
  @Column(name = "type_id")
  private Long typeId;

  @OneToMany(mappedBy = "exampleTypeMO", fetch = FetchType.LAZY)
  private List<ExampleTypeNameMO> exampleTypeNameMOs;

}
