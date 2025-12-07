package com.mercadona.mbordoya.web.main.driven.repositories.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExampleTypeNameMOPK implements Serializable {
  private Long typeId;
  private String localeLanguageCode;
}
