package com.mercadona.mbordoya.web.main.driven.repositories.mappers;

import com.mercadona.mbordoya.web.main.domain.Specialization;
import com.mercadona.mbordoya.web.main.domain.SpecializationName;
import com.mercadona.mbordoya.web.main.driven.repositories.models.stores.SpecializationMO;
import com.mercadona.mbordoya.web.main.driven.repositories.models.stores.SpecializationNameMO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SpecializationDBMapper {

  @Mapping(target = "specializationNameList", source = "specializationNameMOList")
  Specialization toSpecialization(SpecializationMO specializationMO);

  SpecializationName toSpecializationName(SpecializationNameMO specializationNameMO);
}
