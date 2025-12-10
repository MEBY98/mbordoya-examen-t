package com.mercadona.mbordoya.web.main.driven.repositories.adapters;

import com.mercadona.mbordoya.web.main.application.ports.driven.StoreDbPort;
import com.mercadona.mbordoya.web.main.domain.ModuleDomain;
import com.mercadona.mbordoya.web.main.domain.Store;
import com.mercadona.mbordoya.web.main.domain.StoreQuery;
import com.mercadona.mbordoya.web.main.domain.StoreStorage;
import com.mercadona.mbordoya.web.main.driven.repositories.jpa_repositories.*;
import com.mercadona.mbordoya.web.main.driven.repositories.mappers.StoreDBMapper;
import com.mercadona.mbordoya.web.main.driven.repositories.specifications.StoreSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
@RequiredArgsConstructor
public class StoreDbAdapter implements StoreDbPort {

  private final StoreJpaRepository storeJpaRepository;
  private final ModuleJpaRepository moduleJpaRepository;
  private final StoreStorageJpaRepository storeStorageJpaRepository;
  private final StoreStorageStockJpaRepository storeStorageStockJpaRepository;
  private final ModuleStockJpaRepository moduleStockJpaRepository;
  private final StoreDBMapper storeDBMapper;

  @Override
  @Transactional
  public Long insert(final Store storeDomain) {
    return this.storeJpaRepository.save(this.storeDBMapper.toStoreMO(storeDomain)).getId();
  }

  @Override
  @Transactional
  public void insertModule(final ModuleDomain moduleDomain) {
    this.moduleJpaRepository.save(this.storeDBMapper.toModuleMO(moduleDomain));
  }

  @Override
  @Transactional
  public void insertStoreStorage(final StoreStorage storeStorage) {
    this.storeStorageJpaRepository.save(this.storeDBMapper.toStoreStorageMO(storeStorage));

  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Store> findById(final Long id) {
    return this.storeJpaRepository.findById(id).map(this.storeDBMapper::toStore);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Store> getAll(final StoreQuery query) {
    final var specifications = new StoreSpecification(query.getName());
    final Pageable pageRequest = PageRequest.of(query.getPage(), query.getPageSize());
    final var page = this.storeJpaRepository.findAll(specifications, pageRequest);
    return page.map(this.storeDBMapper::toStore);
  }

  @Override
  @Transactional
  public void updateStore(final Store storeDB) {
    this.storeJpaRepository.updateStore(storeDB.getId(), storeDB.getName(), storeDB.getAddress());
  }

  @Override
  @Transactional(readOnly = true)
  public boolean existsById(final Long storeId) {
    return this.storeJpaRepository.existsById(storeId);
  }

  @Override
  @Transactional(readOnly = true)
  public List<StoreStorage> getStoreStoragesByStoreId(final Long storeId) {
    return this.storeStorageJpaRepository.findAllByStoreId(storeId).stream().map(this.storeDBMapper::toBasicStoreStorage).toList();
  }

  @Override
  @Transactional
  public void deleteStoreStorageStock(final Long storeStorageId) {
    this.storeStorageStockJpaRepository.deleteAllByStoreStorageId(storeStorageId);
    this.storeStorageStockJpaRepository.flush();
  }

  @Override
  @Transactional
  public void deleteStoreStorageById(final Long storeStorageId) {
    this.storeStorageJpaRepository.deleteById(storeStorageId);
    this.storeStorageJpaRepository.flush();
  }

  @Override
  @Transactional(readOnly = true)
  public List<ModuleDomain> getModulesByStoreId(final Long storeId) {
    return this.moduleJpaRepository.findAllByStoreId(storeId).stream().map(this.storeDBMapper::toBasicModuleDomain).toList();
  }

  @Override
  @Transactional
  public void deleteModuleStock(final Long moduleId) {
    this.moduleStockJpaRepository.deleteAllByModuleId(moduleId);
    this.moduleStockJpaRepository.flush();
  }

  @Override
  @Transactional
  public void deleteModuleById(final Long moduleId) {
    this.moduleJpaRepository.deleteById(moduleId);
    this.moduleJpaRepository.flush();
  }

  @Override
  @Transactional
  public void deleteById(final Long storeId) {
    this.storeJpaRepository.deleteById(storeId);
    this.storeJpaRepository.flush();
  }

  @Override
  @Transactional(readOnly = true)
  public List<ModuleDomain> getModulesWithStockByStoreId(final Long storeId) {
    return this.moduleJpaRepository.findAllByStoreId(storeId).stream().map(this.storeDBMapper::toModuleDomain).toList();
  }

  @Override
  @Transactional(readOnly = true)
  public List<StoreStorage> getStoreStorageWithStockByStoreId(final Long storeId) {
    return this.storeStorageJpaRepository.findAllByStoreId(storeId).stream().map(this.storeDBMapper::toStoreStorage).toList();
  }
}
