package com.mercadona.mbordoya.web.main.driven.repositories.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "example_child_table",
    uniqueConstraints = {
        @UniqueConstraint(name = "example_child_ukey", columnNames = {"id", "example_id"})
    },
    indexes = {
        @Index(name = "id_example_id_idx", columnList = "id, example_id"),
    })
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExampleChildMO {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "example_child_table_id_seq_generator")
  @SequenceGenerator(name = "example_child_table_id_seq_generator", sequenceName = "example_child_table_id_seq",
      allocationSize = 1)
  private Long id;

  @Column(name = "example_id")
  private Long exampleId;

  @Column(name = "child_description")
  private String childDescription;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "example_id", referencedColumnName = "id", updatable = false, insertable = false, foreignKey =
  @ForeignKey(name = "example_child_example_fk"))
  private ExampleMO exampleMO;
}
