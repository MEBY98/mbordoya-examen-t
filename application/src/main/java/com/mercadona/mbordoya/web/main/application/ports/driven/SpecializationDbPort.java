package com.mercadona.mbordoya.web.main.application.ports.driven;

import com.mercadona.mbordoya.web.main.domain.store.Specialization;

import java.util.List;

public interface SpecializationDbPort {

  List<Specialization> getAll();
}
