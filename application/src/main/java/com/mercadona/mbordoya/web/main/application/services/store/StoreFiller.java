package com.mercadona.mbordoya.web.main.application.services.store;

import com.mercadona.mbordoya.web.main.domain.ModuleDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreFiller {

  private final SpecializationService specializationService;

  public void fillModulesSpecialization(final List<ModuleDomain> modules) {
    final var specializationMap = this.specializationService.getMap();
    modules.forEach(moduleDomain -> {
      if (moduleDomain.hasSpecialization()) {
        final var specialization = specializationMap.get(moduleDomain.getSpecialization().getId());
        moduleDomain.setSpecialization(specialization);
      }
    });
  }
}
