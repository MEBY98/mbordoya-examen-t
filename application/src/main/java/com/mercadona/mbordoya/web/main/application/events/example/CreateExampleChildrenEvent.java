package com.mercadona.mbordoya.web.main.application.events.example;

import com.mercadona.mbordoya.web.main.domain.example.ExampleChildDomain;

import java.util.List;

public record CreateExampleChildrenEvent(Long exampleDomainId, List<ExampleChildDomain> children) {}
