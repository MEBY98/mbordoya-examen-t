package com.mercadona.mbordoya.web.main.driven.repositories.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.mercadona.mbordoya.web.main.driven.repositories.models")
@ComponentScan("com.mercadona.mbordoya.web.main")
@EnableJpaRepositories(basePackages = {"com.mercadona.mbordoya.web.main.driven.repositories"})
public class JpaRepositoryConfig {}
