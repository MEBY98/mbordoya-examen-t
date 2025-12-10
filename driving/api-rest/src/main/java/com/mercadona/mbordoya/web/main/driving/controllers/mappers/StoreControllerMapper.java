package com.mercadona.mbordoya.web.main.driving.controllers.mappers;

import com.mercadona.mbordoya.web.main.domain.store.ModuleDomain;
import com.mercadona.mbordoya.web.main.domain.store.Specialization;
import com.mercadona.mbordoya.web.main.domain.store.SpecializationName;
import com.mercadona.mbordoya.web.main.domain.store.Store;
import com.mercadona.mbordoya.web.main.driving.controllers.http_models.store.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StoreControllerMapper {

  @Mapping(target = "storeStorages", ignore = true)
  @Mapping(target = "id", ignore = true)
  Store toStoreDomain(StoreCreateRequest storeRequest);

  @Mapping(target = "moduleStocks", ignore = true)
  @Mapping(target = "store", ignore = true)
  @Mapping(target = "specialization.id", source = "specializationId")
  @Mapping(target = "id", ignore = true)
  ModuleDomain toModuleDomain(StoreCreateModuleItemRequest moduleItemRequest);

  StoreResponse toStoreResponse(Store store);

  StoreModuleResponse toStoreModuleResponse(ModuleDomain moduleDomain);

  @Mapping(target = "specializationName", source = "localizedName")
  SpecializationResponse toSpecializationResponse(Specialization specialization);

  SpecializationNameResponse toSpecializationNameResponse(SpecializationName specializationName);

  @Mapping(target = "storeStorages", ignore = true)
  @Mapping(target = "modules", ignore = true)
  Store toStoreDomain(Long id, StoreUpdateRequest storeRequest);

  StoreUpdatedResponse toStoreUpdatedResponse(Store store);
}
