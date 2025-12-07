package com.mercadona.mbordoya.web.main.driven.repositories.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "example_type_name_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ExampleTypeNameMOPK.class)
public class ExampleTypeNameMO {

  @Id
  @Column(name = "type_id")
  private Long typeId;

  @Id
  @Column(name = "locale_language_code")
  private String localeLanguageCode;

  @Column(name = "description")
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "type_id", referencedColumnName = "type_id", updatable = false, insertable = false, foreignKey =
  @ForeignKey(name = "example_type_name_example_type_fk"))
  private ExampleTypeMO exampleTypeMO;
}
