package com.mercadona.mbordoya.web.main.driven.repositories.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "example_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExampleMO {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "example_table_id_seq_generator")
  @SequenceGenerator(name = "example_table_id_seq_generator", sequenceName = "example_table_id_seq", allocationSize = 1)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "type_id")
  private Long typeId;

  @OneToMany(mappedBy = "exampleMO", fetch = FetchType.LAZY)
  private List<ExampleChildMO> exampleChildMOs;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "type_id", referencedColumnName = "type_id", updatable = false, insertable = false, foreignKey =
  @ForeignKey(name = "example_example_type_fk"))
  private ExampleTypeMO exampleTypeMO;
}
