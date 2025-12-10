package com.mercadona.mbordoya.web.main.driven.repositories.mappers;

import com.mercadona.mbordoya.web.main.domain.store.*;
import com.mercadona.mbordoya.web.main.driven.repositories.models.stores.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface StoreDBMapper {

  @Mapping(target = "storeStorageMOS", ignore = true)
  @Mapping(target = "moduleMOS", ignore = true)
  StoreMO toStoreMO(Store storeDomain);

  @Mapping(target = "specializationId", source = "specialization.id")
  @Mapping(target = "storeMO", ignore = true)
  @Mapping(target = "storeId", source = "store.id")
  @Mapping(target = "specializationMO", ignore = true)
  @Mapping(target = "moduleStockMOS", ignore = true)
  ModuleMO toModuleMO(ModuleDomain moduleDomain);

  @Mapping(target = "storeStorageStockMOS", ignore = true)
  @Mapping(target = "storeMO", ignore = true)
  @Mapping(target = "storeId", source = "store.id")
  StoreStorageMO toStoreStorageMO(StoreStorage storeStorage);

  @Mapping(target = "storeStorages", ignore = true)
  @Mapping(target = "modules", source = "moduleMOS", qualifiedByName = "toModuleDomain")
  Store toStore(StoreMO storeMO);

  @Named("toModuleDomain")
  @Mapping(target = "store", ignore = true)
  @Mapping(target = "specialization.id", source = "specializationId")
  @Mapping(target = "moduleStocks", source = "moduleStockMOS")
  ModuleDomain toModuleDomain(ModuleMO moduleMO);

  @Mapping(target = "product", source = "productMO")
  @Mapping(target = "moduleDomain", ignore = true)
  ModuleStock toModuleStock(ModuleStockMO moduleStockMO);

  @Mapping(target = "specialization.id", source = "specializationId")
  Product toProduct(ProductMO productMO);

  @Mapping(target = "storeStorageStocks", ignore = true)
  @Mapping(target = "store.id", source = "storeId")
  StoreStorage toBasicStoreStorage(StoreStorageMO storeStorageMO);

  @Mapping(target = "store.id", source = "storeId")
  @Mapping(target = "specialization.id", source = "specializationId")
  @Mapping(target = "moduleStocks", ignore = true)
  ModuleDomain toBasicModuleDomain(ModuleMO moduleMO);

  @Mapping(target = "storeStorageStocks", source = "storeStorageStockMOS")
  @Mapping(target = "store", ignore = true)
  StoreStorage toStoreStorage(StoreStorageMO storeStorageMO);

  @Mapping(target = "storeStorage", ignore = true)
  @Mapping(target = "product", source = "productMO")
  StoreStorageStock toStoreStorageStock(StoreStorageStockMO storeStorageStockMO);
}
