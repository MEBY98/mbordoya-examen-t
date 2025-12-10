package com.mercadona.mbordoya.web.main.application.ports.driven;

import com.mercadona.mbordoya.web.main.domain.Specialization;

import java.util.List;

public interface SpecializationDbPort {

  List<Specialization> getAll();
}
