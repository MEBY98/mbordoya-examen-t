package com.mercadona.mbordoya.web.main.driven.repositories.models.stores;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SpecializationNameMOPK implements Serializable {
  private Long id;
  private String localeLanguageCode;
}
